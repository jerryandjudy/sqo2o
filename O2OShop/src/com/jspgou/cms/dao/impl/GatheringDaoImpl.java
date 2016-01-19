package com.jspgou.cms.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.GatheringDao;
import com.jspgou.cms.entity.Gathering;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class GatheringDaoImpl extends HibernateBaseDao<Gathering, Long> implements GatheringDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public List<Gathering> getlist(Long orderId) {
		Finder f = Finder.create("from Gathering bean where bean.indent.id=:id");
        f.setParam("id", orderId);
		return find(f);
	}

	public Gathering findById(Long id) {
		Gathering entity = get(id);
		return entity;
	}

	public Gathering save(Gathering bean) {
		getSession().save(bean);
		return bean;
	}

	public Gathering deleteById(Long id) {
		Gathering entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<Gathering> getEntityClass() {
		return Gathering.class;
	}
}