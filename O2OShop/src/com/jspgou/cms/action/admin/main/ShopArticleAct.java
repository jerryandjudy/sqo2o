package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
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

import com.jspgou.cms.entity.ShopArticle;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.manager.ShopArticleMng;
import com.jspgou.cms.manager.ShopChannelMng;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShopArticleAct {
	private static final Logger log = LoggerFactory
			.getLogger(ShopArticleAct.class);
	
	@RequestMapping("/article/v_left.do")
	public String left() {
		return "article/left";
	}
	
	/**
	 * 栏目导航
	 * 
	 * @param root
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/article/v_tree.do")
	public String tree(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		WebErrors errors = validateTree(root, request);
		if (errors.hasErrors()) {
			log.error(errors.getErrors().get(0));
			ResponseUtils.renderJson(response, "[]");
			return null;
		}
		List<ShopChannel> list;
		if (isRoot) {
			list =shopChannelMng.getTopList(null);
		} else {
			Long rootId = Long.valueOf(root);
			list = shopChannelMng.getChildList(null,rootId);
		}
		model.addAttribute("list", list);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "article/tree";
	}

	@RequestMapping("/article/v_list.do")
	public String list(Long cid,Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		Pagination pagination = shopArticleMng.getPage(cid, web.getId(), cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		if(cid!=null){
		   ShopChannel channel=shopChannelMng.findById(cid);
		   model.addAttribute("channel", channel);
		}
		model.addAttribute("cid", cid);
		return "article/list";
	}

	@RequestMapping("/article/v_add.do")
	public String add(Long cid,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List<ShopChannel> channelList = shopChannelMng.getArticleList(null);
		model.addAttribute("channelList", channelList);
		if (cid != null) {
			model.addAttribute("cid", cid);
		}
		return "article/add";
	}

	@RequestMapping("/article/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<ShopChannel> articleChannelList = shopChannelMng.getArticleList(null);
		model.addAttribute("articleChannelList", articleChannelList);
		model.addAttribute("shopArticle", shopArticleMng.findById(id));
		return "article/edit";
	}

	@RequestMapping("/article/o_save.do")
	public String save(ShopArticle bean, Long channelId, String content,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, channelId, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shopArticleMng.save(bean, channelId, content);
		log.info("save ShopArticle id={}", bean.getId());
		return "redirect:v_list.do?cid="+channelId;
	}

	@RequestMapping("/article/o_update.do")
	public String update(ShopArticle bean, Long channelId, String content,
			Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), channelId, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = shopArticleMng.update(bean, channelId, content);
		log.info("update ShopArticle id={}.", bean.getId());
		return list(channelId,pageNo, request, model);
	}

	@RequestMapping("/article/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ShopArticle[] beans = shopArticleMng.deleteByIds(ids);
		for (ShopArticle bean : beans) {
			log.info("delete ShopArticle id={}", bean.getId());
		}
		return list(null,pageNo, request, model);
	}

	private WebErrors validateSave(ShopArticle bean, Long channelId,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		bean.setWebsite(web);
		if (errors.ifNull(channelId, "channelId")) {
			return errors;
		}
//		ShopChannel channel = shopChannelMng.findById(channelId);
//		if (!channel.getWebsite().getId().equals(web.getId())) {
//			errors.notInWeb(ShopChannel.class, channelId);
//			return errors;
//		}
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

	private WebErrors validateUpdate(Long id, Long channelId,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldExist(id, web.getId(), errors)) {
			return errors;
		}
		ShopChannel channel = shopChannelMng.findById(channelId);
//		if (!channel.getWebsite().getId().equals(web.getId())) {
//			errors.notInWeb(ShopChannel.class, channelId);
//			return errors;
//		}
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
		ShopArticle entity = shopArticleMng.findById(id);
		if (errors.ifNotExist(entity, ShopArticle.class, id)) {
			return true;
		}
		if (!entity.getWebsite().getId().equals(webId)) {
			errors.notInWeb(ShopArticle.class, id);
			return true;
		}
		return false;
	}
	
	private WebErrors validateTree(String path, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		// if (errors.ifBlank(path, "path", 255)) {
		// return errors;
		// }
		return errors;
	}

	@Autowired
	private ShopChannelMng shopChannelMng;
	@Autowired
	private ShopArticleMng shopArticleMng;
}