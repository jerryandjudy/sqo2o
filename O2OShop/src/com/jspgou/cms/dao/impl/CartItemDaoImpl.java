package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.CartItemDao;
import com.jspgou.cms.entity.CartItem;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class CartItemDaoImpl extends HibernateBaseDao<CartItem, Long> implements CartItemDao {
	
	public CartItem findById(Long id) {
		CartItem entity = get(id);
		return entity;
	}
	
	public CartItem deleteById(Long id) {
		CartItem entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public List<CartItem> getlist(Long cartId,Long popularityGroupId) {//会员中心购买记录
		Finder f = Finder.create("select bean from CartItem bean");
		f.append(" where bean.cart.id=:cartId ");
		f.setParam("cartId", cartId);
		if(popularityGroupId!=null && popularityGroupId>0){
			f.append("  bean.popularityGroup.id=:popularityGroupId");
			f.setParam("popularityGroupId",popularityGroupId);

		}
		f.append(" order by bean.id desc");
		return find(f);
	}
	public Pagination getlist(Long cartId) {//
		Finder f = Finder.create("select bean from CartItem bean");
		f.append(" where bean.cart.id=:cartId ");
		f.setParam("cartId", cartId);
		f.append(" order by bean.id desc");
		return this.find(f, 1, 1);
	}
	
	public int deleteByProductId(Long productId) {
		String hql = " delete CartItem bean where bean.product.id=:productId";
		return getSession().createQuery(hql).setParameter("productId", productId).executeUpdate();
	}
	
	protected Class<CartItem> getEntityClass() {
		return CartItem.class;
	}
}

