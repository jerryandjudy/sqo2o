package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.CmsFile;
import com.jspgou.cms.entity.Gys;


public interface CmsFileMng {
	public List<CmsFile> getList(Boolean valid);

	public CmsFile findById(String id);
	
	public CmsFile findByPath(String path);

	public CmsFile save(CmsFile bean);

	public CmsFile update(CmsFile bean);

	public CmsFile deleteById(String id);
	
	public CmsFile deleteByPath(String path);
	
	public void deleteByGysId(String gysId);
	
	public void saveFileByPath(String filepath,String name,Boolean valid);
	
	public void updateFileByPath(String path,Boolean valid,Gys gys);
	
	public void updateFileByPaths(String[] attachmentPaths,String[]picPaths,String mediaPath,
			String titleImg, String typeImg,String contentImg,Boolean valid,Gys gys);
}