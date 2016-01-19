package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ProductTypePropertyDao;
import com.jspgou.cms.entity.ProductTypeProperty;
import com.jspgou.cms.manager.ProductTypePropertyMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductTypePropertyMngImpl implements ProductTypePropertyMng {

	@Autowired
	private ProductTypePropertyDao productTypePropertyDao;
	
	public ProductTypeProperty deleteById(Long id) {
		return productTypePropertyDao.deleteById(id);
	}
	
	public ProductTypeProperty[] deleteByIds(Long[] ids) {
		ProductTypeProperty[] beans = new ProductTypeProperty[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public ProductTypeProperty findById(Long id) {
		return productTypePropertyDao.findById(id);
	}

	public Pagination getList(int pageNo,int pageSize,Long typeid,
		Boolean isCategory,String searchType,String searchContent) {
		return productTypePropertyDao.getList(pageNo,pageSize,typeid,isCategory,searchType,searchContent);
	}
	
	public List<ProductTypeProperty> getList(Long typeId,Boolean isCategory){
		return productTypePropertyDao.getList(typeId, isCategory);
	}
	
	public void saveList(List<ProductTypeProperty> list) {
		for (ProductTypeProperty item : list) {
			save(item);
		}
	}
	
	public ProductTypeProperty save(ProductTypeProperty bean) {
		return productTypePropertyDao.save(bean);
	}
	
	public void updatePriority(Long[] wids, Integer[] sort,
			String[] propertyName, Boolean[] single) {
		ProductTypeProperty item;
		for (int i = 0, len = wids.length; i < len; i++) {
			item = findById(wids[i]);
			item.setPropertyName(propertyName[i]);
			item.setSort(sort[i]);
			item.setSingle(single[i]);
		}
	}

	public List<ProductTypeProperty> findBySearch(String searchType,
			String searchContent) {
		return productTypePropertyDao.findBySearch(searchType, searchContent);
	}

	public List<ProductTypeProperty> findListByTypeId(Long typeid) {
		return productTypePropertyDao.findListByTypeId(typeid);
	}
	
	public List<ProductTypeProperty> getList(Long typeId,boolean isCategory){
		return productTypePropertyDao.getList(typeId, isCategory);
	}

	public ProductTypeProperty update(ProductTypeProperty bean) {
		Updater<ProductTypeProperty> updater = new Updater<ProductTypeProperty>(bean);
		ProductTypeProperty entity = productTypePropertyDao.updateByUpdater(updater);
		return entity;
	}

}

