package com.jspgou.cms.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ProductFashionDao;
import com.jspgou.cms.entity.ProductFashion;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ProductFashionDaoImpl extends HibernateBaseDao<ProductFashion, Long> implements ProductFashionDao {


	@Override
	protected Class<ProductFashion> getEntityClass() {
		return ProductFashion.class;
	}

	public ProductFashion deleteById(Long id) {
		ProductFashion entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public ProductFashion findById(Long id) {
		ProductFashion entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductFashion> findByProductId(Long productId){
		Finder f = Finder.create(" from ProductFashion bean where bean.productId.id=:productId ");
		return find(f);
	}

	public Pagination getPage(Long productId, int pageNo, int pageSize) {
		Finder f = Finder.create(" from ProductFashion bean where bean.productId.id=:productId ");
		f.setParam("productId", productId);
		f.setCacheable(true);
		return find(f, pageNo, pageSize);
	}
	
	public ProductFashion save(ProductFashion bean) {
		getSession().save(bean);
		return bean;
	}
	
	public Pagination productLack(Integer status,Integer count,int pageNo, int pageSize) {
		Finder f = Finder.create("from ProductFashion bean where bean.lackRemind=:status ");
		f.setParam("status", status);
		if(count == null){
			count = 5;
		}
		f.append(" and bean.stockCount < :count").setParam("count", count);
		return find(f, pageNo, pageSize);
	}

	public Integer productLackCount(Integer status,Integer count) {
		String hql = " select count(bean) from ProductFashion bean where bean.lackRemind=:status ";
		if(count == null){
			count = 5;
		}
		hql += " and bean.stockCount < :count";
		Iterator<Integer> ite = getSession().createQuery(hql).setInteger("count", count).setInteger("status", status).iterate();
		Integer result = 0;
		if(ite.hasNext()){
			result = Integer.parseInt(ite.next()+"");
		}
		return result;
		
	}

	public Pagination getSaleTopPage(int pageNo, int pageSize) {
		Finder f = Finder.create(" from ProductFashion bean order by bean.saleCount desc");
		return find(f, pageNo, pageSize);
	}
	
	public ProductFashion getPfashion(Long productId,Long fashId){//获得商品某一款式 王则武
		return (ProductFashion)this.getSession().createQuery("from ProductFashion bean where bean.productId.id=:pid and bean.id=:fid")
		.setParameter("pid", productId).setParameter("fid", fashId).uniqueResult();
	}
	
	public Boolean getOneFashion(Long productId){
		Finder f=Finder.create("from ProductFashion bean where bean.productId.id=:id").setParam("id", productId);
		List l=find(f);
		if(l.size()<=1){
			return false;
		}else{
			return true;
		}
	}

}

