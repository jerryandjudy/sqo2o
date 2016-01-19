package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseDiscuss;


/**
* This class should preserve.
* @preserve
*/
public class Discuss extends BaseDiscuss {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Discuss () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Discuss (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Discuss (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.ProductSite productSite,
		java.lang.String content) {

		super (
			id,
			member,
			productSite,
			content);
	}

/*[CONSTRUCTOR MARKER END]*/


}