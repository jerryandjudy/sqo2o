package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.LogisticsTextDao;
import com.jspgou.cms.entity.LogisticsText;
import com.jspgou.common.hibernate3.HibernateBaseDao;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class LogisticsTextDaoImpl extends HibernateBaseDao<LogisticsText, Long>
		implements LogisticsTextDao {
	public LogisticsText save(LogisticsText bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<LogisticsText> getEntityClass() {
		return LogisticsText.class;
	}
}