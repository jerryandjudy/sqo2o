package com.jspgou.cms.manager.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ShippingDao;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.manager.ShippingMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShippingMngImpl implements ShippingMng {
	@Transactional(readOnly = true)
	public List<Shipping> getList(Long webId, boolean all) {
		return shippingDao.getList(webId, all, false);
	}

	@Transactional(readOnly = true)
	public List<Shipping> getListForCart(Long webId, Long countryId,
			int weight, int count) {
		List<Shipping> list = shippingDao.getList(webId, false, true);
		for (Shipping shipping : list) {
//			shipping.setPrice(shipping.calPrice(weight));
		}
		return list;
	}

	@Transactional(readOnly = true)
	public Shipping findById(Long id) {
		Shipping entity = shippingDao.findById(id);
		return entity;
	}

	public Shipping save(Shipping bean) {
		shippingDao.save(bean);
		return bean;
	}

	public Shipping update(Shipping bean) {
		Shipping entity = findById(bean.getId());
		Updater<Shipping> updater = new Updater<Shipping>(bean);
		entity = shippingDao.updateByUpdater(updater);
		return entity;
	}

	public Shipping deleteById(Long id) {
		Shipping bean = shippingDao.deleteById(id);
		return bean;
	}

	public Shipping[] deleteByIds(Long[] ids) {
		Shipping[] beans = new Shipping[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public Shipping[] updatePriority(Long[] ids, Integer[] priority) {
		Shipping[] beans = new Shipping[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	@Autowired
	private ShippingDao shippingDao;
}