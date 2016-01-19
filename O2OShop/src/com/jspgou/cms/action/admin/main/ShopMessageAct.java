package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.ShopMessage;
import com.jspgou.cms.manager.ShopMessageMng;
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
public class ShopMessageAct {
	private static final Logger log = LoggerFactory.getLogger(ShopMessageAct.class);

	@RequestMapping("/shopMessage/v_list.do")
	public String list(Integer pageNo, 
			HttpServletRequest request, ModelMap model) {
		
		Pagination pagination = shopMessageMng.getPage(
				cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pageNo);
		return "shopMessage/list";
	}

	@RequestMapping("/shopMessage/v_add.do")
	public String add(ModelMap model) {
		return "shopMessage/add";
	}

	@RequestMapping("/shopMessage/v_edit.do")
	public String edit(Long id,Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ShopMessage shopMessage = shopMessageMng.findById(id);
		model.addAttribute("shopMessage", shopMessage);
		model.addAttribute("pageNo", pageNo);
		return "shopMessage/edit";
	}

	@RequestMapping("/shopMessage/o_save.do")
	public String save(ShopMessage bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		log.info("save shopMessage id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/shopMessage/o_update.do")
	public String update(ShopMessage bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shopMessageMng.update(bean);
		log.info("update Discuss id={}.", bean.getId());
		return list(pageNo,request, model);
	}

	@RequestMapping("/shopMessage/o_delete.do")
	public String delete(Long id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		shopMessageMng.deleteById(id);
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(ShopMessage bean, HttpServletRequest request) {
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

	private WebErrors validateDelete(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		
		vldExist(id, web.getId(), errors);
		
		return errors;
	}

	private boolean vldExist(Long id, Long webId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		ShopMessage entity = shopMessageMng.findById(id);
		if(errors.ifNotExist(entity, ShopMessage.class, id)) {
			return true;
		}
		return false;
	}
	
	@Autowired
	private ShopMessageMng shopMessageMng;
}