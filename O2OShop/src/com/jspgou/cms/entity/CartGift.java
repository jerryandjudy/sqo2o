package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseCartGift;


/**
* This class should preserve.
* @preserve
*/
public class CartGift extends BaseCartGift {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CartGift () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CartGift (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CartGift (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.Cart cart,
		com.jspgou.cms.entity.Gift gift,
		java.lang.Integer count) {

		super (
			id,
			website,
			cart,
			gift,
			count);
	}

/*[CONSTRUCTOR MARKER END]*/


}