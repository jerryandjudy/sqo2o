package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_online_customerservice table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_online_customerservice"
 */

public abstract class BaseCustomerService  implements Serializable {

	public static String REF = "CustomerService";
	public static String PROP_NAME = "name";
	public static String PROP_DISABLE = "disable";
	public static String PROP_TYPE = "type";
	public static String PROP_ID = "id";
	public static String PROP_CONTENT = "content";
	public static String PROP_PRIORITY = "priority";


	// constructors
	public BaseCustomerService () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCustomerService (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCustomerService (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String type,
		java.lang.String content,
		java.lang.Integer priority,
		java.lang.Boolean disable) {

		this.setId(id);
		this.setName(name);
		this.setType(type);
		this.setContent(content);
		this.setPriority(priority);
		this.setDisable(disable);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String type;
	private java.lang.String content;
	private java.lang.Integer priority;
	private java.lang.Boolean disable;
	private com.jspgou.core.entity.Website website;



	public com.jspgou.core.entity.Website getWebsite() {
		return website;
	}

	public void setWebsite(com.jspgou.core.entity.Website website) {
		this.website = website;
	}

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
	 * Return the value associated with the column: type
	 */
	public java.lang.String getType () {
		return type;
	}

	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType (java.lang.String type) {
		this.type = type;
	}


	/**
	 * Return the value associated with the column: content
	 */
	public java.lang.String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content
	 * @param content the content value
	 */
	public void setContent (java.lang.String content) {
		this.content = content;
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
	 * Return the value associated with the column: is_disable
	 */
	public java.lang.Boolean getDisable () {
		return disable;
	}

	/**
	 * Set the value related to the column: is_disable
	 * @param disable the is_disable value
	 */
	public void setDisable (java.lang.Boolean disable) {
		this.disable = disable;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.CustomerService)) return false;
		else {
			com.jspgou.cms.entity.CustomerService customerService = (com.jspgou.cms.entity.CustomerService) obj;
			if (null == this.getId() || null == customerService.getId()) return false;
			else return (this.getId().equals(customerService.getId()));
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