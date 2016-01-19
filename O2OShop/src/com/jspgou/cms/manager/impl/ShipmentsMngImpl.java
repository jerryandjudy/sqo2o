package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShipmentsDao;
import com.jspgou.cms.entity.Gathering;
import com.jspgou.cms.entity.Shipments;
import com.jspgou.cms.manager.ShipmentsMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShipmentsMngImpl implements ShipmentsMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = shipmentsDao.getPage(pageNo, pageSize);
		return page;
	}
	
	public List<Shipments> getlist(Long orderId) {
		return shipmentsDao.getlist(orderId);
	}
	
	public void deleteByorderId(Long orderId) {
		List<Shipments> list = getlist(orderId);
		for(Shipments shipments:list){
			deleteById(shipments.getId());
		}
	}

	@Transactional(readOnly = true)
	public Shipments findById(Long id) {
		Shipments entity = shipmentsDao.findById(id);
		return entity;
	}

	public Shipments save(Shipments bean) {
		shipmentsDao.save(bean);
		return bean;
	}

	public Shipments update(Shipments bean) {
		Updater<Shipments> updater = new Updater<Shipments>(bean);
		Shipments entity = shipmentsDao.updateByUpdater(updater);
		return entity;
	}

	public Shipments deleteById(Long id) {
		Shipments bean = shipmentsDao.deleteById(id);
		return bean;
	}
	
	public Shipments[] deleteByIds(Long[] ids) {
		Shipments[] beans = new Shipments[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	private ShipmentsDao shipmentsDao;
}