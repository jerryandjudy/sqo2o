package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseGathering;


/**
* This class should preserve.
* @preserve
*/
public class Gathering extends BaseGathering {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Gathering () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Gathering (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Gathering (
		java.lang.Long id,
		com.jspgou.cms.entity.Order indent,
		com.jspgou.cms.entity.ShopAdmin shopAdmin,
		java.lang.String bank,
		java.lang.String accounts,
		java.lang.String drawee,
		java.lang.String comment) {

		super (
			id,
			indent,
			shopAdmin,
			bank,
			accounts,
			drawee,
			comment);
	}

/*[CONSTRUCTOR MARKER END]*/


}