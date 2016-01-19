package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.AccountTxDao;
import com.jspgou.cms.entity.AccountTx;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class AccountTxDaoImpl extends HibernateBaseDao<AccountTx, Long> implements AccountTxDao {

	

	@Override
	public AccountTx findById(Long id) {
		// TODO Auto-generated method stub
		AccountTx entity = get(id);
		return entity;
	}
	@Override
	public AccountTx update(AccountTx bean){
		getSession().update(bean);
		return bean;
	}
	@Override
	public AccountTx save(AccountTx bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	public AccountTx deleteById(Long id) {
		// TODO Auto-generated method stub
		AccountTx bean = findById(id);
		getSession().delete(bean);
		return bean;
	}


	@Override
	protected Class<AccountTx> getEntityClass() {
		// TODO Auto-generated method stub
		return AccountTx.class;
	}

	@Override
	public Pagination findAll(Long siteId, String payStatus, String paySuccessTime, String username, String organizationType,
			int pageNo, int pageSize) {
		
		
		Finder f = Finder
				.create("from  AccountTx bean where 1=1 ");
		if(siteId!=null ){
			f.append(" and bean.website.id="+siteId);
		}
		if(payStatus!=null && payStatus.trim().length()>0){
			f.append(" and bean.payStatus="+payStatus);
		}
		if(paySuccessTime!=null && paySuccessTime.trim().length()>0){
			f.append(" and bean.paySuccessTime like '%"+paySuccessTime+"%'");
		}
		if(username!=null && username.trim().length()>0){
			f.append(" and bean.ketaUser.username like '%"+username+"%'");
		}
		if(organizationType!=null && organizationType.trim().length()>0){
			f.append(" and bean.organizationType like '%"+organizationType+"%'");
		}
		
		
		
		
		f.append(" order by bean.addTime desc");
		return find(f, pageNo, pageSize);
	}
	
	
	

	
	
}