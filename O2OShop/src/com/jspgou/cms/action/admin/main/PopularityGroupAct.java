package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.PopularityGroup;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.PopularityGroupMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductTypeMng;

@Controller
public class PopularityGroupAct {
	private static final Logger log = LoggerFactory.getLogger(PopularityGroupAct.class);

	@RequestMapping("/popularityGroup/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = popularityGroupMng.getPage(cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "popularityGroup/list";
	}
	
	// AJAX请求，不返回页面
	@RequestMapping("/popularityGroup/v_search.do")
	public void update(Long typeId,Long brandId,String productName,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		List<Product> list = productMng.getList(typeId, brandId, productName);
		JSONObject json=new JSONObject();
		try {
			for (int i=0 ,j=list.size(); i < j; i++) {
					json.append(list.get(i).getId() + "", list.get(i).getName());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequestMapping("/popularityGroup/v_add.do")
	public String add(ModelMap model) {
		List<ProductType> typeList = productTypeMng.getList(1L);
		List<Brand> brandList = brandMng.getAllList();
		model.addAttribute("brandList", brandList);
		model.addAttribute("typeList", typeList);
		return "popularityGroup/add";
	}

	@RequestMapping("/popularityGroup/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<ProductType> typeList = productTypeMng.getList(1L);
		List<Brand> brandList = brandMng.getAllList();
		model.addAttribute("brandList", brandList);
		model.addAttribute("typeList", typeList);
		model.addAttribute("popularityGroup", popularityGroupMng.findById(id));
		return "popularityGroup/edit";
	}

	@RequestMapping("/popularityGroup/o_save.do")
	public String save(PopularityGroup bean,String rightlist, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = popularityGroupMng.save(bean);
		popularityGroupMng.addProduct(bean,getProductIds(rightlist));
		log.info("save PopularityGroup id={}", bean.getId());
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/popularityGroup/o_update.do")
	public String update(PopularityGroup bean,String rightlist, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = popularityGroupMng.update(bean);
		popularityGroupMng.updateProduct(bean,getProductIds(rightlist));
		log.info("update PopularityGroup id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/popularityGroup/o_delete.do")
	public String delete(Long[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		PopularityGroup[] beans = popularityGroupMng.deleteByIds(ids);
		for (PopularityGroup bean : beans) {
			log.info("delete PopularityGroup id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	public Long[] getProductIds(String rightlist){
		String[] arr;
		arr = rightlist.split(",");
		Long productIds[]  = new Long[arr.length] ; 
		for(int i=0,j=arr.length;i<j;i++){
			if(!arr[i].equals("")){
				productIds[i] = Long.parseLong(arr[i]);
			}
		}
		return productIds;
	}
	
	private WebErrors validateSave(PopularityGroup bean, HttpServletRequest request) {
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
		PopularityGroup entity = popularityGroupMng.findById(id);
		if(errors.ifNotExist(entity, PopularityGroup.class, id)) {
			return true;
		}
		return false;
	}
	
	@Autowired
	private BrandMng brandMng;
	@Autowired
	private ProductTypeMng productTypeMng;
	@Autowired
	private PopularityGroupMng popularityGroupMng;
	@Autowired
	private ProductMng productMng;
}