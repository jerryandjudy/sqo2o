package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseCustomerService;


/**
* This class should preserve.
* @preserve
*/
public class CustomerService extends BaseCustomerService {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CustomerService () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CustomerService (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CustomerService (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String type,
		java.lang.String content,
		java.lang.Integer priority,
		java.lang.Boolean disable) {

		super (
			id,
			name,
			type,
			content,
			priority,
			disable);
	}

/*[CONSTRUCTOR MARKER END]*/


}