package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.Address;
import com.jspgou.cms.manager.AddressMng;
/**
* This class should preserve.
* @preserve private
*/
@Controller
public class AddressAct {
	private static final Logger log = LoggerFactory.getLogger(AddressAct.class);

	@RequestMapping("/address/v_list.do")
	public String list(Long parentId,Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = addressMng.getPageByParentId(parentId,cpn(pageNo), CookieUtils
				.getPageSize(request));
		Website web = SiteUtils.getWeb(request);
		model.addAttribute("pagination",pagination);
		model.addAttribute("parentId",parentId);
		model.addAttribute("website",web);
		return "address/list";
	}

	@RequestMapping("/address/v_add.do")
	public String add(Long parentId,ModelMap model) {
		model.addAttribute("parentId",parentId);
		if(parentId==null){
			List<Website> allsite = websiteMng.getAllList();
			model.put("allsite", allsite);
	    }
		return "address/add";
	}

	@RequestMapping("/address/v_edit.do")
	public String edit(Long id, Long parentId,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(parentId==null){
		List<Website> allsite = websiteMng.getAllList();
		model.put("allsite", allsite);
		}
		model.addAttribute("address", addressMng.findById(id));
		model.addAttribute("parentId",parentId);
		return "address/edit";
	}

	@RequestMapping("/address/o_save.do")
	public String save(Long parentId,Address bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(parentId!=null){
			Address address=addressMng.findById(parentId);
			bean.setParent(address);
			bean.setWebsite(address.getWebsite());
		}else{
			bean.setWebsite(websiteMng.findById(bean.getWebsite().getId()));	
		}
		bean = addressMng.save(bean);
		log.info("save Address id={}", bean.getId());
		model.addAttribute("parentId",parentId);
		return "redirect:v_list.do";
	}

	@RequestMapping("/address/o_update.do")
	public String update(Long parentId,Address bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(parentId==null){
			bean.setWebsite(websiteMng.findById(bean.getWebsite().getId()));	
		}else{
			Address address=addressMng.findById(parentId);
			bean.setWebsite(address.getWebsite());
		}
		bean = addressMng.update(bean);
		log.info("update Address id={}.", bean.getId());
		return list(parentId,pageNo, request, model);
	}

	@RequestMapping("/address/o_delete.do")
	public String delete(Long[] ids,Long parentId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Address[] beans = addressMng.deleteByIds(ids);
		for (Address bean : beans) {
			log.info("delete Address id={}", bean.getId());
		}
		return list(parentId,pageNo, request, model);
	}
	
	@RequestMapping("/address/o_priority.do")
	public String priority(Long[] wids,Long parentId, Integer[] priority, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		addressMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(parentId,pageNo, request, model);
	}
	
	private WebErrors validatePriority(Long[] wids, Integer[] priority,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(wids, "wids")) {
			return errors;
		}
		if (errors.ifEmpty(priority, "priority")) {
			return errors;
		}
		if (wids.length != priority.length) {
			errors.addErrorString("wids length not equals priority length");
			return errors;
		}
		for (int i = 0, len = wids.length; i < len; i++) {
			vldExist(wids[i], errors);
			if (priority[i] == null) {
				priority[i] = 0;
			}
		}
		return errors;
	}
	
	private boolean vldExist(Long id, WebErrors errors) {
		Address entity = addressMng.findById(id);
		return errors.ifNotExist(entity, Address.class, id);
	}

	private WebErrors validateSave(Address bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
//		Website web = SiteUtils.getWeb(request);
//		bean.setWebsite(web);
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

	private WebErrors validateUpdate(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
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
		if (errors.ifNull(id, "id")) {
			return true;
		}
		Address entity = addressMng.findById(id);
		if(errors.ifNotExist(entity, Address.class, id)) {
			return true;
		}
//		if (!entity.getWebsite().getId().equals(webId)) {
//			errors.notInWeb(Address.class, id);
//			return true;
//		}
		return false;
	}
	
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private WebsiteMng websiteMng;
}