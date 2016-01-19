package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_payment table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_payment"
 * This class should preserve.
 * @preserve
 */

public abstract class BasePayment  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Payment";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_WEBSITE = "website";
	public static String PROP_DISABLED = "disabled";
	public static String PROP_CODE = "code";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_CONFIG = "config";


	// constructors
	public BasePayment () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePayment (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePayment (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.Integer priority,
		java.lang.Boolean disabled) {

		this.setId(id);
		this.setWebsite(website);
		this.setName(name);
		this.setPriority(priority);
		this.setDisabled(disabled);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.Integer priority;
	private java.lang.Boolean disabled;
	private java.lang.Boolean isDefault;
	private java.lang.Byte type;
	private java.lang.String introduce;


	// many to one
	private com.jspgou.core.entity.Website website;
	
	private java.util.Set<com.jspgou.cms.entity.Shipping> shippings;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="payment_id"
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
	 * Return the value associated with the column: is_disabled
	 */
	public java.lang.Boolean getDisabled () {
		return disabled;
	}

	/**
	 * Set the value related to the column: is_disabled
	 * @param disabled the is_disabled value
	 */
	public void setDisabled (java.lang.Boolean disabled) {
		this.disabled = disabled;
	}


	public java.lang.Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(java.lang.Boolean isDefault) {
		this.isDefault = isDefault;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Payment)) return false;
		else {
			com.jspgou.cms.entity.Payment payment = (com.jspgou.cms.entity.Payment) obj;
			if (null == this.getId() || null == payment.getId()) return false;
			else return (this.getId().equals(payment.getId()));
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

	public void setType(java.lang.Byte type) {
		this.type = type;
	}

	public java.lang.Byte getType() {
		return type;
	}

	public void setShippings(java.util.Set<com.jspgou.cms.entity.Shipping> shippings) {
		this.shippings = shippings;
	}

	public java.util.Set<com.jspgou.cms.entity.Shipping> getShippings() {
		return shippings;
	}

	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}

	public java.lang.String getIntroduce() {
		return introduce;
	}


}