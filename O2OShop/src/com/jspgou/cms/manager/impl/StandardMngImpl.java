package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.StandardDao;
import com.jspgou.cms.entity.Standard;
import com.jspgou.cms.manager.StandardMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class StandardMngImpl implements StandardMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = standardDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public Standard findById(Long id) {
		Standard entity = standardDao.findById(id);
		return entity;
	}
	
	public List<Standard> findByTypeId(Long typeId) {
		return standardDao.findByTypeId(typeId);
	}

	public Standard save(Standard bean) {
		standardDao.save(bean);
		return bean;
	}

	public Standard update(Standard bean) {
		Updater<Standard> updater = new Updater<Standard>(bean);
		Standard entity = standardDao.updateByUpdater(updater);
		return entity;
	}

	public Standard deleteById(Long id) {
		Standard bean = standardDao.deleteById(id);
		return bean;
	}
	
	public Standard[] deleteByIds(Long[] ids) {
		Standard[] beans = new Standard[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public Standard[] updatePriority(Long[] ids, Integer[] priority){
		int len = ids.length;
		Standard[] beans = new Standard[len];
		for (int i = 0; i < len; i++) {
			beans[i] = findById(ids[i]);
		}
		return beans;
	}
	@Autowired
	private StandardDao standardDao;

	
}