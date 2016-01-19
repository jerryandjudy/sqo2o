package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_coupon table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_coupon"
 */

public abstract class BaseCoupon  implements Serializable {

	public static String REF = "Coupon";
	public static String PROP_COUPON_COUNT = "couponCount";
	public static String PROP_WEBSITE = "website";
	public static String PROP_COUPON_TIME = "couponTime";
	public static String PROP_ID = "id";
	public static String PROP_COUPON_NAME = "couponName";
	public static String PROP_ISUSING = "isusing";
	public static String PROP_LEAST_PRICE = "leastPrice";
	public static String PROP_COUPON_END_TIME = "couponEndTime";
	public static String PROP_COUPON_PRICE = "couponPrice";
	public static String PROP_COUPON_PICTURE = "couponPicture";


	// constructors
	public BaseCoupon () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCoupon (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCoupon (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String couponName,
		java.util.Date couponTime,
		java.util.Date couponEndTime,
		java.lang.String couponPicture,
		java.math.BigDecimal couponPrice,
		java.math.BigDecimal leastPrice,
		java.lang.Boolean isusing,
		java.lang.Integer couponCount) {

		this.setId(id);
		this.setWebsite(website);
		this.setCouponName(couponName);
		this.setCouponTime(couponTime);
		this.setCouponEndTime(couponEndTime);
		this.setCouponPicture(couponPicture);
		this.setCouponPrice(couponPrice);
		this.setLeastPrice(leastPrice);
		this.setIsusing(isusing);
		this.setCouponCount(couponCount);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String couponName;
	private java.util.Date couponTime;
	private java.util.Date couponEndTime;
	private java.lang.String couponPicture;
	private java.math.BigDecimal couponPrice;
	private java.math.BigDecimal leastPrice;
	private java.lang.Boolean isusing;
	private java.lang.Integer couponCount;

	// many to one
	private com.jspgou.core.entity.Website website;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
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
	 * Return the value associated with the column: coupon_name
	 */
	public java.lang.String getCouponName () {
		return couponName;
	}

	/**
	 * Set the value related to the column: coupon_name
	 * @param couponName the coupon_name value
	 */
	public void setCouponName (java.lang.String couponName) {
		this.couponName = couponName;
	}


	/**
	 * Return the value associated with the column: coupon_begintime
	 */
	public java.util.Date getCouponTime () {
		return couponTime;
	}

	/**
	 * Set the value related to the column: coupon_begintime
	 * @param couponTime the coupon_begintime value
	 */
	public void setCouponTime (java.util.Date couponTime) {
		this.couponTime = couponTime;
	}


	/**
	 * Return the value associated with the column: coupon_endtime
	 */
	public java.util.Date getCouponEndTime () {
		return couponEndTime;
	}

	/**
	 * Set the value related to the column: coupon_endtime
	 * @param couponEndTime the coupon_endtime value
	 */
	public void setCouponEndTime (java.util.Date couponEndTime) {
		this.couponEndTime = couponEndTime;
	}


	/**
	 * Return the value associated with the column: coupon_pic
	 */
	public java.lang.String getCouponPicture () {
		return couponPicture;
	}

	/**
	 * Set the value related to the column: coupon_pic
	 * @param couponPicture the coupon_pic value
	 */
	public void setCouponPicture (java.lang.String couponPicture) {
		this.couponPicture = couponPicture;
	}


	/**
	 * Return the value associated with the column: coupon_price
	 */
	public java.math.BigDecimal getCouponPrice () {
		return couponPrice;
	}

	/**
	 * Set the value related to the column: coupon_price
	 * @param couponPrice the coupon_price value
	 */
	public void setCouponPrice (java.math.BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}


	/**
	 * Return the value associated with the column: coupon_leastPrice
	 */
	public java.math.BigDecimal getLeastPrice () {
		return leastPrice;
	}

	/**
	 * Set the value related to the column: coupon_leastPrice
	 * @param leastPrice the coupon_leastPrice value
	 */
	public void setLeastPrice (java.math.BigDecimal leastPrice) {
		this.leastPrice = leastPrice;
	}


	/**
	 * Return the value associated with the column: is_using
	 */
	public java.lang.Boolean getIsusing () {
		return isusing;
	}

	/**
	 * Set the value related to the column: is_using
	 * @param isusing the is_using value
	 */
	public void setIsusing (java.lang.Boolean isusing) {
		this.isusing = isusing;
	}


	/**
	 * Return the value associated with the column: coupon_count
	 */
	public java.lang.Integer getCouponCount () {
		return couponCount;
	}

	/**
	 * Set the value related to the column: coupon_count
	 * @param couponCount the coupon_count value
	 */
	public void setCouponCount (java.lang.Integer couponCount) {
		this.couponCount = couponCount;
	}


	/**
	 * Return the value associated with the column: website_id
	 */
	public com.jspgou.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: website_id
	 * @param website the website_id value
	 */
	public void setWebsite (com.jspgou.core.entity.Website website) {
		this.website = website;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Coupon)) return false;
		else {
			com.jspgou.cms.entity.Coupon coupon = (com.jspgou.cms.entity.Coupon) obj;
			if (null == this.getId() || null == coupon.getId()) return false;
			else return (this.getId().equals(coupon.getId()));
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