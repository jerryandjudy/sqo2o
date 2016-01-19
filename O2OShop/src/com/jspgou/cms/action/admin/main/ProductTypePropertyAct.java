package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.web.Constants.MESSAGE;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.entity.ProductTypeProperty;
import com.jspgou.cms.manager.ProductTypeMng;
import com.jspgou.cms.manager.ProductTypePropertyMng;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ProductTypePropertyAct {
	private static final Logger log = LoggerFactory.getLogger(ProductTypePropertyAct.class);
	
	@RequestMapping("/typeProperty/v_list.do")
	public String list(Long typeId,Boolean isCategory,
			HttpServletRequest request,ModelMap model) {
		ProductType pType = productTypeMng.findById(typeId);
		List<ProductTypeProperty> list=productTypePropertyMng.getList(typeId, isCategory);
		model.addAttribute("list", list);
		model.addAttribute("typeId", typeId);
		model.addAttribute("isCategory", isCategory);
		model.addAttribute("fieldList", getFieldList(list));
		model.addAttribute("pType", pType);
		if (isCategory) {
			return "typeProperty/list_category";
		} else {
			return "typeProperty/list_product";
		}
	}
	
	@RequestMapping("/typeProperty/v_add.do")
	public String add(Long typeId,Boolean isCategory,
			HttpServletRequest request,ModelMap model) {
		ProductTypeProperty property = productTypePropertyMng.findById(typeId);
		model.addAttribute("property", property);
		model.addAttribute("typeId", typeId);
		model.addAttribute("isCategory", isCategory);
		return "typeProperty/add";
	}
	
	@RequestMapping("/typeProperty/v_edit.do")
	public String delete(Long id,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		ProductTypeProperty property = productTypePropertyMng.findById(id);
		model.addAttribute("property", property);
		model.addAttribute("typeId", property.getPropertyType().getId());
		model.addAttribute("isCategory", property.getCategory());
		return "typeProperty/edit";
	}
	
	@RequestMapping("/typeProperty/o_save_list.do")
	public String saveList(Long typeId, Boolean isCategory, String[] fields,
			String[] propertyNames, Integer[] dataTypes, Integer[] sorts,
			Boolean[] singles,  HttpServletRequest request,
			ModelMap model) {
		ProductType pType = productTypeMng.findById(typeId);
		List<ProductTypeProperty> itemList = getItems(pType, isCategory, fields, propertyNames,
				dataTypes, sorts, singles);
		productTypePropertyMng.saveList(itemList);
		log.info("save CmsModelItem count={}", itemList.size());
		model.addAttribute("typeId", typeId);
		model.addAttribute("isCategory", isCategory);
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/typeProperty/o_save.do")
	public String save(ProductTypeProperty bean,Long typeId,
			HttpServletRequest request,ModelMap model){
		ProductType type = new ProductType();
		type.setId(typeId);
		bean.setPropertyType(type);
		bean.setSingle(true);
		bean.setCustom(true);
		productTypePropertyMng.save(bean);
		model.addAttribute("typeId", typeId);
		model.addAttribute("isCategory", bean.getCategory());
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/typeProperty/o_update.do")
	public String update(ProductTypeProperty bean,Long typeId,boolean category,
			HttpServletRequest request,ModelMap model){
		productTypePropertyMng.update(bean);
		model.addAttribute("typeId", typeId);
		model.addAttribute("isCategory", category);
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/typeProperty/o_priority.do")
	public String priority(Long[] wids, Integer[] sort, String[] propertyName,
			Boolean[] single, Long typeId,Boolean isCategory,
			HttpServletRequest request, ModelMap model) {
		if (wids != null && wids.length > 0) {
			productTypePropertyMng.updatePriority(wids, sort, propertyName, single);
		}
		model.addAttribute(MESSAGE, "global.success");
		return list(typeId, isCategory, request, model);
	}
	
	@RequestMapping("/typeProperty/o_delete.do")
	public String delete(Long[] ids, Integer typeId, Boolean isCategory,
			HttpServletRequest request, ModelMap model) {
		ProductTypeProperty[] beans = productTypePropertyMng.deleteByIds(ids);
		for (ProductTypeProperty bean : beans) {
			log.info("delete CmsModelItem id={}", bean.getId());
		}
		model.addAttribute("typeId", typeId);
		model.addAttribute("isCategory", isCategory);
		return "redirect:v_list.do";
	}
	
	private List<String> getFieldList(List<ProductTypeProperty> items) {
		List<String> list = new ArrayList<String>(items.size());
		for (ProductTypeProperty item : items) {
			list.add(item.getField());
		}
		return list;
	}
	
	private List<ProductTypeProperty> getItems(ProductType model, boolean isCategory,
			String[] fields, String[] propertyNames, Integer[] dataTypes,
			Integer[] sorts, Boolean[] singles) {
		List<ProductTypeProperty> list = new ArrayList<ProductTypeProperty>();
		ProductTypeProperty item;
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!StringUtils.isBlank(fields[i])) {
				item = new ProductTypeProperty();
				item.setCustom(false);
				item.setPropertyType(model);
				item.setCategory(isCategory);

				item.setField(fields[i]);
				item.setPropertyName(propertyNames[i]);
				item.setSort(sorts[i]);
				item.setDataType(dataTypes[i]);
				item.setSingle(singles[i]);
				list.add(item);
			}
		}
		return list;
	}
	@Autowired
	private ProductTypePropertyMng productTypePropertyMng;
	@Autowired
	private ProductTypeMng productTypeMng;
}