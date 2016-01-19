package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_payment_plugins table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_payment_plugins"
 * This class should preserve.
 * @preserve
 */

public abstract class BasePaymentPlugins  implements Serializable {

	public static String REF = "PaymentPlugins";
	public static String PROP_NAME = "name";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_SELLER_KEY = "sellerKey";
	public static String PROP_ID = "id";
	public static String PROP_IMG_PATH = "imgPath";
	public static String PROP_SELLER_EMAIL = "sellerEmail";
	public static String PROP_PARTNER = "partner";
	public static String PROP_CODE = "code";
	public static String PROP_PRIORITY = "priority";


	// constructors
	public BasePaymentPlugins () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePaymentPlugins (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePaymentPlugins (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String code,
		java.lang.Integer priority) {

		this.setId(id);
		this.setName(name);
		this.setCode(code);
		this.setPriority(priority);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String code;
	private java.lang.String description;
	private java.lang.Integer priority;
	private java.lang.String imgPath;
	private java.lang.String partner;
	private java.lang.String sellerKey;
	private java.lang.String sellerEmail;
	private java.lang.String partnerKey;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="plugins_id"
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
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}


	/**
	 * Return the value associated with the column: code
	 */
	public java.lang.String getCode () {
		return code;
	}

	/**
	 * Set the value related to the column: code
	 * @param code the code value
	 */
	public void setCode (java.lang.String code) {
		this.code = code;
	}


	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}


	/**
	 * Return the value associated with the column: priority
	 */
	public java.lang.Integer getPriority () {
		return priority;
	}

	/**
	 * Set the value related to the column: priority
	 * @param priority the priority value
	 */
	public void setPriority (java.lang.Integer priority) {
		this.priority = priority;
	}


	/**
	 * Return the value associated with the column: img_path
	 */
	public java.lang.String getImgPath () {
		return imgPath;
	}

	/**
	 * Set the value related to the column: img_path
	 * @param imgPath the img_path value
	 */
	public void setImgPath (java.lang.String imgPath) {
		this.imgPath = imgPath;
	}


	/**
	 * Return the value associated with the column: partner
	 */
	public java.lang.String getPartner () {
		return partner;
	}

	/**
	 * Set the value related to the column: partner
	 * @param partner the partner value
	 */
	public void setPartner (java.lang.String partner) {
		this.partner = partner;
	}


	/**
	 * Return the value associated with the column: seller_key
	 */
	public java.lang.String getSellerKey () {
		return sellerKey;
	}

	/**
	 * Set the value related to the column: seller_key
	 * @param sellerKey the seller_key value
	 */
	public void setSellerKey (java.lang.String sellerKey) {
		this.sellerKey = sellerKey;
	}


	/**
	 * Return the value associated with the column: seller_email
	 */
	public java.lang.String getSellerEmail () {
		return sellerEmail;
	}

	/**
	 * Set the value related to the column: seller_email
	 * @param sellerEmail the seller_email value
	 */
	public void setSellerEmail (java.lang.String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}



	public java.lang.String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(java.lang.String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.PaymentPlugins)) return false;
		else {
			com.jspgou.cms.entity.PaymentPlugins paymentPlugins = (com.jspgou.cms.entity.PaymentPlugins) obj;
			if (null == this.getId() || null == paymentPlugins.getId()) return false;
			else return (this.getId().equals(paymentPlugins.getId()));
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