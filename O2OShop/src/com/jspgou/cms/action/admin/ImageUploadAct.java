package com.jspgou.cms.action.admin;

import static com.jspgou.common.web.Constants.SPT;
import static com.jspgou.common.web.Constants.UPLOAD_PATH;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.cms.web.SiteUtils;
import com.jspgou.common.file.FileNameUtils;
import com.jspgou.common.image.ImageUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class ImageUploadAct implements ServletContextAware {
	private static final Logger log = LoggerFactory
			.getLogger(ImageUploadAct.class);
	/**
	 * 结果页
	 */
	private static final String RESULT_PAGE = "/common/iframe_upload";
	
	private static final String RESULT_PAGE_DOC = "/common/iframe_upload_doc";
	
	private static final String RESULT_SWITCH_PAGE = "/common/iframe_switch_upload";
	
	private static final String RESULT_BIG_PAGE = "/common/iframe_big_upload";
	
	private static final String RESULT_AMP_PAGE = "/common/iframe_amp_upload";

	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";

	@RequestMapping("/common/o_upload_image.do")
	public String execute(
			String fileName,
			Integer uploadNum,
			Integer zoomWidth,
			Integer zoomHeight,
			@RequestParam(value = "uploadFile", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(fileName, file, request);
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return RESULT_PAGE;
		}
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getUploadRel());
		String dateDir = FileNameUtils.genPathName();
		// 创建目录
		File root = new File(real, dateDir);
		if (!root.exists()) {
			root.mkdirs();
		}
		// 取文件名
//		if (StringUtils.isBlank(fileName)) {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = FileNameUtils.genFileName(ext);
//		} else {
//			fileName = FilenameUtils.getName(fileName);
//		}
		// 保存为临时文件
		File tempFile = new File(root, fileName);
		// 相对路径
		String ctx = request.getContextPath();
		String relPath =ctx+ SPT+UPLOAD_PATH+SPT + dateDir + SPT + fileName;
		model.addAttribute("zoomWidth",zoomWidth);
		model.addAttribute("zoomHeight",zoomHeight);
		try {
			file.transferTo(tempFile);
			model.addAttribute("uploadPath", relPath);
			model.addAttribute("uploadNum", uploadNum);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return RESULT_PAGE;
	}
	
	
	
	
	@RequestMapping("/common/o_upload_doc.do")
	public String executeDoc(
			String fileName,
			Integer uploadNum,
			@RequestParam(value = "uploadFileDoc", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDoc(fileName, file, request);
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return RESULT_PAGE;
		}
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getUploadRel());
		String dateDir = FileNameUtils.genPathName();
		// 创建目录
		File root = new File(real, dateDir);
		if (!root.exists()) {
			root.mkdirs();
		}
		// 取文件名
//		if (StringUtils.isBlank(fileName)) {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = FileNameUtils.genFileName(ext);
//		} else {
//			fileName = FilenameUtils.getName(fileName);
//		}
		// 保存为临时文件
		File tempFile = new File(root, fileName);
		// 相对路径
		String ctx = request.getContextPath();
		String relPath =ctx+ SPT+UPLOAD_PATH+SPT + dateDir + SPT + fileName;
		
		try {
			file.transferTo(tempFile);
			model.addAttribute("uploadPath", relPath);
			model.addAttribute("uploadNum", uploadNum);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return RESULT_PAGE_DOC;
	}
	
	private WebErrors validateDoc(String fileName, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (file == null) {
			errors.addErrorCode("docupload.error.noFileToUpload");
			return errors;
		}
		if (StringUtils.isBlank(fileName)) {
			fileName = file.getOriginalFilename();
		}
		String ext = FilenameUtils.getExtension(fileName);
		if (!ImageUtils.isDoc(ext)) {
			errors.addErrorCode("docupload.error.notSupportExt", ext);
			return errors;
		}
		return errors;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//切换图片上传
	@RequestMapping("/common/o_upload_switch_image.do")
	public String executeSwitch(
			String fileName,
			Integer uploadNum,
			@RequestParam(value = "uploadFileSwitch", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(fileName, file, request);
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return RESULT_SWITCH_PAGE;
		}
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getUploadRel());
		String dateDir = FileNameUtils.genPathName();
		// 创建目录
		File root = new File(real, dateDir);
		if (!root.exists()) {
			root.mkdirs();
		}
		// 取文件名
		if (StringUtils.isBlank(fileName)) {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = FileNameUtils.genFileName(ext);
		} else {
			fileName = FilenameUtils.getName(fileName);
		}
		// 保存为临时文件
		File tempFile = new File(root, fileName);
		// 相对路径
		String ctx = request.getContextPath();
		String relPath = ctx+SPT+UPLOAD_PATH+SPT + dateDir + SPT + fileName;
	
		try {
			file.transferTo(tempFile);
			model.addAttribute("uploadPath", relPath);
			model.addAttribute("uploadNum", uploadNum);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return RESULT_SWITCH_PAGE;
	}
	
	//大图片上传
	@RequestMapping("/common/o_upload_big_image.do")
	public String executeBig(
			String fileName,
			Integer uploadNum,
			@RequestParam(value = "uploadFileBig", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(fileName, file, request);
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return RESULT_BIG_PAGE;
		}
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getUploadRel());
		String dateDir = FileNameUtils.genPathName();
		// 创建目录
		File root = new File(real, dateDir);
		if (!root.exists()) {
			root.mkdirs();
		}
		// 取文件名
		if (StringUtils.isBlank(fileName)) {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = FileNameUtils.genFileName(ext);
		} else {
			fileName = FilenameUtils.getName(fileName);
		}
		// 保存为临时文件
		File tempFile = new File(root, fileName);
		// 相对路径
		String ctx = request.getContextPath();
		String relPath =ctx + SPT+UPLOAD_PATH+SPT + dateDir + SPT + fileName;
		try {
			file.transferTo(tempFile);
			model.addAttribute("uploadPath", relPath);
			model.addAttribute("uploadNum", uploadNum);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return RESULT_BIG_PAGE;
	}
	
	//放大图片上传
	@RequestMapping("/common/o_upload_amp_image.do")
	public String executeAmp(
			String fileName,
			Integer uploadNum,
			@RequestParam(value = "uploadFileAmp", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(fileName, file, request);
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return RESULT_AMP_PAGE;
		}
		Website web = SiteUtils.getWeb(request);
		String real = servletContext.getRealPath(web.getUploadRel());
		String dateDir = FileNameUtils.genPathName();
		// 创建目录
		File root = new File(real, dateDir);
		if (!root.exists()) {
			root.mkdirs();
		}
		// 取文件名
		if (StringUtils.isBlank(fileName)) {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = FileNameUtils.genFileName(ext);
		} else {
			fileName = FilenameUtils.getName(fileName);
		}
		// 保存为临时文件
		File tempFile = new File(root, fileName);
		// 相对路径
		String ctx = request.getContextPath();
		String relPath =ctx+ SPT+UPLOAD_PATH+SPT + dateDir + SPT + fileName;
		try {
			file.transferTo(tempFile);
			model.addAttribute("uploadPath", relPath);
			model.addAttribute("uploadNum", uploadNum);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return RESULT_AMP_PAGE;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private WebErrors validate(String fileName, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (file == null) {
			errors.addErrorCode("imageupload.error.noFileToUpload");
			return errors;
		}
		if (StringUtils.isBlank(fileName)) {
			fileName = file.getOriginalFilename();
		}
		String ext = FilenameUtils.getExtension(fileName);
		if (!ImageUtils.isImage(ext)) {
			errors.addErrorCode("imageupload.error.notSupportExt", ext);
			return errors;
		}
		return errors;
	}

	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
