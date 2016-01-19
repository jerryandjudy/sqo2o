package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.ProductTagDao;
import com.jspgou.cms.entity.ProductTag;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ProductTagDaoImpl extends HibernateBaseDao<ProductTag, Long>
		implements ProductTagDao {
	@SuppressWarnings("unchecked")
	public List<ProductTag> getList(Long webId) {
		String hql = "from ProductTag bean where 1=1";
		if(webId!=null){
			hql=hql+" and  bean.website.id="+webId;
		}
		return find(hql);
	}
	public List<ProductTag> getList() {
		String hql = "from ProductTag bean ";
		return find(hql);
	}

	public ProductTag findById(Long id) {
		ProductTag entity = get(id);
		return entity;
	}

	public ProductTag save(ProductTag bean) {
		getSession().save(bean);
		return bean;
	}

	public ProductTag deleteById(Long id) {
		ProductTag entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ProductTag> getEntityClass() {
		return ProductTag.class;
	}
}