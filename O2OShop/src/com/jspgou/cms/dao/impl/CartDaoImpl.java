package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.CartDao;
import com.jspgou.cms.entity.Cart;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class CartDaoImpl extends HibernateBaseDao<Cart, Long> implements
		CartDao {
	public Cart findById(Long id) {
		Cart entity = get(id);
		return entity;
	}
	public List<Cart> findByMemberId(Long memberId) {
		Finder f = Finder.create("select bean from Cart bean");
		f.append(" where bean.member.id=:memberId ");
		f.setParam("memberId", memberId);
		f.append(" order by bean.website.id,bean.id desc");
		return find(f);
	}
	public Cart findByWebId(Long webId,Long memberId) {
		String hql = "select bean from Cart bean where bean.website.id=? and bean.member.id=?";
		return (Cart) findUnique(hql, webId,memberId);
	}
	public Cart findById(Long id,Long webId) {
		Cart entity = get(id);
		return entity;
	}

	public Cart saveOrUpdate(Cart bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}

	public Cart update(Cart bean) {
		getSession().update(bean);
		return bean;
	}

	public Cart deleteById(Long id) {
		Cart entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<Cart> getEntityClass() {
		return Cart.class;
	}
}