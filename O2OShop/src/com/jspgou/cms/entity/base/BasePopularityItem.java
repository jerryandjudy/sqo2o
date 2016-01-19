package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_popularity_item table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_popularity_item"
 */

public abstract class BasePopularityItem  implements Serializable {

	public static String REF = "PopularityItem";
	public static String PROP_COUNT = "count";
	public static String PROP_CART = "cart";
	public static String PROP_ID = "id";
	public static String PROP_POPULARITY_GROUP = "popularityGroup";


	// constructors
	public BasePopularityItem () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePopularityItem (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePopularityItem (
		java.lang.Long id,
		com.jspgou.cms.entity.Cart cart,
		java.lang.Integer count) {

		this.setId(id);
		this.setCart(cart);
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
	private com.jspgou.cms.entity.Cart cart;
	private com.jspgou.cms.entity.PopularityGroup popularityGroup;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="popularityitem_id"
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


	/**
	 * Return the value associated with the column: popularity_id
	 */
	public com.jspgou.cms.entity.PopularityGroup getPopularityGroup () {
		return popularityGroup;
	}

	/**
	 * Set the value related to the column: popularity_id
	 * @param popularityGroup the popularity_id value
	 */
	public void setPopularityGroup (com.jspgou.cms.entity.PopularityGroup popularityGroup) {
		this.popularityGroup = popularityGroup;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.PopularityItem)) return false;
		else {
			com.jspgou.cms.entity.PopularityItem popularityItem = (com.jspgou.cms.entity.PopularityItem) obj;
			if (null == this.getId() || null == popularityItem.getId()) return false;
			else return (this.getId().equals(popularityItem.getId()));
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