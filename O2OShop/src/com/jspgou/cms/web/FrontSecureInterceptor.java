package com.jspgou.cms.web;

import static com.jspgou.core.web.Constants.REQUEST_LOGIN_URL_KEY;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jspgou.common.security.annotation.Secured;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
 * 前台安全拦截器
 * 
 * @author liufang
* This class should preserve.
* @preserve
*/
public class FrontSecureInterceptor extends HandlerInterceptorAdapter implements
		InitializingBean {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute(REQUEST_LOGIN_URL_KEY, loginUrl);
		Secured secured = handler.getClass().getAnnotation(Secured.class);
		if (secured != null) {
			if (MemberThread.get() == null) {
				loginSvc.clearCookie(request, response);
				response.sendRedirect(FrontHelper.getLoginUrl(loginUrl, request
						.getContextPath(), request.getRequestURL().toString()));
				return false;
			}
		}
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(loginUrl);
	}

	private String loginUrl;
	private LoginSvc loginSvc;

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Autowired
	public void setLoginSvc(LoginSvc loginSvc) {
		this.loginSvc = loginSvc;
	}

}
