package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.JmfDao;
import com.jspgou.cms.entity.Jmf;
import com.jspgou.cms.manager.JmfMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class JmfMngImpl implements JmfMng {
	
	@Transactional(readOnly = true)
	public List<Jmf> getAllList() {
		List<Jmf> list = jmfDao.getAllList();
		return list;
	}

	@Transactional(readOnly = true)
	public Jmf findById(Long id) {
		Jmf entity = jmfDao.findById(id);
		return entity;
	}

	public Jmf save(Jmf bean) {
		Jmf entity = jmfDao.save(bean);
		return entity;
	}

	public Jmf update(Jmf bean) {
		Updater<Jmf> updater = new Updater<Jmf>(bean);
		Jmf entity = jmfDao.updateByUpdater(updater);
		return entity;
	}

	public Jmf deleteById(Long id) {
		return jmfDao.deleteById(id);
	}

	public Jmf[] deleteByIds(Long[] ids) {
		Jmf[] beans = new Jmf[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	
	
	
	
	
	@Autowired
	public JmfDao jmfDao;
	@Override
	public Jmf[] updatePriority(Long[] ids, Integer[] priority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jmf findByJmftypeId(Long jmftypeId, Long websiteId) {
		// TODO Auto-generated method stub
		return jmfDao.findByJmftypeId(jmftypeId, websiteId);
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return jmfDao.getPageByIsDisabled(isDisabled, pageNo, pageSize);
	}

	@Override
	public Jmf changeIsDisabledById(Long id) {
		// TODO Auto-generated method stub
		return jmfDao.changeIsDisabledById(id);
	}

	@Override
	public Jmf[] changeIsDisabledByIds(Long[] ids) {
		// TODO Auto-generated method stub
		Jmf[] beans = new Jmf[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = changeIsDisabledById(ids[i]);
		}
		return beans;
		
	}

	@Override
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId,
			Boolean isDisabled, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return jmfDao.getPageByIsDisabledAndWebsiteId(websiteId, isDisabled, pageNo, pageSize);
	}
	
	
}