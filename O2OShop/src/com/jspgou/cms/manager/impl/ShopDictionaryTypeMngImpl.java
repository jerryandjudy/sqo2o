package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopDictionaryTypeDao;
import com.jspgou.cms.entity.ShopDictionaryType;
import com.jspgou.cms.manager.ShopDictionaryTypeMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopDictionaryTypeMngImpl implements ShopDictionaryTypeMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = shopDictionaryTypeDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ShopDictionaryType findById(Long id) {
		ShopDictionaryType entity = shopDictionaryTypeDao.findById(id);
		return entity;
	}
	
	public List<ShopDictionaryType> findAll(){
		return shopDictionaryTypeDao.findAll();
	}

	public ShopDictionaryType save(ShopDictionaryType bean) {
		shopDictionaryTypeDao.save(bean);
		return bean;
	}

	public ShopDictionaryType update(ShopDictionaryType bean) {
		Updater<ShopDictionaryType> updater = new Updater<ShopDictionaryType>(bean);
		ShopDictionaryType entity = shopDictionaryTypeDao.updateByUpdater(updater);
		return entity;
	}

	public ShopDictionaryType deleteById(Long id) {
		ShopDictionaryType bean = shopDictionaryTypeDao.deleteById(id);
		return bean;
	}
	
	public ShopDictionaryType[] deleteByIds(Long[] ids) {
		ShopDictionaryType[] beans = new ShopDictionaryType[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private ShopDictionaryTypeDao shopDictionaryTypeDao;
}