package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.ProductTextDao;
import com.jspgou.cms.entity.ProductText;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ProductTextDaoImpl extends HibernateBaseDao<ProductText, Long>
		implements ProductTextDao {
	public ProductText save(ProductText text) {
		getSession().save(text);
		return text;
	}

	@Override
	protected Class<ProductText> getEntityClass() {
		return ProductText.class;
	}
}