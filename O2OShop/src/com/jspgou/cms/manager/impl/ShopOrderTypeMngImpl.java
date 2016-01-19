package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.cms.manager.ShopOrderTypeMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopOrderTypeMngImpl implements ShopOrderTypeMng {
	
	@Transactional(readOnly = true)
	public List<ShopOrderType> getAllList() {
		List<ShopOrderType> list = shopOrderTypeDao.getAllList();
		return list;
	}

	@Transactional(readOnly = true)
	public ShopOrderType findById(Long id) {
		ShopOrderType entity = shopOrderTypeDao.findById(id);
		return entity;
	}

	public ShopOrderType save(ShopOrderType bean) {
		ShopOrderType entity = shopOrderTypeDao.save(bean);
		return entity;
	}

	public ShopOrderType update(ShopOrderType bean) {
		Updater<ShopOrderType> updater = new Updater<ShopOrderType>(bean);
		ShopOrderType entity = shopOrderTypeDao.updateByUpdater(updater);
		return entity;
	}

	public ShopOrderType deleteById(Long id) {
		return shopOrderTypeDao.deleteById(id);
	}

	public ShopOrderType[] deleteByIds(Long[] ids) {
		ShopOrderType[] beans = new ShopOrderType[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public ShopOrderType[] updatePriority(Long[] ids, Integer[] priority) {
		ShopOrderType[] beans = new ShopOrderType[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}
	
	
	
	
	@Autowired
	private ShopOrderTypeDao shopOrderTypeDao;

	@Override
	public ShopOrderType findByTypeCode(String typeCode) {
		// TODO Auto-generated method stub
		ShopOrderType entity = shopOrderTypeDao.findByTypeCode(typeCode);
		return entity;
	}
	
	
}