package com.jspgou.cms.manager.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.ExendedItemDao;
import com.jspgou.cms.entity.Exended;
import com.jspgou.cms.entity.ExendedItem;
import com.jspgou.cms.manager.ExendedItemMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ExendedItemMngImpl implements ExendedItemMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = exendedItemDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ExendedItem findById(Long id) {
		ExendedItem entity = exendedItemDao.findById(id);
		return entity;
	}

	public ExendedItem save(ExendedItem item) {
		return exendedItemDao.save(item);
	}

	public ExendedItem update(ExendedItem item) {
		Updater<ExendedItem> updater = new Updater<ExendedItem>(item);
		ExendedItem entity = exendedItemDao.updateByUpdater(updater);
		return entity;
	}

	public ExendedItem deleteById(Long id) {
		ExendedItem bean = exendedItemDao.deleteById(id);
		return bean;
	}

	public ExendedItem[] deleteByIds(Long[] ids) {
		ExendedItem[] beans = new ExendedItem[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	public ExendedItemDao exendedItemDao;

	
}