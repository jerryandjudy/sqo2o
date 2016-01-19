package com.jspgou.cms.service.impl;

import static com.jspgou.cms.CookieConstants.CART_TOTAL_ITEMS;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.CartMng;
import com.jspgou.cms.manager.PopularityItemMng;
import com.jspgou.cms.service.ShoppingSvc;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShoppingSvcImpl implements ShoppingSvc {
	
	//购物车
	public Cart collectAddToCart(Product product,Long fashId,Long popularityId, int count, boolean isAdd,//王泽武（增加了商品款式,收藏加入购物车）
			ShopMember member, Website web, HttpServletRequest request,
			HttpServletResponse response) {
		Cart cart = cartMng.collectAddItem(product,fashId,popularityId, count, isAdd, member.getId(), web.getId());
		Cookie cookie = createCookie(cart.getTotalItems().toString(), request);
		response.addCookie(cookie);
		return cart;
	}
	//购物车
	public Cart collectAddToCart(ProductSite productSite,Long fashId,Long popularityId, int count, boolean isAdd,//王泽武（增加了商品款式,收藏加入购物车）
			ShopMember member, Website web, HttpServletRequest request,
			HttpServletResponse response) {
		Cart cart = cartMng.collectAddItem(productSite,fashId,popularityId, count, isAdd, member.getId(), web.getId());
		Cookie cookie = createCookie(cart.getTotalItems().toString(), request);
		response.addCookie(cookie);
		return cart;
	}
	
	public void clearCart(ShopMember member){//清空购物车
		Cart cart =cartMng.findById(member.getId());
		cart.getItems().clear();
		cartMng.update(cart);
	}

	public Cart getCart(ShopMember member, HttpServletRequest request,
			HttpServletResponse response) {
		Cart cart = cartMng.findById(member.getId());
		if (cart != null && cart.getItems().size() > 0) {
			Cookie cookie = createCookie(cart.getTotalItems().toString(),
					request);
			response.addCookie(cookie);
			return cart;
		} else {
			Cookie cookie = createCookie("0", request);
			response.addCookie(cookie);
			return null;
		}
	}
	public List<Cart> getCarts(ShopMember member, HttpServletRequest request,
			HttpServletResponse response) {
		List<Cart> carts = cartMng.findByMemberId(member.getId());
		if (carts != null && carts.size() > 0) {
			Integer counts=0;
			for(Cart cart:carts){
				counts=counts+cart.getTotalItems();
			}
			Cookie cookie = createCookie(counts.toString(),
					request);
			response.addCookie(cookie);
			return carts;
		} else {
			Cookie cookie = createCookie("0", request);
			response.addCookie(cookie);
			return null;
		}
	}

	public Cart getCart(Long memberId) {
		Cart cart = cartMng.findById(memberId);
		if (cart != null && cart.getItems().size() > 0) {
			return cart;
		} else {
			return null;
		}
	}
	public Cart getCart(Long memberId,Long webId) {
		Cart cart = cartMng.findById(memberId,webId);
		if (cart != null && cart.getItems().size() > 0) {
			return cart;
		} else {
			return null;
		}
	}

	public void addCookie(ShopMember member, HttpServletRequest request,
			HttpServletResponse response) {
		getCart(member, request, response);
	}

	public void clearCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = createCookie(null, request);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	private Cookie createCookie(String totalItems, HttpServletRequest request) {
		Cookie cookie = new Cookie(CART_TOTAL_ITEMS, totalItems);
		String ctx = request.getContextPath();
		cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
		return cookie;
	}

	@Autowired
	private CartMng cartMng;
	
}
