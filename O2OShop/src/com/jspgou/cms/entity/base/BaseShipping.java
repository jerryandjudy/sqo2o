package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_shipping table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_shipping"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShipping  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Shipping";
	public static String PROP_FIRST_WEIGHT = "firstWeight";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_WEBSITE = "website";
	public static String PROP_FIRST_PRICE = "firstPrice";
	public static String PROP_DISABLED = "disabled";
	public static String PROP_UNIFORM_PRICE = "uniformPrice";
	public static String PROP_METHOD = "method";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_ADDITIONAL_WEIGHT = "additionalWeight";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_ADDITIONAL_PRICE = "additionalPrice";


	// constructors
	public BaseShipping () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShipping (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShipping (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.Integer method,
		java.lang.Integer priority,
		java.lang.Boolean disabled) {

		this.setId(id);
		this.setWebsite(website);
		this.setName(name);
		this.setMethod(method);
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
	private java.lang.Double uniformPrice;
	private java.lang.Integer firstWeight;
	private java.lang.Integer additionalWeight;
	private java.lang.Double firstPrice;
	private java.lang.Double additionalPrice;
	private java.lang.Integer method;
	private java.lang.Integer priority;
	private java.lang.Boolean disabled;
	private java.lang.Boolean isDefault;
	private java.lang.String logisticsType;

	// many to one
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.Logistics logistics;




	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="shipping_id"
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
	 * Return the value associated with the column: uniform_price
	 */
	public java.lang.Double getUniformPrice () {
		return uniformPrice;
	}

	/**
	 * Set the value related to the column: uniform_price
	 * @param uniformPrice the uniform_price value
	 */
	public void setUniformPrice (java.lang.Double uniformPrice) {
		this.uniformPrice = uniformPrice;
	}


	/**
	 * Return the value associated with the column: first_weight
	 */
	public java.lang.Integer getFirstWeight () {
		return firstWeight;
	}
	
	

	/**
	 * Set the value related to the column: first_weight
	 * @param firstWeight the first_weight value
	 */
	public void setFirstWeight (java.lang.Integer firstWeight) {
		this.firstWeight = firstWeight;
	}


	/**
	 * Return the value associated with the column: additional_weight
	 */
	public java.lang.Integer getAdditionalWeight () {
		return additionalWeight;
	}

	/**
	 * Set the value related to the column: additional_weight
	 * @param additionalWeight the additional_weight value
	 */
	public void setAdditionalWeight (java.lang.Integer additionalWeight) {
		this.additionalWeight = additionalWeight;
	}


	public java.lang.Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(java.lang.Boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * Return the value associated with the column: first_price
	 */
	public java.lang.Double getFirstPrice () {
		return firstPrice;
	}

	/**
	 * Set the value related to the column: first_price
	 * @param firstPrice the first_price value
	 */
	public void setFirstPrice (java.lang.Double firstPrice) {
		this.firstPrice = firstPrice;
	}


	/**
	 * Return the value associated with the column: additional_price
	 */
	public java.lang.Double getAdditionalPrice () {
		return additionalPrice;
	}

	/**
	 * Set the value related to the column: additional_price
	 * @param additionalPrice the additional_price value
	 */
	public void setAdditionalPrice (java.lang.Double additionalPrice) {
		this.additionalPrice = additionalPrice;
	}


	/**
	 * Return the value associated with the column: method
	 */
	public java.lang.Integer getMethod () {
		return method;
	}

	/**
	 * Set the value related to the column: method
	 * @param method the method value
	 */
	public void setMethod (java.lang.Integer method) {
		this.method = method;
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


	public void setLogistics(com.jspgou.cms.entity.Logistics logistics) {
		this.logistics = logistics;
	}

	public com.jspgou.cms.entity.Logistics getLogistics() {
		return logistics;
	}

	/**
	 * Return the value associated with the column: freights
	 */



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Shipping)) return false;
		else {
			com.jspgou.cms.entity.Shipping shipping = (com.jspgou.cms.entity.Shipping) obj;
			if (null == this.getId() || null == shipping.getId()) return false;
			else return (this.getId().equals(shipping.getId()));
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

	public void setLogisticsType(java.lang.String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public java.lang.String getLogisticsType() {
		return logisticsType;
	}


}