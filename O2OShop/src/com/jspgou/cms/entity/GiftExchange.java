package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseGiftExchange;



public class GiftExchange extends BaseGiftExchange {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public GiftExchange () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public GiftExchange (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public GiftExchange (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.Gift gift,
		java.util.Date createTime,
		java.lang.Integer status) {

		super (
			id,
			member,
			gift,
			createTime,
			status);
	}

/*[CONSTRUCTOR MARKER END]*/


}