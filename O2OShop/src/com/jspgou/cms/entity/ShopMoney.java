package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseShopMoney;


/**
* This class should preserve.
* @preserve
*/
public class ShopMoney extends BaseShopMoney {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ShopMoney () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopMoney (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopMoney (
		java.lang.Long id,
		java.lang.String name,
		java.lang.Double money,
		boolean status,
		java.util.Date createTime) {

		super (
			id,
			name,
			money,
			status,
			createTime);
	}

/*[CONSTRUCTOR MARKER END]*/


}