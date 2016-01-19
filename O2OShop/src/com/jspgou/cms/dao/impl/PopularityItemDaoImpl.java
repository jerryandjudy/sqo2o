package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.PopularityItemDao;
import com.jspgou.cms.entity.PopularityItem;

@Repository
public class PopularityItemDaoImpl extends HibernateBaseDao<PopularityItem, Long> implements PopularityItemDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public List<PopularityItem> getlist(Long cartId,Long popularityGroupId) {//会员中心购买记录
		Finder f = Finder.create("select bean from PopularityItem bean where 1=1 ");	
		if(cartId!=null){
			f.append(" and bean.cart.id=:cartId");
			f.setParam("cartId", cartId);
		}
		if(popularityGroupId!=null){
			f.append(" and bean.popularityGroup.id=:popularityGroupId");
			f.setParam("popularityGroupId", popularityGroupId);
		}
		
		return find(f);
	}
	
	public PopularityItem findById(Long cartId,Long popularityId){
		String hql = "from PopularityItem bean where bean.cart.id=? and bean.popularityGroup.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, cartId).setParameter(1, popularityId);
		// 做一些容错处理，因为毕竟没有在数据库中限定path是唯一的。
		query.setMaxResults(1);
		return (PopularityItem) query.setCacheable(true).uniqueResult();
	}

	public PopularityItem findById(Long id) {
		PopularityItem entity = get(id);
		return entity;
	}

	public PopularityItem save(PopularityItem bean) {
		getSession().save(bean);
		return bean;
	}

	public PopularityItem deleteById(Long id) {
		PopularityItem entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<PopularityItem> getEntityClass() {
		return PopularityItem.class;
	}
}