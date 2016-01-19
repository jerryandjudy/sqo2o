package com.jspgou.cms.action.member;

import static com.jspgou.common.web.Constants.SPT;
import static com.jspgou.common.web.Constants.UPLOAD_PATH;
import static com.jspgou.cms.Constants.MEMBER_SYS;

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

import com.jspgou.common.file.FileNameUtils;
import com.jspgou.common.image.ImageUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
import com.jspgou.cms.web.SiteUtils;

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
	private static final String RESULT_PAGE = "iframe_upload";
	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";

	@RequestMapping("/common/o_upload_image.jspx")
	public String execute(
			String fileName,
			Integer uploadNum,
			Integer zoomWidth,
			Integer zoomHeight,
			@RequestParam(value = "uploadFile", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(fileName, file, request);
		Website web = SiteUtils.getWeb(request);
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
					RESULT_PAGE));
		}
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
		model.addAttribute("zoomWidth", zoomWidth);
		model.addAttribute("zoomHeight", zoomHeight);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,
				RESULT_PAGE));
//		return RESULT_PAGE;
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
