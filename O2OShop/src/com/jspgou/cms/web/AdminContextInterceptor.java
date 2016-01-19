package com.jspgou.cms.web;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import static com.jspgou.cms.web.Constants.MESSAGE;

import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ShopAdminMng;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Admin;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AdminMng;
import com.jspgou.core.manager.GlobalMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.security.CmsAuthorizingRealm;
import com.jspgou.core.web.CmsThreadVariable;
import com.jspgou.core.web.SiteUtils;


/**
 * CMS上下文信息拦截器
 * 
 * 包括登录信息、权限信息、站点信息
 */
public class AdminContextInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(AdminContextInterceptor.class);
	
	public static final String SITE_PARAM = "_site_id_param";
	public static final String SITE_COOKIE = "_site_id_cookie";
	public static final String SITE_PATH_PARAM = "path";
	public static final String PERMISSION_MODEL = "_permission_key";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 获得站点
		Website oldSite=getByCookie(request);
		Website site = getSite(request, response);
		SiteUtils.setSite(request, site);
		// Site加入线程变量
		CmsThreadVariable.setSite(site);
		// 获得用户
		User user = null;
		Admin admin = null;
		String usernames=null;
		ShopAdmin shopAdmin = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()||subject.isRemembered()) {
			usernames =  (String) subject.getPrincipal();
			if(usernames!=null){
			user = cmsUserMng.getByUsername(usernames);
			admin=adminMng.getByUserId(user.getId(), site.getId());
			if(admin==null || admin.getId()==null){
				admin=adminMng.getByUserId(user.getId());
			}
			if(admin==null||admin.getId()==null){
				admin=adminMng.getByUserId(user.getId());
			}
			if (admin==null||admin.getId()==null) {
				AdminThread.remove();
				GroupThread.remove();
				MemberThread.remove();
				SiteUtils.setUser(request, null);
				SiteUtils.setSite(request, null);
				SecurityUtils.getSecurityManager().logout(subject);
				CmsThreadVariable.removeUser();
				CmsThreadVariable.removeSite();
				loginSvc.logout(request, response);
				request.setAttribute(MESSAGE, MessageResolver.getMessage(request,
						"login.notAdmin"));
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
			if(admin!=null && (admin.isSuper()) || (admin.getWebsite().getId()==site.getId())){
			shopAdmin=shopAdminMng.findById(admin.getId());
			AdminThread.set(shopAdmin);
			}
			}
		}
		
		// 此时用户可以为null
		SiteUtils.setUser(request, user);
		// User加入线程变量
		CmsThreadVariable.setUser(user);

		String uri = getURI(request);
		if (exclude(uri)) {
			return true;
		}
		if (admin==null||admin.getId()==null) {
			AdminThread.remove();
			GroupThread.remove();
			MemberThread.remove();
			SiteUtils.setUser(request, null);
			SiteUtils.setSite(request, null);
			CmsThreadVariable.removeUser();
			SecurityUtils.getSecurityManager().logout(subject);
			CmsThreadVariable.removeSite();
			loginSvc.logout(request, response);
			request.setAttribute(MESSAGE, MessageResolver.getMessage(request,
					"login.notAdmin"));
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		//切换站点移除shiro缓存
		if(oldSite!=null&&!oldSite.equals(site)&&user!=null){
			authorizingRealm.removeUserAuthorizationInfoCache(user.getUsername().toString());
		}
		createJsessionId(request, response, site);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		User user = SiteUtils.getUser(request);
		Website site=SiteUtils.getWeb(request);
		Admin admin = null;
		if(user!=null){
		admin=adminMng.getByUserId(user.getId(), site.getId());
		if(admin==null || admin.getId()==null){
			admin=adminMng.getByUserId(user.getId());
		}
		}
		// 不控制权限时perm为null，PermistionDirective标签将以此作为依据不处理权限问题。
		 if (auth && user != null &&admin != null && !admin.isSuper()  && mav != null
				&& mav.getModelMap() != null && mav.getViewName() != null
				&& !mav.getViewName().startsWith("redirect:")) {
			mav.getModelMap().addAttribute(PERMISSION_MODEL, getUserPermission(site, admin));
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Sevlet容器有可能使用线程池，所以必须手动清空线程变量。
	}

	/**
	 * 按参数、cookie、域名、默认。
	 * 
	 * @param request
	 * @return 不会返回null，如果站点不存在，则抛出异常。
	 */
	private Website getSite(HttpServletRequest request,
			HttpServletResponse response) {
		Website site = getByParams(request,response);
//		if (site == null) {
//			site = getByPath(request,response);
//		}
//		if (site == null) {
//			site = getByCookie(request);
//		}
		if (site == null) {
			site = getByDomain(request);
		}
		if (site == null) {
			site = getByDefault();
		}
		if (site == null) {
			throw new RuntimeException("cannot get site!");
		} else {
			return site;
		}
	}
	

	private Website getByParams(HttpServletRequest request,
			HttpServletResponse response) {
		String p = request.getParameter(SITE_PARAM);
		if (!StringUtils.isBlank(p)) {
			try {
				Integer siteId = Integer.parseInt(p);
				Website site = cmsSiteMng.findById(siteId.longValue());
				if (site != null) {
					// 若使用参数选择站点，则应该把站点保存至cookie中才好。
					CookieUtils.addCookie(request, response, SITE_COOKIE, site
							.getId().toString(), null, null);
					return site;
				}
			} catch (NumberFormatException e) {
				log.warn("param site id format exception", e);
			}
		}
		return null;
	}

	private Website getByCookie(HttpServletRequest request) {
		Cookie cookie = CookieUtils.getCookie(request, SITE_COOKIE);
		if (cookie != null) {
			String v = cookie.getValue();
			if (!StringUtils.isBlank(v)) {
				try {
					Integer siteId = Integer.parseInt(v);
					return  cmsSiteMng.findById(siteId.longValue());
				} catch (NumberFormatException e) {
					log.warn("cookie site id format exception", e);
				}
			}
		}
		return null;
	}
	
	private Website getByDomain(HttpServletRequest request) {
		String domain = request.getServerName();
		if (!StringUtils.isBlank(domain)) {
			return cmsSiteMng.findByDomain(domain);
		}
		return null;
	}

	private Website getByDefault() {
		List<Website> list = cmsSiteMng.getListFromCache();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	private boolean exclude(String uri) {
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void createJsessionId(HttpServletRequest request,HttpServletResponse response,Website site){
		 String JSESSIONID = request.getSession().getId();//获取当前JSESSIONID （不管是从主域还是二级域访问产生）
		 Cookie cookie = new Cookie("JSESSIONID", JSESSIONID);
		 cookie.setDomain(site.getBaseDomain()); //关键在这里，将cookie设成主域名访问，确保不同域之间都能获取到该cookie的值，从而确保session统一
		 String ctx = request.getContextPath();
		 cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
		 response.addCookie(cookie);  //将cookie返回到客户端
	}
	
	/**
	 * 获得第三个路径分隔符的位置
	 * 
	 * @param request
	 * @throws IllegalStateException
	 *             访问路径错误，没有三(四)个'/'
	 */
	private static String getURI(HttpServletRequest request)
			throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);
		int start = 0, i = 0, count = 2;
		if (!StringUtils.isBlank(ctxPath)) {
			count++;
		}
		while (i < count && start != -1) {
			start = uri.indexOf('/', start + 1);
			i++;
		}
		
		if (start <= 0) {
			throw new IllegalStateException(
					"admin access path not like '/nhaocang/admin/...' pattern: "
							+ uri);
		}
		return uri.substring(start);
	}
	
	
	private Set<String>getUserPermission(Website site,Admin user){
		Set<String>viewPermissionSet=new HashSet<String>();
		Set<String> perms = user.getRolesPerms();
		Set<String> userPermission=new HashSet<String>();
		if(perms!=null){
			for(String perm:perms){
//				perm="/"+perm;
				if(perm.contains(":")){
					perm=perm.replace(":", "/").replace("*", "");
				}
				userPermission.add(perm);
			}
		}
		return userPermission;
	}
//	private Set<String>getUserPermission(Website site,User user){
//		Set<String>viewPermissionSet=new HashSet<String>();
//		Set<String> perms = user.getPerms(site.getId(),viewPermissionSet);
//		Set<String> userPermission=new HashSet<String>();
//		if(perms!=null){
//			for(String perm:perms){
//				perm="/"+perm;
//				if(perm.contains(":")){
//					perm=perm.replace(":", "/").replace("*", "");
//				}
//				userPermission.add(perm);
//			}
//		}
//		return userPermission;
//	}
	private WebsiteMng cmsSiteMng;
	private AdminMng adminMng;
	private UserMng cmsUserMng;
	private boolean auth = true;
	private String[] excludeUrls;
	
	@Autowired
	private CmsAuthorizingRealm authorizingRealm;
	@Autowired
	private GlobalMng configMng;
	@Autowired
	private ShopAdminMng shopAdminMng;
	@Autowired
	private LoginSvc loginSvc;
	@Autowired
	public void setWebsiteMng(WebsiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}
	@Autowired
	public void setAdminMng(AdminMng adminMng) {
		this.adminMng = adminMng;
	}

	@Autowired
	public void setUserMng(UserMng cmsUserMng) {
		this.cmsUserMng = cmsUserMng;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
}