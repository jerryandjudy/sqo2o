package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.AccountTxDao;
import com.jspgou.cms.entity.AccountTx;
import com.jspgou.cms.entity.Accountcz;
import com.jspgou.cms.entity.Activitys;
import com.jspgou.cms.manager.AccountTxMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.dao.WebsiteDao;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class AccountTxMngImpl implements AccountTxMng {

	
	

	@Override
	@Transactional(readOnly = true)
	public AccountTx findById(Long id) {
		// TODO Auto-generated method stub
		return accountTxDao.findById(id);
	}

	

	@Override
	public AccountTx deleteById(Long id) {
		// TODO Auto-generated method stub
		//String ids = Long.toString(id);
		return accountTxDao.deleteById(id);
	}
	@Override
	public AccountTx save(AccountTx bean) {
		// TODO Auto-generated method stub
		return accountTxDao.save(bean);
	}
	@Override
	public AccountTx update(AccountTx bean) {
		// TODO Auto-generated method stub
		return accountTxDao.update(bean);
	}

	@Override
	public Pagination findAll(Long siteId, String payStatus,
			String paySuccessTime, String username, String organizationType, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pagination page;
		
		page = accountTxDao.findAll(siteId, payStatus, paySuccessTime, username, organizationType, pageNo, pageSize);
		

		return page;
	}
	
	
	@Autowired
	public AccountTxDao accountTxDao;
	@Autowired
	private WebsiteDao websiteDao;
	
	

	
}