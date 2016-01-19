package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.CartItem;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface CartItemMng {
	
	public List<CartItem> getlist(Long cartId,Long popularityGroupId);
	public Pagination getlist(Long cartId);
	
	public CartItem findById(Long id);
	
	public CartItem updateByUpdater(CartItem updater);
	
	public CartItem deleteById(Long id);
	
	public CartItem[] deleteByIds(Long[] ids);
	
	public int deleteByProductId(Long productId);
}

