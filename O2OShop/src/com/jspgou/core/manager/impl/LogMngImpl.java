package com.jspgou.core.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.LogDao;
import com.jspgou.core.entity.Log;
import com.jspgou.core.manager.LogMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class LogMngImpl implements LogMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public Log findById(Long id) {
		Log entity = dao.findById(id);
		return entity;
	}

	public Log save(Log bean) {
		dao.save(bean);
		return bean;
	}
	
	public void save(String versions, String updatelog){
		Date date = new Date();
		Log bean = new Log();
		bean.setContent(updatelog);
		bean.setTitle(versions);
		bean.setCategory(1);
		bean.setTime(date);
		dao.save(bean);
	}

	public Log update(Log bean) {
		Updater<Log> updater = new Updater<Log>(bean);
		Log entity = dao.updateByUpdater(updater);
		return entity;
	}

	public Log deleteById(Long id) {
		Log bean = dao.deleteById(id);
		return bean;
	}
	
	public Log[] deleteByIds(Long[] ids) {
		Log[] beans = new Log[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private LogDao dao;

	@Autowired
	public void setDao(LogDao dao) {
		this.dao = dao;
	}
}