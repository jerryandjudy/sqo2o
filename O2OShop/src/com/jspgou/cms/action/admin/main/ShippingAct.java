package com.jspgou.cms.action.admin.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.manager.LogisticsMng;
import com.jspgou.cms.manager.ShippingMng;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShippingAct {
	private static final Logger log = LoggerFactory
			.getLogger(ShippingAct.class);

	@RequestMapping("/shipping/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		List<Shipping> list = shippingMng.getList(null, true);
		model.addAttribute("list", list);
		return "shipping/list";
	}

	@RequestMapping("/shipping/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<Logistics> list = logisticsMng.getAllList();
		model.addAttribute("list", list);
		return "shipping/add";
	}

	@RequestMapping("/shipping/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("shipping", shippingMng.findById(id));
		List<Logistics> list = logisticsMng.getAllList();
		model.addAttribute("list", list);
		return "shipping/edit";
	}

	@RequestMapping("/shipping/o_save.do")
	public String save(Shipping bean,Long logisticsId,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(bean.getIsDefault()){
			List<Shipping> list=shippingMng.getList((long)1, true);
			for(int i=0;i<list.size();i++){
				list.get(i).setIsDefault(false);
				shippingMng.update(list.get(i));
			}
		}
		bean.setLogistics(logisticsMng.findById(logisticsId));
		bean = shippingMng.save(bean);
		log.info("save Shipping. id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/shipping/o_update.do")
	public String update(Shipping bean,Long logisticsId,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(bean.getIsDefault()){
			List<Shipping> list=shippingMng.getList((long)1, true);
			for(int i=0;i<list.size();i++){
				list.get(i).setIsDefault(false);
				shippingMng.update(bean);
			}
		}
		bean.setLogistics(logisticsMng.findById(logisticsId));
		bean = shippingMng.update(bean);
		log.info("update Shipping. id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/shipping/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Shipping[] beans = shippingMng.deleteByIds(ids);
		for (Shipping bean : beans) {
			log.info("delete Shipping. id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	@RequestMapping("/shipping/o_priority.do")
	public String priority(Long[] wids, Integer[] priority, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		shippingMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(pageNo, request, model);
	}

	

	private WebErrors validateSave(Shipping bean, HttpServletRequest request) {
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
		Shipping entity = shippingMng.findById(id);
		return errors.ifNotExist(entity, Shipping.class, id);
	}

	@Autowired
	private ShippingMng shippingMng;
	@Autowired
	private LogisticsMng logisticsMng;
}