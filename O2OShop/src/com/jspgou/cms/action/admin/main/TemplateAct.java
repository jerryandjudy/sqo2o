package com.jspgou.cms.action.admin.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.cms.manager.ResourceMng;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.common.file.FileWrap;
import com.jspgou.common.util.Zipper;
import com.jspgou.common.util.Zipper.FileEntry;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.TemplateMng;
import com.jspgou.core.tpl.Tpl;
import com.jspgou.core.tpl.TplManager;

/**
 * 模板Action类
 * 
 * @author liufang
 * @preserve
 */
@Controller
public class TemplateAct implements ServletContextAware {
	private static final Logger log = LoggerFactory.getLogger(TemplateAct.class);
	/**
	 * 相对地址字符串
	 */
	private static final String REL_PATH = "relPath";

	@RequestMapping("/template/v_left.do")
	public String left(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String tplReal = servletContext.getRealPath(web.getTemplateRel());
		String tplName = MessageResolver.getMessage(request, "template.tplName");
		FileWrap wrap = templateMng.getTplFileWrap(tplReal, tplName);
		model.addAttribute("treeRoot", wrap);
		return "template/left";
	}
	
	@RequestMapping(value = "/template/o_swfupload.do", method = RequestMethod.POST)
	public void swfUpload(
			String root,
			@RequestParam(value = "Filedata", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IllegalStateException, IOException {
		tplManager.save(root, file);
		model.addAttribute("root", root);
		log.info("file upload seccess: {}, size:{}.", file
				.getOriginalFilename(), file.getSize());
		ResponseUtils.renderText(response, "");
	}
	
	@RequestMapping(value = "/template/v_tree.do", method = RequestMethod.GET)
	public String tree(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String root = RequestUtils.getQueryParam(request, "root");
		log.debug("tree path={}", root);
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			root =web.getTemplateRel();
			model.addAttribute("isRoot", true);
		} else {
			model.addAttribute("isRoot", false);
		}
		List<? extends Tpl> tplList = tplManager.getChild(root);
		model.addAttribute("tplList", tplList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "template/tree";
	}
	
	// 直接调用方法需要把root参数保存至model中
	@RequestMapping(value = "/template/v_list.do", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String root = (String) model.get("root");
		if (root == null) {
			root = RequestUtils.getQueryParam(request, "root");
		}
		log.debug("list Template root: {}", root);
		if (StringUtils.isBlank(root)) {
			root =new String(web.getTemplateRelBuff());
		}
		String rel = root.substring(new String(web.getTemplateRelBuff()).length());
		if (rel.length() == 0) {
			rel = "/";
		}
		System.out.println(root);
		System.out.println(rel);
		model.addAttribute("root", root);
		model.addAttribute("rel", rel);
		model.addAttribute("list", tplManager.getChild(root));
		return "template/list";
	}

	@RequestMapping("/template/o_create_dir.do")
	public String createDir(String relPath, String dirName,
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getTemplateRel(relPath));
		File file = new File(real, dirName);
		// 创建文件夹
		if (!file.exists()) {
			file.mkdirs();
		}
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}
	
	@RequestMapping(value = "/template/v_rename.do", method = RequestMethod.GET)
	public String renameInput(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String root = RequestUtils.getQueryParam(request, "root");
		String name = RequestUtils.getQueryParam(request, "name");
		String origName = name.substring(web.getTemplateRel().length());
		model.addAttribute("origName", origName);
		model.addAttribute("root", root);
		return "template/rename";
	}

	@RequestMapping("/template/o_rename.do")
	public String renameUpdate(String relPath, String origName, String newName,
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getTemplateRel(relPath));
		File origFile = new File(real, origName);
		// TODO 验证原文件是否存在
		// 重命名
		File newFile = new File(real, newName);
		origFile.renameTo(newFile);
		log.info("rename template dir {} to {}", origFile.getAbsolutePath(),
				newFile.getAbsolutePath());
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}

	@RequestMapping(value = "/template/v_add.do", method = RequestMethod.GET)
	public String add(HttpServletRequest request, ModelMap model) {
		String root = RequestUtils.getQueryParam(request, "root");
		model.addAttribute("root", root);
		return "template/add";
	}
	
	@RequestMapping("/template/v_edit.do")
	public String edit(HttpServletRequest request, ModelMap model) {
		String root = RequestUtils.getQueryParam(request, "root");
		String name = RequestUtils.getQueryParam(request, "name");
		model.addAttribute("template", tplManager.get(name));
		model.addAttribute("root", root);
		return "template/edit";
	}
	
	@RequestMapping("/template/o_save.do")
	public String save(String root, String filename, String source,
			HttpServletRequest request, ModelMap model) {
		String name = root + "/" + filename + Website.TPL_SUFFIX;
		tplManager.save(name, source, false);
		model.addAttribute("root", root);
		log.info("save Template name={}", filename);
		return "redirect:v_list.do";
	}
	
	// AJAX请求，不返回页面
	@RequestMapping("/template/o_update.do")
	public void update(String root, String name, String source,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		tplManager.update(name, source);
		log.info("update Template name={}.", name);
		model.addAttribute("root", root);
		ResponseUtils.renderJson(response, "{success:true}");
	}
	
	@RequestMapping("/template/o_delete.do")
	public String delete(String root, String[] names,
			HttpServletRequest request, ModelMap model) {
		int count = tplManager.delete(names);
		log.info("delete Template count: {}", count);
		for (String name : names) {
			log.info("delete Template name={}", name);
		}
		model.addAttribute("root", root);
		return list(request, model);
	}
	
	@RequestMapping("/template/o_delete_single.do")
	public String deleteSingle(HttpServletRequest request, ModelMap model) {
		// TODO 输入验证
		String root = RequestUtils.getQueryParam(request, "root");
		String name = RequestUtils.getQueryParam(request, "name");
		int count = tplManager.delete(new String[] { name });
		log.info("delete Template {}, count {}", name, count);
		model.addAttribute("root", root);
		return list(request, model);
	}

	@RequestMapping("/template/v_solution.do")
	public String solutionInput(HttpServletRequest request, ModelMap model) {
		return "template/solution";
	}

	@RequestMapping("/template/o_export.do")
	public void templateExport(HttpServletRequest request,
			HttpServletResponse response) {
		List<FileEntry> fileEntrys = resourceMng.export();
		response.setContentType("application/x-download;charset=UTF-8");
		response.addHeader("Content-disposition", "filename=template.zip");
		try {
			Zipper.zip(response.getOutputStream(), fileEntrys, "GBK");
		} catch (IOException e) {
			log.error("export template error!", e);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/template/o_solution.do")
	public String solutionUpdate(HttpServletRequest request, ModelMap model) {
		return solutionInput(request, model);
	}

	@RequestMapping(value = "/template/o_import.do")
	public String importSubmit(
			@RequestParam(value = "tplZip", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		Website site = SiteUtils.getWeb(request);
		File tempFile = File.createTempFile("tplZip", "temp");
		file.transferTo(tempFile);
		resourceMng.imoport(tempFile, site);
		tempFile.delete();
		return solutionInput(request, model);
	}

	@Autowired
	private TemplateMng templateMng;
	private ServletContext servletContext;
	private ResourceMng resourceMng;
	
	private TplManager tplManager;
	
	public void setTplManager(TplManager tplManager) {
		this.tplManager = tplManager;
	}

	@Autowired
	public void setResourceMng(ResourceMng resourceMng) {
		this.resourceMng = resourceMng;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}