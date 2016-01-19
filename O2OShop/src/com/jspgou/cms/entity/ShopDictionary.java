package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseShopDictionary;


/**
* This class should preserve.
* @preserve
*/
public class ShopDictionary extends BaseShopDictionary {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ShopDictionary () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopDictionary (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopDictionary (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopDictionaryType shopDictionaryType,
		java.lang.String name) {

		super (
			id,
			shopDictionaryType,
			name);
	}

/*[CONSTRUCTOR MARKER END]*/


}