package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopOrderTypeDaoImpl extends HibernateBaseDao<ShopOrderType, Long> implements ShopOrderTypeDao {
	@SuppressWarnings("unchecked")
	public List<ShopOrderType> getAllList() {
		Criteria crit = createCriteria();
		crit.addOrder(Order.asc(ShopOrderType.PROP_PRIORITY));
		List<ShopOrderType> list = crit.list();
		return list;
	}
	
	

	public ShopOrderType findById(Long id) {
		ShopOrderType entity = get(id);
		return entity;
	}

	public ShopOrderType save(ShopOrderType bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<ShopOrderType> getEntityClass() {
		return ShopOrderType.class;
	}

	@Override
	public ShopOrderType deleteById(Long id) {
		ShopOrderType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}



	@Override
	public ShopOrderType findByTypeCode(String typeCode) {
		// TODO Auto-generated method stub
		ShopOrderType entity = findUniqueByProperty(ShopOrderType.PROP_TYPE_CODE, typeCode);
		
		return entity;
	}

	

	
	
}