package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShipmentsDao;
import com.jspgou.cms.entity.Shipments;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShipmentsDaoImpl extends HibernateBaseDao<Shipments, Long> implements ShipmentsDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public List<Shipments> getlist(Long orderId) {
		Finder f = Finder.create("from Shipments bean where bean.indent.id=:id");
        f.setParam("id", orderId);
		return find(f);
	}

	public Shipments findById(Long id) {
		Shipments entity = get(id);
		return entity;
	}

	public Shipments save(Shipments bean) {
		getSession().save(bean);
		return bean;
	}

	public Shipments deleteById(Long id) {
		Shipments entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<Shipments> getEntityClass() {
		return Shipments.class;
	}
}