package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopMemberDao;
import com.jspgou.cms.entity.ShopMember;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopMemberDaoImpl extends HibernateBaseDao<ShopMember, Long>
		implements ShopMemberDao {
	public Pagination getPage(Long webId, int pageNo, int pageSize) {
		Finder f = Finder
				.create("from ShopMember bean where 1=1 ");
		if(webId!=null){
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		f.append("order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	public ShopMember findById(Long id) {
		ShopMember entity = get(id);
		return entity;
	}

	public ShopMember save(ShopMember bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopMember deleteById(Long id) {
		ShopMember entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ShopMember> getEntityClass() {
		return ShopMember.class;
	}
}