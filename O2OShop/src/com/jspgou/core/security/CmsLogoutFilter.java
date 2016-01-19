package com.jspgou.core.security;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.core.web.CmsThreadVariable;
import com.jspgou.core.web.SiteUtils;

/**
 * CmsUserFilter
 */
public class CmsLogoutFilter extends LogoutFilter {
	/**
	 * 返回URL
	 */
	public static final String RETURN_URL = "returnUrl";

	@Override
	protected boolean preHandle(ServletRequest arg0, ServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		  HttpServletResponse respose = (HttpServletResponse) arg1;
		AdminThread.remove();
		GroupThread.remove();
		MemberThread.remove();
		CmsThreadVariable.removeUser();
		SiteUtils.setUser(request, null);
		
		loginSvc.logout(request, respose);
		return super.preHandle(arg0, arg1);
	}

	protected String getRedirectUrl(ServletRequest req, ServletResponse resp,Subject subject) {
		HttpServletRequest request = (HttpServletRequest) req;
		  HttpServletResponse respose = (HttpServletResponse) resp;
		String redirectUrl = request.getParameter(RETURN_URL);
		
		if (StringUtils.isBlank(redirectUrl)) {
			if (request.getRequestURI().startsWith(request.getContextPath() + getAdminPrefix())) {
				
//				subject.logout();
				redirectUrl = getAdminLogin();
			} else {
				redirectUrl = getRedirectUrl();
			}
		}
		if(!StringUtils.isBlank(redirectUrl)){
			if(redirectUrl.indexOf("?")>-1&&request.getSession().getAttribute("imei")!=null){
				redirectUrl=redirectUrl+"&imei="+request.getSession().getAttribute("imei");
			}else if(request.getSession().getAttribute("imei")!=null){
				redirectUrl=redirectUrl+"?imei="+request.getSession().getAttribute("imei");
				
			}
		}
		return redirectUrl;
	}

	@Autowired
	private LoginSvc loginSvc;
	private String adminPrefix;
	private String adminLogin;

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

	
}
