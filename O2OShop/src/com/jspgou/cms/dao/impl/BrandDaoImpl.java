package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.BrandDao;
import com.jspgou.cms.entity.Brand;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class BrandDaoImpl extends HibernateBaseDao<Brand, Long> implements
		BrandDao {
	@SuppressWarnings("unchecked")
	public List<Brand> getAllList() {
		Criteria crit = createCriteria();
		crit.addOrder(Order.asc(Brand.PROP_PRIORITY));
		List<Brand> list = crit.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Brand> getList(Long webId, int firstResult, int maxResults,
			boolean cacheable) {
		Finder f = Finder.create("select bean from Brand bean where bean.website.id=:webId order by bean.priority");
		f.setParam("webId", webId);
		f.setFirstResult(firstResult);
		f.setMaxResults(maxResults);
		f.setCacheable(cacheable);
		return find(f);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Brand> getList() {
		Finder f = Finder.create("from Brand bean where bean.disabled=false");
		f.append(" order by bean.priority");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Brand> getListByCate(Long categoryId){//获得某类型的品牌
		Finder f = Finder.create("select bean from Brand bean");
		if(categoryId!=null){
			f.append(" join bean.categorys cate where cate.id=:categoryId");
			f.setParam("categoryId",categoryId);
		}
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Brand> getListByCateSearchName(String searchName){//根据搜索名称获得品牌
		Finder f = Finder.create("select bean from Brand bean");
		if(searchName!=null){
			f.append(" where bean.id in( select product.brand.id from Product product where product.name like '%"+searchName+"%' group by product.brand.id)");
			//f.append(" join bean.categorys cate where cate.id=:categoryId");
//			f.setParam("searchName",searchName);
		}
		return find(f);
	}
	
	
	
	
	
	
	
	public Brand getsiftBrand(){//获得精选品牌
		return (Brand)this.getSession().createQuery("from Brand bean where bean.sift=true order by bean.id desc")
		.setMaxResults(1).uniqueResult();
	}

	public Brand findById(Long id) {
		Brand entity = get(id);
		return entity;
	}

	public Brand save(Brand bean) {
		getSession().save(bean);
		return bean;
	}

	public Brand deleteById(Long id) {
		Brand entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	public int countByBrandName(String brandName){
		String hql = "select count(*) from Brand bean where bean.name=:brandName";
		Query query = getSession().createQuery(hql);
		query.setParameter("brandName", brandName);
		return ((Number) query.iterate().next()).intValue();
	}

	@Override
	protected Class<Brand> getEntityClass() {
		return Brand.class;
	}

	
}