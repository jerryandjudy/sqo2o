package com.jspgou.cms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.AddressDao;
import com.jspgou.cms.dao.MessageDao;
import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Message;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class MessageDaoImpl extends HibernateBaseDao<Message, Long> implements MessageDao {

	@Override
	protected Class<Message> getEntityClass() {
		// TODO Auto-generated method stub
		
		return Message.class;
	}

	@Override
	public Long getCountByTel(String tel,Integer minute,Integer types) {
		// TODO Auto-generated method stub
		String hql="select count(*) from jc_core_message bean where yxq>NOW() and types= "+types;
		SQLQuery query = this.getSession().createSQLQuery(hql);
		return  Long.valueOf(query.uniqueResult().toString());
	}
	@Override
	public Long getYzmByTel(String tel,String yzm,Integer minute ,Integer types){
		// TODO Auto-generated method stub
		String hql="select count(*) from jc_core_message bean where bean.tel='"+tel+"' and bean.yzm='"+yzm+"' and bean.yxq>NOW() and types= "+types;
		SQLQuery query = this.getSession().createSQLQuery(hql);
		return  Long.valueOf(query.uniqueResult().toString());
	}
	@Override
	public Long getTodayCountByTel(String tel,Integer types){
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hql="select count(*) from Message bean where yxq like'"+formatter.format(new Date())+"%' and tel='"+tel+"'  and types= "+types;
		return (Long) this.findUnique(hql);
	}

	@Override
	public Message save(Message bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	
}