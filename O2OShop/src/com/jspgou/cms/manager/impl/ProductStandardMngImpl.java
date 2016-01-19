package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.ProductStandardDao;
import com.jspgou.cms.entity.ProductStandard;
import com.jspgou.cms.manager.ProductStandardMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductStandardMngImpl implements ProductStandardMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = productStandardDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ProductStandard findById(Long id) {
		ProductStandard entity = productStandardDao.findById(id);
		return entity;
	}
	
	public ProductStandard findByProductIdAndStandardId(Long productId,Long standardId){
		List<ProductStandard> list=productStandardDao.findByProductIdAndStandardId(productId, standardId);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<ProductStandard> findByProductId(Long productId){
		return productStandardDao.findByProductId(productId);
	}

	public ProductStandard save(ProductStandard bean) {
		productStandardDao.save(bean);
		return bean;
	}

	public ProductStandard update(ProductStandard bean) {
		Updater<ProductStandard> updater = new Updater<ProductStandard>(bean);
		ProductStandard entity = productStandardDao.updateByUpdater(updater);
		return entity;
	}

	public ProductStandard deleteById(Long id) {
		ProductStandard bean = productStandardDao.deleteById(id);
		return bean;
	}
	
	public ProductStandard[] deleteByIds(Long[] ids) {
		ProductStandard[] beans = new ProductStandard[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	private ProductStandardDao productStandardDao;
}