package com.jspgou.cms.action.admin.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.core.entity.Website;
import com.jspgou.cms.entity.Coupon;
import com.jspgou.cms.manager.CouponMng;
import com.jspgou.cms.web.SiteUtils;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class CouponAct {

	@RequestMapping("/coupon/v_add.do")
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		return "coupon/add";
	}

	@RequestMapping("/coupon/o_save.do")
	public String save(Coupon bean, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website site = SiteUtils.getWeb(request);
		couponMng.save(bean, site.getId());
		return this.list(request, response, model);
	}

	@RequestMapping("/coupon/v_list.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Coupon> cList = couponMng.getList();
		model.addAttribute("list", cList);
		return "coupon/list";
	}

	@RequestMapping("/coupon/v_edit.do")
	public String edit(Long id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.addAttribute("coupon", couponMng.findById(id));
		return "coupon/edit";
	}

	@RequestMapping("/coupon/o_update.do")
	public String update(Coupon bean, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		couponMng.update(bean);
		return this.list(request, response, model);
	}

	@RequestMapping("/coupon/o_delete.do")
	public String delete(Long[] ids, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website site = SiteUtils.getWeb(request);
		String url = site.getUploadUrl();
		couponMng.deleteByIds(ids, url);
		return this.list(request, response, model);
	}

	@Autowired
	private CouponMng couponMng;

}