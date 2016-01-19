package com.jspgou.core.web;

import static com.jspgou.cms.Constants.REQUEST_SHOP_CONFIG_KEY;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ShopAdminMng;
import com.jspgou.cms.manager.ShopConfigMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Admin;
import com.jspgou.core.entity.Global;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AdminMng;
import com.jspgou.core.manager.GlobalMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.manager.WebsiteMng;


/**
 * CMS上下文信息拦截器
 * 
 * 包括登录信息、权限信息、站点信息
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {
	public static final String SITE_COOKIE = "_site_id_cookie";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		Global config=configMng.findById(1l);
		Website site = null;
		List<Website> list = cmsSiteMng.getListFromCache();
		int size = list.size();
		if (size == 0) {
			throw new RuntimeException("no site record in database!");
		} else if (size == 1) {
			site = list.get(0);
		} else {
			String server = request.getServerName();
			String alias, redirect;
				for (Website s : list) {
					// 检查域名
					if (s.getDomain().equals(server)) {
						site = s;
						break;
					}
					// 检查域名别名
					alias = s.getDomainAlias();
					if (!StringUtils.isBlank(alias)) {
						for (String a : StringUtils.split(alias, ',')) {
							if (a.equals(server)) {
								site = s;
								break;
							}
						}
					}
					// 检查重定向
/*					redirect = s.getDomainRedirect();
					if (!StringUtils.isBlank(redirect)) {
						for (String r : StringUtils.split(redirect, ',')) {
							if (r.equals(server)) {
								try {
									response.sendRedirect(s.getUrl());
								} catch (IOException e) {
									throw new RuntimeException(e);
								}
								return false;
							}
						}
					}*/
				}
		}
		
		SiteUtils.setSite(request, site);
		ShopConfig shopConfig = shopConfigMng.findById(site.getId());
		if (config == null) {
			throw new IllegalStateException(
					"no ShopConfig found in Website id=" + site.getId());
		}
		SiteUtils.setConfig(request, shopConfig);
		CmsThreadVariable.setSite(site);
		Subject subject = SecurityUtils.getSubject();
		Admin admin = null;
		ShopAdmin shopAdmin = null;
		if (subject.isAuthenticated()|| subject.isRemembered()) {
			String username =  (String) subject.getPrincipal();
			User user = cmsUserMng.getByUsername(username);
			SiteUtils.setUser(request, user);
			CmsThreadVariable.setUser(user);
			createJsessionId(request, response, site);
			admin=adminMng.getByUserId(user.getId(), site.getId());
			if(admin==null||admin.getId()==null){
				admin=adminMng.getByUserId(user.getId());
			}
			if(admin!=null && ((admin.isSuper()) || (admin.getWebsite().getId()==site.getId()))){
			shopAdmin=shopAdminMng.findById(admin.getId());
			AdminThread.set(shopAdmin);
			}
			ShopMember member = shopMemberMng.getByUsername(username);
			if (member != null &&member.getUsername()!=null) {
				MemberThread.set(member);
				GroupThread.set(member.getGroup());
			}else{
				CmsThreadVariable.removeUser();
				SiteUtils.setUser(request, null);
			}
			
		}else{
			AdminThread.remove();
			GroupThread.remove();
			MemberThread.remove();
			CmsThreadVariable.removeUser();
			SiteUtils.setUser(request, null);
		}
		/***
		 * 用户和会员混淆
		 */
		/*else if(CmsThreadVariable.getUser()!=null && CmsThreadVariable.getUser().getId()!=null &&MemberThread.get()!=null&&MemberThread.get().getId()!=null){
			User user= CmsThreadVariable.getUser();
			SiteUtils.setUser(request, CmsThreadVariable.getUser());
			admin=adminMng.getByUserId(user.getId(), site.getId());
			if(admin==null||admin.getId()==null){
				admin=adminMng.getByUserId(user.getId());
			}
			if(admin!=null && ((admin.isSuper()) || (admin.getWebsite().getId()==site.getId()))){
			shopAdmin=shopAdminMng.findById(admin.getId());
			AdminThread.set(shopAdmin);
			}
			ShopMember member = shopMemberMng.getByUsername(user.getUsername());
			if (member != null &&member.getUsername()!=null) {
				MemberThread.set(member);
				GroupThread.set(member.getGroup());
			}else{
				CmsThreadVariable.removeUser();
				SiteUtils.setUser(request, null);
			}
//			createJsessionId(request, response, site);
		}*/
		/*
		CmsUser user = null;
		Integer userId = authMng.retrieveUserIdFromSession(session, request);
		if (userId != null) {
			user = cmsUserMng.findById(userId);
		}
		if (user != null) {
			CmsUtils.setUser(request, user);
		}
		*/
		
		return true;
	}
	
	private Website getByCookie(HttpServletRequest request) {
		Cookie cookie = CookieUtils.getCookie(request, SITE_COOKIE);
		if (cookie != null) {
			String v = cookie.getValue();
			if (!StringUtils.isBlank(v)) {
				try {
					Integer siteId = Integer.parseInt(v);
					return cmsSiteMng.findById(siteId.longValue());
				} catch (NumberFormatException e) {
				}
			}
		}
		return null;
	}
	
	private void createJsessionId(HttpServletRequest request,HttpServletResponse response,Website site){
		 String JSESSIONID = request.getSession().getId();//获取当前JSESSIONID （不管是从主域还是二级域访问产生）
		 Cookie cookie = new Cookie("JSESSIONID", JSESSIONID);
		 cookie.setDomain(site.getBaseDomain()); //关键在这里，将cookie设成主域名访问，确保不同域之间都能获取到该cookie的值，从而确保session统一
		 response.addCookie(cookie);  //将cookie返回到客户端
	}

	private WebsiteMng cmsSiteMng;
	private UserMng cmsUserMng;
	@Autowired
	private GlobalMng configMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private ShopAdminMng shopAdminMng;
	@Autowired
	private ShopConfigMng shopConfigMng;
	private AdminMng adminMng;
	@Autowired
	public void setAdminMng(AdminMng adminMng) {
		this.adminMng = adminMng;
	}
	@Autowired
	public void setWebsiteMng(WebsiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}

	@Autowired
	public void setCmsUserMng(UserMng cmsUserMng) {
		this.cmsUserMng = cmsUserMng;
	}
}