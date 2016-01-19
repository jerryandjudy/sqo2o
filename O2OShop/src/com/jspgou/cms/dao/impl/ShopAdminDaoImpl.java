package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopAdminDao;
import com.jspgou.cms.entity.ShopAdmin;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopAdminDaoImpl extends HibernateBaseDao<ShopAdmin, Long>
		implements ShopAdminDao {
	public Pagination getPage(Long webId, int pageNo, int pageSize) {
		Finder f = Finder
				.create("from ShopAdmin bean where 1=1  ");
		if(webId!=null){
			f.append(" and (bean.website.id=:webId )");
			f.setParam("webId", webId);
		}
		f.append("order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	public ShopAdmin findById(Long id) {
		ShopAdmin entity = get(id);
		return entity;
	}

	public ShopAdmin save(ShopAdmin bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopAdmin deleteById(Long id) {
		ShopAdmin entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ShopAdmin> getEntityClass() {
		return ShopAdmin.class;
	}
}