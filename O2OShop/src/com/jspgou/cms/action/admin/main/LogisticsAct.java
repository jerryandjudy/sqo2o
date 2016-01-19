package com.jspgou.cms.action.admin.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.util.StrUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.manager.LogisticsMng;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class LogisticsAct {
	private static final Logger log = LoggerFactory.getLogger(LogisticsAct.class);

	@RequestMapping("/logistics/v_list.do")
	public String list( HttpServletRequest request,ModelMap model) {
		List<Logistics> list = logisticsMng.getAllList();
		model.addAttribute("list", list);
		return "logistics/list";
	}

	@RequestMapping("/logistics/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		return "logistics/add";
	}

	@RequestMapping("/logistics/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("logistics", logisticsMng.findById(id));
		return "logistics/edit";
	}

	@RequestMapping("/logistics/o_save.do")
	public String save(Logistics bean, String text, Long[] typeIds,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = logisticsMng.save(bean, text);
		log.info("save brand. id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/logistics/o_update.do")
	public String update(Logistics bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = logisticsMng.update(bean, text);
		log.info("update brand. id={}.", bean.getId());
		return list(request, model);
	}

	@RequestMapping("/logistics/o_delete.do")
	public String delete(Long[] ids,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Logistics[] beans = logisticsMng.deleteByIds(ids);
		for (Logistics bean : beans) {
			log.info("delete brand. id={}", bean.getId());
		}
		return list(request, model);
	}

	@RequestMapping("/logistics/o_priority.do")
	public String priority(Long[] wids, Integer[] priority,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		logisticsMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(request, model);
	}
	

	private WebErrors validateSave(Logistics bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	private WebErrors validateUpdate(Logistics bean, HttpServletRequest request) {
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
		Logistics entity = logisticsMng.findById(id);
		return errors.ifNotExist(entity, Logistics.class, id);
	}

	@Autowired
	private LogisticsMng logisticsMng;
}