package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.ProductTypeDao;
import com.jspgou.cms.entity.ProductType;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ProductTypeDaoImpl extends HibernateBaseDao<ProductType, Long>
		implements ProductTypeDao {
	@SuppressWarnings("unchecked")
	public List<ProductType> getList(Long webId) {
		String hql = "from ProductType bean where bean.website.id=?";
		return find(hql, webId);
	}
	public List<ProductType> getList() {
		String hql = "from ProductType bean ";
		return find(hql);
	}

	public ProductType findById(Long id) {
		ProductType entity = get(id);
		return entity;
	}

	public ProductType save(ProductType bean) {
		getSession().save(bean);
		return bean;
	}

	public ProductType deleteById(Long id) {
		ProductType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ProductType> getEntityClass() {
		return ProductType.class;
	}
}