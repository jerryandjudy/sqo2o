package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.GatheringDao;
import com.jspgou.cms.entity.Gathering;
import com.jspgou.cms.manager.GatheringMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class GatheringMngImpl implements GatheringMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = gatheringDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public Gathering findById(Long id) {
		Gathering entity = gatheringDao.findById(id);
		return entity;
	}
	
	public List<Gathering> getlist(Long orderId) {
		return gatheringDao.getlist(orderId);
	}
	
	public void deleteByorderId(Long orderId) {
		List<Gathering> list = getlist(orderId);
		for(Gathering gathering:list){
			deleteById(gathering.getId());
		}
	}

	public Gathering save(Gathering bean) {
		gatheringDao.save(bean);
		return bean;
	}

	public Gathering update(Gathering bean) {
		Updater<Gathering> updater = new Updater<Gathering>(bean);
		Gathering entity = gatheringDao.updateByUpdater(updater);
		return entity;
	}

	public Gathering deleteById(Long id) {
		Gathering bean = gatheringDao.deleteById(id);
		return bean;
	}
	
	public Gathering[] deleteByIds(Long[] ids) {
		Gathering[] beans = new Gathering[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	public GatheringDao gatheringDao;

	
}