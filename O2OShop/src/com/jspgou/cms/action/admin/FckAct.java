package com.jspgou.cms.action.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jspgou.common.fck.Command;
import com.jspgou.common.fck.ResourceType;
import com.jspgou.common.fck.UploadResponse;
import com.jspgou.common.fck.Utils;
import com.jspgou.common.upload.FileRepository;
import com.jspgou.common.upload.UploadUtils;

/**
 * This class should preserve.
 * @preserve private
 * FCK服务器端接口
 * 为了更好、更灵活的处理fck上传，重新实现FCK服务器端接口。
*/
@Controller
public class FckAct {
	
	/**
	 * 上传路径
	 */
	public static final String UPLOAD_PATH = "/u/jspgou/";

	private static final Logger log = LoggerFactory.getLogger(FckAct.class);

	
	@RequestMapping(value = "/fck/upload.do", method = RequestMethod.POST)
	public void upload(
			@RequestParam(value = "Command", required = false) String commandStr,
			@RequestParam(value = "Type", required = false) String typeStr,
			@RequestParam(value = "CurrentFolder", required = false) String currentFolderStr,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("Entering Dispatcher#doPost");
		responseInit(response);
		if (Utils.isEmpty(commandStr) && Utils.isEmpty(currentFolderStr)) {
			commandStr = "QuickUpload";
			currentFolderStr = "/";
			if (Utils.isEmpty(typeStr)) {
				typeStr = "File";
			}
		}
		if (currentFolderStr != null && !currentFolderStr.startsWith("/")) {
			currentFolderStr = "/".concat(currentFolderStr);
		}
		log.debug("Parameter Command: {}", commandStr);
		log.debug("Parameter Type: {}", typeStr);
		log.debug("Parameter CurrentFolder: {}", currentFolderStr);
		UploadResponse ur = validateUpload(request, commandStr, typeStr,
				currentFolderStr);
		if (ur == null) {
			ur = doUpload(request, typeStr, currentFolderStr);
		}
		PrintWriter out = response.getWriter();
		out.print(ur);
		out.flush();
		out.close();
	}


	private UploadResponse doUpload(HttpServletRequest request, String typeStr,
			String currentFolderStr) throws Exception {
		ResourceType type = ResourceType.getDefaultResourceType(typeStr);
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile uplFile = multipartRequest.getFileMap().entrySet().iterator().next().getValue();
			String filename = FilenameUtils.getName(uplFile
					.getOriginalFilename());
			log.debug("Parameter NewFile: {}", filename);
			String ext = FilenameUtils.getExtension(filename);
			if (type.isDeniedExtension(ext)) {
				return UploadResponse.getInvalidFileTypeError(request);
			}
			String fileUrl;
		    fileUrl = fileRepository.storeByExt(UPLOAD_PATH,ext, uplFile);
				// 加上部署路径
			fileUrl = request.getContextPath() + fileUrl;
			return UploadResponse.getOK(request, fileUrl);
		} catch (IOException e) {
			return UploadResponse.getFileUploadWriteError(request);
		}
	}

	private void responseInit(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
	}


	private UploadResponse validateUpload(HttpServletRequest request,
			String commandStr, String typeStr, String currentFolderStr) {
		// TODO 是否允许上传
		if (!Command.isValidForPost(commandStr)) {
			return UploadResponse.getInvalidCommandError(request);
		}
		if (!ResourceType.isValidType(typeStr)) {
			return UploadResponse.getInvalidResourceTypeError(request);
		}
		if (!UploadUtils.isValidPath(currentFolderStr)) {
			return UploadResponse.getInvalidCurrentFolderError(request);
		}
		return null;
	}

	private FileRepository fileRepository;


	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

}
