package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ShopConfigDao;
import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.manager.ShopConfigMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopConfigMngImpl implements ShopConfigMng {
	@Transactional(readOnly = true)
	public ShopConfig findById(Long id) {
		ShopConfig entity = shopConfigDao.findById(id);
		return entity;
	}

	public ShopConfig save(ShopConfig bean) {
		shopConfigDao.save(bean);
		return bean;
	}

	public ShopConfig update(ShopConfig bean) {
		Updater<ShopConfig> updater = new Updater<ShopConfig>(bean);
		ShopConfig entity = shopConfigDao.updateByUpdater(updater);
		return entity;
	}

	public ShopConfig deleteById(Long id) {
		ShopConfig bean = shopConfigDao.deleteById(id);
		return bean;
	}

	@Autowired 
	private ShopConfigDao shopConfigDao;
}