package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_dictionary table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_dictionary"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopDictionary  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ShopDictionary";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_SHOP_DICTIONARY_TYPE = "shopDictionaryType";


	// constructors
	public BaseShopDictionary () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopDictionary (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopDictionary (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopDictionaryType shopDictionaryType,
		java.lang.String name) {

		this.setId(id);
		this.setShopDictionaryType(shopDictionaryType);
		this.setName(name);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private Integer priority;

	// many to one
	private com.jspgou.cms.entity.ShopDictionaryType shopDictionaryType;



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
	 * Return the value associated with the column: type_id
	 */
	public com.jspgou.cms.entity.ShopDictionaryType getShopDictionaryType () {
		return shopDictionaryType;
	}

	/**
	 * Set the value related to the column: type_id
	 * @param shopDictionaryType the type_id value
	 */
	public void setShopDictionaryType (com.jspgou.cms.entity.ShopDictionaryType shopDictionaryType) {
		this.shopDictionaryType = shopDictionaryType;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopDictionary)) return false;
		else {
			com.jspgou.cms.entity.ShopDictionary shopDictionary = (com.jspgou.cms.entity.ShopDictionary) obj;
			if (null == this.getId() || null == shopDictionary.getId()) return false;
			else return (this.getId().equals(shopDictionary.getId()));
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