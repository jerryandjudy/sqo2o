package com.jspgou.cms.action.admin.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.util.StrUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.common.util.CnToSpell;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class BrandAct {
	private static final Logger log = LoggerFactory.getLogger(BrandAct.class);

	@RequestMapping("/brand/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		
		List<Brand> list = brandMng.getAllList();
		model.addAttribute("list", list);
		return "brand/list";
	}

	@RequestMapping("/brand/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		return "brand/add";
	}

	@RequestMapping("/brand/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("brand", brandMng.findById(id));
		return "brand/edit";
	}

	@RequestMapping("/brand/o_save.do")
	public String save(Brand bean, String text, 
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		//获取品牌名称的首字母
		String name=bean.getName();
		CnToSpell cts = new CnToSpell();
	    bean.setFirstCharacter(cts.getBeginCharacter(name).substring(0, 1));
		bean = brandMng.save(bean, text);
		log.info("save brand. id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/brand/o_update.do")
	public String update(Brand bean, String text,
			Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		//获取品牌名称的首字母
		String name=bean.getName();
		CnToSpell cts = new CnToSpell();
	    bean.setFirstCharacter(cts.getBeginCharacter(name).substring(0, 1));
		bean = brandMng.update(bean, text);
		log.info("update brand. id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/brand/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Brand[] beans = brandMng.deleteByIds(ids);
		for (Brand bean : beans) {
			log.info("delete brand. id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	@RequestMapping("/brand/o_priority.do")
	public String priority(Long[] wids, Integer[] priority, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		brandMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(pageNo, request, model);
	}
	
	@RequestMapping(value = "/brand/v_check_brandName.do")
	public void checkUsername(String name, HttpServletResponse response) {
		String pass;
		if (StringUtils.isBlank(name)) {
			pass = "false";
		} else {
			pass = brandMng.brandNameNotExist(name) ? "true" : "false";
		}
		ResponseUtils.renderJson(response, pass);
	}

	private WebErrors validateSave(Brand bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));
		bean.setWebsite(SiteUtils.getWeb(request));
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	private WebErrors validateUpdate(Brand bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();
		bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));
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
		Brand entity = brandMng.findById(id);
		return errors.ifNotExist(entity, Brand.class, id);
	}


	@Autowired
	private BrandMng brandMng;
}