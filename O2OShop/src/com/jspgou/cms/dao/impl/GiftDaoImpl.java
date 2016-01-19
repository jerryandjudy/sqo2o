package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.GiftDao;
import com.jspgou.cms.entity.Gift;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class GiftDaoImpl extends HibernateBaseDao<Gift, Long> implements
		GiftDao {
	public Pagination getPageGift( int pageNo, int pageSize) {
		Finder f = Finder.create("from Gift bean");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	

	public Gift findById(Long id) {
		Gift entity = get(id);
		return entity;
	}

	public Gift save(Gift bean) {
		getSession().save(bean);
		return bean;
	}

	public Gift deleteById(Long id) {
		Gift entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Gift> getEntityClass() {
		return Gift.class;
	}


}