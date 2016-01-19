package com.jspgou.cms.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopMoneyDao;
import com.jspgou.cms.entity.ShopMoney;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopMoneyDaoImpl extends HibernateBaseDao<ShopMoney, Long> implements ShopMoneyDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public Pagination getPage(Long memberId,Boolean status,
			Date startTime,Date endTime,Integer pageSize,Integer pageNo){
		Finder f = Finder.create("select bean from ShopMoney bean where 1=1 ");
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId").setParam("memberId", memberId);
		}
		if(status!=null){
			f.append(" and bean.status=:status").setParam("status", status);
		}
		if(startTime!=null){
			f.append(" and bean.createTime>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.createTime<:endTime");
			f.setParam("endTime", endTime);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	public ShopMoney findById(Long id) {
		ShopMoney entity = get(id);
		return entity;
	}

	public ShopMoney save(ShopMoney bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopMoney deleteById(Long id) {
		ShopMoney entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<ShopMoney> getEntityClass() {
		return ShopMoney.class;
	}
}