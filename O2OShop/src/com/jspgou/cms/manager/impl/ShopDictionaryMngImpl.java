package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopDictionaryDao;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.manager.ShopDictionaryMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopDictionaryMngImpl implements ShopDictionaryMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = shopDictionaryDao.getPage(pageNo, pageSize);
		return page;
	}
	
	public Pagination getPage(String name,Long typeId,int pageNo, int pageSize){
		Pagination page = shopDictionaryDao.getPage(name,typeId,pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ShopDictionary findById(Long id) {
		ShopDictionary entity = shopDictionaryDao.findById(id);
		return entity;
	}
	
	public List<ShopDictionary> getListByType(Long typeId){
	     return shopDictionaryDao.getListByType(typeId);
	}


	public ShopDictionary save(ShopDictionary bean) {
		shopDictionaryDao.save(bean);
		return bean;
	}

	public ShopDictionary update(ShopDictionary bean) {
		Updater<ShopDictionary> updater = new Updater<ShopDictionary>(bean);
		ShopDictionary entity = shopDictionaryDao.updateByUpdater(updater);
		return entity;
	}

	public ShopDictionary deleteById(Long id) {
		ShopDictionary bean = shopDictionaryDao.deleteById(id);
		return bean;
	}
	
	public ShopDictionary[] deleteByIds(Long[] ids) {
		ShopDictionary[] beans = new ShopDictionary[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public ShopDictionary[] updatePriority(Long[] ids, Integer[] priority){
		int len = ids.length;
		ShopDictionary[] beans = new ShopDictionary[len];
		for (int i = 0; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	@Autowired
	private ShopDictionaryDao shopDictionaryDao;

}