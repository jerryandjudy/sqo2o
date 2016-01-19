package com.jspgou.core.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.LogDao;
import com.jspgou.core.entity.Log;
/**
* This class should preserve.
* @preserve
*/
@Repository
public class LogDaoImpl extends HibernateBaseDao<Log, Long> implements LogDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public Log findById(Long id) {
		Log entity = get(id);
		return entity;
	}

	public Log save(Log bean) {
		getSession().save(bean);
		return bean;
	}

	public Log deleteById(Long id) {
		Log entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<Log> getEntityClass() {
		return Log.class;
	}
}