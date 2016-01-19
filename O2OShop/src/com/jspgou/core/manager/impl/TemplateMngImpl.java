package com.jspgou.core.manager.impl;

import com.jspgou.common.file.FileWrap;
import com.jspgou.core.manager.TemplateMng;
import java.io.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class TemplateMngImpl implements TemplateMng{
    private static final Logger log = LoggerFactory.getLogger(TemplateMngImpl.class);
    
    /**
    * This class should preserve.
    * @preserve
    */
    private FileFilter resFilter = new FileFilter(){
      public boolean accept(File paramFile){
        if (paramFile.isDirectory()) return true;
        String str = FilenameUtils.getExtension(paramFile.getName());
        return FileWrap.allowEdit(str);
      }
    };

    public FileWrap getTplFileWrap(String s, String rootPath){
        FileWrap filewrap = new FileWrap(new File(s), s);
        filewrap.setFilename(rootPath);
        return filewrap;
    }

    public FileWrap getResFileWrap(String s, String rootPath){
        FileWrap filewrap = new FileWrap(new File(s), s, this.resFilter);
        filewrap.setFilename(rootPath);
        return filewrap;
    }
    
    public int uploadResourceFile(String s, MultipartFile[] files){
      if ((files == null) || (files.length == 0))  return 0;
      File localFile = new File(s);
      int i = 0;
      try{
        for (MultipartFile file : files){
          String str = file.getOriginalFilename();
          if ((file.isEmpty()) || (!allowUpload(FilenameUtils.getExtension(str))))
            continue;
          file.transferTo(new File(localFile, str));
          i++;
        }
      }catch (IOException e){
        log.error("upload resource failed", e);
      }
      return i;
    }

    public boolean allowUpload(String s){
        return true;
    }
}
