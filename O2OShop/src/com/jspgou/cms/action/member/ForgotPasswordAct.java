package com.jspgou.cms.action.member;

import static com.jspgou.cms.Constants.MEMBER_SYS;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.session.SessionProvider;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.EmailSender;
import com.jspgou.core.entity.MessageTemplate;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.cms.entity.Message;
import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.MessageMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

/**
 * 找回密码Action
 * 
 * 用户忘记密码后点击找回密码链接，输入用户名、邮箱和验证码<li>
 * 如果信息正确，返回一个提示页面，并发送一封找回密码的邮件，邮件包含一个链接及新密码，点击链接新密码即生效<li>
 * 如果输入错误或服务器邮箱等信息设置不完整，则给出提示信息<li>
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
 */
@Controller
public class ForgotPasswordAct {
	private static final Logger log = LoggerFactory
			.getLogger(ForgotPasswordAct.class);
	private static final String FORGOTTEN_INPUT = "tpl.forgottenInput";
	private static final String FORGOTTEN_RESULT = "tpl.forgottenResult";
	private static final String RESET_PASSWORD_TPL = "tpl.resetPassword";

	/**
	 * 找回密码输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgot_password.jspx", method = RequestMethod.GET)
	public String fogottenInput(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		//用户已经登录
		ShopMember member=MemberThread.get();
				if(member != null && member.getUsername()!=null){
					return "redirect:logout.jspx?returnUrl=forgot_password.jspx";
				}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
				FORGOTTEN_INPUT));
	}
	private WebErrors validate(String checkcode, String username, String email,
			String password, HttpServletRequest request,
			HttpServletResponse response) {
		Website web=SiteUtils.getWeb(request);
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(checkcode)) {
			errors.addError("短信验证码不能为空！！");;
			return errors;
		}
		Integer yxmis=web.getGlobal().getYxq();
		Long cus=messageMng.getYzmByTel(username, checkcode, yxmis,1);
		if(cus<=0){
			errors.addError("短信验证码无效,请重新获取！！");;
			return errors;
		}
		if (!userMng.usernameExist(username)) {
			errors.addError("该号码不存在！！");;
			return errors;
		}
		return errors;
	}
	@RequestMapping(value = "/reset_password.jspx",method = RequestMethod.POST)
	public void resetSubmit(String code, String account,
			String email, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		WebErrors errors = validate(code, account, email, password,request, response);
		JSONObject json=new JSONObject();
		String error="";
		if (errors.hasErrors()) {  //验证失败
			List list=errors.getErrors();
			error= list.toString();
			try {
				json.put("info", error);
				json.put("url", "");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		User user = userMng.getByUsername(account);
		userMng.updateUser(user.getId(), password, email);
//		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
//		ShopMember member = shopMemberMng.register(account, password, email,true,uuid,
//				request.getRemoteAddr(), false, web.getId(), config.getRegisterGroup().getId());
		try {
			json.put("url", "login.jspx");
			json.put("info", "");
			json.put("status", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, json.toString());
		
	}
	/**
	 * 重置密码发送验证码
	 * @param checkcode
	 * @param username
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/resetsendCode.jspx",method = RequestMethod.POST)
	public void sendCode(String checkcode, String username,
			HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		
		WebErrors errors = validateUser(checkcode, username, request, response);
		String error="";
		JSONObject json=new JSONObject();
		String id = session.getSessionId(request, response);
		if (errors.hasErrors()) {
			List list=errors.getErrors();
			error= list.toString();
			try {
				json.put("status", false);
				json.put("info",error);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}else{ //发送短信验证码
			String res="";
			String yzm=((int)((Math.random()*9+1)*100000))+"";  
			Long yxs=messageMng.getTodayCountByTel(username,1);
			Integer yxmis=web.getGlobal().getYxq();
			if(yxs>=web.getGlobal().getCs()){
				res="对不起,您当前号码发送验证码次数已经用完,请明天再试！！";
			}else{
				res=SendMsg_webchinese.sendMsg(username,"您的忘记密码操作的短信验证码是 "+yzm+",有效时间"+yxmis+"分钟 。如果非本人操作,请忽略。");
			}
			if(res.length()>0){
				try {
					json.put("status", false);
					json.put("info",res);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
			try {
				Message bean=new Message();
				bean.setTel(username);
				bean.setYzm(yzm);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+yxmis);
				bean.setYxq(cal.getTime());
				bean.setTypes(1);
				messageMng.save(bean);
				json.put("status", true);
				json.put("info","");
				ResponseUtils.renderJson(response, json.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private WebErrors validateUser(String checkcode, String username,  HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		String id = session.getSessionId(request, response);
		// 用户名为空，返回false。
		if (StringUtils.isBlank(checkcode)) {
			errors.addError("验证码不能为空！");
			return errors;
		}
		try {
			if (!captchaService.validateResponseForID(id, checkcode
					.toUpperCase(Locale.ENGLISH))) {
				errors.addErrorCode("error.checkcodeIncorrect");
				return errors;
			}
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
			errors.addErrorCode("error.checkcodeInvalid");
			errors.addErrorString(e.getMessage());
			return errors;
		}
		
		if (StringUtils.isBlank(username)) {
					errors.addError("号码不能为空！");
					return errors;
				}
				// 用户名不存在，返回false。
				if (!userMng.usernameExist(username)) {
					errors.addError("号码不存存在！");
					return errors;
				}
		
		
		return errors;
	}

	/**
	 * 找回密码提交页
	 * 
	 * @param username
	 * @param email
	 * @param captcha
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/forgot_password.jspx", method = RequestMethod.POST)
	public String fogottenSubmit(String checkcode, String username,
			String email, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateFogotten(checkcode, username, email,
				request, response);
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		// 0:成功1:用户名不存在;2:邮箱地址错误;3: 邮箱输入错误;4:邮件发送器没有设置;5:找回密码模板没有设置
		User user = userMng.getByUsername(username);
		MessageTemplate tpl = (MessageTemplate) web.getMessages().get(
				MessageTemplate.RESET_PASSWORD);
		EmailSender sender = web.getEmailSender();
		if (user == null) {
			// 用户名不存在
			model.addAttribute("status", 1);
		} else if (!user.getEmail().equalsIgnoreCase(email)) {
			// 用户没有设置邮箱
			model.addAttribute("status", 2);
		} else if (!user.getEmail().equals(email)) {
			// 邮箱输入错误
			model.addAttribute("status", 3);
		} else if (sender == null) {
			// 邮件服务器没有设置好
			model.addAttribute("status", 4);
		} else if (tpl == null) {
			// 邮件模板没有设置好
			model.addAttribute("status", 5);
		} else {
			// 0:成功
			try {
			String base=new String(web.getUrlBuff(true));
			  user = userMng.passwordForgotten(user.getId(),base, sender, tpl);
			  // 获取邮件类型
			   String emailtype = email.substring(email.indexOf("@") + 1, email.indexOf("."));
			   model.addAttribute("emailtype", emailtype);
			   model.addAttribute("status", 0);
			   model.addAttribute("user", user);
			} catch (Exception e) {
				// 发送邮件异常
				model.addAttribute("status", 100);
				model.addAttribute("message", e.getMessage());
				log.error("send email exception.", e);
			}
		}
		model.addAttribute("user", user);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		log.info("find passsword, username={} email={}", username, email);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
				FORGOTTEN_RESULT));
	}

	@RequestMapping(value = "/reset_password.jspx")
	public String resetPwd(Long uid, String activationCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateReset(uid, activationCode, request);
		if (errors.hasErrors()) {
			return FrontHelper.showMessage(errors.getErrors().get(0), web,
					model, request);
		}
		User user = userMng.findById(uid);
		boolean success;
		if (activationCode.equals(user.getResetKey())) {
			user = userMng.resetPassword(user.getId());
			success = true;
		} else {
			success = false;
		}
		model.addAttribute("user", user);
		model.addAttribute("success", success);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
				RESET_PASSWORD_TPL));
	}

	private WebErrors validateFogotten(String checkcode, String username,
			String email, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		String id = session.getSessionId(request, response);
		if (errors.ifOutOfLength(checkcode, "checkcode", 3, 10)) {
			return errors;
		}
		try {
			if (!captchaService.validateResponseForID(id, checkcode
					.toUpperCase(Locale.ENGLISH))) {
				errors.addErrorCode("error.checkcodeIncorrect");
				return errors;
			}
		} catch (CaptchaServiceException e) {
			errors.addErrorCode("error.checkcodeInvalid");
			errors.addErrorString(e.getMessage());
			return errors;
		}
		if (errors.ifNotEmail(email, "email", 100)) {
			return errors;
		}
		if (errors.ifNotUsername(username, "username", 3, 100)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateReset(Long uid, String resetKey,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(uid, "uid")) {
			return errors;
		}
		User user = userMng.findById(uid);
		if (errors.ifNotExist(user, User.class, uid)) {
			return errors;
		}
		if (errors.ifOutOfLength(resetKey, "resetKey", 32, 32)) {
			return errors;
		}
		return errors;
	}

	@Autowired
	private UserMng userMng;
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private SessionProvider session;
	@Autowired
	private MessageMng messageMng;
}
