package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.cms.entity.Adspace;
import com.jspgou.cms.entity.Advertise;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.manager.AdspaceMng;
import com.jspgou.cms.manager.AdvertiseMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.web.RequestUtils1;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class AdvertiseAct {
	private static final Logger log = LoggerFactory
			.getLogger(AdvertiseAct.class);

	@RequestMapping("/advertise/v_list.do")
	public String list(Integer queryAdspaceId, Boolean queryEnabled,
			Integer pageNo, HttpServletRequest request, ModelMap model) {
		Long caId=null;
		Pagination pagination = advertiseMng.getPage(caId,queryAdspaceId,queryEnabled, cpn(pageNo), CookieUtils.getPageSize(request),null);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		if (queryAdspaceId != null) {
			model.addAttribute("queryAdspaceId", queryAdspaceId);
		}
		if (queryEnabled != null) {
			model.addAttribute("queryEnabled", queryEnabled);
		}
		return "advertise/list";
	}

	@RequestMapping("/advertise/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<Adspace> adspaceList = adspaceMng.getList();
		model.addAttribute("adspaceList", adspaceList);
		List<Category> categorys=categoryMng.getChildList(null, null, true);
		model.addAttribute("categorys", categorys);
		return "advertise/add";
	}

	@RequestMapping("/advertise/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Advertise advertise = advertiseMng.findById(id);
		List<Category> categorys=categoryMng.getChildList(null, null, true);
		model.addAttribute("categorys", categorys);
		model.addAttribute("advertise", advertise);
		model.addAttribute("attr", advertise.getAttr());
		model.addAttribute("adspaceList", adspaceMng.getList());
		model.addAttribute("pageNo", pageNo);
		return "advertise/edit";
	}

	@RequestMapping("/advertise/o_save.do")
	public String save(Advertise bean, Integer adspaceId,
			HttpServletRequest request, ModelMap model) {
		Map<String, String> attr = RequestUtils1.getRequestMap(request, "attr_");
		// 去除为空串的属性
		Set<String> toRemove = new HashSet<String>();
		for (Entry<String, String> entry : attr.entrySet()) {
			if (StringUtils.isBlank(entry.getValue())) {
				toRemove.add(entry.getKey());
			}
		}
		for (String key : toRemove) {
			attr.remove(key);
		}
		bean = advertiseMng.save(bean, adspaceId, attr);
		log.info("save advertise id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping("/advertise/o_update.do")
	public String update(Integer queryAdspaceId, Boolean queryEnabled,
			Advertise bean, Integer adspaceId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Map<String, String> attr = RequestUtils1.getRequestMap(request, "attr_");
		// 去除为空串的属性
		Set<String> toRemove = new HashSet<String>();
		for (Entry<String, String> entry : attr.entrySet()) {
			if (StringUtils.isBlank(entry.getValue())) {
				toRemove.add(entry.getKey());
			}
		}
		for (String key : toRemove) {
			attr.remove(key);
		}
		bean = advertiseMng.update(bean, adspaceId, attr);
		log.info("update advertise id={}.", bean.getId());
		return list(queryAdspaceId, queryEnabled, pageNo, request, model);
	}

	@RequestMapping("/advertise/o_delete.do")
	public String delete(Integer[] ids, Integer queryAdspaceId,
			Boolean queryEnabled, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Advertise[] beans = advertiseMng.deleteByIds(ids);
		for (Advertise bean : beans) {
			log.info("delete advertise id={}", bean.getId());
		}
		return list(queryAdspaceId, queryEnabled, pageNo, request, model);
	}

	

	@Autowired
	private AdspaceMng adspaceMng;
	@Autowired
	private AdvertiseMng advertiseMng;
	@Autowired
	private CategoryMng categoryMng;
}