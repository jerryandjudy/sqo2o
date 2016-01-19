package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class LsfcMngImpl implements LsfcMng {
	
	@Transactional(readOnly = true)
	public List<Lsfc> getAllList() {
		List<Lsfc> list = lsfcDao.getAllList();
		return list;
	}

	@Transactional(readOnly = true)
	public Lsfc findById(Long id) {
		Lsfc entity = lsfcDao.findById(id);
		return entity;
	}

	public Lsfc save(Lsfc bean) {
		Lsfc entity = lsfcDao.save(bean);
		return entity;
	}

	public Lsfc update(Lsfc bean) {
		Updater<Lsfc> updater = new Updater<Lsfc>(bean);
		Lsfc entity = lsfcDao.updateByUpdater(updater);
		return entity;
	}

	public Lsfc deleteById(Long id) {
		return lsfcDao.deleteById(id);
	}

	public Lsfc[] deleteByIds(Long[] ids) {
		Lsfc[] beans = new Lsfc[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	
	
	
	
	
	@Autowired
	public LsfcDao lsfcDao;

	@Override
	public Lsfc[] updatePriority(Long[] ids, Integer[] priority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lsfc findByFctypeId(Long fctypeId, Long websiteId) {
		// TODO Auto-generated method stub
		return lsfcDao.findByFctypeId(fctypeId, websiteId);
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return lsfcDao.getPageByIsDisabled(isDisabled, pageNo, pageSize);
	}

	@Override
	public Lsfc changeIsDisabledById(Long id) {
		// TODO Auto-generated method stub
		return lsfcDao.changeIsDisabledById(id);
	}

	@Override
	public Lsfc[] changeIsDisabledByIds(Long[] ids) {
		// TODO Auto-generated method stub
		Lsfc[] beans = new Lsfc[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = changeIsDisabledById(ids[i]);
		}
		return beans;
		
	}

	@Override
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId,
			Boolean isDisabled, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return lsfcDao.getPageByIsDisabledAndWebsiteId(websiteId, isDisabled, pageNo, pageSize);
	}
	
	
}