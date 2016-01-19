package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.StandardTypeDao;
import com.jspgou.cms.entity.StandardType;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class StandardTypeDaoImpl extends HibernateBaseDao<StandardType, Long> implements StandardTypeDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Finder f = Finder.create("from StandardType bean where 1=1");
		f.append(" order by bean.priority");
		return find(f, pageNo, pageSize);
	}

	public StandardType getByField(String field){
		return (StandardType)findUniqueByProperty("field", field);
	}
	 
	public StandardType findById(Long id) {
		StandardType entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<StandardType> getList(){
		Finder f = Finder.create("from StandardType bean where 1=1");
		f.append(" order by bean.priority");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<StandardType> getList(Long categoryId){
		Finder f = Finder.create("select bean from StandardType bean ");
		f.append(" inner join bean.categorys category");
		f.append(" where category.id=:categoryId").setParam("categoryId", categoryId);
		f.append(" order by bean.priority");
		return find(f);
	}
	

	public StandardType save(StandardType bean) {
		getSession().save(bean);
		return bean;
	}

	public StandardType deleteById(Long id) {
		StandardType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<StandardType> getEntityClass() {
		return StandardType.class;
	}
}