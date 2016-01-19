package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_order_shipments table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_order_shipments"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShipments  implements Serializable {

	public static String REF = "Shipments";
	public static String PROP_RECEIVING = "receiving";
	public static String PROP_MONEY = "money";
	public static String PROP_COMMENT = "comment";
	public static String PROP_WAYBILL = "waybill";
	public static String PROP_ID = "id";
	public static String PROP_SHOP_ADMIN = "shopAdmin";
	public static String PROP_INDENT = "indent";


	// constructors
	public BaseShipments () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShipments (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShipments (
		java.lang.Long id,
		com.jspgou.cms.entity.Order indent,
		com.jspgou.cms.entity.ShopAdmin shopAdmin,
		java.lang.String waybill,
		java.lang.String receiving,
		java.lang.String comment) {

		this.setId(id);
		this.setIndent(indent);
		this.setShopAdmin(shopAdmin);
		this.setWaybill(waybill);
		this.setReceiving(receiving);
		this.setComment(comment);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String waybill;
	private double money;
	private java.lang.String receiving;
	private java.lang.String comment;

	// many to one
	private com.jspgou.cms.entity.Order indent;
	private com.jspgou.cms.entity.ShopAdmin shopAdmin;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="Id"
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
	 * Return the value associated with the column: waybill
	 */
	public java.lang.String getWaybill () {
		return waybill;
	}

	/**
	 * Set the value related to the column: waybill
	 * @param waybill the waybill value
	 */
	public void setWaybill (java.lang.String waybill) {
		this.waybill = waybill;
	}


	/**
	 * Return the value associated with the column: money
	 */
	public double getMoney () {
		return money;
	}

	/**
	 * Set the value related to the column: money
	 * @param money the money value
	 */
	public void setMoney (double money) {
		this.money = money;
	}


	/**
	 * Return the value associated with the column: receiving
	 */
	public java.lang.String getReceiving () {
		return receiving;
	}

	/**
	 * Set the value related to the column: receiving
	 * @param receiving the receiving value
	 */
	public void setReceiving (java.lang.String receiving) {
		this.receiving = receiving;
	}


	/**
	 * Return the value associated with the column: comment
	 */
	public java.lang.String getComment () {
		return comment;
	}

	/**
	 * Set the value related to the column: comment
	 * @param comment the comment value
	 */
	public void setComment (java.lang.String comment) {
		this.comment = comment;
	}


	/**
	 * Return the value associated with the column: order_id
	 */
	public com.jspgou.cms.entity.Order getIndent () {
		return indent;
	}

	/**
	 * Set the value related to the column: order_id
	 * @param indent the order_id value
	 */
	public void setIndent (com.jspgou.cms.entity.Order indent) {
		this.indent = indent;
	}


	/**
	 * Return the value associated with the column: admin_id
	 */
	public com.jspgou.cms.entity.ShopAdmin getShopAdmin () {
		return shopAdmin;
	}

	/**
	 * Set the value related to the column: admin_id
	 * @param shopAdmin the admin_id value
	 */
	public void setShopAdmin (com.jspgou.cms.entity.ShopAdmin shopAdmin) {
		this.shopAdmin = shopAdmin;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Shipments)) return false;
		else {
			com.jspgou.cms.entity.Shipments shipments = (com.jspgou.cms.entity.Shipments) obj;
			if (null == this.getId() || null == shipments.getId()) return false;
			else return (this.getId().equals(shipments.getId()));
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