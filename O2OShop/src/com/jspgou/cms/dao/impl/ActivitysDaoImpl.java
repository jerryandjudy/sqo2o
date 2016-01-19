package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.ActivitysDao;
import com.jspgou.cms.entity.Activitys;
import com.jspgou.cms.entity.Order;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ActivitysDaoImpl extends HibernateBaseDao<Activitys, Long> implements ActivitysDao {
	
	@Override
	public Activitys findById(Long id) {
		Activitys entity = get(id);
		return entity;
	}
	@Override
	public Activitys save(Activitys bean) {
		getSession().save(bean);
		return bean;
	}
	@Override
	public Activitys update(Activitys bean) {
		getSession().update(bean);
		return bean;
	}
	@Override
	public Activitys delete(Activitys bean) {
		if (bean != null) {
			getSession().delete(bean);
		}
		return bean;
	}

	@Override
	protected Class<Activitys> getEntityClass() {
		return Activitys.class;
	}
	@Override
	public Pagination getPage(Long siteId, Boolean isusing, String name,
			int pageNo, int pageSize){
		Finder f=Finder.create("from Activitys bean where 1=1");
		if(siteId!=null){
			f.append(" and bean.website.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if(isusing!=null){
			f.append(" and bean.isusing=:isusing");
			f.setParam("isusing", isusing);
		}
		if(name!=null && name.length()>0){
			f.append(" and bean.name like:name");
			f.setParam("name", "%"+name+"%");
		}
		f.append(" order by bean.addTime");
		return find(f, pageNo, pageSize);
	}
	
	
	

}