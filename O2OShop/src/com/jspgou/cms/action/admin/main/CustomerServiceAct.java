package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.CustomerService;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.manager.CustomerServiceMng;
import com.jspgou.cms.web.AdminContextInterceptor;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class CustomerServiceAct {
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceAct.class);
	
	@RequestMapping("/customerService/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website site = SiteUtils.getWeb(request);
		List siteList=websiteMng.getAllList();
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		model.addAttribute("isSuper", isSuper);
		model.addAttribute("siteList", siteList);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("site", site);
		Pagination pagination = customerServiceMng.getPagination(null,cpn(pageNo), CookieUtils
				.getPageSize(request),site.getId());
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		return "customerService/list";
	}
	
	@RequestMapping("/customerService/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		return "customerService/add";
	}
	
	@RequestMapping("/customerService/v_edit.do")
	public String edit(Long id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("customerService", customerServiceMng.findById(id));
		model.addAttribute("pageNo", pageNo);
		return "customerService/edit";
	}

	@RequestMapping("/customerService/o_save.do")
	public String save(CustomerService bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website site = SiteUtils.getWeb(request);
		bean.setWebsite(site);
		bean=customerServiceMng.save(bean);
		log.info("save CustomerService id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/customerService/o_update.do")
	public String qqUpdate(CustomerService bean,Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean=customerServiceMng.update(bean);
		log.info("update CustomerService id={}.", bean.getId());
		return list(pageNo,request, model);
	}
	
	@RequestMapping("/customerService/o_delete.do")
	public String qqDelete(Long[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CustomerService[] beans=customerServiceMng.deleteByIds(ids);
		for (CustomerService bean : beans) {
			log.info("delete CustomerService id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}
	
	@RequestMapping("/customerService/o_priority.do")
	public String priority(Long[] wids, Integer[] priority, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		customerServiceMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(CustomerService bean, HttpServletRequest request) {
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
		CustomerService entity = customerServiceMng.findById(id);
		if(errors.ifNotExist(entity, CustomerService.class, id)) {
			return true;
		}
		return false;
	}
	
	@Autowired
	private CustomerServiceMng customerServiceMng;
	@Autowired
	private WebsiteMng websiteMng;
}