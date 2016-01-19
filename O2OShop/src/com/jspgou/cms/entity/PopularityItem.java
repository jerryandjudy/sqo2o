package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BasePopularityItem;



public class PopularityItem extends BasePopularityItem {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public PopularityItem () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public PopularityItem (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public PopularityItem (
		java.lang.Long id,
		com.jspgou.cms.entity.Cart cart,
		java.lang.Integer count) {

		super (
			id,
			cart,
			count);
	}

/*[CONSTRUCTOR MARKER END]*/


}