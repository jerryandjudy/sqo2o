package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Set;

import com.jspgou.cms.entity.Activitys;


/**
 * This is an object that contains data related to the jc_shop_product table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_site_product"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseProductSite  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ProductSite";
	public static String PROP_PRODUCT = "product";
	public static String PROP_SALE_COUNT = "saleCount";
	public static String PROP_SPECIAL = "special";
	public static String PROP_TYPE = "type";
	public static String PROP_SHARE_CONTENT = "shareContent";
	public static String PROP_RECOMMEND = "recommend";
	public static String PROP_NEW_PRODUCT = "newProduct";
	public static String PROP_VIEW_COUNT = "viewCount";
	public static String PROP_HOTSALE = "hotsale";
	public static String PROP_SCORE = "score";
	public static String PROP_MARKET_PRICE = "marketPrice";
	public static String PROP_WEBSITE = "website";
	public static String PROP_STOCK_COUNT = "stockCount";
	public static String PROP_ON_SALE = "onSale";
	public static String PROP_SALE_PRICE = "salePrice";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ID = "id";
	public static String PROP_COST_PRICE = "costPrice";


	// constructors
	public BaseProductSite () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProductSite (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductSite(
		java.lang.Long id,
		com.jspgou.cms.entity.Product product,
		com.jspgou.core.entity.Website website,
		java.lang.Double marketPrice,
		java.lang.Double salePrice,
		java.lang.Double costPrice,
		java.lang.Long viewCount,
		java.lang.Integer saleCount,
		java.lang.Integer stockCount,
		java.util.Date createTime,
		java.lang.Boolean special,
		java.lang.Boolean recommend,
		java.lang.Boolean hotsale,
		java.lang.Boolean newProduct,
		java.lang.Boolean onSale) {

		this.setId(id);
		this.setWebsite(website);
		this.setMarketPrice(marketPrice);
		this.setSalePrice(salePrice);
		this.setCostPrice(costPrice);
		this.setViewCount(viewCount);
		this.setSaleCount(saleCount);
		this.setStockCount(stockCount);
		this.setCreateTime(createTime);
		this.setSpecial(special);
		this.setRecommend(recommend);
		this.setHotsale(hotsale);
		this.setNewProduct(newProduct);
		this.setOnSale(onSale);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Double marketPrice;
	private java.lang.Double salePrice;
	private java.lang.Double costPrice;
	private java.lang.Long viewCount;
	private java.lang.Integer saleCount;
	private java.lang.Integer stockCount;
	private java.util.Date createTime;
	private java.util.Date expireTime;
	private java.lang.Boolean special;
	private java.lang.Boolean recommend;
	private java.lang.Boolean hotsale;
	private java.lang.Boolean newProduct;
	private java.lang.Boolean onSale;
	private java.lang.Boolean lackRemind;
	private java.lang.Integer score;
	private java.lang.String shareContent;
	private java.lang.Integer alertInventory;
	private java.lang.Double liRun;
	private java.lang.Double ptFc;
	private java.lang.Double dlsFc;


	private com.jspgou.cms.entity.Product product;
	private com.jspgou.core.entity.Website website;
	/*private Set<Activitys> activitys;*/
  
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="product_id"
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
	 * Return the value associated with the column: market_price
	 */
	public java.lang.Double getMarketPrice () {
		if(marketPrice==null){
			marketPrice=0d;
		}
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
		if(salePrice==null){
			salePrice=0d;
		}
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
		if(costPrice==null){
			costPrice=0d;
		}
		return costPrice;
	}

	public java.lang.Double getPtFc() {
		return ptFc;
	}

	public void setPtFc(java.lang.Double ptFc) {
		this.ptFc = ptFc;
	}

	public java.lang.Double getDlsFc() {
		return dlsFc;
	}

	public void setDlsFc(java.lang.Double dlsFc) {
		this.dlsFc = dlsFc;
	}

	/**
	 * Set the value related to the column: cost_price
	 * @param costPrice the cost_price value
	 */
	public void setCostPrice (java.lang.Double costPrice) {
		this.costPrice = costPrice;
	}


	/**
	 * Return the value associated with the column: view_count
	 */
	public java.lang.Long getViewCount () {
		if(viewCount==null){
			viewCount=0l;
		}
		return viewCount;
	}

	/**
	 * Set the value related to the column: view_count
	 * @param viewCount the view_count value
	 */
	public void setViewCount (java.lang.Long viewCount) {
		this.viewCount = viewCount;
	}


	/**
	 * Return the value associated with the column: sale_count
	 */
	public java.lang.Integer getSaleCount () {
		if(saleCount==null){
			saleCount=0;
		}
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
		if(stockCount==null){
			stockCount=0;
		}
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
	 * Return the value associated with the column: is_special
	 */
	public java.lang.Boolean getSpecial () {
		if(special==null){
			special=false;
		}
		return special;
	}

	/**
	 * Set the value related to the column: is_special
	 * @param special the is_special value
	 */
	public void setSpecial (java.lang.Boolean special) {
		this.special = special;
	}


	/**
	 * Return the value associated with the column: is_recommend
	 */
	public java.lang.Boolean getRecommend () {
		if(recommend==null){
			recommend=false;
		}
		return recommend;
	}

	/**
	 * Set the value related to the column: is_recommend
	 * @param recommend the is_recommend value
	 */
	public void setRecommend (java.lang.Boolean recommend) {
		this.recommend = recommend;
	}


	/**
	 * Return the value associated with the column: is_hotsale
	 */
	public java.lang.Boolean getHotsale () {
		if(hotsale==null){
			hotsale=false;
		}
		return hotsale;
	}

	/**
	 * Set the value related to the column: is_hotsale
	 * @param hotsale the is_hotsale value
	 */
	public void setHotsale (java.lang.Boolean hotsale) {
		this.hotsale = hotsale;
	}


	/**
	 * Return the value associated with the column: is_newProduct
	 */
	public java.lang.Boolean getNewProduct () {
		if(newProduct==null){
			newProduct=false;
		}
		return newProduct;
	}

	/**
	 * Set the value related to the column: is_newProduct
	 * @param newProduct the is_newProduct value
	 */
	public void setNewProduct (java.lang.Boolean newProduct) {
		this.newProduct = newProduct;
	}


	/**
	 * Return the value associated with the column: on_sale
	 */
	public java.lang.Boolean getOnSale () {
		if(onSale==null){
			onSale=false;
		}
		return onSale;
	}

	/**
	 * Set the value related to the column: on_sale
	 * @param onSale the on_sale value
	 */
	public void setOnSale (java.lang.Boolean onSale) {
		this.onSale = onSale;
	}

	public java.lang.Boolean getLackRemind() {
		if(lackRemind==null){
			lackRemind=true;
		}
		return lackRemind;
	}

	public void setLackRemind(java.lang.Boolean lackRemind) {
		this.lackRemind = lackRemind;
	}
	
	/**
	 * Return the value associated with the column: score
	 */
	public java.lang.Integer getScore () {
		if(score==null){
			score=0;
		}
		return score;
	}

	/**
	 * Set the value related to the column: score
	 * @param score the score value
	 */
	public void setScore (java.lang.Integer score) {
		this.score = score;
	}


	/**
	 * Return the value associated with the column: shareContent
	 */
	public java.lang.String getShareContent () {
		if(shareContent==null){
			shareContent="";
		}
		return shareContent;
	}

	/**
	 * Set the value related to the column: shareContent
	 * @param shareContent the shareContent value
	 */
	public void setShareContent (java.lang.String shareContent) {
		this.shareContent = shareContent;
	}

	public java.util.Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(java.util.Date expireTime) {
		this.expireTime = expireTime;
	}

	public java.lang.Integer getAlertInventory() {
		return alertInventory;
	}

	public void setAlertInventory(java.lang.Integer alertInventory) {
		this.alertInventory = alertInventory;
	}

	public java.lang.Double getLiRun() {
		if(liRun==null){
			liRun=0d;
		}
		
		return liRun;
	}

	public void setLiRun(java.lang.Double liRun) {
		
		this.liRun = liRun;
	}

	public com.jspgou.cms.entity.Product getProduct() {
		return product;
	}

	public void setProduct(com.jspgou.cms.entity.Product product) {
		this.product = product;
	}

	public com.jspgou.core.entity.Website getWebsite() {
		return website;
	}

	public void setWebsite(com.jspgou.core.entity.Website website) {
		this.website = website;
	}

	/*public Set<Activitys> getActivitys() {
		return activitys;
	}

	public void setActivitys(Set<Activitys> activitys) {
		this.activitys = activitys;
	}*/


	

}