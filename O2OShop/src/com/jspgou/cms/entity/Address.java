package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseAddress;


/**
* This class should preserve.
* @preserve
*/
public class Address extends BaseAddress {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Address () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Address (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Address (
		java.lang.Long id,
		java.lang.String name) {

		super (
			id,
			name);
	}

/*[CONSTRUCTOR MARKER END]*/


}