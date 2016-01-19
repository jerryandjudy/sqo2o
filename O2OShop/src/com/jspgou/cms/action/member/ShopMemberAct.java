package com.jspgou.cms.action.member;

import static com.jspgou.cms.Constants.MEMBER_SYS;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.cms.dao.OrderDao;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
 * 会员中心Action
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
 */
@Controller
public class ShopMemberAct {
	private static final Logger log = LoggerFactory.getLogger(ShopMemberAct.class);

	/**
	 * 会员中心页
	 */
	public static final String MEMBER_CENTER = "tpl.memberCenter";
	public static final String MEMBER_PORTRAIT = "tpl.memberPortrait";
	/**
	 * 会员信息修改页
	 */
	public static final String MEMBER_PROFILE = "tpl.memberProfile";
	/**
	 * 会员密码修改页
	 */
	public static final String MEMBER_PASSWORD = "tpl.memberPassword";
	
	
	/**
	 * 会员中心页
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shopMember/index.jspx", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member=MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		BigDecimal money=orderDao.getMemberMoneyByYear(member.getId());
		Integer orders[] = orderDao.getOrderByMember(member.getId());
//		Integer[] products=productMng.getProductByTag(web.getId());
		Integer[] products=productSiteMng.getProductSiteByTag(web.getId());
		model.addAttribute("products", products);
		model.addAttribute("orders", orders);
		model.addAttribute("money", money);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
				MEMBER_CENTER));
	}
	
	/**
	 * 个人资料输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shopMember/profile.jspx", method = RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member=MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		//身份list ShopDictionary
		List<ShopDictionary> userDegreeList=shopDictionaryMng.getListByType((long)1);
		//家庭成员list
		List<ShopDictionary> familyMembersList=shopDictionaryMng.getListByType((long)2);
		//个人收入状况list
		List<ShopDictionary> incomeDescList=shopDictionaryMng.getListByType((long)3);
		//工作年限list
		List<ShopDictionary> workSeniorityList=shopDictionaryMng.getListByType((long)4);
		//教育程度list
		List<ShopDictionary> degreeList=shopDictionaryMng.getListByType((long)5);
		model.addAttribute("member", member);
		
		model.addAttribute("userDegreeList", userDegreeList);
		model.addAttribute("familyMembersList", familyMembersList);
		model.addAttribute("incomeDescList", incomeDescList);
		model.addAttribute("workSeniorityList", workSeniorityList);
		model.addAttribute("degreeList", degreeList);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MEMBER_PROFILE));
	}
	
	/**
	 * 个人资料提交页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/shopMember/profile.jspx", method = RequestMethod.POST)
	public String profileSubmit(ShopMember bean, Long groupId,Long userDegreeId,
			Long degreeId,Long incomeDescId,Long workSeniorityId,Long familyMembersId,
			HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		ShopMember member=MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		bean = shopMemberMng.update(bean,groupId,userDegreeId,degreeId,incomeDescId,workSeniorityId,familyMembersId);
		log.info("ShopMember update infomation: {}", bean.getUsername());
		return index(request,response,model);
	}

	/**
	 * 密码修改输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shopMember/pwd.jspx", method = RequestMethod.GET)
	public String passwordInput(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member=MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		model.addAttribute("email", MemberThread.get().getEmail());
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MEMBER_PASSWORD));
	}

	
	/**
	 * 更换头像
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shopMember/portrait.jspx", method = RequestMethod.GET)
	public String portrait(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member=MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MEMBER_PORTRAIT));
	}
	
	/**
	 * 会员更改图像
	 * 
	 * @param memberId
	 *           会员ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/shopMember/updateAvatar.jspx", method = RequestMethod.POST)//会员更改图像
	public String updateAvatar(String picPaths,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		ShopMember member=MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		member.setAvatar(picPaths);
		shopMemberMng.update(member);
		return "redirect: index.jspx";
	}
	
	
	/**
	 * 密码修改提交页
	 * 
	 * @param origPwd
	 *            原始密码
	 * @param newPwd
	 *            新密码
	 * @param email
	 *            邮箱
	 * @param nextUrl
	 *            下一个页面地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/shopMember/pwd.jspx", method = RequestMethod.POST)
	public String passwordSubmit(String origPwd, String newPwd, String email,
			String nextUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		Website web = SiteUtils.getWeb(request);
		ShopMember member=MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		Long userId = member.getMember().getUser().getId();
		WebErrors errors = validatePassword(userId, email, newPwd, member.getEmail(), origPwd, request);
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		userMng.updateUser(userId, newPwd, email);
		log.info("ShopMember update password: {}", member.getUsername());
		return FrontHelper.showSuccess("global.success", nextUrl, web, model,request);
	}

	/**
	 * 验证密码是否正确
	 * 
	 * @param origPwd
	 *            原密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/shopMember/checkPwd.jspx")
	public void checkPwd(String origPwd, HttpServletRequest request,
			HttpServletResponse response) {
		ShopMember member = MemberThread.get();
		Long userId = member.getMember().getUser().getId();
		boolean pass=userMng.isPasswordValid(userId, origPwd);
		ResponseUtils.renderJson(response, pass ? "true" : "false");
	}
	
	public String getHistoryProductIds(HttpServletRequest request){
		String str = null ;
		Cookie[] cookie = request.getCookies();
		if(cookie!=null){
		int num = cookie.length;
		for (int i = 0; i < num; i++) {
			if (cookie[i].getName().equals("shop_record")) {
				str = cookie[i].getValue();
				break;
			}
		}
		}
		return str;
	}

	private WebErrors validatePassword(Long userId, String email,
			String newPwd, String origEmail, String origPwd,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (!StringUtils.isBlank(newPwd)
				&& errors.ifOutOfLength(newPwd, "password", 3, 32)) {
			return errors;
		}
		if (!userMng.isPasswordValid(userId, origPwd)) {
			errors.addErrorCode("error.passwordInvalid");
		}
		if (errors.ifNotEmail(email, "email", 100)) {
			return errors;
		}
		if (!email.equals(origEmail) && userMng.emailExist(email)) {
			errors.addErrorCode("error.emailExist");
			return errors;
		}
		return errors;

	}

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserMng userMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
}
