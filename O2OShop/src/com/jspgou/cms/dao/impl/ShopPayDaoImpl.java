package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopPayDao;
import com.jspgou.cms.entity.ShopPay;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopPayDaoImpl extends HibernateBaseDao<ShopPay, Integer> implements
		ShopPayDao {
	public Pagination getPageShopPay( int pageNo, int pageSize) {
		Finder f = Finder.create("from ShopPay bean");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	

	public ShopPay findById(Integer id) {
		ShopPay entity = get(id);
		return entity;
	}

	public ShopPay save(ShopPay bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopPay deleteById(Integer id) {
		ShopPay entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ShopPay> getEntityClass() {
		return ShopPay.class;
	}


}