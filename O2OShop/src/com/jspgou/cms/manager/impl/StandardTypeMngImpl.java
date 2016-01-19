package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.StandardTypeDao;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.cms.manager.StandardTypeMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class StandardTypeMngImpl implements StandardTypeMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = standardTypeDao.getPage(pageNo, pageSize);
		return page;
	}
	
	public StandardType getByField(String field){
		return standardTypeDao.getByField(field);
	}

	@Transactional(readOnly = true)
	public StandardType findById(Long id) {
		StandardType entity = standardTypeDao.findById(id);
		return entity;
	}
	
	public List<StandardType> getList(){
		return standardTypeDao.getList();
	}
	
	public List<StandardType> getList(Long categoryId){
		return standardTypeDao.getList(categoryId);
	}

	public StandardType save(StandardType bean) {
		bean=standardTypeDao.save(bean);
		return bean;
	}

	public StandardType update(StandardType bean) {
		Updater<StandardType> updater = new Updater<StandardType>(bean);
		StandardType entity = standardTypeDao.updateByUpdater(updater);
		return entity;
	}

	public StandardType deleteById(Long id) {
		StandardType bean = standardTypeDao.deleteById(id);
		return bean;
	}
	
	public StandardType[] deleteByIds(Long[] ids) {
		StandardType[] beans = new StandardType[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public StandardType[] updatePriority(Long[] wids, Integer[] priority){
		int len = wids.length;
		StandardType[] beans = new StandardType[len];
		for (int i = 0; i < len; i++) {
			beans[i] = findById(wids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}
	@Autowired
	private StandardTypeDao standardTypeDao;

	
}