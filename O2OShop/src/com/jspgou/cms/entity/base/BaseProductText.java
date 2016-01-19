package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_product_text table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_product_text"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseProductText  implements Serializable {

	public static String REF = "ProductText";
	public static String PROP_TEXT = "text";
	public static String PROP_PRODUCT = "product";
	public static String PROP_TEXT1 = "text1";
	public static String PROP_TEXT2 = "text2";
	public static String PROP_ID = "id";


	// constructors
	public BaseProductText () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProductText (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String text;
	private java.lang.String text1;
	private java.lang.String text2;

	// one to one
	private com.jspgou.cms.entity.Product product;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="product_id"
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
	 * Return the value associated with the column: text1
	 */
	public java.lang.String getText1 () {
		return text1;
	}

	/**
	 * Set the value related to the column: text1
	 * @param text1 the text1 value
	 */
	public void setText1 (java.lang.String text1) {
		this.text1 = text1;
	}


	/**
	 * Return the value associated with the column: text2
	 */
	public java.lang.String getText2 () {
		return text2;
	}

	/**
	 * Set the value related to the column: text2
	 * @param text2 the text2 value
	 */
	public void setText2 (java.lang.String text2) {
		this.text2 = text2;
	}


	/**
	 * Return the value associated with the column: product
	 */
	public com.jspgou.cms.entity.Product getProduct () {
		return product;
	}

	/**
	 * Set the value related to the column: product
	 * @param product the product value
	 */
	public void setProduct (com.jspgou.cms.entity.Product product) {
		this.product = product;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ProductText)) return false;
		else {
			com.jspgou.cms.entity.ProductText productText = (com.jspgou.cms.entity.ProductText) obj;
			if (null == this.getId() || null == productText.getId()) return false;
			else return (this.getId().equals(productText.getId()));
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