package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.FwsDao;
import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.Fws;
import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.manager.FwsMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class FwsMngImpl implements FwsMng {

	@Override
	@Transactional(readOnly = true)
	public List<Fws> getAllList() {
		// TODO Auto-generated method stub
		return fwsDao.getAllList();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Fws> getFwsBySiteAndPsqy(Long siteId,Long province,Long city,Long country,Long street){
		// TODO Auto-generated method stub
		return fwsDao.getFwsBySiteAndPsqy( siteId, province, city, country, street);
	}

	@Override
	@Transactional(readOnly = true)
	public Fws findById(String id) {
		// TODO Auto-generated method stub
		return fwsDao.findById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Fws findByKetaUserId(Long id){
		return fwsDao.findByKetaUserId(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Fws findByIsDef(Boolean isDef,Long siteId) {
		// TODO Auto-generated method stub
		return fwsDao.findByIsDef( isDef, siteId);
	}

	@Override
	public Fws save(Fws bean) {
		// TODO Auto-generated method stub
		Account account=accountDao.save(bean.getAccount());
    	bean.setAccount(account);
		return fwsDao.save(bean);
	}


	@Override
	public Fws deleteById(String id) {
		// TODO Auto-generated method stub
		return fwsDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return fwsDao.getPageByIsDisabled(organizationName, siteId, username, companyName,isDisabled, pageNo, pageSize);
	}
	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return fwsDao.getPageByIsDisabled( isDisabled, pageNo, pageSize);
	}
	@Override
	@Transactional(readOnly = true)
	public Pagination getPageBySiteId(Long siteId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return fwsDao.getPageBySiteId(siteId, pageNo, pageSize);
	}
	
	@Autowired
	private FwsDao fwsDao;
	@Autowired
	private WebsiteDao sitedao;
	private AccountDao accountDao;
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	@Override
	public Fws updateByUpdater(Updater<Fws> updater) {
		// TODO Auto-generated method stub
		return fwsDao.updateByUpdater(updater);
	}
	
}