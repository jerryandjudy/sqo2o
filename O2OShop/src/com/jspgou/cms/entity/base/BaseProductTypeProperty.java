package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_ptype_property table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_ptype_property"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseProductTypeProperty  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ProductTypeProperty";
	public static String PROP_COLS = "cols";
	public static String PROP_SORT = "sort";
	public static String PROP_ROWS = "rows";
	public static String PROP_FIELD = "field";
	public static String PROP_DATA_TYPE = "dataType";
	public static String PROP_IS_NOT_NULL = "isNotNull";
	public static String PROP_PROPERTY_TYPE = "propertyType";
	public static String PROP_CUSTOM = "custom";
	public static String PROP_PROPERTY_NAME = "propertyName";
	public static String PROP_IS_START = "isStart";
	public static String PROP_SINGLE = "single";
	public static String PROP_OPT_VALUE = "optValue";
	public static String PROP_CATEGORY = "category";
	public static String PROP_DEF_VALUE = "defValue";
	public static String PROP_ID = "id";


	// constructors
	public BaseProductTypeProperty () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProductTypeProperty (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductTypeProperty (
		java.lang.Long id,
		com.jspgou.cms.entity.ProductType propertyType,
		java.lang.String propertyName,
		java.lang.String field,
		java.lang.Integer isStart,
		java.lang.Integer isNotNull,
		java.lang.Integer sort,
		java.lang.Integer dataType,
		java.lang.Boolean single,
		java.lang.Boolean category,
		java.lang.Boolean custom) {

		this.setId(id);
		this.setPropertyType(propertyType);
		this.setPropertyName(propertyName);
		this.setField(field);
		this.setIsStart(isStart);
		this.setIsNotNull(isNotNull);
		this.setSort(sort);
		this.setDataType(dataType);
		this.setSingle(single);
		this.setCategory(category);
		this.setCustom(custom);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String propertyName;
	private java.lang.String field;
	private java.lang.Integer isStart;
	private java.lang.Integer isNotNull;
	private java.lang.Integer sort;
	private java.lang.String defValue;
	private java.lang.String optValue;
	private java.lang.String rows;
	private java.lang.String cols;
	private java.lang.Integer dataType;
	private java.lang.Boolean single;
	private java.lang.Boolean category;
	private java.lang.Boolean custom;

	// many to one
	private com.jspgou.cms.entity.ProductType propertyType;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="type_property_id"
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
	 * Return the value associated with the column: property_name
	 */
	public java.lang.String getPropertyName () {
		return propertyName;
	}

	/**
	 * Set the value related to the column: property_name
	 * @param propertyName the property_name value
	 */
	public void setPropertyName (java.lang.String propertyName) {
		this.propertyName = propertyName;
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
	 * Return the value associated with the column: is_start
	 */
	public java.lang.Integer getIsStart () {
		return isStart;
	}

	/**
	 * Set the value related to the column: is_start
	 * @param isStart the is_start value
	 */
	public void setIsStart (java.lang.Integer isStart) {
		this.isStart = isStart;
	}


	/**
	 * Return the value associated with the column: is_notNull
	 */
	public java.lang.Integer getIsNotNull () {
		return isNotNull;
	}

	/**
	 * Set the value related to the column: is_notNull
	 * @param isNotNull the is_notNull value
	 */
	public void setIsNotNull (java.lang.Integer isNotNull) {
		this.isNotNull = isNotNull;
	}


	/**
	 * Return the value associated with the column: sort
	 */
	public java.lang.Integer getSort () {
		return sort;
	}

	/**
	 * Set the value related to the column: sort
	 * @param sort the sort value
	 */
	public void setSort (java.lang.Integer sort) {
		this.sort = sort;
	}


	/**
	 * Return the value associated with the column: def_value
	 */
	public java.lang.String getDefValue () {
		return defValue;
	}

	/**
	 * Set the value related to the column: def_value
	 * @param defValue the def_value value
	 */
	public void setDefValue (java.lang.String defValue) {
		this.defValue = defValue;
	}


	/**
	 * Return the value associated with the column: opt_value
	 */
	public java.lang.String getOptValue () {
		return optValue;
	}

	/**
	 * Set the value related to the column: opt_value
	 * @param optValue the opt_value value
	 */
	public void setOptValue (java.lang.String optValue) {
		this.optValue = optValue;
	}


	/**
	 * Return the value associated with the column: area_rows
	 */
	public java.lang.String getRows () {
		return rows;
	}

	/**
	 * Set the value related to the column: area_rows
	 * @param rows the area_rows value
	 */
	public void setRows (java.lang.String rows) {
		this.rows = rows;
	}


	/**
	 * Return the value associated with the column: area_cols
	 */
	public java.lang.String getCols () {
		return cols;
	}

	/**
	 * Set the value related to the column: area_cols
	 * @param cols the area_cols value
	 */
	public void setCols (java.lang.String cols) {
		this.cols = cols;
	}


	/**
	 * Return the value associated with the column: data_type
	 */
	public java.lang.Integer getDataType () {
		return dataType;
	}

	/**
	 * Set the value related to the column: data_type
	 * @param dataType the data_type value
	 */
	public void setDataType (java.lang.Integer dataType) {
		this.dataType = dataType;
	}


	/**
	 * Return the value associated with the column: is_single
	 */
	public java.lang.Boolean getSingle () {
		return single;
	}

	/**
	 * Set the value related to the column: is_single
	 * @param single the is_single value
	 */
	public void setSingle (java.lang.Boolean single) {
		this.single = single;
	}


	/**
	 * Return the value associated with the column: is_category
	 */
	public java.lang.Boolean getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: is_category
	 * @param category the is_category value
	 */
	public void setCategory (java.lang.Boolean category) {
		this.category = category;
	}


	/**
	 * Return the value associated with the column: is_custom
	 */
	public java.lang.Boolean getCustom () {
		return custom;
	}

	/**
	 * Set the value related to the column: is_custom
	 * @param custom the is_custom value
	 */
	public void setCustom (java.lang.Boolean custom) {
		this.custom = custom;
	}


	/**
	 * Return the value associated with the column: ptype_id
	 */
	public com.jspgou.cms.entity.ProductType getPropertyType () {
		return propertyType;
	}

	/**
	 * Set the value related to the column: ptype_id
	 * @param propertyType the ptype_id value
	 */
	public void setPropertyType (com.jspgou.cms.entity.ProductType propertyType) {
		this.propertyType = propertyType;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ProductTypeProperty)) return false;
		else {
			com.jspgou.cms.entity.ProductTypeProperty productTypeProperty = (com.jspgou.cms.entity.ProductTypeProperty) obj;
			if (null == this.getId() || null == productTypeProperty.getId()) return false;
			else return (this.getId().equals(productTypeProperty.getId()));
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