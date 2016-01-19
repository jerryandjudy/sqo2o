package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseShopConfig;


/**
* This class should preserve.
* @preserve
*/
public class ShopConfig extends BaseShopConfig {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ShopConfig () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopConfig (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopConfig (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMemberGroup registerGroup,
		java.lang.String shopPriceName,
		java.lang.String marketPriceName,
		java.lang.Integer favoriteSize,
		java.lang.Boolean registerAuto) {

		super (
			id,
			registerGroup,
			shopPriceName,
			marketPriceName,
			favoriteSize,
			registerAuto);
	}

/*[CONSTRUCTOR MARKER END]*/


}