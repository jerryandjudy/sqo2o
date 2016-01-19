package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.Arrays;
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

import com.jspgou.cms.entity.Standard;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.cms.manager.StandardMng;
import com.jspgou.cms.manager.StandardTypeMng;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class StandardTypeAct {
	private static final Logger log = LoggerFactory.getLogger(StandardTypeAct.class);

	@RequestMapping("/standardType/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = standardTypeMng.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "standardType/list";
	}

	@RequestMapping("/standardType/v_add.do")
	public String add(HttpServletRequest request,ModelMap model) {
		return "standardType/add";
	}

	@RequestMapping("/standardType/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		StandardType bean=standardTypeMng.findById(id);
		List<Standard> standards= standardMng.findByTypeId(id);
		model.addAttribute("standardType", bean);
		model.addAttribute("standards", standards);
		return "standardType/edit";
	}

	@RequestMapping("/standardType/o_save.do")
	public String save(StandardType bean,String[] itemName,
			String[] itemColor, Integer[] itemPriority,
			HttpServletRequest request,ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = standardTypeMng.save(bean);
		addStandard(bean,itemName, itemColor,itemPriority);
		log.info("save StandardType id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/standardType/o_update.do")
	public String update(StandardType bean,Long[] itemId,String[] itemName,
			String[] itemColor, Integer[] itemPriority, Integer pageNo, 
			HttpServletRequest request,ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = standardTypeMng.update(bean);
	
		updateStandard(bean,itemId,itemName, itemColor,itemPriority);
		log.info("update StandardType id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/standardType/o_priority.do")
	public String priority(Integer pageNo, Long[] wids, Integer[] priority,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		standardTypeMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(pageNo, request, model);
	}
	
	
	
	@RequestMapping("/standardType/o_delete.do")
	public String delete(Long[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		StandardType[] beans = standardTypeMng.deleteByIds(ids);
		for (StandardType bean : beans) {
			log.info("delete StandardType id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}
	
	private void addStandard(StandardType bean,String[] itemName,String[] itemColor, Integer[] itemPriority) {
		Standard item;
		if(itemName!=null){
			for (int i = 0, len = itemName.length; i < len; i++) {
				if (!StringUtils.isBlank(itemName[i])) {
					item = new Standard();
					item.setName(itemName[i]);
					item.setColor(itemColor[i]);
					item.setPriority(itemPriority[i]);
					item.setType(bean);
					standardMng.save(item);
				}
			}
		}
	}
	
	@RequestMapping("/standardType/v_check_field.do")
	public String checkUsername(String field, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(field)||standardTypeMng.getByField(field)!=null) {
			ResponseUtils.renderJson(response, "false");
		} else {
			ResponseUtils.renderJson(response, "true");
		}
		return null;
	}
	
	private void updateStandard(StandardType bean,Long[] itemId,String[] itemName,String[] itemColor, Integer[] itemPriority) {
		java.util.Set<Standard> set = bean.getStandardSet();
		if(itemId!=null){
			for(Standard s:set){
				if(!Arrays.asList(itemId).contains(s.getId())){
					standardMng.deleteById(s.getId());
				}
			}
		}else{
			for(Standard s:set){
				standardMng.deleteById(s.getId());
			}
		}
		Standard item;
		if(itemName!=null){
			for (int i = 0, len = itemName.length; i < len; i++) {
				if (!StringUtils.isBlank(itemName[i])) {
					if(itemId!=null&&i<itemId.length){
						item = standardMng.findById(itemId[i]);
						item.setName(itemName[i]);
						item.setColor(itemColor[i]);
						item.setPriority(itemPriority[i]);
						item.setType(bean);
						standardMng.update(item);
					}else{
						item = new Standard();
						item.setName(itemName[i]);
						item.setColor(itemColor[i]);
						item.setPriority(itemPriority[i]);
						item.setType(bean);
						standardMng.save(item);
					}
				}
			}
		}
	}

	private WebErrors validateSave(StandardType bean, HttpServletRequest request) {
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
		StandardType entity = standardTypeMng.findById(id);
		if(errors.ifNotExist(entity, StandardType.class, id)) {
			return true;
		}
		return false;
	}
 
	private WebErrors validatePriority(Long[] wids, Integer[] priority,
			HttpServletRequest request) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(wids, "ids")) {
			return errors;
		}
		if (errors.ifEmpty(priority, "priority")) {
			return errors;
		}
		if (wids.length != priority.length) {
			errors.addErrorString("ids length not equals priority length");
			return errors;
		}
		for (int i = 0, len = wids.length; i < len; i++) {
			if (vldExist(wids[i], web.getId(), errors)) {
				return errors;
			}
			if (priority[i] == null) {
				priority[i] = 0;
			}
		}
		return errors;
	}
	
	
	
	
	@Autowired
	private StandardTypeMng  standardTypeMng;
	@Autowired
	private StandardMng standardMng;
}