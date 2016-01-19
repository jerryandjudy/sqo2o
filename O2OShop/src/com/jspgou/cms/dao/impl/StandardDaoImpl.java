package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.StandardDao;
import com.jspgou.cms.entity.Standard;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class StandardDaoImpl extends HibernateBaseDao<Standard, Long> implements StandardDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Finder f=Finder.create("from Standard bean where 1=1 ");
		f.append(" order by bean.priority");
		return find(f, pageNo, pageSize);
	}

	public Standard findById(Long id) {
		Standard entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<Standard> findByTypeId(Long typeId){
		String hql="from Standard bean where 1=1";
		Finder f=Finder.create(hql);
		f.append(" and bean.type.id=:typeId").setParam("typeId", typeId);
		f.append(" order by bean.priority");
		return find(f);
	}

	public Standard save(Standard bean) {
		getSession().save(bean);
		return bean;
	}

	public Standard deleteById(Long id) {
		Standard entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<Standard> getEntityClass() {
		return Standard.class;
	}
}