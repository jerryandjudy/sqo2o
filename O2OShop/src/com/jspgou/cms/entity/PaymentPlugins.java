package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BasePaymentPlugins;


/**
* This class should preserve.
* @preserve
*/
public class PaymentPlugins extends BasePaymentPlugins {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public PaymentPlugins () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public PaymentPlugins (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public PaymentPlugins (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String code,
		java.lang.Integer priority) {

		super (
			id,
			name,
			code,
			priority);
	}

/*[CONSTRUCTOR MARKER END]*/


}