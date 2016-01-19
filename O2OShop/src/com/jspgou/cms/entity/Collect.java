package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseCollect;


/**
* This class should preserve.
* @preserve
*/
public class Collect extends BaseCollect {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Collect () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Collect (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Collect (
		java.lang.Integer id,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.ProductSite productSite) {

		super (
			id,
			member,
			productSite);
	}

/*[CONSTRUCTOR MARKER END]*/


}