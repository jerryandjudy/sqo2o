package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.CartDao;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.manager.CartMng;
import com.jspgou.cms.manager.GiftMng;
import com.jspgou.cms.manager.PopularityGroupMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.core.manager.MemberMng;
import com.jspgou.core.manager.WebsiteMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class CartMngImpl implements CartMng {
	@Transactional(readOnly = true)
	public Cart findById(Long id) {
		Cart entity = cartDao.findById(id);
		return entity;
	}
	@Transactional(readOnly = true)
	public List<Cart> findByMemberId(Long memberId) {
		return cartDao.findByMemberId(memberId);
	}
	@Transactional(readOnly = true)
	public Cart findByWebId(Long webId,Long memberId) {
		return cartDao.findByWebId(webId,memberId);
	}
	@Transactional(readOnly = true)
	public Cart findById(Long id,Long webId) {
		Cart entity = cartDao.findByWebId(webId, id);
		return entity;
	}
	
	//购物车
	//（增加了商品,收藏加入购物车）
	public Cart collectAddItem(Product product,Long fashId,Long popularityId,int count,boolean isAdd,Long memberId, Long webId){
		Cart cart = findByWebId(webId,memberId);
		if (cart == null) {
			cart = new Cart();
			cart.setMember(memberMng.findById(memberId));
			cart.setWebsite(websiteMng.findById(webId));
			cart.init();
		}
		if(fashId!=null){
			ProductFashion productFashion=productFashionMng.findById(fashId);
//			cart.addItem(productFashion.getProductId(),productFashion,popularityGroupMng.findById(popularityId), count, isAdd);
		}else{
//			cart.addItem(product,null,popularityGroupMng.findById(popularityId),count,isAdd);
		}
		cart=cartDao.saveOrUpdate(cart);
		return cart;
	}
	//购物车
	//（增加了商品,收藏加入购物车）
	public Cart collectAddItem(ProductSite productSite,Long fashId,Long popularityId,int count,boolean isAdd,Long memberId, Long webId){
		Cart cart = findByWebId(webId,memberId);
		if (cart == null) {
			cart = new Cart();
			cart.setMember(memberMng.findById(memberId));
			cart.setWebsite(websiteMng.findById(webId));
			cart.init();
		}
		cart.addItem(productSite,null,popularityGroupMng.findById(popularityId),count,isAdd);
		cart=cartDao.saveOrUpdate(cart);
		return cart;
	}
	
	public Cart addGift(Long giftId, int count, boolean isAdd,
			Long memberId, Long webId) {
		Cart cart = findByWebId(webId,memberId);
		if (cart == null) {
			cart = new Cart();
			cart.setMember(memberMng.findById(memberId));
			cart.setWebsite(websiteMng.findById(webId));
		}
		cart.addGift(giftMng.findById(giftId), count, isAdd);
		cartDao.saveOrUpdate(cart);
		return cart;
	}

	public Cart update(Cart cart) {
		return cartDao.update(cart);
	}

	public Cart deleteById(Long id) {
		Cart bean = cartDao.deleteById(id);
		return bean;
	}
	
	@Autowired
	private PopularityGroupMng popularityGroupMng;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private GiftMng giftMng;
	@Autowired
	private MemberMng memberMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private ProductFashionMng productFashionMng;
}