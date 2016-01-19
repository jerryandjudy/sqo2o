package com.jspgou.cms.action.admin.main;

import static com.jspgou.core.web.Constants.ENCODING;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.jspgou.common.file.FileWrap;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.common.web.springmvc.RealPathResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.TemplateMng;
import com.jspgou.cms.manager.ResourceMng;
import com.jspgou.cms.web.SiteUtils;

/**
 * 资源Action类
 * @author
 * @preserve
 */
@Controller
public class ResourceAct implements ServletContextAware {
	private static final Logger log = LoggerFactory
			.getLogger(ResourceAct.class);
	/**
	 * 相对地址字符串
	 */
	private static final String REL_PATH = "relPath";

	@RequestMapping("/resource/v_left.do")
	public String left(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String resReal = servletContext.getRealPath(web.getResBaseRel());
		String resName = MessageResolver
				.getMessage(request, "resource.resName");
		FileWrap wrap = templateMng.getResFileWrap(resReal, resName);
		model.addAttribute("treeRoot", wrap);
		return "resource/left";
	}
	
	@RequestMapping(value = "/resource/v_tree.do")
	public String tree(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String root = RequestUtils.getQueryParam(request, "root");
		log.debug("tree path={}", root);
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			root =web.getResBaseRel();
			model.addAttribute("isRoot", true);
		} else {
			model.addAttribute("isRoot", false);
		}
		List<? extends FileWrap> resList = resourceMng.listFile(root, true);
		model.addAttribute("resList", resList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "resource/tree";
	}
	
	// 直接调用方法需要把root参数保存至model中
	@RequestMapping(value = "/resource/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String root = (String) model.get("root");
		if (root == null) {
			root = RequestUtils.getQueryParam(request, "root");
		}
		log.debug("list Resource root: {}", root);
		if (StringUtils.isBlank(root)) {
			root =web.getResBaseRel();
		}
		String rel = root.substring(web.getResBaseRel().length());
		if (rel.length() == 0) {
			rel = "/";
		}
		model.addAttribute("root", root);
		model.addAttribute("rel", rel);
		model.addAttribute("resBase", web.getResBaseUrl());
		model.addAttribute("list", resourceMng.listFile(root, false));
		return "resource/list";
	}
	
	@RequestMapping(value = "/resource/o_create_dir.do")
	public String createDir(String root, String dirName,
			HttpServletRequest request, ModelMap model) {
		// TODO 检查dirName是否存在
		resourceMng.createDir(root, dirName);
		model.addAttribute("root", root);
		return list(request, model);
	}
	
	@RequestMapping(value = "/resource/v_rename.do")
	public String renameInput(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String root = RequestUtils.getQueryParam(request, "root");
		String name = RequestUtils.getQueryParam(request, "name");
		String origName = name.substring(web.getResBaseRel().length());
		model.addAttribute("origName", origName);
		model.addAttribute("root", root);
		return "resource/rename";
	}

	@RequestMapping("/resource/o_rename.do")
	public String renameUpdate(String relPath, String origName, String newName,
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getResBaseRel(relPath));
		File origFile = new File(real, origName);
		// TODO 验证原文件是否存在
		// 重命名
		File newFile = new File(real, newName);
		origFile.renameTo(newFile);
		log.info("rename resource dir {} to {}", origFile.getAbsolutePath(),
				newFile.getAbsolutePath());
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}

	@RequestMapping("/resource/v_add.do")
	public String add(String relPath, HttpServletRequest request, ModelMap model) {
		model.addAttribute("relPath", relPath);
		return "resource/add";
	}

	@RequestMapping("/resource/o_save.do")
	public String save(String relPath, String filename, String extension,
			String content, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getResBaseRel(relPath));
		try {
			File file = new File(real, filename + extension);
			FileUtils.writeStringToFile(file, content, ENCODING);
			log.info("save resource file success: {}", file.getAbsolutePath());
		} catch (IOException e) {
			log.error("write resource file error", e);
		}
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}

	@RequestMapping("/resource/v_edit.do")
	public String edit(HttpServletRequest request, ModelMap model) {
		String root = RequestUtils.getQueryParam(request, "root");
		String name = RequestUtils.getQueryParam(request, "name");
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(name);
		File file = new File(real);
		String filename = file.getName();
		try {
			String content = FileUtils.readFileToString(file, ENCODING);
			model.addAttribute("content", content);
		} catch (IOException e) {
			log.error("read resource file error", e);
		}
		model.addAttribute("filename", filename);
		model.addAttribute("root", root);
		model.addAttribute("name", name);
		return "resource/edit";
	}

	@RequestMapping("/resource/o_update.do")
	public String update(String name, String content,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String real = realPathResolver.get(name);
		File file = new File(real);
		try {
			FileUtils.writeStringToFile(file, content, ENCODING);
		} catch (IOException e) {
			log.error("write resource file error", e);
		}
		ResponseUtils.renderJson(response, "true");
		// AJAX请求，不返回页面。
		return null;
	}

	@RequestMapping("/resource/o_delete.do")
	public String delete(String relPath, String[] names,
			HttpServletRequest request, ModelMap model) {
		String root = RequestUtils.getQueryParam(request, "root");
		for (String name : names) {
			int count = resourceMng.delete(new String[] { name });
			log.info("delete Resource {}, count {}", name, count);
		}
		model.addAttribute("root", root);
		return list(request, model);
	}
	
	@RequestMapping("/resource/o_delete_single.do")
	public String deleteSingle(HttpServletRequest request, ModelMap model) {
		// TODO 输入验证
		String root = RequestUtils.getQueryParam(request, "root");
		String name = RequestUtils.getQueryParam(request, "name");
		int count = resourceMng.delete(new String[] { name });
		log.info("delete Resource {}, count {}", name, count);
		model.addAttribute("root", root);
		return list(request, model);
	}

	@RequestMapping("/resource/v_upload.do")
	public String uploadInput(String relPath, HttpServletRequest request,
			ModelMap model) {
		model.addAttribute(REL_PATH, relPath);
		return "resource/upload";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/resource/o_upload.do")
	public String uploadSubmit(String relPath, HttpServletRequest request,
			ModelMap model) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		MultipartFile[] files = new MultipartFile[map.size()];
		map.values().toArray(files);
		if (files.length > 0) {
			Website web = SiteUtils.getWeb(request);
			String realPath = servletContext.getRealPath(web
					.getResBaseRel(relPath));
			templateMng.uploadResourceFile(realPath, files);
		}
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}
	
	@RequestMapping(value = "/resource/o_swfupload.do", method = RequestMethod.POST)
	public void swfUpload(
			String root,
			@RequestParam(value = "Filedata", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IllegalStateException, IOException {
		resourceMng.saveFile(root, file);
		model.addAttribute("root", root);
		log.info("file upload seccess: {}, size:{}.", file
				.getOriginalFilename(), file.getSize());
		ResponseUtils.renderText(response, "");
	}

	private String root;
	@Autowired
	private TemplateMng templateMng;
	@Autowired
	private ResourceMng resourceMng;
	private ServletContext servletContext;

	private RealPathResolver realPathResolver;

	@Autowired
	public void setRealPathResolver(RealPathResolver realPathResolver) {
		this.realPathResolver = realPathResolver;
		root = realPathResolver.get("");
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}