package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseConsult;


/**
* This class should preserve.
* @preserve
*/
public class Consult extends BaseConsult {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Consult () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Consult (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Consult (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.ProductSite productSite) {

		super (
			id,
			member,
			productSite);
	}

/*[CONSTRUCTOR MARKER END]*/


}