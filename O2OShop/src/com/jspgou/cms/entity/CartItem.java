package com.jspgou.cms.entity;

import java.math.BigDecimal;

import com.jspgou.cms.entity.base.BaseCartItem;
/**
* This class should preserve.
* @preserve
*/
public class CartItem extends BaseCartItem {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得小计
	 * 
	 * @return
	 */
	public Double getSubtotal() {
//		return this.getProductSite().getMemberPrice().multiply(new BigDecimal(getCount()));		
		return getProductSite().getMemberPrice()*getCount();
//		return null;
	}
	public Product getProduct(){
		return getProductSite().getProduct();
	}
	/**
	 * 获得秒杀价小计
	 * 
	 * @return
	 */
	public Double getLimitSubtotal() {
		int count = getCount();
		Double b1 = getProduct().getProductExt().getSeckillprice();
		Double b3 = b1*count;
		return b3;
	}

	/**
	 * 获得重量小计，免运费的商品重量记0。
	 * 
	 * @return
	 */
	public int getWeightForFreight() {
		Product p = getProduct();
		// TODO 免运费?
		return p.getWeight() * getCount();
	}

	/**
	 * 获得商品数量小计，免运费的商品数量记0。
	 * 
	 * @return
	 */
	public int getCountForFreigt() {
		// TODO 免运费?
		return getCount();
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CartItem () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CartItem (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CartItem (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.Cart cart,
		com.jspgou.cms.entity.ProductSite productSite,
		java.lang.Integer count) {

		super (
			id,
			website,
			cart,
			productSite,
			count);
	}

	/* [CONSTRUCTOR MARKER END] */

}