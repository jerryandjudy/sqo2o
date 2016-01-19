package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_product table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_product"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseProductExended  implements Serializable {

	public static String REF = "ProductExended";
	public static String PROP_NAME = "name";
	public static String PROP_VALUE = "value";


	// constructors
	public BaseProductExended () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductExended (
		java.lang.String name) {

		this.setName(name);
		initialize();
	}

	protected void initialize () {}



	// fields
	private java.lang.String name;
	private java.lang.String value;






	/**
	 * Return the value associated with the column: attr_name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: attr_name
	 * @param name the attr_name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}


	/**
	 * Return the value associated with the column: attr_value
	 */
	public java.lang.String getValue () {
		return value;
	}

	/**
	 * Set the value related to the column: attr_value
	 * @param value the attr_value value
	 */
	public void setValue (java.lang.String value) {
		this.value = value;
	}






	public String toString () {
		return super.toString();
	}


}