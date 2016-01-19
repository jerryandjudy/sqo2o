package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.GiftExchangeDao;
import com.jspgou.cms.entity.GiftExchange;

@Repository
public class GiftExchangeDaoImpl extends HibernateBaseDao<GiftExchange, Long> implements GiftExchangeDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public List<GiftExchange> getlist(Long memberId) {
		Finder f = Finder.create("from GiftExchange bean where bean.member.id=:memberId");
        f.setParam("memberId", memberId);
		return find(f);
	}

	public GiftExchange findById(Long id) {
		GiftExchange entity = get(id);
		return entity;
	}

	public GiftExchange save(GiftExchange bean) {
		getSession().save(bean);
		return bean;
	}

	public GiftExchange deleteById(Long id) {
		GiftExchange entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<GiftExchange> getEntityClass() {
		return GiftExchange.class;
	}
}