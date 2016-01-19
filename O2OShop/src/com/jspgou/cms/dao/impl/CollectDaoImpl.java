package com.jspgou.cms.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.CollectDao;
import com.jspgou.cms.entity.Collect;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class CollectDaoImpl extends HibernateBaseDao<Collect, Integer> implements
		CollectDao {

	public Pagination getList(Integer pageSize,Integer pageNo,Long memberId){
		String hql="from Collect bean where 1=1 and bean.member.id=:id";
		Finder f = Finder.create(hql).setParam("id", memberId);
		return this.find(f, pageNo, pageSize);
	}	
	public Pagination getList(Long siteId,Integer pageSize,Integer pageNo,Long memberId){
		String hql="from Collect bean where 1=1 and bean.member.id=:id and bean.website.id=:siteId";
		Finder f = Finder.create(hql).setParam("id", memberId).setParam("siteId", siteId);
		return this.find(f, pageNo, pageSize);
	}	

	public Collect findById(Integer id) {
		Collect entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<Collect> findByProductId(Long productId){//通过productId查收藏
		Finder f = Finder.create("from Collect bean where bean.productSite.id=:id").setParam("id", productId);
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public Collect findByProductFashionId(Long id) {//通过fashid查收藏
		Iterator<Collect> list=this.getSession().createQuery("from Collect bean where bean.fashion.id=:id").setParameter("id", id).iterate();
		if(list.hasNext()){
			return list.next();
		}
		return null;
	}

	public Collect save(Collect bean) {
		getSession().save(bean);
		return bean;
	}

	public Collect deleteById(Integer id) {
		Collect entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Collect> getEntityClass() {
		return Collect.class;
	}
}