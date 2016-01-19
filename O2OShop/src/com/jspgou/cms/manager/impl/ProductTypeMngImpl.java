package com.jspgou.cms.manager.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ProductTypeDao;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.ProductTypeMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductTypeMngImpl implements ProductTypeMng {
	@Transactional(readOnly = true)
	public List<ProductType> getList(Long webId) {
		return productTypeDao.getList(webId);
	}
	@Transactional(readOnly = true)
	public List<ProductType> getList() {
		return productTypeDao.getList();
	}

	@Transactional(readOnly = true)
	public ProductType findById(Long id) {
		ProductType entity = productTypeDao.findById(id);
		return entity;
	}

	public ProductType save(ProductType bean) {
		productTypeDao.save(bean);
		return bean;
	}

	public ProductType update(ProductType bean) {
		ProductType entity = productTypeDao.updateByUpdater(new Updater<ProductType>(bean));
		return entity;
	}

	public ProductType deleteById(Long id) {
		ProductType entity = productTypeDao.deleteById(id);
		return entity;
	}

	public ProductType[] deleteByIds(Long[] ids) {
		ProductType[] beans = new ProductType[ids.length];
		for (int i = 0; i < ids.length; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private BrandMng brandMng;
	@Autowired
	private ProductTypeDao productTypeDao;

	@Autowired
	public void setBrandMng(BrandMng brandMng) {
		this.brandMng = brandMng;
	}

}