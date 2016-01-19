package com.jspgou.cms.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.PopularityGroupDao;
import com.jspgou.cms.entity.PopularityGroup;

@Repository
public class PopularityGroupDaoImpl extends HibernateBaseDao<PopularityGroup, Long> implements PopularityGroupDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public PopularityGroup findById(Long id) {
		PopularityGroup entity = get(id);
		return entity;
	}

	public PopularityGroup save(PopularityGroup bean) {
		getSession().save(bean);
		return bean;
	}

	public PopularityGroup deleteById(Long id) {
		PopularityGroup entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<PopularityGroup> getEntityClass() {
		return PopularityGroup.class;
	}
}