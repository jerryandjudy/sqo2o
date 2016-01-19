package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_exended_item table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_exended_item"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseExendedItem  implements Serializable {

	public static String REF = "ExendedItem";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_EXENDED = "exended";
	public static String PROP_PRIORITY = "priority";


	// constructors
	public BaseExendedItem () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExendedItem (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseExendedItem (
		java.lang.Long id,
		com.jspgou.cms.entity.Exended exended,
		java.lang.String name) {

		this.setId(id);
		this.setExended(exended);
		this.setName(name);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;

	// many to one
	private com.jspgou.cms.entity.Exended exended;



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
	 * Return the value associated with the column: exended_id
	 */
	public com.jspgou.cms.entity.Exended getExended () {
		return exended;
	}

	/**
	 * Set the value related to the column: exended_id
	 * @param exended the exended_id value
	 */
	public void setExended (com.jspgou.cms.entity.Exended exended) {
		this.exended = exended;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ExendedItem)) return false;
		else {
			com.jspgou.cms.entity.ExendedItem exendedItem = (com.jspgou.cms.entity.ExendedItem) obj;
			if (null == this.getId() || null == exendedItem.getId()) return false;
			else return (this.getId().equals(exendedItem.getId()));
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