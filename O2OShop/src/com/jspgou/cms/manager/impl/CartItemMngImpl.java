package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.CartItemDao;
import com.jspgou.cms.entity.CartItem;
import com.jspgou.cms.manager.CartItemMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class CartItemMngImpl implements CartItemMng {
	
	@Transactional(readOnly = true)
	public CartItem findById(Long id) {
		CartItem entity = cartItemDao.findById(id);
		return entity;
	}
	
	public List<CartItem> getlist(Long cartId,Long popularityGroupId){
		return cartItemDao.getlist(cartId, popularityGroupId);
	}
	public Pagination  getlist(Long cartId){
		return cartItemDao.getlist(cartId);
	}
	
	public CartItem updateByUpdater(CartItem bean){
		Updater<CartItem> updater = new Updater<CartItem>(bean);
		return cartItemDao.updateByUpdater(updater);
	}
	
	public CartItem deleteById(Long id) {
		CartItem bean = cartItemDao.deleteById(id);
		return bean;
	}

	public CartItem[] deleteByIds(Long[] ids) {
		CartItem[] beans = new CartItem[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public int deleteByProductId(Long productId) {
		return cartItemDao.deleteByProductId(productId);
	}
	
	
	@Autowired
	private CartItemDao cartItemDao;
}

