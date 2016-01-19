package com.jspgou.cms;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.cms.manager.CmsFileMng;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.common.image.ImageScale;
import com.jspgou.common.image.ImageUtils;
import com.jspgou.common.upload.FileRepository;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.RealPathResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.web.WebErrors;

/**
 * @author Tom
 */
public class CommonUpload {
	
	protected void swfPicsUpload(
			String root,
			Integer uploadNum,
			@RequestParam(value = "Filedata", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		JSONObject data=new JSONObject();
		WebErrors errors = validateImage(file, request);
		if (errors.hasErrors()) {
			data.put("error", errors.getErrors().get(0));
			ResponseUtils.renderJson(response, data.toString());
		}else{
			Website site = SiteUtils.getWeb(request);
			String ctx = request.getContextPath();
//			MarkConfig conf = site.getConfig().getMarkConfig();
//			Boolean mark = conf.getOn();
			String origName = file.getOriginalFilename();
			String ext = FilenameUtils.getExtension(origName).toLowerCase(
					Locale.ENGLISH);
			String fileUrl;

		fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext,
							file);
			
//			cmsUserMng.updateUploadSize(SiteUtils.getUserId(request), Integer.parseInt(String.valueOf(file.getSize()/1024)));
			// 加上部署路径
			fileUrl = ctx + fileUrl;
			data.put("imgUrl", fileUrl);
			ResponseUtils.renderText(response, data.toString());
		}
	}
	
	protected void swfAttachsUpload(
			String root,
			Integer uploadNum,
			@RequestParam(value = "Filedata", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		JSONObject data=new JSONObject();
		WebErrors errors = validateUpload( file, request);
		if (errors.hasErrors()) {
			data.put("error", errors.getErrors().get(0));
			ResponseUtils.renderJson(response, data.toString());
		}else{
			Website site = SiteUtils.getWeb(request);
			String ctx = request.getContextPath();
			String origName = file.getOriginalFilename();
			String ext = FilenameUtils.getExtension(origName).toLowerCase(
					Locale.ENGLISH);
			// TODO 检查允许上传的后缀
			String fileUrl="";
			try {
			
					fileUrl = fileRepository.storeByExt(site.getUploadPath(), ext,
							file);
					// 加上部署路径
					fileUrl = ctx + fileUrl;
				
//				cmsUserMng.updateUploadSize(SiteUtils.getUserId(request), Integer.parseInt(String.valueOf(file.getSize()/1024)));
					cmsFileMng.saveFileByPath(fileUrl, origName, false);
				model.addAttribute("attachmentPath", fileUrl);
			} catch (IllegalStateException e) {
				model.addAttribute("error", e.getMessage());
			} catch (IOException e) {
				model.addAttribute("error", e.getMessage());
			}
			data.put("attachUrl", fileUrl);
			data.put("attachName", origName);
			ResponseUtils.renderJson(response, data.toString());
		}
	}
	/**
	 * 验证图片（图片有格式需求系统设定）
	 * @param file
	 * @param request
	 * @return
	 */
	protected WebErrors validateImage(MultipartFile file,
			HttpServletRequest request) {
		String filename=file.getOriginalFilename();
		User user=SiteUtils.getUser(request);
		WebErrors errors = WebErrors.create(request);
		if (file == null) {
			errors.addErrorCode("imageupload.error.noFileToUpload");
			return errors;
		}
		if (StringUtils.isBlank(filename)) {
			filename = file.getOriginalFilename();
		}
		if(filename!=null&&filename.indexOf("\0")!=-1){
			errors.addErrorCode("upload.error.filename", filename);
		}
		String ext = FilenameUtils.getExtension(filename);
		if (!ImageUtils.isValidImageExt(ext)) {
			errors.addErrorCode("imageupload.error.notSupportExt", ext);
			return errors;
		}
		try {
			if (!ImageUtils.isImage(file.getInputStream())) {
				errors.addErrorCode("imageupload.error.notImage", ext);
				return errors;
			}
		} catch (IOException e) {
			errors.addErrorCode("imageupload.error.ioError", ext);
			return errors;
		}
		//验证文件大小
		errors=validateFile(file, user, request);
		return errors;
	}

	/**
	 * 验证附件
	 * @param file
	 * @param request
	 * @return
	 */
	protected WebErrors validateUpload(MultipartFile file,
			HttpServletRequest request) {
		String origName = file.getOriginalFilename();
		User user=SiteUtils.getUser(request);
		String ext = FilenameUtils.getExtension(origName).toLowerCase(
				Locale.ENGLISH);
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(file, "file")) {
			return errors;
		}
		if(origName!=null&&origName.indexOf("\0")!=-1){
			errors.addErrorCode("upload.error.filename", origName);
		}
		//非允许的后缀
//		if(!user.isAllowSuffix(ext)){
//			errors.addErrorCode("upload.error.invalidsuffix", ext);
//			return errors;
//		}
		errors=validateFile(file, user, request);
		return errors;
	}
	
	protected WebErrors validateFile(MultipartFile file,User user,
			HttpServletRequest request) {
		String origName = file.getOriginalFilename();
		int fileSize = (int) (file.getSize() / 1024);
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(file, "file")) {
			return errors;
		}
		if(origName!=null&&origName.indexOf("\0")!=-1){
			errors.addErrorCode("upload.error.filename", origName);
		}
		//超过附件大小限制
//		if(!user.isAllowMaxFile((int)(file.getSize()/1024))){
//			errors.addErrorCode("upload.error.toolarge",origName,user.getGroup().getAllowMaxFile());
//			return errors;
//		}
//		超过每日上传限制
//		if (!user.isAllowPerDay(fileSize)) {
//			long laveSize=user.getGroup().getAllowPerDay()-user.getUploadSize();
//			if(laveSize<0){
//				laveSize=0;
//			}
//			errors.addErrorCode("upload.error.dailylimit", laveSize);
//		}
		return errors;
	}

//	protected File mark(MultipartFile file, MarkConfig conf) throws Exception {
//		String path = System.getProperty("java.io.tmpdir");
//		File tempFile = new File(path, String.valueOf(System
//				.currentTimeMillis()));
//		file.transferTo(tempFile);
//		boolean imgMark = !StringUtils.isBlank(conf.getImagePath());
//		if (imgMark) {
//			File markImg = new File(realPathResolver.get(conf.getImagePath()));
//			imageScale.imageMark(tempFile, tempFile, conf.getMinWidth(), conf
//					.getMinHeight(), conf.getPos(), conf.getOffsetX(), conf
//					.getOffsetY(), markImg);
//		} else {
//			imageScale.imageMark(tempFile, tempFile, conf.getMinWidth(), conf
//					.getMinHeight(), conf.getPos(), conf.getOffsetX(), conf
//					.getOffsetY(), conf.getContent(), Color.decode(conf
//					.getColor()), conf.getSize(), conf.getAlpha());
//		}
//		return tempFile;
//	}
	@Autowired
	protected FileRepository fileRepository;
	@Autowired
	protected ImageScale imageScale;
	@Autowired
	protected RealPathResolver realPathResolver;
	@Autowired
	protected CmsFileMng cmsFileMng;
	@Autowired
	protected UserMng cmsUserMng;
}
