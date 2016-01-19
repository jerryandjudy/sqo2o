package com.jspgou.core.security;


import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.core.web.CmsThreadVariable;
import com.jspgou.core.web.SiteUtils;

/**
 * CmsUserFilter
 */
public class CmsUserFilter extends UserFilter {
	//未登录重定向到登陆页
	protected void redirectToLogin(ServletRequest req, ServletResponse resp)
			throws IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String loginUrl;
		//后台地址跳转到后台登录地址，前台需要登录的跳转到shiro配置的登录地址
		if (request.getRequestURI().startsWith(request.getContextPath() + getAdminPrefix())) {
			AdminThread.remove();
			GroupThread.remove();
			MemberThread.remove();
			SiteUtils.setUser(request, null);
			SiteUtils.setSite(request, null);
			CmsThreadVariable.removeUser();
			CmsThreadVariable.removeSite();
//			loginSvc.logout(request, response);
			loginUrl = getAdminLogin();
		}else if(request.getRequestURI().startsWith(request.getContextPath() + getBbsAdminPrefix())){
			loginUrl = getBbsAdminLogin();
		}else {
			loginUrl = getLoginUrl();
		}
		WebUtils.issueRedirect(request, response, loginUrl);
	}
	
	private String adminPrefix;
	private String adminLogin;
	private String bbsAdminPrefix;
	private String bbsAdminLogin;
	
	public String getAdminPrefix() {
		return adminPrefix;
	}

	public void setAdminPrefix(String adminPrefix) {
		this.adminPrefix = adminPrefix;
	}

	public String getAdminLogin() {
		return adminLogin;
	}

	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}

	public String getBbsAdminPrefix() {
		return bbsAdminPrefix;
	}

	public void setBbsAdminPrefix(String bbsAdminPrefix) {
		this.bbsAdminPrefix = bbsAdminPrefix;
	}

	public String getBbsAdminLogin() {
		return bbsAdminLogin;
	}

	public void setBbsAdminLogin(String bbsAdminLogin) {
		this.bbsAdminLogin = bbsAdminLogin;
	}
	

}
