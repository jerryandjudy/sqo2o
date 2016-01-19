package com.jspgou.cms.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;
import com.jspgou.cms.dao.AdvertiseDao;
import com.jspgou.cms.entity.Advertise;
import com.jspgou.cms.manager.AdspaceMng;
import com.jspgou.cms.manager.AdvertiseMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class AdvertiseMngImpl implements AdvertiseMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Long categoryId, Integer adspaceId,
			Boolean enabled, int pageNo, int pageSize,Integer count) {
		Pagination page = advertiseDao.getPage(categoryId, adspaceId, enabled, pageNo,
				pageSize,count);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPage(Website website, Integer adspaceId,
			Boolean enabled, int pageNo, int pageSize,Integer count) {
		Pagination page = advertiseDao.getPage(website, adspaceId, enabled, pageNo,
				pageSize,count);
		return page;
	}

	@Transactional(readOnly = true)
	public List<Advertise> getList(Integer adspaceId, Boolean enabled) {
		return advertiseDao.getList(adspaceId, enabled);
	}

	@Transactional(readOnly = true)
	public Advertise findById(Integer id) {
		Advertise entity = advertiseDao.findById(id);
		return entity;
	}

	public Advertise save(Advertise bean, Integer adspaceId,
			Map<String, String> attr) {
		bean.setAdspace(adspaceMng.findById(adspaceId));
		bean.setAttr(attr);
		bean.init();
		advertiseDao.save(bean);
		return bean;
	}

	public Advertise update(Advertise bean, Integer adspaceId,
			Map<String, String> attr) {
		Updater<Advertise> updater = new Updater<Advertise>(bean);
		bean = advertiseDao.updateByUpdater(updater);
		bean.setAdspace(adspaceMng.findById(adspaceId));
		bean.getAttr().clear();
		bean.getAttr().putAll(attr);
		return bean;
	}

	public Advertise deleteById(Integer id) {
		Advertise bean = advertiseDao.deleteById(id);
		return bean;
	}

	public Advertise[] deleteByIds(Integer[] ids) {
		Advertise[] beans = new Advertise[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public void display(Integer id) {
		Advertise ad = findById(id);
		if (ad != null) {
			ad.setDisplayCount(ad.getDisplayCount() + 1);
		}
	}

	public void click(Integer id) {
		Advertise ad = findById(id);
		if (ad != null) {
			ad.setClickCount(ad.getClickCount() + 1);
		}
	}

	
	

	private AdspaceMng adspaceMng;
	@Autowired
	private AdvertiseDao advertiseDao;

	@Autowired
	public void setAdspaceMng(AdspaceMng adspaceMng) {
		this.adspaceMng = adspaceMng;
	}

}