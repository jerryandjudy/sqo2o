package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_standard_type table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_standard_type"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseStandardType  implements Serializable {

	public static String REF = "StandardType";
	public static String PROP_NAME = "name";
	public static String PROP_DATA_TYPE = "dataType";
	public static String PROP_FIELD = "field";
	public static String PROP_ID = "id";
	public static String PROP_PRIORITY = "priority";


	// constructors
	public BaseStandardType () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseStandardType (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseStandardType (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String field,
		boolean dataType) {

		this.setId(id);
		this.setName(name);
		this.setField(field);
		this.setDataType(dataType);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String field;
	private java.lang.String remark;
 	private boolean dataType;
	private java.lang.Integer priority;

	// collections
	private java.util.Set<com.jspgou.cms.entity.Standard> standardSet;
    private java.util.Set<com.jspgou.cms.entity.Category> categorys;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="standardtype_id"
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
	 * Return the value associated with the column: field
	 */
	public java.lang.String getField () {
		return field;
	}

	/**
	 * Set the value related to the column: field
	 * @param field the field value
	 */
	public void setField (java.lang.String field) {
		this.field = field;
	}


	/**
	 * Return the value associated with the column: dataType
	 */
	public boolean getDataType () {
		return dataType;
	}

	/**
	 * Set the value related to the column: dataType
	 * @param dataType the dataType value
	 */
	public void setDataType (boolean dataType) {
		this.dataType = dataType;
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
	 * Return the value associated with the column: standardSet
	 */
	public java.util.Set<com.jspgou.cms.entity.Standard> getStandardSet () {
		return standardSet;
	}

	/**
	 * Set the value related to the column: standardSet
	 * @param standardSet the standardSet value
	 */
	public void setStandardSet (java.util.Set<com.jspgou.cms.entity.Standard> standardSet) {
		this.standardSet = standardSet;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.StandardType)) return false;
		else {
			com.jspgou.cms.entity.StandardType standardType = (com.jspgou.cms.entity.StandardType) obj;
			if (null == this.getId() || null == standardType.getId()) return false;
			else return (this.getId().equals(standardType.getId()));
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

	public void setCategorys(java.util.Set<com.jspgou.cms.entity.Category> categorys) {
		this.categorys = categorys;
	}

	public java.util.Set<com.jspgou.cms.entity.Category> getCategorys() {
		return categorys;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public java.lang.String getRemark() {
		return remark;
	}


}