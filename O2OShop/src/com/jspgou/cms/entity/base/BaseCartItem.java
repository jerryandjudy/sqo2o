package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_cart_item table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_cart_item"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseCartItem  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "CartItem";
	public static String PROP_COUNT = "count";
	public static String PROP_PRODUCTSITE = "productSite";
	public static String PROP_PRODUCT_FASH = "productFash";
	public static String PROP_WEBSITE = "website";
	public static String PROP_CART = "cart";
	public static String PROP_ID = "id";
	public static String PROP_SCORE = "score";


	// constructors
	public BaseCartItem () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCartItem (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCartItem (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.Cart cart,
		com.jspgou.cms.entity.ProductSite productSite,
		java.lang.Integer count) {

		this.setId(id);
		this.setWebsite(website);
		this.setCart(cart);
		this.setProductSite(productSite);
		this.setCount(count);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer count;
	private java.lang.Integer score;

	// many to one
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.Cart cart;
	private com.jspgou.cms.entity.ProductSite productSite;
	private com.jspgou.cms.entity.ProductFashion productFash;
	private com.jspgou.cms.entity.PopularityGroup popularityGroup;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="cartitem_id"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: count
	 */
	public java.lang.Integer getCount () {
		return count;
	}

	/**
	 * Set the value related to the column: count
	 * @param count the count value
	 */
	public void setCount (java.lang.Integer count) {
		this.count = count;
	}


	/**
	 * Return the value associated with the column: score
	 */
	public java.lang.Integer getScore () {
		return score;
	}

	/**
	 * Set the value related to the column: score
	 * @param score the score value
	 */
	public void setScore (java.lang.Integer score) {
		this.score = score;
	}

	/**
	 * Return the value associated with the column: website_id
	 */
	public com.jspgou.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: website_id
	 * @param website the website_id value
	 */
	public void setWebsite (com.jspgou.core.entity.Website website) {
		this.website = website;
	}


	/**
	 * Return the value associated with the column: cart_id
	 */
	public com.jspgou.cms.entity.Cart getCart () {
		return cart;
	}

	/**
	 * Set the value related to the column: cart_id
	 * @param cart the cart_id value
	 */
	public void setCart (com.jspgou.cms.entity.Cart cart) {
		this.cart = cart;
	}



	public com.jspgou.cms.entity.ProductSite getProductSite() {
		return productSite;
	}

	public void setProductSite(com.jspgou.cms.entity.ProductSite productSite) {
		this.productSite = productSite;
	}

	/**
	 * Return the value associated with the column: productFash_id
	 */
	public com.jspgou.cms.entity.ProductFashion getProductFash () {
		return productFash;
	}

	/**
	 * Set the value related to the column: productFash_id
	 * @param productFash the productFash_id value
	 */
	public void setProductFash (com.jspgou.cms.entity.ProductFashion productFash) {
		this.productFash = productFash;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.CartItem)) return false;
		else {
			com.jspgou.cms.entity.CartItem cartItem = (com.jspgou.cms.entity.CartItem) obj;
			if (null == this.getId() || null == cartItem.getId()) return false;
			else return (this.getId().equals(cartItem.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

	public void setPopularityGroup(com.jspgou.cms.entity.PopularityGroup popularityGroup) {
		this.popularityGroup = popularityGroup;
	}

	public com.jspgou.cms.entity.PopularityGroup getPopularityGroup() {
		return popularityGroup;
	}


}