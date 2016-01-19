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
import com.jspgou.cms.entity.Consult;
import com.jspgou.cms.entity.Discuss;
import com.jspgou.cms.manager.ConsultMng;
import com.jspgou.cms.manager.DiscussMng;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class MessageAct {
	private static final Logger log = LoggerFactory.getLogger(MessageAct.class);

	@RequestMapping("/message/v_productDiss.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = discussMng.getPage(null,null,null,null,null, cpn(pageNo), CookieUtils
				.getPageSize(request), true);
		model.addAttribute("pagination", pagination);
		
		return "message/list";
	}

	@RequestMapping("/message/v_edit.do")
	public String edit(Long id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Discuss bean = discussMng.findById(id);
		model.addAttribute("discuss", bean);
		return "message/edit";
	}

	@RequestMapping("/message/v_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		discussMng.deleteByIds(ids);
		model.addAttribute("pageNo", pageNo);
		return "redirect:v_productDiss.do";
	}

	@RequestMapping("/message/v_productConsult.do")
	public String listConsult(Integer pageNo, HttpServletRequest request,
			ModelMap model) {

		Pagination pagination = consultMng.getPage(null, null,null,null,null,cpn(pageNo),
				CookieUtils.getPageSize(request), true);
		model.addAttribute("pagination", pagination);
		return "consult/list";
	}

	@RequestMapping("/message/v_editConsult.do")
	public String editConsult(Long id, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Consult bean = consultMng.findById(id);
		model.addAttribute("consult", bean);
		return "consult/edit";
	}

	@RequestMapping("/message/v_updateConsult.do")
	public String updateConsult(Long id, String adminReplay, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Consult bean = consultMng.findById(id);
		bean.setAdminReplay(adminReplay);
		consultMng.update(bean);
		model.addAttribute("pageNo", pageNo);
		return "redirect:v_productConsult.do";
	}

	@RequestMapping("/message/v_deleteConsult.do")
	public String deleteConsult(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		consultMng.deleteByIds(ids);
		model.addAttribute("pageNo", pageNo);
		return "redirect:v_productConsult.do";
	}

	@Autowired
	private DiscussMng discussMng;
	@Autowired
	private ConsultMng consultMng;
}