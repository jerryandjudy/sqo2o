package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseCoupon;


/**
* This class should preserve.
* @preserve
*/
public class Coupon extends BaseCoupon {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Coupon () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Coupon (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Coupon (
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

		super (
			id,
			website,
			couponName,
			couponTime,
			couponEndTime,
			couponPicture,
			couponPrice,
			leastPrice,
			isusing,
			couponCount);
	}

/*[CONSTRUCTOR MARKER END]*/


}