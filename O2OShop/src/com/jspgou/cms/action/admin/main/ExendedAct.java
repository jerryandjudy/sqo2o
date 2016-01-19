package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Exended;
import com.jspgou.cms.entity.ExendedItem;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.cms.manager.ExendedItemMng;
import com.jspgou.cms.manager.ExendedMng;
import com.jspgou.cms.manager.ProductTypeMng;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ExendedAct {
	private static final Logger log = LoggerFactory.getLogger(ExendedAct.class);

	@RequestMapping("/exended/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = exendedMng.getPage(cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "exended/list";
	}

	@RequestMapping("/exended/v_add.do")
	public String add(HttpServletRequest request,ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List<ProductType> ptList = productTypeMng.getList();
		model.addAttribute("ptList", ptList);
		return "exended/add";
	}

	@RequestMapping("/exended/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Long[] typeIds=exendedMng.findById(id).getProductTypeIds();
		List<ExendedItem> list = new ArrayList(); 
		list.addAll(exendedMng.findById(id).getItems());
		List<ProductType> ptList = productTypeMng.getList();
		model.addAttribute("ptList", ptList);
		model.addAttribute("list",list);
		model.addAttribute("typeIds",typeIds);
		model.addAttribute("exended", exendedMng.findById(id));
		return "exended/edit";
	}
	
	@RequestMapping("/exended/o_save.do")
	public String save(Exended bean,Long[] typeIds,String[] itemName, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = exendedMng.save(bean,typeIds);
		addExendedItems(bean,itemName);
		log.info("save exended id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/exended/o_update.do")
	public String update(Exended bean,Long[] typeIds,Long[] itemId,String[] itemName, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = exendedMng.update(bean,typeIds);
		updateExendedItems(bean,itemId,itemName);
		log.info("update Exended id={}.", bean.getId());
		return list(pageNo, request, model);
	}
	
	@RequestMapping("/exended/o_priority.do")
	public String priority(Integer pageNo, Long[] wids, Integer[] priority,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		exendedMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(pageNo, request, model);
	}

	@RequestMapping("/exended/o_delete.do")
	public String delete(Long[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Exended[] beans = exendedMng.deleteByIds(ids);
		for (Exended bean : beans) {
			log.info("delete Exended id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	
	private void addExendedItems(Exended bean,String[] itemName) {
		ExendedItem item;
		if(itemName!=null){
			for (int i = 0, len = itemName.length; i < len; i++) {
				if (!StringUtils.isBlank(itemName[i])) {
					item = new ExendedItem();
					item.setName(itemName[i]);
					item.setExended(bean);
					exendedItemMng.save(item);
				}
			}
		}
	}
	
	
	private void updateExendedItems(Exended bean,Long[] itemId,String[] itemName) {
		java.util.Set<ExendedItem> set = bean.getItems();
		if(itemId!=null){
			for(ExendedItem e:set){
				if(!Arrays.asList(itemId).contains(e.getId())){
					exendedItemMng.deleteById(e.getId());
				}
			}
		}else{
			for(ExendedItem e:set){
				exendedItemMng.deleteById(e.getId());
			}
		}
		ExendedItem item;
		if(itemName!=null){
			for (int i = 0, len = itemName.length; i < len; i++) {
				if (!StringUtils.isBlank(itemName[i])) {
					if(itemId!=null&&i<itemId.length){
						item = exendedItemMng.findById(itemId[i]);
						item.setName(itemName[i]);
						item.setExended(bean);
						exendedItemMng.update(item);
					}else{
						item = new ExendedItem();
						item.setName(itemName[i]);
						item.setExended(bean);
						exendedItemMng.save(item);
					}
				}
			}
		}
	}
	
	/**
	 * 从集合中提取ID数组
	 * 
	 * @param groups
	 * @return
	 */
	public static Long[] fetchProductTypeIds(Collection<ProductType> productTypes) {
		Long[] ids = new Long[productTypes.size()];
		int i = 0;
		for (ProductType sdt : productTypes) {
			ids[i++] = sdt.getId();
		}
		return ids;
	}
	
	
	private WebErrors validateSave(Exended bean, HttpServletRequest request) {
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
		Exended entity = exendedMng.findById(id);
		if(errors.ifNotExist(entity, Exended.class, id)) {
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
	private ExendedMng exendedMng;
	@Autowired
	private ProductTypeMng productTypeMng;
	@Autowired
	private ExendedItemMng exendedItemMng;
}