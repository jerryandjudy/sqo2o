package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.CartItem;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface CartItemDao {
	
	public CartItem findById(Long id);
	
	public List<CartItem> getlist(Long cartId,Long popularityGroupId);
	
	public CartItem updateByUpdater(Updater<CartItem> updater);
	
	public CartItem deleteById(Long id);
	
	public int deleteByProductId(Long productId);
	public Pagination getlist(Long cartId);
}