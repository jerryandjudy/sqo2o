package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Role;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AdminMng;
import com.jspgou.core.manager.RoleMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.manager.ShopAdminMng;
import com.jspgou.cms.web.threadvariable.AdminThread;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShopAdminAct {
	private static final Logger log = LoggerFactory
			.getLogger(ShopAdminAct.class);

	@RequestMapping("/admin/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		ShopAdmin admin = AdminThread.get();
		Pagination pagination =null;
		boolean isSuper=false;
		if(admin.getIsSuper()||admin.getAdmin().getIsSuper()){
			isSuper=true;
		}
		if(isSuper){
			//如果登录用户是超级管理员，显示所有的站点流水分成信息
			pagination = shopAdminMng.getPage(null,cpn(pageNo), CookieUtils.getPageSize(request));
		}else{
			Website website=SiteUtils.getWeb(request);
			pagination = shopAdminMng.getPage(website.getId(),cpn(pageNo), CookieUtils.getPageSize(request));
		}
		
		model.addAttribute(pagination);
		return "admin/list";
	}

	@RequestMapping("/admin/v_add.do")
	public String add(HttpServletRequest request,
			ModelMap model) {
		boolean isSuper=false;
		ShopAdmin admin = AdminThread.get();
		List<Role> roleList =null;
		List<Website> siteList=new ArrayList();
		if(admin.getIsSuper()||admin.getAdmin().getIsSuper()){
			isSuper=true;
		}
		if(isSuper){
			//如果登录用户是超级管理员
			roleList= roleMng.getList();
			siteList = websiteMng.getAllList();
		}else{
			Website website=SiteUtils.getWeb(request);
			siteList.add(website);
			roleList= roleMng.getListByType(2);
		}
		
		
		model.addAttribute("isSuper", isSuper);
		model.addAttribute("siteList", siteList);
		model.addAttribute("roleList", roleList);
		return "admin/add";
	}

	@RequestMapping("/admin/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Integer[] roleIds=shopAdminMng.findById(id).getAdmin().getRoleIds();
		boolean isSuper=false;
		boolean siteAdmin=false;
		Set<Role> adminrole =new HashSet();
		ShopAdmin admin = AdminThread.get();
		List<Role> roleList =null;
		List<Website> siteList=new ArrayList();
		if(admin.getIsSuper()||admin.getAdmin().getIsSuper()){
			isSuper=true;
		}
		if(isSuper){
			//如果登录用户是超级管理员，显示所有的站点流水分成信息
			roleList= roleMng.getList();
			siteList = websiteMng.getAllList();
		}else{
			Website website=SiteUtils.getWeb(request);
			siteList.add(website);
			roleList= roleMng.getListByType(2);
			adminrole=admin.getAdmin().getRoles();
			for(Integer roleId:roleIds){
				if(roleId==2){
					siteAdmin=true;
					break;
				}
			}
		}
		
		
		model.addAttribute("isSuper", isSuper);
		model.addAttribute("roleList", roleList);
		model.addAttribute("adminrole", adminrole);
		model.addAttribute("siteList", siteList);
		model.addAttribute("siteAdmin", siteAdmin);
		model.addAttribute("admin", shopAdminMng.findById(id));
		model.addAttribute("roleIds", roleIds);
		return "admin/edit";
	}

	@RequestMapping("/admin/o_save.do")
	public String save(ShopAdmin bean, String username, String password,Boolean isSuper,Boolean viewonlyAdmin,
			String email, Boolean disabled,Integer[] roleIds,
			HttpServletRequest request, ModelMap model,Long siteId) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website web = SiteUtils.getWeb(request);
		if(siteId==null ||siteId<1){
			siteId=web.getId();
		}
		bean = shopAdminMng.register(username, password,viewonlyAdmin, email, request.getRemoteAddr(), disabled,siteId, bean);
		adminMng.addRole(bean.getAdmin(), roleIds);
		log.info("save ShopAdmin id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/admin/o_update.do")
	public String update(ShopAdmin bean, String password, Boolean viewonlyAdmin,Boolean isSuper,String email,
			Boolean disabled, Integer[] roleIds,Long siteId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(),request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website web = SiteUtils.getWeb(request);
		if(siteId==null ||siteId<1){
			siteId=web.getId();
		}
		bean = shopAdminMng.update(bean, password, disabled, email,viewonlyAdmin,isSuper,siteId);
		adminMng.updateRole(bean.getAdmin(),roleIds);
		log.info("update ShopAdmin id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/admin/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ShopAdmin[] beans = shopAdminMng.deleteByIds(ids);
		for (ShopAdmin bean : beans) {
			log.info("delete ShopAdmin id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	@RequestMapping("/admin/v_check_username.do")
	public String checkUsername(String username, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(username) || userMng.usernameExist(username)) {
			ResponseUtils.renderJson(response, "false");
		} else {
			ResponseUtils.renderJson(response, "true");
		}
		return null;
	}

	@RequestMapping("/admin/v_check_email.do")
	public String checkEmail(String email, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(email) || userMng.emailExist(email)) {
			ResponseUtils.renderJson(response, "false");
		} else {
			ResponseUtils.renderJson(response, "true");
		}
		return null;
	}

	private WebErrors validateSave(ShopAdmin bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	private WebErrors validateUpdate(Long id,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Long id, WebErrors errors) {
		ShopAdmin entity = shopAdminMng.findById(id);
		return errors.ifNotExist(entity, ShopAdmin.class, id);
	}

	private Set<String> handleperms(String[] perms) {
		Set<String> permSet = new HashSet<String>();
		String[] arr;
		for (String perm : perms) {
			arr = perm.split("@");
			for (int i = 0, len = arr.length; i < len; i++) {
				permSet.add(arr[i]);
			}
		}
		return permSet;
	}

	@Autowired
	private ShopAdminMng shopAdminMng;
	@Autowired
	private UserMng userMng;
	@Autowired
	protected RoleMng roleMng;
	@Autowired
	protected AdminMng adminMng;
	@Autowired
	protected WebsiteMng websiteMng;
}