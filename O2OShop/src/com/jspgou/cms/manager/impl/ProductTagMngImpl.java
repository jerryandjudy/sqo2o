package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jspgou.cms.dao.ProductTagDao;
import com.jspgou.cms.entity.ProductTag;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductTagMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductTagMngImpl implements ProductTagMng {
	@Transactional(readOnly = true)
	public List<ProductTag> getList(Long webId) {
		List<ProductTag> list = productTagDao.getList(webId);
		return list;
	}
	@Transactional(readOnly = true)
	public List<ProductTag> getList() {
		List<ProductTag> list = productTagDao.getList();
		return list;
	}

	@Transactional(readOnly = true)
	public ProductTag findById(Long id) {
		ProductTag entity = productTagDao.findById(id);
		return entity;
	}

	public ProductTag save(ProductTag bean) {
		bean.init();
		productTagDao.save(bean);
		return bean;
	}

	public ProductTag[] updateTagName(Long[] ids, String[] tagNames) {
		Assert.notEmpty(ids);
		Assert.notEmpty(tagNames);
		if (ids.length != tagNames.length) {
			throw new IllegalArgumentException(
					"ids length not equals tagNames length");
		}
		ProductTag[] tags = new ProductTag[ids.length];
		ProductTag tag;
		for (int i = 0, len = ids.length; i < len; i++) {
			tag = findById(ids[i]);
			tag.setName(tagNames[i]);
			tags[i] = tag;
		}
		return tags;
	}

	public ProductTag[] deleteByIds(Long[] ids) {
		ProductTag[] beans = new ProductTag[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
		}
		productMng.deleteTagAssociation(beans);
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = productTagDao.deleteById(ids[i]);
		}
		return beans;
	}

	private ProductMng productMng;

	@Autowired
	public void setProductMng(ProductMng productMng) {
		this.productMng = productMng;
	}
	@Autowired
	private ProductTagDao productTagDao;

}