package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.ShopConfigDao;
import com.jspgou.cms.entity.ShopConfig;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopConfigDaoImpl extends HibernateBaseDao<ShopConfig, Long>
		implements ShopConfigDao {
	public ShopConfig findById(Long id) {
		ShopConfig entity = get(id);
		return entity;
	}

	public ShopConfig save(ShopConfig bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopConfig deleteById(Long id) {
		ShopConfig entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ShopConfig> getEntityClass() {
		return ShopConfig.class;
	}
}