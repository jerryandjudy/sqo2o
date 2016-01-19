package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.CmsFile;
import com.jspgou.common.hibernate3.Updater;


public interface CmsFileDao {
	public List<CmsFile> getList(Boolean valid);

	public CmsFile findById(String id);
	
	public CmsFile findByPath(String path);

	public CmsFile save(CmsFile bean);

	public CmsFile updateByUpdater(Updater<CmsFile> updater);

	public CmsFile deleteById(String id);
	
	public CmsFile deleteByPath(String path);
	
	public void deleteByGysId(String gysId);
}