package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseShopMemberGroup;


/**
* This class should preserve.
* @preserve
*/
public class ShopMemberGroup extends BaseShopMemberGroup {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ShopMemberGroup () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopMemberGroup (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopMemberGroup (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.Integer score,
		java.lang.Integer discount) {

		super (
			id,
			website,
			name,
			score,
			discount);
	}

/*[CONSTRUCTOR MARKER END]*/


}