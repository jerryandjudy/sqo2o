package com.jspgou.cms.action.admin.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.entity.ProductTypeProperty;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.ProductTypeMng;
import com.jspgou.cms.manager.ProductTypePropertyMng;
import com.jspgou.cms.manager.StandardTypeMng;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class CategoryAct implements ServletContextAware {
	private static final Logger log = LoggerFactory
			.getLogger(CategoryAct.class);
	
	@RequestMapping("/category/v_left.do")
	public String left() {
		return "category/left";
	}
	
	@RequestMapping(value = "/category/v_tree.do")
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
		List<Category> list;
		if (isRoot) {	
			list = categoryMng.getTopList();
		} else {
			Long rootId = Long.valueOf(root);
			list = categoryMng.getChildList(rootId);
		}
		model.addAttribute("list", list);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "category/tree";
	}

	@RequestMapping("/category/v_list.do")
	public String list(Long root, HttpServletRequest request,ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List<Category> list;
		if (root == null) {
			list = categoryMng.getTopList();
		} else {
			list = categoryMng.getChildList(root);
		}
		List<ProductType> typeList = productTypeMng.getList();
		model.addAttribute("typeList", typeList);
		model.addAttribute("root", root);
		model.addAttribute("list", list);
		return "category/list";
	}

	@RequestMapping("/category/v_add.do")
	public String add(Long root,Long typeId,
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		Category parent=null;
		List<Brand> brandList;
		// 模型
		ProductType type = productTypeMng.findById(typeId);
		// 模型项列表
		List<ProductTypeProperty> itemList = productTypePropertyMng.getList(typeId, true);
		if (root != null) {
			parent = categoryMng.findById(root);
			model.addAttribute("parent", parent);
			model.addAttribute("root", root);
			brandList=new ArrayList<Brand>(parent.getBrands());
		}else{
			brandList = brandMng.getList();
		}
		model.addAttribute("brandList",brandList);
		//类型模板
		String ctgTplDirRel = type.getCtgTplDirRel();
		String realDir = servletContext.getRealPath(ctgTplDirRel);
		String relPath = ctgTplDirRel.substring(web.getTemplateRel().length());
		//内容模板
		String txtTplDirRel=type.getTxtTplDirRel();
		String txtrealDir = servletContext.getRealPath(txtTplDirRel);
		String txtrelPath = txtTplDirRel.substring(web.getTemplateRel().length());
		// 商品类别模板列表
		String[] channelTpls = type.getChannelTpls(realDir, relPath);
		// 商品内容模板列表
		String[] contentTpls = type.getContentTpls(txtrealDir, txtrelPath);

		List<Category> parentList = categoryMng.getListForParent(SiteUtils
				.getWebId(request), null);
		//商品开启规格类型列表
		List<StandardType> standardTypeList = standardTypeMng.getList();
		model.addAttribute("standardTypeList", standardTypeList);
		model.addAttribute("channelTpls", channelTpls);
		model.addAttribute("contentTpls", contentTpls);
		model.addAttribute("parentList", parentList);	
		model.addAttribute("type", type);
		model.addAttribute("itemList", itemList);
		return "category/add";
	}

	@RequestMapping("/category/v_edit.do")
	public String edit(Long id,Long root, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if (root != null) {
			model.addAttribute("root", root);
		}
		Website web = SiteUtils.getWeb(request);
		Category category = categoryMng.findById(id);
		List<Category> parentList = categoryMng.getListForParent( id);
		List<ProductType> typeList = productTypeMng.getList();
		// 模型
		ProductType type = category.getType();
		// 模型项列表
		List<ProductTypeProperty> itemList = productTypePropertyMng.getList(type.getId(), true);
		List<Brand> brandList;
		if(category.getParent()!=null){
			brandList=new ArrayList<Brand>(category.getParent().getBrands());
		}else{
			brandList=brandList = brandMng.getList();
		}
		model.addAttribute("brandList",brandList);
		//类型模板
		String ctgTplDirRel = type.getCtgTplDirRel();
		String realDir = servletContext.getRealPath(ctgTplDirRel);
		String relPath = ctgTplDirRel.substring(web.getTemplateRel().length());
		//内容模板
		String txtTplDirRel=type.getTxtTplDirRel();
		String txtrealDir = servletContext.getRealPath(txtTplDirRel);
		String txtrelPath = txtTplDirRel.substring(web.getTemplateRel().length());
		// 商品类别模板列表
		String[] channelTpls = type.getChannelTpls(realDir, relPath);
		// 商品内容模板列表
		String[] contentTpls = type.getContentTpls(txtrealDir, txtrelPath);
		String tpl = category.getTplChannel();
		if (!StringUtils.isBlank(tpl) && !ArrayUtils.contains(channelTpls, tpl)) {
			channelTpls = (String[]) ArrayUtils.add(channelTpls, 0, tpl);
		}
		tpl = category.getTplContent();
		if (!StringUtils.isBlank(tpl) && !ArrayUtils.contains(contentTpls, tpl)) {
			contentTpls = (String[]) ArrayUtils.add(contentTpls, 0, tpl);
		}
		List<StandardType> standardTypeList = standardTypeMng.getList();
		Long[] standardTypeIds = StandardType.fetchIds(category.getStandardType());
		model.addAttribute("standardTypeList", standardTypeList);
		model.addAttribute("channelTpls", channelTpls);
		model.addAttribute("contentTpls", contentTpls);
		model.addAttribute("parentList", parentList);
		model.addAttribute("typeList", typeList);
		model.addAttribute("category", category);
		model.addAttribute("standardTypeIds", standardTypeIds);
		model.addAttribute("itemList", itemList);
		return "category/edit";
	}

	@RequestMapping("/category/o_save.do")
	public String save(Category bean, Long root, Long typeId,Long[] brandIds,Long[] standardTypeIds,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));
		bean = categoryMng.save(bean, root, typeId, brandIds,standardTypeIds);
		log.info("save Category. id={}", bean.getId());
		model.addAttribute("root", root);
		return "redirect:v_list.do";
	}

	@RequestMapping("/category/o_update.do")
	public String update(Category bean, Long root,Long parentId,Long[] brandIds,Long[] standardTypeIds,
			Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Map<String, String> attr = RequestUtils.getRequestMap(request, "attr_");
		bean = categoryMng.update(bean, parentId, null, brandIds,attr,standardTypeIds);
		log.info("update Category. id={}.", bean.getId());
		return list(root, request, model);
	}

	@RequestMapping("/category/o_delete.do")
	public String delete(Long[] ids,Long root, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Category[] beans = categoryMng.deleteByIds(ids);
		for (Category bean : beans) {
			log.info("delete Category. id={}", bean.getId());
		}
		return list(root, request, model);
	}

	@RequestMapping("/category/v_checkPath.do")
	public String checkPath(String path, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(path)
				|| !categoryMng.checkPath(SiteUtils.getWebId(request), path)) {
			ResponseUtils.renderJson(response, "false");
		} else {
			ResponseUtils.renderJson(response, "true");
		}
		return null;
	}

	@RequestMapping("/category/o_priority.do")
	public String priority(Long[] wids,Long root, Integer[] priority, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		categoryMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(root, request, model);
	}
	
	private WebErrors validateTree(String path, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		// if (errors.ifBlank(path, "path", 255)) {
		// return errors;
		// }
		return errors;
	}

	private WebErrors validateSave(Category bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		bean.setWebsite(SiteUtils.getWeb(request));
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	private WebErrors validateUpdate(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
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

	private void vldExist(Long id, WebErrors errors) {
		if (errors.hasErrors()) {
			return;
		}
		Category entity = categoryMng.findById(id);
		errors.ifNotExist(entity, Category.class, id);
	}
	
	@Autowired
	private BrandMng brandMng;
	@Autowired
	private ProductTypeMng productTypeMng;
	@Autowired
	private ProductTypePropertyMng productTypePropertyMng;
	@Autowired
	private StandardTypeMng standardTypeMng;
	@Autowired
	private CategoryMng categoryMng;
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}