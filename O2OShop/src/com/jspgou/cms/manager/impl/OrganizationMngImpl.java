package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrderReturnDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class OrganizationMngImpl implements OrganizationMng {

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getAllList() {
		// TODO Auto-generated method stub
		return organizationDao.getAllList();
	}

	@Override
	@Transactional(readOnly = true)
	public Organization findById(Long id) {
		// TODO Auto-generated method stub
		return organizationDao.findById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Organization findByName(String name) {
		// TODO Auto-generated method stub
		return organizationDao.findByName(name);
	}

	@Override
	public Organization save(Organization bean) {
		// TODO Auto-generated method stub
		return organizationDao.save(bean);
	}

	@Override
	public Organization updateByUpdater(Organization bean) {
		// TODO Auto-generated method stub
		Updater<Organization> updater = new Updater<Organization>(bean);
		return organizationDao.updateByUpdater(updater);
	}

	@Override
	public Organization deleteById(Long id) {
		// TODO Auto-generated method stub
		return organizationDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return organizationDao.getPageByIsDisabled(isDisabled, pageNo, pageSize);
	}
	@Autowired
	private OrganizationDao organizationDao;
	
	
}