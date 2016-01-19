package com.jspgou.cms.action.admin.main;



import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.entity.Payment;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.manager.PaymentMng;
import com.jspgou.cms.manager.ShippingMng;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class PaymentAct {
	private static final Logger log = LoggerFactory.getLogger(PaymentAct.class);

	@RequestMapping("/payment/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		List<Payment> list = paymentMng.getList(null, true);
		model.addAttribute("list", list);
		return "payment/list";
	}

	@RequestMapping("/payment/v_add.do")
	public String add(String code, HttpServletRequest request, ModelMap model) {
		List<Shipping> shippingList = shippingMng.getList(null, false);
		model.addAttribute("shippingList",shippingList);
		return "payment/add";
	}

	@RequestMapping("/payment/v_edit.do")
	public String edit(Long id,HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id,request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Payment payment = paymentMng.findById(id);
		List<Shipping> shippingList = shippingMng.getList(null, false);
		model.addAttribute("shippingIds", paymentMng.findById(id).getShippingIds());
		model.addAttribute("shippingList",shippingList);
		model.addAttribute("payment", payment);
		return "payment/edit";
	}

	@RequestMapping("/payment/o_save.do")
	public String save(Payment bean,long shippingIds[],HttpServletRequest request, ModelMap model) {
	    WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		} 
		if(bean.getIsDefault()){
			List<Payment> list=paymentMng.getList((long)1, true);
			for(int i=0;i<list.size();i++){
				list.get(i).setIsDefault(false);
				paymentMng.update(list.get(i));
			}
		}
		bean = paymentMng.save(bean);
		paymentMng.addShipping(bean,shippingIds);
		log.info("save Payment, id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/payment/o_update.do")
	public String update(Payment bean, Integer pageNo,long shippingIds[],
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(bean.getIsDefault()){
			List<Payment> list=paymentMng.getList((long)1, true);
			for(int i=0;i<list.size();i++){
				list.get(i).setIsDefault(false);
				paymentMng.update(list.get(i));
			}
		}
		bean = paymentMng.update(bean);
		paymentMng.updateShipping(bean,shippingIds);
		log.info("update Payment, id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequestMapping("/payment/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Payment[] beans = paymentMng.deleteByIds(ids);
		for (Payment bean : beans) {
			log.info("delete Payment, id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}
	
	@RequestMapping("/payment/o_priority.do")
	public String priority(Long[] wids, Integer[] priority, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		paymentMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(pageNo, request, model);
	}

	

	 private WebErrors validateSave(Payment bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		bean.setWebsite(web);
		return errors;
	} 

	private WebErrors validateEdit(Long id, 
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldExist(id, web.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Long id,
			HttpServletRequest request) {
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
		Payment entity = paymentMng.findById(id);
		if (errors.ifNotExist(entity, Payment.class, id)) {
			return true;
		}
//		if (!entity.getWebsite().getId().equals(webId)) {
//			errors.notInWeb(Payment.class, id);
//			return true;
//		}
		return false;
	}

	private Map<String, String> getCfg(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration e = request.getParameterNames();
		String param = "cfg_";
		String name;
		while (e.hasMoreElements()) {
			name = (String) e.nextElement();
			if (name.startsWith(param)) {
				map.put(name.substring(param.length()), request
						.getParameter(name));
			}
		}
		return map;
	}

	@Autowired
	private PaymentMng paymentMng;
	@Autowired
	private ShippingMng shippingMng;
}