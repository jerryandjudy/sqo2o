package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_cardgift table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_cardgift"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseCartGift  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "CartGift";
	public static String PROP_COUNT = "count";
	public static String PROP_GIFT = "gift";
	public static String PROP_WEBSITE = "website";
	public static String PROP_CART = "cart";
	public static String PROP_ID = "id";


	// constructors
	public BaseCartGift () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCartGift (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCartGift (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.Cart cart,
		com.jspgou.cms.entity.Gift gift,
		java.lang.Integer count) {

		this.setId(id);
		this.setWebsite(website);
		this.setCart(cart);
		this.setGift(gift);
		this.setCount(count);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer count;

	// many to one
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.Cart cart;
	private com.jspgou.cms.entity.Gift gift;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
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
	 * Return the value associated with the column: websiteId
	 */
	public com.jspgou.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: websiteId
	 * @param website the websiteId value
	 */
	public void setWebsite (com.jspgou.core.entity.Website website) {
		this.website = website;
	}


	/**
	 * Return the value associated with the column: cartId
	 */
	public com.jspgou.cms.entity.Cart getCart () {
		return cart;
	}

	/**
	 * Set the value related to the column: cartId
	 * @param cart the cartId value
	 */
	public void setCart (com.jspgou.cms.entity.Cart cart) {
		this.cart = cart;
	}


	/**
	 * Return the value associated with the column: giftId
	 */
	public com.jspgou.cms.entity.Gift getGift () {
		return gift;
	}

	/**
	 * Set the value related to the column: giftId
	 * @param gift the giftId value
	 */
	public void setGift (com.jspgou.cms.entity.Gift gift) {
		this.gift = gift;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.CartGift)) return false;
		else {
			com.jspgou.cms.entity.CartGift cartGift = (com.jspgou.cms.entity.CartGift) obj;
			if (null == this.getId() || null == cartGift.getId()) return false;
			else return (this.getId().equals(cartGift.getId()));
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


}