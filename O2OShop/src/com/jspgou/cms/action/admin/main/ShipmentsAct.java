package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

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
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.Shipments;
import com.jspgou.cms.manager.ShipmentsMng;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShipmentsAct {
	private static final Logger log = LoggerFactory.getLogger(ShipmentsAct.class);

	@RequestMapping("/Shipments/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = shipmentsMng.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "Shipments/list";
	}

	@RequestMapping("/Shipments/v_add.do")
	public String add(ModelMap model) {
		return "Shipments/add";
	}

	@RequestMapping("/Shipments/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("order",shipmentsMng.findById(id).getIndent());
		model.addAttribute("shipments", shipmentsMng.findById(id));
		return "Shipments/edit";
	}

	@RequestMapping("/Shipments/o_save.do")
	public String save(Shipments bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shipmentsMng.save(bean);
		log.info("save Shipments id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/Shipments/o_update.do")
	public String update(Shipments bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shipmentsMng.update(bean);
		log.info("update Shipments id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/Shipments/o_delete.do")
	public String delete(Long[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Shipments[] beans = shipmentsMng.deleteByIds(ids);
		for (Shipments bean : beans) {
			log.info("delete Shipments id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(Shipments bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
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
		Shipments entity = shipmentsMng.findById(id);
		if(errors.ifNotExist(entity, Shipments.class, id)) {
			return true;
		}
		return false;
	}
	
	@Autowired
	private ShipmentsMng shipmentsMng;
}