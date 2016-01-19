package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.DlsDao;
import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.manager.DlsMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class DlsMngImpl implements DlsMng {

	@Override
	@Transactional(readOnly = true)
	public List<Dls> getAllList() {
		// TODO Auto-generated method stub
		return dlsDao.getAllList();
	}

	@Override
	@Transactional(readOnly = true)
	public Dls findById(String id) {
		// TODO Auto-generated method stub
		return dlsDao.findById(id);
	}

	@Override
	public Dls save(Dls bean) {
		// TODO Auto-generated method stub
		return dlsDao.save(bean);
	}

	@Override
	public Dls updateByUpdater(Dls bean) {
		// TODO Auto-generated method stub
		Updater<Dls> updater = new Updater<Dls>(bean);
		return dlsDao.updateByUpdater(updater);
	}

	@Override
	public Dls deleteById(String id) {
		// TODO Auto-generated method stub
		return dlsDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return dlsDao.getPageByIsDisabled(isDisabled, pageNo, pageSize);
	}
	
	@Autowired
	public DlsDao dlsDao;
	@Autowired
	private WebsiteDao websiteDao;

	@Override
	public List<Website> getListNotHasDls() {
		// TODO Auto-generated method stub
		return websiteDao.getListNotHasDls();
	}

	@Override
	public Dls findByKetaUserId(String id) {
		// TODO Auto-generated method stub
		return dlsDao.findByKetaUserId(id);
	}
	
	
	
}