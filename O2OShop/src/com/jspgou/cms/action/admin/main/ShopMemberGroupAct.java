package com.jspgou.cms.action.admin.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.ShopMemberGroup;
import com.jspgou.cms.manager.ShopMemberGroupMng;
import com.jspgou.cms.web.SiteUtils;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShopMemberGroupAct {
	private static final Logger log = LoggerFactory
			.getLogger(ShopMemberGroupAct.class);

	@RequestMapping("/group/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		List<ShopMemberGroup> list = shopMemberGroupMng.getList(null);
		model.addAttribute("list", list);
		return "group/list";
	}

	@RequestMapping("/group/v_add.do")
	public String add(ModelMap model) {
		return "group/add";
	}

	@RequestMapping("/group/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("group", shopMemberGroupMng.findById(id));
		return "group/edit";
	}

	@RequestMapping("/group/o_save.do")
	public String save(ShopMemberGroup bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shopMemberGroupMng.save(bean);
		log.info("save ShopMemberGroup. id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/group/o_update.do")
	public String update(ShopMemberGroup bean, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shopMemberGroupMng.update(bean);
		log.info("update ShopMemberGroup. id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/group/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ShopMemberGroup[] beans = shopMemberGroupMng.deleteByIds(ids);
		for (ShopMemberGroup bean : beans) {
			log.info("delete ShopMemberGroup. id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(ShopMemberGroup bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		bean.setWebsite(SiteUtils.getWeb(request));
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	private WebErrors validateUpdate(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
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

	private void vldExist(Long id, WebErrors errors) {
		if (errors.hasErrors()) {
			return;
		}
		ShopMemberGroup entity = shopMemberGroupMng.findById(id);
		errors.ifNotExist(entity, ShopMemberGroup.class, id);
	}

	@Autowired
	private ShopMemberGroupMng shopMemberGroupMng;
}