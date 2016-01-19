package com.jspgou.cms.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.CmsFileDao;
import com.jspgou.cms.entity.CmsFile;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.manager.CmsFileMng;
import com.jspgou.common.hibernate3.Updater;


@Service
@Transactional
public class CmsFileMngImpl implements CmsFileMng {
	
	public CmsFile deleteById(String id) {
		return cmsFileDao.deleteById(id);
	}
	
	public CmsFile deleteByPath(String path){
		return cmsFileDao.deleteByPath(path);
	}
	
	public void deleteByGysId(String gysId){
		cmsFileDao.deleteByGysId(gysId);
	}


	public CmsFile findById(String id) {
		return cmsFileDao.findById(id);
	}

	public CmsFile findByPath(String path) {
		return cmsFileDao.findByPath(path);
	}

	public List<CmsFile> getList(Boolean valid) {
		return cmsFileDao.getList(valid);
	}

	public CmsFile save(CmsFile bean) {
		return cmsFileDao.save(bean);
	}

	public void saveFileByPath(String filepath, String name, Boolean valid) {
		CmsFile attFile=new CmsFile();
		attFile.setFilePath(filepath);
		attFile.setFileName(name);
		attFile.setFileIsvalid(valid);
		save(attFile);
	}

	public void updateFileByPath(String path, Boolean valid, Gys gys) {
		CmsFile file;
		file=findByPath(path);
		if(file!=null){
			file.setGys(gys);
			file.setFileIsvalid(valid);
			update(file);
		}
	}
	public void updateFileByPaths(String[] attachmentPaths,String[]picPaths,String mediaPath,
			String titleImg, String typeImg,String contentImg,Boolean valid,Gys gys){
		//处理附件有效性
		if(attachmentPaths!=null){
			for(String att:attachmentPaths){
				updateFileByPath(att, valid, gys);
			}
		}
		//处理图片集
		if(picPaths!=null){
			for(String pic:picPaths){
				updateFileByPath(pic, valid, gys);
			}
		}
		//处理多媒体
		if(StringUtils.isNotBlank(mediaPath)){
			updateFileByPath(mediaPath, valid, gys);
		}
		//标题图
		if(StringUtils.isNotBlank(titleImg)){
			updateFileByPath(titleImg, valid, gys);
		}
		//类型图
		if(StringUtils.isNotBlank(typeImg)){
			updateFileByPath(typeImg, valid, gys);
		}
		//内容图
		if(StringUtils.isNotBlank(contentImg)){
			updateFileByPath(contentImg, valid, gys);
		}
	}

	public CmsFile update(CmsFile bean) {
		Updater<CmsFile> updater = new Updater<CmsFile>(bean);
		bean = cmsFileDao.updateByUpdater(updater);
		return bean;
	}

	
	@Autowired
	private CmsFileDao cmsFileDao;


}