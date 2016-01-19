package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopDictionaryTypeDao;
import com.jspgou.cms.entity.ShopDictionaryType;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopDictionaryTypeDaoImpl extends HibernateBaseDao<ShopDictionaryType, Long> 
    implements ShopDictionaryTypeDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public ShopDictionaryType findById(Long id) {
		ShopDictionaryType entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<ShopDictionaryType> findAll(){
		Finder f = Finder.create("from ShopDictionaryType bean ");
		return find(f);
	}

	public ShopDictionaryType save(ShopDictionaryType bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopDictionaryType deleteById(Long id) {
		ShopDictionaryType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<ShopDictionaryType> getEntityClass() {
		return ShopDictionaryType.class;
	}
}