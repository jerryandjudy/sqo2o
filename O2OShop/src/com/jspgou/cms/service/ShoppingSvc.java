package com.jspgou.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
public interface ShoppingSvc {

	public Cart getCart(ShopMember member, HttpServletRequest request,
			HttpServletResponse response);
	public List<Cart> getCarts(ShopMember member, HttpServletRequest request,
			HttpServletResponse response) ;

	public void addCookie(ShopMember member, HttpServletRequest request,
			HttpServletResponse response);

	public void clearCookie(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 获得购物车，不处理cookie。
	 * 
	 * @param memberId
	 * @return 如果购物车为空，则返回null。
	 */
	public Cart getCart(Long memberId);
	/**
	 * 获得会员站点购物车，不处理cookie。
	 * 
	 * @param memberId
	 * @return 如果购物车为空，则返回null。
	 */
	public Cart getCart(Long memberId,Long webId);
	
	public void clearCart(ShopMember member);
	
	
	public Cart collectAddToCart(Product product,Long productFashid,Long popularityId, int count, boolean isAdd,//王泽武（增加了商品款式,收藏加入购物车）
			ShopMember member, Website web, HttpServletRequest request,
			HttpServletResponse response);
	public Cart collectAddToCart(ProductSite productSite,Long productFashid,Long popularityId, int count, boolean isAdd,//王泽武（增加了商品款式,收藏加入购物车）
			ShopMember member, Website web, HttpServletRequest request,
			HttpServletResponse response);
}
