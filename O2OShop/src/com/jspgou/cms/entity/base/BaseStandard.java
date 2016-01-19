package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_standard table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_standard"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseStandard  implements Serializable {

	public static String REF = "Standard";
	public static String PROP_NAME = "name";
	public static String PROP_TYPE = "type";
	public static String PROP_ID = "id";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_COLOR = "color";


	// constructors
	public BaseStandard () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseStandard (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseStandard (
		java.lang.Long id,
		com.jspgou.cms.entity.StandardType type,
		java.lang.String name) {

		this.setId(id);
		this.setType(type);
		this.setName(name);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String color;
	private java.lang.Integer priority;

	// many to one
	private com.jspgou.cms.entity.StandardType type;


	private java.util.Set<com.jspgou.cms.entity.ProductFashion> fashions;

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
	 * Return the value associated with the column: color
	 */
	public java.lang.String getColor () {
		return color;
	}

	/**
	 * Set the value related to the column: color
	 * @param color the color value
	 */
	public void setColor (java.lang.String color) {
		this.color = color;
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
	public com.jspgou.cms.entity.StandardType getType () {
		return type;
	}

	/**
	 * Set the value related to the column: type_id
	 * @param type the type_id value
	 */
	public void setType (com.jspgou.cms.entity.StandardType type) {
		this.type = type;
	}

	public void setFashions(java.util.Set<com.jspgou.cms.entity.ProductFashion> fashions) {
		this.fashions = fashions;
	}

	public java.util.Set<com.jspgou.cms.entity.ProductFashion> getFashions() {
		return fashions;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Standard)) return false;
		else {
			com.jspgou.cms.entity.Standard standard = (com.jspgou.cms.entity.Standard) obj;
			if (null == this.getId() || null == standard.getId()) return false;
			else return (this.getId().equals(standard.getId()));
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