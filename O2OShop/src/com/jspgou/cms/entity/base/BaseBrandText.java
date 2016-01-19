package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_brand_text table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_brand_text"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseBrandText  implements Serializable {

	public static String REF = "BrandText";
	public static String PROP_BRAND = "brand";
	public static String PROP_TEXT = "text";
	public static String PROP_ID = "id";


	// constructors
	public BaseBrandText () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBrandText (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String text;

	// one to one
	private com.jspgou.cms.entity.Brand brand;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="brand_id"
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
	 * Return the value associated with the column: text
	 */
	public java.lang.String getText () {
		return text;
	}

	/**
	 * Set the value related to the column: text
	 * @param text the text value
	 */
	public void setText (java.lang.String text) {
		this.text = text;
	}


	/**
	 * Return the value associated with the column: brand
	 */
	public com.jspgou.cms.entity.Brand getBrand () {
		return brand;
	}

	/**
	 * Set the value related to the column: brand
	 * @param brand the brand value
	 */
	public void setBrand (com.jspgou.cms.entity.Brand brand) {
		this.brand = brand;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.BrandText)) return false;
		else {
			com.jspgou.cms.entity.BrandText brandText = (com.jspgou.cms.entity.BrandText) obj;
			if (null == this.getId() || null == brandText.getId()) return false;
			else return (this.getId().equals(brandText.getId()));
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