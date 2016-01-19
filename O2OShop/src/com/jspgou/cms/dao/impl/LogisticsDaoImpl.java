package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.LogisticsDao;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.common.hibernate3.HibernateBaseDao;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class LogisticsDaoImpl extends HibernateBaseDao<Logistics, Long> implements
     LogisticsDao {
	@SuppressWarnings("unchecked")
	public List<Logistics> getAllList() {
		Criteria crit = createCriteria();
		crit.addOrder(Order.asc(Logistics.PROP_PRIORITY));
		List<Logistics> list = crit.list();
		return list;
	}

	public Logistics findById(Long id) {
		Logistics entity = get(id);
		return entity;
	}

	public Logistics save(Logistics bean) {
		getSession().save(bean);
		return bean;
	}

	public Logistics deleteById(Long id) {
		Logistics entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Logistics> getEntityClass() {
		return Logistics.class;
	}
}