package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ExendedDao;
import com.jspgou.cms.entity.Exended;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ExendedDaoImpl extends HibernateBaseDao<Exended, Long> implements ExendedDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Finder f = Finder.create("from Exended bean where 1=1");
		f.append(" order by bean.priority");
		return find(f, pageNo, pageSize);
	}

	public Exended findById(Long id) {
		Exended entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<Exended> getList(){
		Finder f = Finder.create("from Exended bean where 1=1");
		f.append(" order by bean.priority");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Exended> getList(Long typeId){
		Finder f = Finder.create("select bean from Exended bean");
		if(typeId!=null){
			f.append(" inner join bean.productTypes ptype ");
			f.append(" where ptype.id=:typeId").setParam("typeId", typeId);
		}
		f.append(" order by bean.priority");
		return find(f);
	}
	

	public Exended save(Exended bean) {
		getSession().save(bean);
		return bean;
	}

	public Exended deleteById(Long id) {
		Exended entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<Exended> getEntityClass() {
		return Exended.class;
	}
}