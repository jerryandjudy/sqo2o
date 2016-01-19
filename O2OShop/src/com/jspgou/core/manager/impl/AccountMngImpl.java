package com.jspgou.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.manager.AccountMng;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class AccountMngImpl  implements AccountMng{


	@Override
	public Account findById(String id) {
		// TODO Auto-generated method stub
		
		return accountDao.findById(id);
	}

	@Override
	public Account save(Account bean) {
		// TODO Auto-generated method stub
		
		return accountDao.save(bean);
	}
	
	@Override
	public Account updateByUpdater(Updater<Account> updater) {
		// TODO Auto-generated method stub
		return accountDao.updateByUpdater(updater);
	}
	@Autowired
	public AccountDao accountDao;
	@Override
	public Account findByStatus(Integer status) {
		// TODO Auto-generated method stub
		return accountDao.findByStatus(status);
	}
}
