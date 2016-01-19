package com.jspgou.cms.manager.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.BrandDao;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.BrandTextMng;
import com.jspgou.cms.manager.ProductTypeMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class BrandMngImpl implements BrandMng {
	@Transactional(readOnly = true)
	public List<Brand> getAllList() {
		List<Brand> list = brandDao.getAllList();
		return list;
	}

	public List<Brand> getList(){
		return brandDao.getList();
	}
	
	public List<Brand> getListForTag(Long webId, int firstResult, int maxResults) {
		return brandDao.getList(webId, firstResult, maxResults, true);
	}
	
	public Brand getsiftBrand(){
		return brandDao.getsiftBrand();
	}

	@Transactional(readOnly = true)
	public Brand findById(Long id) {
		Brand entity = brandDao.findById(id);
		return entity;
	}

	public Brand save(Brand bean, String text) {
		Brand entity = brandDao.save(bean);
		brandTextMng.save(entity, text);
		return entity;
	}

	public Brand update(Brand bean, String text) {
		Updater<Brand> updater = new Updater<Brand>(bean);
		Brand entity = brandDao.updateByUpdater(updater);
		// 先更新BrandText
		entity.getBrandText().setText(text);
		return entity;
	}

	public Brand deleteById(Long id) {
		Brand entity = findById(id);
		entity = brandDao.deleteById(id);
		return entity;
	}

	public Brand[] deleteByIds(Long[] ids) {
		Brand[] beans = new Brand[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public Brand[] updatePriority(Long[] ids, Integer[] priority) {
		Brand[] beans = new Brand[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}
	
	public boolean brandNameNotExist(String brandName){
		return brandDao.countByBrandName(brandName) <= 0;
	}

	private ProductTypeMng productTypeMng;
	private BrandTextMng brandTextMng;
	@Autowired
	private BrandDao brandDao;

	@Autowired
	public void setProductTypeMng(ProductTypeMng productTypeMng) {
		this.productTypeMng = productTypeMng;
	}

	@Autowired
	public void setBrandTextMng(BrandTextMng brandTextMng) {
		this.brandTextMng = brandTextMng;
	}

}