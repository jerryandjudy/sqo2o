package com.jspgou.cms.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopChannelContentDao;
import com.jspgou.cms.entity.ShopChannelContent;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopChannelContentDaoImpl extends HibernateBaseDao<ShopChannelContent, Long> implements ShopChannelContentDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public ShopChannelContent findById(Long id) {
		ShopChannelContent entity = get(id);
		return entity;
	}

	public ShopChannelContent save(ShopChannelContent bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopChannelContent deleteById(Long id) {
		ShopChannelContent entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<ShopChannelContent> getEntityClass() {
		return ShopChannelContent.class;
	}
}