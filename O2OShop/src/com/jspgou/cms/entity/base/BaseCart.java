package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_cart table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_cart"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseCart  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Cart";
	public static String PROP_MEMBER = "member";
	public static String PROP_WEBSITE = "website";
	public static String PROP_ADDRESS = "address";
	public static String PROP_TOTAL_ITEMS = "totalItems";
	public static String PROP_ID = "id";
	public static String PROP_SHIPPING = "shipping";
	public static String PROP_TOTAL_GIFTS = "totalGifts";
	public static String PROP_PAYMENT = "payment";


	// constructors
	public BaseCart () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCart (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCart (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.Integer totalItems) {

		this.setId(id);
		this.setWebsite(website);
		this.setTotalItems(totalItems);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer totalItems;
	private java.lang.Integer totalGifts;

	// one to one
	private com.jspgou.core.entity.Member member;

	// many to one
	private com.jspgou.core.entity.Website website;

	// collections
	private java.util.Set<com.jspgou.cms.entity.CartItem> items;
	private java.util.Set<com.jspgou.cms.entity.CartGift> gifts;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="cart_id"
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
	 * Return the value associated with the column: total_items
	 */
	public java.lang.Integer getTotalItems () {
		if(totalItems==null){
			totalItems=0;
		}
		return totalItems;
	}

	/**
	 * Set the value related to the column: total_items
	 * @param totalItems the total_items value
	 */
	public void setTotalItems (java.lang.Integer totalItems) {
		this.totalItems = totalItems;
	}


	/**
	 * Return the value associated with the column: total_gifts
	 */
	public java.lang.Integer getTotalGifts () {
		return totalGifts;
	}

	/**
	 * Set the value related to the column: total_gifts
	 * @param totalGifts the total_gifts value
	 */
	public void setTotalGifts (java.lang.Integer totalGifts) {
		this.totalGifts = totalGifts;
	}

	/**
	 * Return the value associated with the column: member
	 */
	public com.jspgou.core.entity.Member getMember () {
		return member;
	}

	/**
	 * Set the value related to the column: member
	 * @param member the member value
	 */
	public void setMember (com.jspgou.core.entity.Member member) {
		this.member = member;
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
	 * Return the value associated with the column: items
	 */
	public java.util.Set<com.jspgou.cms.entity.CartItem> getItems () {
		return items;
	}

	/**
	 * Set the value related to the column: items
	 * @param items the items value
	 */
	public void setItems (java.util.Set<com.jspgou.cms.entity.CartItem> items) {
		this.items = items;
	}


	/**
	 * Return the value associated with the column: gifts
	 */
	public java.util.Set<com.jspgou.cms.entity.CartGift> getGifts () {
		return gifts;
	}

	/**
	 * Set the value related to the column: gifts
	 * @param gifts the gifts value
	 */
	public void setGifts (java.util.Set<com.jspgou.cms.entity.CartGift> gifts) {
		this.gifts = gifts;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Cart)) return false;
		else {
			com.jspgou.cms.entity.Cart cart = (com.jspgou.cms.entity.Cart) obj;
			if (null == this.getId() || null == cart.getId()) return false;
			else return (this.getId().equals(cart.getId()));
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