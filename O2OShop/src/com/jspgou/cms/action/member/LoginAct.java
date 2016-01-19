package com.jspgou.cms.action.member;

import static com.jspgou.core.web.Constants.MESSAGE;
import static com.jspgou.core.web.Constants.RETURN_URL;
import static com.jspgou.cms.Constants.MEMBER_SYS;
import static com.jspgou.cms.Constants.TPLDIR_INDEX;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.common.security.BadCredentialsException;
import com.jspgou.common.security.UserNotAcitveException;
import com.jspgou.common.security.UsernameNotFoundException;
import com.jspgou.common.security.encoder.PwdEncoder;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.MemberMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.security.UserNotInWebsiteException;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class LoginAct {
	private static final Logger log = LoggerFactory.getLogger(LoginAct.class);
	
	private static final String LOGIN_INPUT = "tpl.loginInput";
	
	public static final String TPL_INDEX = "tpl.index";
	
	@RequestMapping(value = "/login.jspx", method = RequestMethod.GET)
	public String loginInput(String returnUrl, String message,
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
			if (!StringUtils.isBlank(message)) {
				model.addAttribute(MESSAGE, message);
			}
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
				LOGIN_INPUT));
	}

//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
//	public String loginSubmit(String username, String password,
//			String returnUrl,String cookieType, String redirectUrl, HttpServletRequest request,
//			HttpServletResponse response, ModelMap model) {
//		Website web = SiteUtils.getWeb(request);
//		WebErrors errors = WebErrors.create(request);
//		ShopMember member=null;
//		String usernames =  (String) SecurityUtils.getSubject().getPrincipal();
//		if(usernames!=null){
//		member= shopMemberMng.getByUsername(usernames);
//		}
//			member = loginSvc.memberLogin(request, response, web, username,password);
//			if(member == null || member.getUsername()==null||user==null){
//				return "redirect:/";
//			}
//			log.info("member '{}' login success.", username);
			// 4 不保存
						// 3 保存1天
						// 2 一个月
						// 1 年
						// 0 永久
//						Integer maxDate = 360 * 24 * 60 * 60;
//						if ("0".equals(cookieType)) {
//							maxDate = Integer.MAX_VALUE;
//						} else if ("1".equals(cookieType)) {
//							maxDate = 360 * 24 * 60 * 60;
//						} else if ("2".equals(cookieType)) {
//							maxDate = 30 * 24 * 60 * 60;
//						} else if ("3".equals(cookieType)) {
//							maxDate = 1 * 24 * 60 * 60;
//						}
//						CookieUtils.addCookie(request, response, "o2o_username",
//								URLEncoder.encode(username,"utf-8"), maxDate,null);
//						//密码加密后存储
//						CookieUtils.addCookie(request, response, "o2o_password",
//								pwdEncoder.encodePassword(password), maxDate,null);
//						Cookie userCookie = CookieUtils.getCookie(request, "o2o_username"); 
//						Cookie pwCookie = CookieUtils.getCookie(request, "o2o_password"); 
//						MemberThread.set(member);
//						GroupThread.set(member.getGroup());
//			if (!StringUtils.isBlank(returnUrl)) {
//				return "redirect:" + returnUrl;
//			} else if (!StringUtils.isBlank(redirectUrl)) {
//				return "redirect:" + redirectUrl;
//			} else {
//				model.addAttribute("member", member);
//				model.addAttribute("site",web);
//				ShopFrontHelper.setCommonData(request, model, web, 1);
				//登录成功跳转到首页
//				return web.getTemplate(TPLDIR_INDEX, MessageResolver.getMessage(request,
//						TPL_INDEX));
//			}
//		 
//		ShopFrontHelper.setCommonData(request, model, web, 1);
//		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,LOGIN_INPUT));
//	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
	public String loginSubmit(String username, String password,
			String returnUrl, String redirectUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = WebErrors.create(request);
		ShopMember member;
		try {
			String usernames =  (String) SecurityUtils.getSubject().getPrincipal();
			if(usernames!=null){
			member= shopMemberMng.getByUsername(usernames);
			}else{
			member = loginSvc.memberLogin(request, response, web, username,password);
			}
			User user = SiteUtils.getUser(request);
			if(member == null || member.getUsername()==null||user==null){
				return "redirect:/";
			}
			log.info("member '{}' login success.", username);
			if (!StringUtils.isBlank(returnUrl)) {
				return "redirect:" + returnUrl;
			} else if (!StringUtils.isBlank(redirectUrl)) {
				return "redirect:" + redirectUrl;
			} else {
				model.addAttribute("member", member);
				ShopFrontHelper.setCommonData(request, model, web, 1);
				//登录成功跳转到首页
				return web.getTemplate(TPLDIR_INDEX, MessageResolver.getMessage(request,
						TPL_INDEX));
			}
		} catch (UsernameNotFoundException e) {
			errors.addErrorCode("error.usernameNotExist");
			log.info(e.getMessage());
		} catch (BadCredentialsException e) {
			errors.addErrorCode("error.passwordInvalid");
			log.info(e.getMessage());
		} catch (UserNotInWebsiteException e) {
			errors.addErrorCode("error.usernameNotInWebsite");
			log.info(e.getMessage());
		}catch(UserNotAcitveException e){
			errors.addErrorCode("error.usernameNotActivated");
			log.info(e.getMessage());
		}	
		errors.toModel(model);
		model.put("returnUrl", returnUrl);
		model.put("redirectUrl", redirectUrl);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,LOGIN_INPUT));
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/logins.jspx")
	public String loginSubmit( HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = WebErrors.create(request);
		
		String usernames =  (String) SecurityUtils.getSubject().getPrincipal();
		ShopMember member= shopMemberMng.getByUsername(usernames);
			member = loginSvc.memberLogin(request, response, web, usernames,member.getPassword());
			User user = SiteUtils.getUser(request);
			if(member == null || member.getUsername()==null||user==null){
				return "redirect:/";
			}
			log.info("member '{}' login success.", member);
		// 4 不保存
		// 3 保存1天
		// 2 一个月
		// 1 年
		// 0 永久
//						Integer maxDate = 360 * 24 * 60 * 60;
//						if ("0".equals(cookieType)) {
//							maxDate = Integer.MAX_VALUE;
//						} else if ("1".equals(cookieType)) {
//							maxDate = 360 * 24 * 60 * 60;
//						} else if ("2".equals(cookieType)) {
//							maxDate = 30 * 24 * 60 * 60;
//						} else if ("3".equals(cookieType)) {
//							maxDate = 1 * 24 * 60 * 60;
//						}
//						CookieUtils.addCookie(request, response, "o2o_username",
//								URLEncoder.encode(username,"utf-8"), maxDate,null);
//						//密码加密后存储
//						CookieUtils.addCookie(request, response, "o2o_password",
//								pwdEncoder.encodePassword(password), maxDate,null);
//						Cookie userCookie = CookieUtils.getCookie(request, "o2o_username"); 
//						Cookie pwCookie = CookieUtils.getCookie(request, "o2o_password"); 
//						MemberThread.set(member);
//						GroupThread.set(member.getGroup());
			model.addAttribute("member", member);
			model.addAttribute("site",web);
			ShopFrontHelper.setCommonData(request, model, web, 1);
			//登录成功跳转到首页
			return web.getTemplate(TPLDIR_INDEX, MessageResolver.getMessage(request,
					TPL_INDEX));
		
//		ShopFrontHelper.setCommonData(request, model, web, 1);
//		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,LOGIN_INPUT));
	}
	
	public Integer errorRemaining(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		User user = userMng.getByUsername(username);
		if (user == null) {
			return null;
		}
		return  null;
	}
	

	@RequestMapping("/logout.jspx")
	public String logout(String redirectUrl, HttpServletRequest request,
			HttpServletResponse response) {
		AdminThread.remove();
		GroupThread.remove();
		MemberThread.remove();
		if(!StringUtils.isBlank(redirectUrl)){
			if(redirectUrl.indexOf("?")>-1){
				redirectUrl=redirectUrl+"&imei="+request.getSession().getAttribute("imei");
			}else{
				redirectUrl=redirectUrl+"?imei="+request.getSession().getAttribute("imei");
				
			}
		}
		loginSvc.logout(request, response);
		if (!StringUtils.isBlank(redirectUrl)) {
			return "redirect:" + redirectUrl;
		} else {
			return "redirect:/";
		}
	}

	@Autowired
	private LoginSvc loginSvc;
	@Autowired
	private UserMng userMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private PwdEncoder pwdEncoder;
}
