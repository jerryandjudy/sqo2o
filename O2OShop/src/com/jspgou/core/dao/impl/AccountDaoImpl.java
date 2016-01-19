package com.jspgou.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Admin;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class AccountDaoImpl extends HibernateBaseDao<Account,String> implements AccountDao{

	@Override
	protected Class<Account> getEntityClass() {
		// TODO Auto-generated method stub
		return Account.class;
	}

	@Override
	public Account findById(String id) {
		// TODO Auto-generated method stub
		Account entity= get(id);
		return entity;
	}

	@Override
	public Account save(Account bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public Account findByStatus(Integer status) {
		// TODO Auto-generated method stub
	   String s = "from Account bean where bean.status=:status";
        return (Account)getSession().createQuery(s).setParameter("status", 1).uniqueResult();
	}

}
