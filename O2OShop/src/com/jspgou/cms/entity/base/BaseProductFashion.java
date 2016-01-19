package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_product_fashion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_product_fashion"
 * This class should preserve.
 * @preserve
 */

@SuppressWarnings("serial")
public abstract class BaseProductFashion  implements Serializable {

	public static String REF = "ProductFashion";
	public static String PROP_DEFAULT = "default";
	public static String PROP_STANDARD = "standard";
	public static String PROP_SALE_COUNT = "saleCount";
	public static String PROP_MARKET_PRICE = "marketPrice";
	public static String PROP_PRODUCT_CODE = "productCode";
	public static String PROP_STOCK_COUNT = "stockCount";
	public static String PROP_PRODUCT_ID = "productId";
	public static String PROP_ON_SALE = "onSale";
	public static String PROP_SALE_PRICE = "salePrice";
	public static String PROP_STANDARD_SECOND = "standardSecond";
	public static String PROP_FASHION_STYLE = "fashionStyle";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_FASHION_PIC = "fashionPic";
	public static String PROP_FASHION_SIZE = "fashionSize";
	public static String PROP_ID = "id";
	public static String PROP_COST_PRICE = "costPrice";
	public static String PROP_FASHION_COLOR = "fashionColor";


	// constructors
	public BaseProductFashion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProductFashion (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductFashion (
		java.lang.Long id,
		java.lang.Boolean isDefault) {

		this.setId(id);
		this.setIsDefault(isDefault);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String fashionStyle;
	private java.lang.String productCode;
	private java.lang.Integer saleCount;
	private java.lang.Integer stockCount;
	private java.lang.Integer onSale;
	private java.util.Date createTime;
	private java.lang.Double marketPrice;
	private java.lang.Double salePrice;
	private java.lang.Double costPrice;
	private java.lang.Integer lackRemind;
	private java.lang.String fashionPic;
	private java.lang.String fashionColor;
	private java.lang.String fashionSize;
	private java.lang.Boolean isDefault;
	private java.lang.String nature;
	private java.lang.String attitude;
	

	// many to one
	private com.jspgou.cms.entity.Product productId;

	// collections
	private java.util.Set<com.jspgou.cms.entity.CartItem> cartItems;
	private java.util.Set<com.jspgou.cms.entity.Standard> standards;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="fashion_id"
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
	 * Return the value associated with the column: fashion_style
	 */
	public java.lang.String getFashionStyle () {
		return fashionStyle;
	}

	/**
	 * Set the value related to the column: fashion_style
	 * @param fashionStyle the fashion_style value
	 */
	public void setFashionStyle (java.lang.String fashionStyle) {
		this.fashionStyle = fashionStyle;
	}


	/**
	 * Return the value associated with the column: product_code
	 */
	public java.lang.String getProductCode () {
		return productCode;
	}

	/**
	 * Set the value related to the column: product_code
	 * @param productCode the product_code value
	 */
	public void setProductCode (java.lang.String productCode) {
		this.productCode = productCode;
	}


	/**
	 * Return the value associated with the column: sale_count
	 */
	public java.lang.Integer getSaleCount () {
		return saleCount;
	}

	/**
	 * Set the value related to the column: sale_count
	 * @param saleCount the sale_count value
	 */
	public void setSaleCount (java.lang.Integer saleCount) {
		this.saleCount = saleCount;
	}


	/**
	 * Return the value associated with the column: stock_count
	 */
	public java.lang.Integer getStockCount () {
		return stockCount;
	}

	/**
	 * Set the value related to the column: stock_count
	 * @param stockCount the stock_count value
	 */
	public void setStockCount (java.lang.Integer stockCount) {
		this.stockCount = stockCount;
	}


	/**
	 * Return the value associated with the column: on_sale
	 */
	public java.lang.Integer getOnSale () {
		return onSale;
	}

	/**
	 * Set the value related to the column: on_sale
	 * @param onSale the on_sale value
	 */
	public void setOnSale (java.lang.Integer onSale) {
		this.onSale = onSale;
	}


	/**
	 * Return the value associated with the column: create_time
	 */
	public java.util.Date getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: create_time
	 * @param createTime the create_time value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}


	/**
	 * Return the value associated with the column: market_price
	 */
	public java.lang.Double getMarketPrice () {
		return marketPrice;
	}

	/**
	 * Set the value related to the column: market_price
	 * @param marketPrice the market_price value
	 */
	public void setMarketPrice (java.lang.Double marketPrice) {
		this.marketPrice = marketPrice;
	}


	/**
	 * Return the value associated with the column: sale_price
	 */
	public java.lang.Double getSalePrice () {
		return salePrice;
	}

	/**
	 * Set the value related to the column: sale_price
	 * @param salePrice the sale_price value
	 */
	public void setSalePrice (java.lang.Double salePrice) {
		this.salePrice = salePrice;
	}


	/**
	 * Return the value associated with the column: cost_price
	 */
	public java.lang.Double getCostPrice () {
		return costPrice;
	}

	/**
	 * Set the value related to the column: cost_price
	 * @param costPrice the cost_price value
	 */
	public void setCostPrice (java.lang.Double costPrice) {
		this.costPrice = costPrice;
	}


	/**
	 * Return the value associated with the column: lackRemind
	 */
	public java.lang.Integer getLackRemind () {
		return lackRemind;
	}

	/**
	 * Set the value related to the column: lackRemind
	 * @param lackRemind the lackRemind value
	 */
	public void setLackRemind (java.lang.Integer lackRemind) {
		this.lackRemind = lackRemind;
	}


	/**
	 * Return the value associated with the column: fashion_pic
	 */
	public java.lang.String getFashionPic () {
		return fashionPic;
	}

	/**
	 * Set the value related to the column: fashion_pic
	 * @param fashionPic the fashion_pic value
	 */
	public void setFashionPic (java.lang.String fashionPic) {
		this.fashionPic = fashionPic;
	}


	/**
	 * Return the value associated with the column: fashion_color
	 */
	public java.lang.String getFashionColor () {
		return fashionColor;
	}

	/**
	 * Set the value related to the column: fashion_color
	 * @param fashionColor the fashion_color value
	 */
	public void setFashionColor (java.lang.String fashionColor) {
		this.fashionColor = fashionColor;
	}


	/**
	 * Return the value associated with the column: fashion_size
	 */
	public java.lang.String getFashionSize () {
		return fashionSize;
	}

	/**
	 * Set the value related to the column: fashion_size
	 * @param fashionSize the fashion_size value
	 */
	public void setFashionSize (java.lang.String fashionSize) {
		this.fashionSize = fashionSize;
	}


	/**
	 * Return the value associated with the column: is_default
	 */
	public java.lang.Boolean getIsDefault () {
		return isDefault;
	}

	/**
	 * Set the value related to the column: is_default
	 * @param m_default the is_default value
	 */
	public void setIsDefault (java.lang.Boolean isDefault) {
		this.isDefault = isDefault;
	}


	/**
	 * Return the value associated with the column: product_id
	 */
	public com.jspgou.cms.entity.Product getProductId () {
		return productId;
	}

	/**
	 * Set the value related to the column: product_id
	 * @param productId the product_id value
	 */
	public void setProductId (com.jspgou.cms.entity.Product productId) {
		this.productId = productId;
	}




	/**
	 * Return the value associated with the column: cartItems
	 */
	public java.util.Set<com.jspgou.cms.entity.CartItem> getCartItems () {
		return cartItems;
	}

	/**
	 * Set the value related to the column: cartItems
	 * @param cartItems the cartItems value
	 */
	public void setCartItems (java.util.Set<com.jspgou.cms.entity.CartItem> cartItems) {
		this.cartItems = cartItems;
	}



	public void setStandards(java.util.Set<com.jspgou.cms.entity.Standard> standards) {
		this.standards = standards;
	}

	public java.util.Set<com.jspgou.cms.entity.Standard> getStandards() {
		return standards;
	}
	
	public void setNature(java.lang.String nature) {
		this.nature = nature;
	}

	public java.lang.String getNature() {
		return nature;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ProductFashion)) return false;
		else {
			com.jspgou.cms.entity.ProductFashion productFashion = (com.jspgou.cms.entity.ProductFashion) obj;
			if (null == this.getId() || null == productFashion.getId()) return false;
			else return (this.getId().equals(productFashion.getId()));
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

	public void setAttitude(java.lang.String attitude) {
		this.attitude = attitude;
	}

	public java.lang.String getAttitude() {
		return attitude;
	}

	


}