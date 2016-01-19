package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.PosterDao;
import com.jspgou.cms.entity.Poster;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class PosterDaoImpl extends HibernateBaseDao<Poster, Integer> implements
		PosterDao {
	public Poster findById(Integer id) {
		Poster entity = get(id);
		return entity;
	}

	public Poster saveOrUpdate(Poster bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}

	public Poster update(Poster bean) {
		getSession().update(bean);
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public List<Poster> getPage(){
		return this.getSession().createQuery("from Poster bean").list();
	}


	public Poster deleteById(Integer id) {
		Poster entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<Poster> getEntityClass() {
		return Poster.class;
	}
}