package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Cart;

/**
* This class should preserve.
* @preserve
*/
public interface CartDao {
	public Cart findById(Long id);
	public List<Cart> findByMemberId(Long memberId);
	public Cart findByWebId(Long webId,Long memberId);
	public Cart findById(Long id,Long webId);

	public Cart saveOrUpdate(Cart bean);

	public Cart update(Cart bean);

	public Cart deleteById(Long id);
	
}