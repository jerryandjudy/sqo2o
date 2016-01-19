package com.jspgou.cms.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.common.security.BadCredentialsException;
import com.jspgou.common.security.UserNameOrPwException;
import com.jspgou.common.security.UsernameNotFoundException;
import com.jspgou.core.entity.Website;
import com.jspgou.core.security.UserNotInWebsiteException;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.AdminMap;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.AdminThread;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class LoginAct {
	private static final Logger log = LoggerFactory.getLogger(LoginAct.class);

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		ShopAdmin admin = AdminThread.get();
		if (admin != null) {
			model.addAttribute("admin", admin);
			return "index";
		} else {
			return "login";
		}
	}
	@RequestMapping(value = "/login.do")
	public String login(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		 String msg = parseException(request);
		 List errors=null;
		 if(msg.length()>0){
		 errors=new ArrayList();
		 errors.add(msg);
		 }
		 model.put("errors", errors);
		ShopAdmin admin = AdminThread.get();
		if (admin != null) {
			model.addAttribute("admin", admin);
			return "index";
		} else {
			return "login";
		}
	}
	
	private String parseException(ServletRequest request) {
		String errorString = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		Class<?> error = null;
		try {
			if (errorString != null) {
				error = Class.forName(errorString);
			}
		} catch (ClassNotFoundException e) {
//			LOG.error(Exceptions.getStackTraceAsString(e));
		} 
		
		String msg = "";
		if (error != null) {
			if (error.equals(UnknownAccountException.class))
				msg = "未知帐号错误！";
			else if (error.equals(DisabledAccountException.class))
				msg = "账号被冻结！";
			else if (error.equals(UserNameOrPwException.class))
				msg = "用户名或密码错误！";
			else msg = "其他错误！";
				
		}

		return  msg;
	}

	@RequestMapping(value = "/index.do", method = RequestMethod.POST)
	public String loginSubmit(String username, String password,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		WebErrors errors = validateSubmit(username,request,response);
		if (!errors.hasErrors()) {
			Website web = SiteUtils.getWeb(request);
			try {
				loginSvc.adminLogin(request, response, web, username, password);
				log.info("admin '{}' login success.", username);
				return "redirect:index.do";
			} catch (UsernameNotFoundException e) {
				errors.addError(e.getMessage());
				log.info(e.getMessage());
			} catch (BadCredentialsException e) {
				if(!username.trim().equals("admin")){
					AdminMap.addAdminMapVal(username);
				}
				errors.addError(e.getMessage());
				log.info(e.getMessage());
			} catch (UserNotInWebsiteException e) {
				errors.addError(e.getMessage());
				log.info(e.getMessage());
			}
		}
		errors.toModel(model);
		return "login";
	}
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		loginSvc.logout(request, response);
		return "redirect:login.do";
	}
	
	private WebErrors validateSubmit(String username,HttpServletRequest request,HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		Integer errCount=AdminMap.getAdminMapVal(username);
		if(errCount!=null&&errCount>=3){
			errors.addError("你的账号被锁定!");
			return errors;
		}
		return errors;
	}

	@Autowired
	private LoginSvc loginSvc;
	
}
