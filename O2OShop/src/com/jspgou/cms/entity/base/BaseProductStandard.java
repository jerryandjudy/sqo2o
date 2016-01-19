package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_product_standard table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_product_standard"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseProductStandard  implements Serializable {

	public static String REF = "ProductStandard";
	public static String PROP_STANDARD = "standard";
	public static String PROP_DATA_TYPE = "dataType";
	public static String PROP_PRODUCT = "product";
	public static String PROP_TYPE = "type";
	public static String PROP_ID = "id";
	public static String PROP_IMG_PATH = "imgPath";


	// constructors
	public BaseProductStandard () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProductStandard (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductStandard (
		java.lang.Long id,
		com.jspgou.cms.entity.Product product,
		com.jspgou.cms.entity.Standard standard,
		com.jspgou.cms.entity.StandardType type,
		java.lang.String imgPath,
		boolean dataType) {

		this.setId(id);
		this.setProduct(product);
		this.setStandard(standard);
		this.setType(type);
		this.setImgPath(imgPath);
		this.setDataType(dataType);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String imgPath;
	private boolean dataType;

	// many to one
	private com.jspgou.cms.entity.Product product;
	private com.jspgou.cms.entity.Standard standard;
	private com.jspgou.cms.entity.StandardType type;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="ps_id"
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
	 * Return the value associated with the column: dataType
	 */
	public boolean isDataType () {
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
	 * Return the value associated with the column: product_id
	 */
	public com.jspgou.cms.entity.Product getProduct () {
		return product;
	}

	/**
	 * Set the value related to the column: product_id
	 * @param product the product_id value
	 */
	public void setProduct (com.jspgou.cms.entity.Product product) {
		this.product = product;
	}


	/**
	 * Return the value associated with the column: standard_id
	 */
	public com.jspgou.cms.entity.Standard getStandard () {
		return standard;
	}

	/**
	 * Set the value related to the column: standard_id
	 * @param standard the standard_id value
	 */
	public void setStandard (com.jspgou.cms.entity.Standard standard) {
		this.standard = standard;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ProductStandard)) return false;
		else {
			com.jspgou.cms.entity.ProductStandard productStandard = (com.jspgou.cms.entity.ProductStandard) obj;
			if (null == this.getId() || null == productStandard.getId()) return false;
			else return (this.getId().equals(productStandard.getId()));
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