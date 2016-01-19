package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.ProductExtDao;
import com.jspgou.cms.entity.ProductExt;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ProductExtDaoImpl extends HibernateBaseDao<ProductExt, Long>
		implements ProductExtDao {

	public ProductExt findById(Long id) {
		ProductExt entity = get(id);
		return entity;
	}

	public ProductExt save(ProductExt bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<ProductExt> getEntityClass() {
		return ProductExt.class;
	}
}