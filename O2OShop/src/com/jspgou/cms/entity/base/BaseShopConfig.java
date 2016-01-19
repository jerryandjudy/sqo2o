package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_config table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_config"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopConfig  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ShopConfig";
	public static String PROP_REGISTER_AUTO = "registerAuto";
	public static String PROP_FAVORITE_SIZE = "favoriteSize";
	public static String PROP_WEBSITE = "website";
	public static String PROP_REGISTER_GROUP = "registerGroup";
	public static String PROP_ID = "id";
	public static String PROP_MARKET_PRICE_NAME = "marketPriceName";
	public static String PROP_SHOP_PRICE_NAME = "shopPriceName";


	// constructors
	public BaseShopConfig () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopConfig (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopConfig (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMemberGroup registerGroup,
		java.lang.String shopPriceName,
		java.lang.String marketPriceName,
		java.lang.Integer favoriteSize,
		java.lang.Boolean registerAuto) {

		this.setId(id);
		this.setRegisterGroup(registerGroup);
		this.setShopPriceName(shopPriceName);
		this.setMarketPriceName(marketPriceName);
		this.setFavoriteSize(favoriteSize);
		this.setRegisterAuto(registerAuto);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String shopPriceName;
	private java.lang.String marketPriceName;
	private java.lang.Integer favoriteSize;
	private java.lang.Boolean registerAuto;

	// one to one
	private com.jspgou.core.entity.Website website;

	// many to one
	private com.jspgou.cms.entity.ShopMemberGroup registerGroup;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="config_id"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: shop_price_name
	 */
	public java.lang.String getShopPriceName () {
		return shopPriceName;
	}

	/**
	 * Set the value related to the column: shop_price_name
	 * @param shopPriceName the shop_price_name value
	 */
	public void setShopPriceName (java.lang.String shopPriceName) {
		this.shopPriceName = shopPriceName;
	}


	/**
	 * Return the value associated with the column: market_price_name
	 */
	public java.lang.String getMarketPriceName () {
		return marketPriceName;
	}

	/**
	 * Set the value related to the column: market_price_name
	 * @param marketPriceName the market_price_name value
	 */
	public void setMarketPriceName (java.lang.String marketPriceName) {
		this.marketPriceName = marketPriceName;
	}


	/**
	 * Return the value associated with the column: favorite_size
	 */
	public java.lang.Integer getFavoriteSize () {
		return favoriteSize;
	}

	/**
	 * Set the value related to the column: favorite_size
	 * @param favoriteSize the favorite_size value
	 */
	public void setFavoriteSize (java.lang.Integer favoriteSize) {
		this.favoriteSize = favoriteSize;
	}


	/**
	 * Return the value associated with the column: register_auto
	 */
	public java.lang.Boolean getRegisterAuto () {
		return registerAuto;
	}

	/**
	 * Set the value related to the column: register_auto
	 * @param registerAuto the register_auto value
	 */
	public void setRegisterAuto (java.lang.Boolean registerAuto) {
		this.registerAuto = registerAuto;
	}


	/**
	 * Return the value associated with the column: website
	 */
	public com.jspgou.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: website
	 * @param website the website value
	 */
	public void setWebsite (com.jspgou.core.entity.Website website) {
		this.website = website;
	}


	/**
	 * Return the value associated with the column: register_group_id
	 */
	public com.jspgou.cms.entity.ShopMemberGroup getRegisterGroup () {
		return registerGroup;
	}

	/**
	 * Set the value related to the column: register_group_id
	 * @param registerGroup the register_group_id value
	 */
	public void setRegisterGroup (com.jspgou.cms.entity.ShopMemberGroup registerGroup) {
		this.registerGroup = registerGroup;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopConfig)) return false;
		else {
			com.jspgou.cms.entity.ShopConfig shopConfig = (com.jspgou.cms.entity.ShopConfig) obj;
			if (null == this.getId() || null == shopConfig.getId()) return false;
			else return (this.getId().equals(shopConfig.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}