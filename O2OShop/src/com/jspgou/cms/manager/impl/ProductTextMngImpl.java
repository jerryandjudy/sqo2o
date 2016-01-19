package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ProductTextDao;
import com.jspgou.cms.entity.ProductText;
import com.jspgou.cms.manager.ProductTextMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductTextMngImpl implements ProductTextMng {
	public ProductText update(ProductText bean) {
		Updater<ProductText> updater = new Updater<ProductText>(bean);
		ProductText entity = productTextDao.updateByUpdater(updater);
		return entity;
	}

	public ProductText save(ProductText bean) {
		return productTextDao.save(bean);
	}
	@Autowired
	private ProductTextDao productTextDao;
}