package com.jspgou.cms.action.admin.main;

import java.util.Date;
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

import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.ShopOrderTypeMng;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShopOrderTypeAct {
	private static final Logger log = LoggerFactory.getLogger(ShopOrderTypeAct.class);

	@RequestMapping("/ordertype/order_time_out.do")
	public String list( HttpServletRequest request,ModelMap model) {
		List<ShopOrderType> list = shopOrderTypeMng.getAllList();
		model.addAttribute("list", list);
		return "ordertype/list";
	}

	@RequestMapping("/ordertype/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		Website website=SiteUtils.getWeb(request);
		List<Website> allsite = websiteMng.getAllList();
		model.put("allsite", allsite);
		
		return "ordertype/add";
	}
	
	@RequestMapping("/ordertype/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<Website> allsite = websiteMng.getAllList();
		model.put("allsite", allsite);
		model.addAttribute("shopOrderType", shopOrderTypeMng.findById(id));
		return "ordertype/edit";
	}
	
	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(Long id, WebErrors errors) {
		ShopOrderType entity = shopOrderTypeMng.findById(id);
		return errors.ifNotExist(entity, ShopOrderType.class, id);
	}

	
	@RequestMapping("/ordertype/o_save.do")
	public String save(ShopOrderType bean,Long siteId, String text, Long[] typeIds,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website website=SiteUtils.getWeb(request);
		User user = SiteUtils.getUser(request);
		
		bean.setUser(user);
		bean.setCreateTime(new Date());
		bean = shopOrderTypeMng.save(bean);
		log.info("save brand. id={}", bean.getId());
		
		
		
		
		String content="";
		//ProductSite oldbean=null;
		//oldbean=siteshopOrderTypeMng.findById(bean.getId());
		content="Id="+bean.getId()+";typeName="+bean.getTypeName()+"";
		cmsLogMng.operating(request, "shopOrderType.log.save", content);
		return "redirect:order_time_out.do";
	}
	private WebErrors validateSave(ShopOrderType bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		
		return errors;
	}
	
	
	
	@RequestMapping("/ordertype/o_update.do")
	public String update(ShopOrderType bean,Long siteId, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		ShopOrderType oldbean=null;
		oldbean=shopOrderTypeMng.findById(bean.getId());
		content="Id="+bean.getId()+";TypeCode="+oldbean.getTypeCode()+" -> "+bean.getTypeCode()+";TimeOut="+oldbean.getTimeOut()+" -> "+
				bean.getTimeOut()+"";
		cmsLogMng.operating(request, "shopOrderType.log.update", content);
		
		
		
		
		bean = shopOrderTypeMng.update(bean);
		log.info("update brand. id={}.", bean.getId());
		
		
		
		return list(request, model);
	}
	private WebErrors validateUpdate(ShopOrderType bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	@RequestMapping("/ordertype/o_delete.do")
	public String delete(Long[] ids,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ShopOrderType[] beans = shopOrderTypeMng.deleteByIds(ids);
		for (ShopOrderType bean : beans) {
			log.info("delete brand. id={}", bean.getId());
		}
		return list(request, model);
	}
	
	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}
	
	@RequestMapping("/ordertype/o_priority.do")
	public String priority(Long[] wids, Integer[] priority,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		shopOrderTypeMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(request, model);
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
	
	@RequestMapping("/ordertype/v_checkTypeCode.do")
	public void checkTypeCodeJson(String typeCode, HttpServletResponse response) {
		String pass;
	
			if (StringUtils.isBlank(typeCode)) {
				pass = "false";
			} else {
				ShopOrderType sot=shopOrderTypeMng.findByTypeCode(typeCode);
				if(sot==null){
					pass= "true";
				}else{
					pass= "false";
					
				}
			}
		ResponseUtils.renderJson(response, pass);
	}
	
	
	
	@Autowired
	private ShopOrderTypeMng shopOrderTypeMng;
	
	@Autowired
	private WebsiteMng websiteMng;
	
	@Autowired
	private CmsLogMng cmsLogMng;
	
}