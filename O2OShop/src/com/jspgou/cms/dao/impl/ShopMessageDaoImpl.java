package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.ShopMessageDao;
import com.jspgou.cms.entity.ShopMessage;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopMessageDaoImpl extends HibernateBaseDao<ShopMessage, Long> implements ShopMessageDao {
	public ShopMessage findById(Long id) {
		ShopMessage entity = get(id);
		return entity;
	}

	public ShopMessage saveOrUpdate(ShopMessage bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	public Pagination getPage(int pageNo,int pageSize){
		Finder f=Finder.create("from ShopMessage bean ");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	public ShopMessage update(ShopMessage bean) {
		getSession().update(bean);
		return bean;
	}
	


	public ShopMessage deleteById(Long id) {
		ShopMessage entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<ShopMessage> getEntityClass() {
		return ShopMessage.class;
	}
}