package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberGroup;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.manager.ShopMemberAddressMng;
import com.jspgou.cms.manager.ShopMemberGroupMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShopMemberAct {
	private static final Logger log = LoggerFactory
			.getLogger(ShopMemberAct.class);

	@RequestMapping("/member/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = shopMemberMng.getPage(null,
				cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		return "member/list";
	}

	@RequestMapping("/member/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List<ShopMemberGroup> groupList = shopMemberGroupMng.getList(SiteUtils
				.getWebId(request));
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
		
		model.addAttribute("groupList", groupList);
		model.addAttribute("userDegreeList", userDegreeList);
		model.addAttribute("familyMembersList", familyMembersList);
		model.addAttribute("incomeDescList", incomeDescList);
		model.addAttribute("workSeniorityList", workSeniorityList);
		model.addAttribute("degreeList", degreeList);
		return "member/add";
	}
	
	@RequestMapping("/member/o_save.do")
	public String save(ShopMember bean, Long groupId,Long userDegreeId,
			Long degreeId,Long incomeDescId,Long workSeniorityId,Long familyMembersId,
			String username, String password, String email, Boolean disabled,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, username,  email, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		//后台注册会员，激活码不需要
		bean = shopMemberMng.register(username, password, email,true,null, request
				.getRemoteAddr(), disabled, SiteUtils.getWebId(request), 
				groupId,userDegreeId,degreeId,incomeDescId,workSeniorityId,
				familyMembersId,bean);
		log.info("save ShopMember, id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/member/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<ShopMemberGroup> groupList = shopMemberGroupMng.getList(null);
		List addrList=shopMemberAddressMng.findByMemberId(id);
		//身份list ShopDictionary
//		List<ShopDictionary> userDegreeList=shopDictionaryMng.getListByType((long)1);
//		//家庭成员list
//		List<ShopDictionary> familyMembersList=shopDictionaryMng.getListByType((long)2);
//		//个人收入状况list
//		List<ShopDictionary> incomeDescList=shopDictionaryMng.getListByType((long)3);
//		//工作年限list
//		List<ShopDictionary> workSeniorityList=shopDictionaryMng.getListByType((long)4);
//		//教育程度list
//		List<ShopDictionary> degreeList=shopDictionaryMng.getListByType((long)5);
//		
		ShopMember member = shopMemberMng.findById(id);
		model.addAttribute("groupList", groupList);
		model.addAttribute("member", member);
		model.addAttribute("groupList", groupList);
		model.addAttribute("addrList", addrList);
//		model.addAttribute("userDegreeList", userDegreeList);
//		model.addAttribute("familyMembersList", familyMembersList);
//		model.addAttribute("incomeDescList", incomeDescList);
//		model.addAttribute("workSeniorityList", workSeniorityList);
//		model.addAttribute("degreeList", degreeList);
		return "member/edit";
	}

	@RequestMapping("/member/o_update.do")
	public String update(ShopMember bean,  Long groupId,Long userDegreeId,
			Long degreeId,Long incomeDescId,Long workSeniorityId,Long familyMembersId,
			String password, String email, Boolean disabled, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), email,request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shopMemberMng.update(bean,groupId,userDegreeId,
				degreeId,incomeDescId,workSeniorityId,familyMembersId,
				password, email, disabled);
		log.info("update ShopMember, id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/member/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ShopMember[] beans = shopMemberMng.deleteByIds(ids);
		for (ShopMember bean : beans) {
			log.info("delete ShopMember, id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(ShopMember bean,
			String username, String email,HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		bean.setWebsite(web);
		if (vldUsername(username, errors)) {
			return errors;
		}
		if (vldEmail(email, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldExist(id, web.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Long id, String email,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldEmailUpdate(id, email, errors)) {
			return errors;
		}
		if (vldExist(id, web.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, web.getId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Long id, Long webId, WebErrors errors) {
		if (errors.hasErrors()) {
			return true;
		}
		if (errors.ifNull(id, "id")) {
			return true;
		}
		ShopMember entity = shopMemberMng.findById(id);
		if (errors.ifNotExist(entity, ShopMember.class, id)) {
			return true;
		}
//		if (!entity.getWebsite().getId().equals(webId)) {
//			errors.notInWeb(ShopMember.class, id);
//			return true;
//		}
		return false;
	}

	private boolean vldEmailUpdate(Long id, String email, WebErrors errors) {
		if (!StringUtils.isBlank(email)) {
			ShopMember member = shopMemberMng.findById(id);
			if (!member.getEmail().equals(email)) {
				if (vldEmail(email, errors)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean vldEmail(String email, WebErrors errors) {
		if (errors.ifNotEmail(email, "email", 100)) {
			return true;
		}
		if (userMng.emailExist(email)) {
			errors.addErrorCode("error.emailExist");
			return true;
		}
		return false;
	}

	private boolean vldUsername(String username, WebErrors errors) {
		if (errors.ifNotUsername(username, "username", 3, 100)) {
			return true;
		}
		if (userMng.usernameExist(username)) {
			errors.addErrorCode("error.usernameExist");
			return true;
		}
		return false;
	}

	@Autowired
	private ShopMemberGroupMng shopMemberGroupMng;
	@Autowired
	private ShopMemberAddressMng shopMemberAddressMng;
	@Autowired
	private UserMng userMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
}