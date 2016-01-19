package com.jspgou.core.manager;

import com.jspgou.common.file.FileWrap;
import org.springframework.web.multipart.MultipartFile;
/**
* This class should preserve.
* @preserve
*/
public interface TemplateMng{

    public FileWrap getTplFileWrap(String s, String rootPath);

    public FileWrap getResFileWrap(String s, String s1);

    public int uploadResourceFile(String s, MultipartFile[] files);
}
