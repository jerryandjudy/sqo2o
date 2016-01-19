package com.jspgou.cms.action.member;

import static com.jspgou.cms.Constants.MEMBER_SYS;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Message;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.cms.entity.ShopScore.ScoreTypes;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.MessageMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.ShopScoreMng;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.session.SessionProvider;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.EmailSender;
import com.jspgou.core.entity.Member;
import com.jspgou.core.entity.MessageTemplate;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.MemberMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.security.CmsAuthorizingRealm;
import com.jspgou.core.security.CmsAuthorizingRealm.HashPassword;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class RegisterAct {
	private static final Logger log = LoggerFactory
			.getLogger(RegisterAct.class);
	private static final String REGISTER= "tpl.register";
	private static final String REGISTERBLD= "tpl.registerbld";
	private static final String REGISTERGYS= "tpl.registergys";
	private static final String REGISTER_RESULT = "tpl.registerResult";
	private static final String REGISTER_TREATY = "tpl.registerTreaty";
	private static final String REGISTER_ACTIVE_STATUS="tpl.registerActiveStatus";

	/**
	 * 会员注册
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register.jspx",method = RequestMethod.GET)
	public String registerInput(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		//用户已经登录
				ShopMember member=MemberThread.get();
						if(member != null && member.getUsername()!=null){
							return "redirect:logout.jspx?returnUrl=register.jspx";
						}
//						member=MemberThread.get();
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,REGISTER));
	}
	/**
	 * 便利店注册
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registerbld.jspx",method = RequestMethod.GET)
	public String registerbldInput(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		//用户已经登录
		ShopMember member=MemberThread.get();
		if(member != null && member.getUsername()!=null){
			return "redirect:logout.jspx?returnUrl=registerbld.jspx";
		}
//						member=MemberThread.get();
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,REGISTERBLD));
	}
	/**
	 * 供应商注册
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registergys.jspx",method = RequestMethod.GET)
	public String registergysInput(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		//用户已经登录
		ShopMember member=MemberThread.get();
		if(member != null && member.getUsername()!=null){
			return "redirect:logout.jspx?returnUrl=registergys.jspx";
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,REGISTERGYS));
	}

/*	@RequestMapping(value = "/register.jspx",method = RequestMethod.POST)
	public String registerSubmit(String checkcode, String username,
			String email, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		WebErrors errors = validate(checkcode, username, email, password,request, response);
		
		if (errors.hasErrors()) {
			ResponseUtils.renderJson(response, errors.toString());
			//return FrontHelper.showError(errors, web, model, request);
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
        EmailSender sender = web.getEmailSender();
		MessageTemplate tpl = (MessageTemplate) web.getMessages().get(MessageTemplate.RESET_PASSWORD);
		//发送激活邮件
		if (sender == null) {
			// 邮件服务器没有设置好
			model.addAttribute("status", 2);
		} else if (tpl == null) {
			// 邮件模板没有设置好
			model.addAttribute("status", 3);
		} else {
		    try {
		    	//uuid激活码
		    	String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
		    	String base=new String(web.getUrlBuff(true));
		    	userMng.senderActiveEmail(username,base,email,uuid, sender, tpl);
		    	ShopMember member = shopMemberMng.register(username, password, email,false,uuid,
						request.getRemoteAddr(), false, web.getId(), config.getRegisterGroup().getId());
				// 获取邮件类型
				String emailtype = email.substring(email.indexOf("@") + 1, email.indexOf("."));
				model.addAttribute("emailtype", emailtype);
				model.addAttribute("member", member);
			    model.addAttribute("status", 1);
			    log.info("register member '{}'", member.getUsername());
		       } catch (Exception e) {
					// 发送邮件异常
					model.addAttribute("status", 4);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception {}.", e.getMessage());
		       }
		}
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,REGISTER_RESULT));
	}*/
	@RequestMapping(value = "/register.jspx",method = RequestMethod.POST)
	public void registerSubmit(Integer types,String companyName,String code, String account,
			String email, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		WebErrors errors = null;
		if(types==null||types==1){
			errors = validate(code, account, email, password,request, response);
		}else{
			errors = validatebldgys(code, account, email, password,request, response,types);
		}
		
		JSONObject json=new JSONObject();
		String error="";
		if (errors.hasErrors()) {  //验证失败
			List list=errors.getErrors();
			error= list.toString();
//			return errors.toString();
			//return FrontHelper.showError(errors, web, model, request);
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
		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
		if(types==null || types==1){
		ShopMember member = shopMemberMng.register(account, password, email,true,uuid,
				request.getRemoteAddr(), false, web.getId(), config.getRegisterGroup().getId());
		try {
			json.put("url", "login.jspx");
			json.put("info", "");
			json.put("type", "1");
			json.put("status", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else if(types==2){
			Organization organization=organizationMng.findByName("便利店注册");
			KetaUser ketaUser=new KetaUser();
			ketaUser.setUsername(account);
			ketaUser.setOrganization(organization);
			HashPassword hashPassword = CmsAuthorizingRealm.encryptPassword(password);
			ketaUser.setSalt(hashPassword.salt);
			ketaUser.setPassword(hashPassword.password);
			ketaUser.setRealname(companyName);
			ketaUser.setPhone(account);
//			ketaUserMng.save(ketaUser);
			User user = SiteUtils.getUser(request);
			Bld bld=new Bld();
			bld.setCreateTime(new Date());
			bld.setWebsite(web);
			Account accounts=new Account();
			accounts.setMoney(0d);
			bld.setCompanyName(companyName);
			bld.setContactTel(account);
			accounts.setUsername(account);
			accounts.setStatus(0);
//			bld.setAccount(accounts);
//			bld.setKetaUser(ketaUser);
			bld = bldMng.register(bld,accounts,ketaUser);
			try {
				json.put("url", "2");
				json.put("info", "");
				json.put("type", "2");
				json.put("status", true);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(types==3){
			Organization organization=organizationMng.findByName("供应商注册");
			KetaUser ketaUser=new KetaUser();
			ketaUser.setUsername(account);
			ketaUser.setOrganization(organization);
			HashPassword hashPassword = CmsAuthorizingRealm.encryptPassword(ketaUser.getPainPass());
			ketaUser.setSalt(hashPassword.salt);
			ketaUser.setPassword(hashPassword.password);
			ketaUser.setRealname(companyName);
			ketaUser.setPhone(account);
//			ketaUserMng.save(ketaUser);
			Gys gys=new Gys();
			gys.setCreateTime(new Date());
			gys.setCompanyName(companyName);
			gys.setWebsite(web);
			Account accounts=new Account();
			accounts.setMoney(0d);
			gys.setPhone(account);
			accounts.setUsername(account);
			accounts.setStatus(0);
//			gys.setAccount(accounts);
//			gys.setKetaUser(ketaUser);
			gys = gysMng.register(gys,accounts,ketaUser);
			try {
				json.put("url", "3");
				json.put("info", "");
				json.put("type", "3");
				json.put("status", true);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ResponseUtils.renderJson(response, json.toString());
		
	}
	@RequestMapping(value = "/sendCode.jspx",method = RequestMethod.POST)
	public void sendCode(Integer types,String checkcode, String username,
			HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		WebErrors errors=null;
		if(types==null){
			types=1;
		}
		errors = validateUser(types,checkcode, username, request, response);
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
//			return errors.toString();
			//return FrontHelper.showError(errors, web, model, request);
			ResponseUtils.renderJson(response, json.toString());
			return;
		}else{ //发送短信验证码
			String res="";
			String yzm=((int)((Math.random()*9+1)*100000))+"";  
			Long yxs=messageMng.getTodayCountByTel(username,types);
			Integer yxmis=web.getGlobal().getYxq();
			if(yxs>=web.getGlobal().getCs()){
				res="对不起,您当前号码发送验证码次数已经用完,请明天或换个号码试试！！";
			}else{
				res=SendMsg_webchinese.sendMsg(username,"您的注册新用户操作的短信验证码是 "+yzm+",有效时间"+yxmis+"分钟 。如果非本人操作,请忽略。");
			}
			if(res.length()>0){
				try {
					json.put("status", false);
					json.put("info",res);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				return errors.toString();
				//return FrontHelper.showError(errors, web, model, request);
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
//			session.setAttribute(request, response, id+"yzm", yzm);
			try {
				Message bean=new Message();
				bean.setTel(username);
				bean.setYzm(yzm);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
//				cal.roll(Calendar.MINUTE, yxmis);
				cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+yxmis);
				bean.setYxq(cal.getTime());
				bean.setTypes(types);
				messageMng.save(bean);
				json.put("status", true);
				json.put("info","");
				ResponseUtils.renderJson(response, json.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,REGISTER_RESULT));
	}
	
	//激活用户
	@RequestMapping(value = "/active.jspx", method = RequestMethod.GET)
	public String active(String userName, String activationCode,HttpServletRequest request, 
			HttpServletResponse response,ModelMap model) throws IOException {
		Website web = SiteUtils.getWeb(request);
		Member bean=memberMng.getByUsername(web.getId(), userName);
		long l=System.currentTimeMillis();
	    l=l-24*60*60*1000;
	    Date date=new Date();
		date.setTime(l);
		if (StringUtils.isBlank(String.valueOf(userName))|| StringUtils.isBlank(activationCode)) {
             model.addAttribute("status", 2);
		}else if (bean == null) {
			 model.addAttribute("status", 3);
		}else if (bean.getActive()) {
			 model.addAttribute("status", 4);
		}else if (!bean.getActivationCode().equals(activationCode)) {
			 model.addAttribute("status", 5);
		}else if(bean.getCreateTime().compareTo(date)<0){
			 model.addAttribute("status", 6);
	    }else{
			bean.setActive(true);
			memberMng.update(bean); 
			//验证邮箱加十个积分
			shopMemberMng.updateScore(shopMemberMng.findById(bean.getId()),
			SiteUtils.getWeb(request).getGlobal().getActiveScore());
			ShopScore shopScore=new ShopScore();
			shopScore.setMember(shopMemberMng.findById(bean.getId()));
			shopScore.setName("邮箱验证送积分");
			shopScore.setScoreTime(new Date());
			shopScore.setStatus(true);
			shopScore.setUseStatus(false);
			shopScore.setScoreType(ScoreTypes.EMAIL_VALIDATE.getValue());
			shopScoreMng.save(shopScore);
			model.addAttribute("status", 1);
			model.addAttribute("member", bean);
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,REGISTER_ACTIVE_STATUS));
	}
	// 重新发送找回密码邮件
		@RequestMapping(value = "/reactive.jspx", method = RequestMethod.POST)
		public void reactive(Long userId,HttpServletRequest request,
				HttpServletResponse response, ModelMap model) throws IOException {
			Website web = SiteUtils.getWeb(request);
			JSONObject json = new JSONObject();
			Member bean=memberMng.findById(userId);
			if(bean.getActive()){
				try {
					json.put("data", 1);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
			    //重新获取uuid激活码，更新激活码，更新创建时间
	    	    String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
	         	bean.setActivationCode(uuid);
	         	bean.setCreateTime(new Date());
	    	    memberMng.update(bean);
			    String base=new String(web.getUrlBuff(true));
	            EmailSender sender = web.getEmailSender();
	            Map<String, MessageTemplate> messages = web.getMessages();
			    MessageTemplate tpl =messages.get("resetPassword");
		        try {
	    	         userMng.senderActiveEmail(bean.getUsername(),base,bean.getEmail(),uuid, sender, tpl);
	    				try {
	    					json.put("data", 2);
	    				} catch (JSONException e) {
	    					e.printStackTrace();
	    				}
		         } catch (Exception e) {
					  // 发送邮件异常
						try {
							json.put("data", 3);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
					  //log.error("send email exception {}.", e.getMessage());
		         }
			}
			ResponseUtils.renderJson(response, json.toString());
		}
	// 重新发送激活邮件
	@RequestMapping(value = "/reactives.jspx", method = RequestMethod.POST)
	public void reactive(String email,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		Website web = SiteUtils.getWeb(request);
		JSONObject json = new JSONObject();
		if(email==null || email.trim().length()<1){
			try {
				json.put("data", 0);//用户不能为空
			} catch (JSONException e) {
				e.printStackTrace();
			}
		ResponseUtils.renderJson(response, json.toString());
		return;
		}
		User user=userMng.getByEmail(email);
		if(user==null){
			try {
				json.put("data", 2);//用户不存在
			} catch (JSONException e) {
				e.printStackTrace();
			}
		ResponseUtils.renderJson(response, json.toString());
		return;	
		}
		Member bean=memberMng.getByUserId(web.getId(), user.getId());
		if(bean.getActive()){
			try {
				json.put("data", 1); //用户已经激活
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
		    //重新获取uuid激活码，更新激活码，更新创建时间
    	    String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
         	bean.setActivationCode(uuid);
         	bean.setCreateTime(new Date());
    	    memberMng.update(bean);
		    String base=new String(web.getUrlBuff(true));
            EmailSender sender = web.getEmailSender();
            Map<String, MessageTemplate> messages = web.getMessages();
		    MessageTemplate tpl =messages.get("resetPassword");
	        try {
    	         userMng.senderActiveEmail(bean.getUsername(),base,bean.getEmail(),uuid, sender, tpl);
    			json.put("data", 4);//成功
	         } catch (Exception e) {
				  // 发送邮件异常
					try {
						json.put("data", 3);//发送邮件异常
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				  //log.error("send email exception {}.", e.getMessage());
	         }
		}
		ResponseUtils.renderJson(response, json.toString());
	}
	
	//用户协议
	@RequestMapping(value = "/treaty.jspx")
	public String treaty(HttpServletRequest request, ModelMap model) {
			Website web = SiteUtils.getWeb(request);
			model.addAttribute("global", SiteUtils.getWeb(request).getGlobal());
			ShopFrontHelper.setCommonData(request, model, web, 1);
			return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
					REGISTER_TREATY));
	}
	
	//检查用户名是否唯一
	@RequestMapping(value="/username_unique.jspx")
	public void checkUsername(HttpServletRequest request,
			HttpServletResponse response) {
		String username = RequestUtils.getQueryParam(request, "username");
		// 用户名为空，返回false。
		if (StringUtils.isBlank(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// 用户名存在，返回false。
		if (userMng.usernameExist(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}
	
	//检查邮箱是否唯一
	@RequestMapping(value="/email_unique.jspx")
	public void checkEmail(HttpServletRequest request,
			HttpServletResponse response) {
		String email = RequestUtils.getQueryParam(request, "email");
		// email为空，返回false。
		if (StringUtils.isBlank(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// email存在，返回false。
		if (userMng.emailExist(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		} 
		ResponseUtils.renderJson(response, "true");
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
		if (userMng.usernameExist(username)) {
			errors.addError("该号码已经存在！！");;
			return errors;
		}
		return errors;
	}
	/**
	 * 便利店和供应商注册验证
	 * @param checkcode
	 * @param username
	 * @param email
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	private WebErrors validatebldgys(String checkcode, String username, String email,
			String password, HttpServletRequest request,
			HttpServletResponse response,Integer types) {
		Website web=SiteUtils.getWeb(request);
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(checkcode)) {
			errors.addError("短信验证码不能为空！！");;
			return errors;
		}
		Integer yxmis=web.getGlobal().getYxq();
		Long cus=messageMng.getYzmByTel(username, checkcode, yxmis,types);
		if(cus<=0){
			errors.addError("短信验证码无效,请重新获取！！");;
			return errors;
		}
		KetaUser s=ketaUserMng.findByUserName(username);
		if (s!=null&&s.getId()>0) {
			errors.addError("该号码已经存在！！");;
			return errors;
		}
		return errors;
	}
	private WebErrors validateUser(Integer types,String checkcode, String username,  HttpServletRequest request,
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
				// 用户名存在，返回false。
		        if(types==null||types==1){
				if (userMng.usernameExist(username)) {
					errors.addError("号码已经存在！");
					return errors;
				}
		        }else{
		        	KetaUser s=ketaUserMng.findByUserName(username);
		    		if (s!=null&&s.getId()>0) {
		    			errors.addError("该号码已经存在！！");;
		    			return errors;
		    		}
		        }
		
		
		return errors;
	}

	@Autowired
	private UserMng userMng;
	@Autowired
	private KetaUserMng ketaUserMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private ShopScoreMng shopScoreMng;
	@Autowired
	private MemberMng memberMng;
	@Autowired
	private MessageMng messageMng;
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private SessionProvider session;
	@Autowired
	private LoginSvc loginSvc;
	@Autowired
	private OrganizationMng organizationMng;
	@Autowired
	private BldMng bldMng;
	@Autowired
	private GysMng gysMng;
}
