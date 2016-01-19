package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.BrandTextDao;
import com.jspgou.cms.entity.BrandText;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class BrandTextDaoImpl extends HibernateBaseDao<BrandText, Long>
		implements BrandTextDao {
	public BrandText save(BrandText bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<BrandText> getEntityClass() {
		return BrandText.class;
	}
}