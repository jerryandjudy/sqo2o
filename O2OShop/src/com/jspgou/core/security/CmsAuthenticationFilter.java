package com.jspgou.core.security;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.common.security.CaptchaErrorException;
import com.jspgou.common.security.CaptchaRequiredException;
import com.jspgou.common.security.DisabledException;
import com.jspgou.common.security.UserNameOrPwException;
import com.jspgou.common.security.rememberme.RememberMeService;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.session.SessionProvider;
import com.jspgou.core.entity.Admin;
import com.jspgou.core.entity.Config.ConfigLogin;
import com.jspgou.core.entity.User;
import com.jspgou.core.manager.AdminMng;
import com.jspgou.core.manager.ConfigMng;
import com.jspgou.core.manager.GlobalMng;
import com.jspgou.core.manager.UserMng;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * CmsAuthenticationFilter自定义登录认证filter
 */
public class CmsAuthenticationFilter extends FormAuthenticationFilter {
	
	private Logger logger = LoggerFactory.getLogger(CmsAuthenticationFilter.class);
	
	public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
	/**
	 * 验证码名称
	 */
	public static final String CAPTCHA_PARAM = "captcha";
	/**
	 * 返回URL
	 */
	public static final String RETURN_URL = "returnUrl";

	protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			String msg = "create AuthenticationToken error";
			throw new IllegalStateException(msg);
		}
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String username = (String) token.getPrincipal();
		boolean adminLogin=false;
		if (req.getRequestURI().startsWith(req.getContextPath() + getAdminPrefix())){
			adminLogin=true;
		}
//		boolean bbsAdminLogin=false;
//		if (req.getRequestURI().startsWith(req.getContextPath() + getBbsAdminPrefix())){
//			bbsAdminLogin=true;
//		}
		//验证用户存在不存在
		if(userMng.getByUsername(username)==null ){
			return onLoginFailure(token,adminLogin,new UserNameOrPwException("用户名不存在"), request, response);
		}
		//验证码校验
		if (isCaptchaRequired(username,req, res)) {
			String captcha = request.getParameter(CAPTCHA_PARAM);
			if (captcha != null) {
				if (!imageCaptchaService.validateResponseForID(session.getSessionId(req, res), captcha)) {
					return onLoginFailure(token,adminLogin,new CaptchaErrorException(), request, response);
				}
			} else {
				return onLoginFailure(token,adminLogin,new CaptchaRequiredException(),request, response);
			}
		}
		if(isDisabled(username)){
			return onLoginFailure(token,adminLogin,new DisabledException(),request, response);
		}
		if(!adminLogin){
			ShopMember member;
			member = loginSvc.memberLogin(req, res, username);
		}
		try {
			Subject subject = getSubject(request, response);
			
			try {
			subject.login(token);
			} catch (AuthenticationException e) {
				throw  new UserNameOrPwException("用户名或密码错误");
			}
			return onLoginSuccess(token,adminLogin,subject, request, response);
		} catch (AuthenticationException e) {
			//e.printStackTrace();
			return onLoginFailure(token,adminLogin,e, request, response);
		}
	}

	public boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		boolean isAllowed = isAccessAllowed(request, response, mappedValue);
		//登录跳转
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (isAllowed && isLoginRequest(request, response)) {
			try {
				if (!"XMLHttpRequest".equalsIgnoreCase(req
			            .getHeader("X-Requested-With"))) {// 不是ajax请求
					issueSuccessRedirect(request, response);
			    } else {
			    	res.setCharacterEncoding("UTF-8");
			    	res.setContentType("application/json;charset=UTF-8");
			    	res.setHeader("Pragma", "No-cache");
			    	res.setHeader("Cache-Control", "no-cache");
			    	res.setDateHeader("Expires", 0);
			        
			        String info="";
					JSONObject json=new JSONObject();
					String returnUrl=req.getParameter(RETURN_URL);
					if (!StringUtils.isBlank(returnUrl)) {
						json.put("info", "");
						json.put("url", returnUrl);
						json.put("status", true);
					} else {
						//登录成功跳转到首页
						json.put("url", "/wap/wapindex.jspx");
						json.put("status", true);
					}
					
					PrintWriter out = res.getWriter();
					out.print(json);
			        out.flush();
			        out.close();
			    }
				
			} catch (Exception e) {
				logger.error("", e);
			}
			return false;
		}
		
		return isAllowed || onAccessDenied(request, response, mappedValue);
	}
	
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception{
		
	
	}
	
	public static void redirectToSavedRequest(ServletRequest request, ServletResponse response, String fallbackUrl)
            throws IOException {
        String successUrl = null;
        boolean contextRelative = false;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
            successUrl = savedRequest.getRequestUrl();
            contextRelative = false;
        }

        if (successUrl == null) {
            successUrl = fallbackUrl;
        }

        if (successUrl == null) {
            throw new IllegalStateException("Success URL not available via saved request or via the " +
                    "successUrlFallback method parameter. One of these must be non-null for " +
                    "issueSuccessRedirect() to work.");
        }
        Subject subjectss = SecurityUtils.getSubject();
        WebUtils.issueRedirect(request, response, successUrl, null, contextRelative);
    }
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String successUrl = req.getParameter(RETURN_URL);
		if (StringUtils.isBlank(successUrl)) {
			if (req.getRequestURI().startsWith(
					req.getContextPath() + getAdminPrefix())) {
				// 后台直接返回首页
				successUrl = getAdminIndex();
				// 清除SavedRequest
				WebUtils.getAndClearSavedRequest(request);
				WebUtils.issueRedirect(request, response, successUrl, null,true);
				return;
			}else if(req.getRequestURI().startsWith(req.getContextPath() + getWapPrefix())){
				successUrl = this.getWapIndex();
				// 清除SavedRequest
				WebUtils.getAndClearSavedRequest(request);
				WebUtils.issueRedirect(request, response, successUrl, null,true);
				return;
			}else {
				successUrl = getSuccessUrl();
			}
		}
		WebUtils.getAndClearSavedRequest(request);
		WebUtils.issueRedirect(request, response, successUrl, null,true);
		Subject subjectss = SecurityUtils.getSubject();
		return;
	}

	protected boolean isLoginRequest(ServletRequest req, ServletResponse resp) {
		return pathsMatch(getLoginUrl(), req)|| pathsMatch(getAdminLogin(), req)||pathsMatch(getWapLogin(), req);
	}

	/**
	 * 登录成功
	 */
	private boolean onLoginSuccess(AuthenticationToken token,boolean adminLogin,Subject subject,
			ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subjects = getSubject(request, response);
		HttpServletResponse res = (HttpServletResponse) response;
		if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)

			    .getHeader("X-Requested-With"))) {// 不是ajax请求
		String username = (String) subject.getPrincipal();
		User user = userMng.getByUsername(username);
		//管理登录
		if(adminLogin){
			cmsLogMng.loginSuccess(req, user);
		}
			
		
		String ip = RequestUtils.getIpAddr(req);
		userMng.updateLoginInfo(user.getId(), ip);
		// 清除需要验证码cookie
		removeCookieErrorRemaining(req, res);
		return super.onLoginSuccess(token, subject, request, response);
		}
		try {

			res.setCharacterEncoding("UTF-8");
	    	res.setContentType("application/json;charset=UTF-8");
	    	res.setHeader("Pragma", "No-cache");
	    	res.setHeader("Cache-Control", "no-cache");
	    	res.setDateHeader("Expires", 0);
		PrintWriter out = res.getWriter();
	    JSONObject json=new JSONObject();
	    String returnUrl = req.getParameter(RETURN_URL);
	    json.put("info", "");
		json.put("url", returnUrl);
		json.put("status", true);
		out.print(json);
		out.flush();
		out.close();

		} catch (IOException | JSONException e1) {

		// TODO Auto-generated catch block
		e1.printStackTrace();

		}
		return false; 
	}

	/**
	 * 登录失败
	 */
	private boolean onLoginFailure(AuthenticationToken token,boolean adminLogin,AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)

			    .getHeader("X-Requested-With"))) {// 不是ajax请求
			String username = (String) token.getPrincipal();
			User user=userMng.getByUsername(username);
			if(user!=null){
			user.setErrCount(user.getErrCount()+1);
			user.setErrTime(new Date());
			userMng.updateErrorCount(user);
			Integer errorRemaining = user.getErrCount();
			writeCookieErrorRemaining(errorRemaining, req, res);
			}
			//管理登录
			if(adminLogin){
				cmsLogMng.loginFailure(req,"username=" + username);
			}
				String returnUrl = req.getParameter(RETURN_URL);
				req.setAttribute(RETURN_URL, returnUrl);
				WebUtils.saveRequest(req);
			return super.onLoginFailure(token, e, req, response);

			}
			try {

				res.setCharacterEncoding("UTF-8");
		    	res.setContentType("application/json;charset=UTF-8");
		    	res.setHeader("Pragma", "No-cache");
		    	res.setHeader("Cache-Control", "no-cache");
		    	res.setDateHeader("Expires", 0);
			PrintWriter out = res.getWriter();
			String message = e.getMessage();
		    JSONObject json=new JSONObject();
		    json.put("info", message);
			json.put("url", "");
			json.put("status", false);
			out.print(json);
			out.flush();
			out.close();

			} catch (IOException | JSONException e1) {

			// TODO Auto-generated catch block
			e1.printStackTrace();

			}
			return false; 
		
	}
	
	private boolean isCaptchaRequired(String username,HttpServletRequest request,
			HttpServletResponse response) {
		if(userMng.getByUsername(username)!=null ){
			Integer errorRemaining = userMng.getByUsername(username).getErrCount();
			String captcha=RequestUtils.getQueryParam(request, CAPTCHA_PARAM);
			// 如果输入了验证码，那么必须验证；如果没有输入验证码，则根据当前用户判断是否需要验证码。
			if (!StringUtils.isBlank(captcha)) {
				return true;
			}
		}
		return false;
	}
	
	//用户禁用返回true 未查找到用户或者非禁用返回false
	private boolean isDisabled(String username){
		User user=userMng.getByUsername(username);
		Admin admin=adminMng.getByUserId(user.getId());
		if(admin!=null){
			if(admin.getDisabled()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	//用户禁用返回true 未查找到用户或者非禁用返回false
	private boolean errorCount(String username){
		User user=userMng.getByUsername(username);
		Integer errorcount=user.getErrCount();
//		if(admin!=null){
//			if(admin.getDisabled()){
//				return true;
//			}else{
//				return false;
//			}
//		}else{
//			return false;
//		}
		return false;
	}
	
	private void writeCookieErrorRemaining(Integer userErrorRemaining,
			HttpServletRequest request, HttpServletResponse response) {
		// 所有访问的页面都需要写一个cookie，这样可以判断已经登录了几次。
		Integer errorRemaining = getCookieErrorRemaining(request, response);
		ConfigLogin configLogin = configMng.getConfigLogin();
		Integer errorInterval = configLogin.getErrorInterval();
		if (userErrorRemaining != null
				&& (errorRemaining == null || userErrorRemaining < errorRemaining)) {
			errorRemaining = userErrorRemaining;
		}
		int maxErrorTimes = configLogin.getErrorTimes();
		if (errorRemaining == null || errorRemaining > maxErrorTimes) {
			errorRemaining = maxErrorTimes;
		} else if (errorRemaining <= 0) {
			errorRemaining = 0;
		} else {
			errorRemaining--;
		}
		CookieUtils.addCookie(request, response, COOKIE_ERROR_REMAINING,errorRemaining.toString(), errorInterval * 60, null);
	}

	private void removeCookieErrorRemaining(HttpServletRequest request,HttpServletResponse response) {
		CookieUtils.cancleCookie(request, response, COOKIE_ERROR_REMAINING,null);
	}

	private Integer getCookieErrorRemaining(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = CookieUtils.getCookie(request, COOKIE_ERROR_REMAINING);
		if (cookie != null) {
			String value = cookie.getValue();
			if (NumberUtils.isDigits(value)) {
				return Integer.parseInt(value);
			}
		}
		return null;
	}
	

	@Autowired
	private UserMng userMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private GlobalMng globalMng;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private AdminMng adminMng;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private RememberMeService rememberMeService;
	@Autowired
	private LoginSvc loginSvc;
	private String adminPrefix;
	private String adminIndex;
	private String adminLogin;
	private String wapPrefix;
	private String wapIndex;
	private String wapLogin;
	public String getAdminPrefix() {
		return adminPrefix;
	}

	public void setAdminPrefix(String adminPrefix) {
		this.adminPrefix = adminPrefix;
	}

	public String getAdminIndex() {
		return adminIndex;
	}

	public void setAdminIndex(String adminIndex) {
		this.adminIndex = adminIndex;
	}
	
	public String getAdminLogin() {
		return adminLogin;
	}

	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}

	public String getWapPrefix() {
		return wapPrefix;
	}

	public void setWapPrefix(String wapPrefix) {
		this.wapPrefix = wapPrefix;
	}

	public String getWapIndex() {
		return wapIndex;
	}

	public void setWapIndex(String wapIndex) {
		this.wapIndex = wapIndex;
	}

	public String getWapLogin() {
		return wapLogin;
	}

	public void setWapLogin(String wapLogin) {
		this.wapLogin = wapLogin;
	}

	
	

}
