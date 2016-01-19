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

public abstract class BaseProductPicture  implements Serializable {

	public static String REF = "ProductPicture";
	public static String PROP_BIG_PICTURE_PATH = "bigPicturePath";
	public static String PROP_APP_PICTURE_PATH = "appPicturePath";
	public static String PROP_PICTURE_PATH = "picturePath";


	// constructors
	public BaseProductPicture () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductPicture (
		java.lang.String picturePath,
		java.lang.String bigPicturePath,
		java.lang.String appPicturePath) {

		this.setPicturePath(picturePath);
		this.setBigPicturePath(bigPicturePath);
		this.setAppPicturePath(appPicturePath);
		initialize();
	}

	protected void initialize () {}



	// fields
	private java.lang.String picturePath;
	private java.lang.String bigPicturePath;
	private java.lang.String appPicturePath;






	/**
	 * Return the value associated with the column: picture_path
	 */
	public java.lang.String getPicturePath () {
		return picturePath;
	}

	/**
	 * Set the value related to the column: picture_path
	 * @param picturePath the picture_path value
	 */
	public void setPicturePath (java.lang.String picturePath) {
		this.picturePath = picturePath;
	}


	/**
	 * Return the value associated with the column: bigpicture_path
	 */
	public java.lang.String getBigPicturePath () {
		return bigPicturePath;
	}

	/**
	 * Set the value related to the column: bigpicture_path
	 * @param bigPicturePath the bigpicture_path value
	 */
	public void setBigPicturePath (java.lang.String bigPicturePath) {
		this.bigPicturePath = bigPicturePath;
	}


	/**
	 * Return the value associated with the column: apppicture_path
	 */
	public java.lang.String getAppPicturePath () {
		return appPicturePath;
	}

	/**
	 * Set the value related to the column: apppicture_path
	 * @param appPicturePath the apppicture_path value
	 */
	public void setAppPicturePath (java.lang.String appPicturePath) {
		this.appPicturePath = appPicturePath;
	}






	public String toString () {
		return super.toString();
	}


}