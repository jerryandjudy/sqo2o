package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopDictionaryDao;
import com.jspgou.cms.entity.ShopDictionary;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopDictionaryDaoImpl extends HibernateBaseDao<ShopDictionary, Long> implements ShopDictionaryDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public Pagination getPage(String name,Long typeId,int pageNo, int pageSize){
		Finder f = Finder.create("from ShopDictionary bean where 1=1");
		if(name!=null){
			f.append(" and bean.name like :name");
			f.setParam("name", "%"+name+"%");
		}
		if(typeId!=null){
			f.append(" and bean.shopDictionaryType.id=:typeId");
			f.setParam("typeId", typeId);
		}
		f.append(" order by bean.priority asc, bean.id asc");
		f.setCacheable(true);
		return find(f, pageNo, pageSize);
	}

	public ShopDictionary findById(Long id) {
		ShopDictionary entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<ShopDictionary> getListByType(Long typeId){
		Finder f = Finder.create("from ShopDictionary bean where 1=1");
		if(typeId!=null){
			f.append(" and bean.shopDictionaryType.id=:typeId");
			f.setParam("typeId", typeId);
		}
		return find(f);
	}

	public ShopDictionary save(ShopDictionary bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopDictionary deleteById(Long id) {
		ShopDictionary entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<ShopDictionary> getEntityClass() {
		return ShopDictionary.class;
	}
}