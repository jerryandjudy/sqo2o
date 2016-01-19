package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductSite;

/**
* This class should preserve.
* @preserve
*/
public interface CartMng {
	public Cart findById(Long id);
	public List<Cart> findByMemberId(Long memberId);
	public Cart findById(Long id,Long webId);

	public Cart update(Cart cart);

	public Cart deleteById(Long id);
	
	public Cart addGift(Long giftId, int count, boolean isAdd,
			Long memberId, Long webId);
	

	//isAdd是否可以叠加
	public Cart collectAddItem(Product product,Long productFashId,Long popularityId,int count,boolean isAdd,Long memberId, Long webId);
	//isAdd是否可以叠加
	public Cart collectAddItem(ProductSite productSite,Long productFashId,Long popularityId,int count,boolean isAdd,Long memberId, Long webId);
}