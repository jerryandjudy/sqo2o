package com.jspgou.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.AccountItemDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.AccountItem;
import com.jspgou.core.entity.Admin;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class AccountItemDaoImpl extends HibernateBaseDao<AccountItem,Long> implements AccountItemDao{

	@Override
	protected Class<AccountItem> getEntityClass() {
		// TODO Auto-generated method stub
		return AccountItem.class;
	}

	@Override
	public AccountItem findById(Long id) {
		// TODO Auto-generated method stub
		AccountItem entity= get(id);
		return entity;
	}

	@Override
	public AccountItem save(AccountItem bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@Override
	public AccountItem deleteByBean(AccountItem bean) {
		// TODO Auto-generated method stub
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByAccountId(String accountId, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from AccountItem bean where 1=1");
		if(accountId!=null){
		f.append(" and bean.account.id='"+accountId+"'");
		}
		f.append(" order by bean.moneyTime desc,id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public AccountItem findByAccountIdAndOrderId(String accountId,Long ordertId) {
		// TODO Auto-generated method stub
		String hql="from AccountItem bean where status="+ false+" and useStatus="+true+" and moneyType=1";
		if(accountId!=null){
			hql=hql+" and bean.account.id='"+accountId+"'";
		}
		if(ordertId!=null){
			hql=hql+" and bean.order.id="+ordertId+"";
		}
		hql=hql+" order by bean.moneyTime desc";
		return (AccountItem) this.getSession().createQuery(hql).uniqueResult();
	}

}
