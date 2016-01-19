package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_product table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_product"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseProduct  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Product";
	public static String PROP_PRODUCT_EXT = "productExt";
	public static String PROP_BRAND = "brand";
	public static String PROP_CONFIG = "config";
	public static String PROP_TYPE = "type";
	public static String PROP_SHARE_CONTENT = "shareContent";
	public static String PROP_SCORE = "score";
	public static String PROP_WEBSITE = "website";
	public static String PROP_STOCK_COUNT = "stockCount";
	public static String PROP_PRODUCT_TEXT = "productText";
	public static String PROP_NAME = "name";
	public static String PROP_CATEGORY = "category";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ID = "id";
	
	public static String PROP_BARCODE = "barcode";


	// constructors
	public BaseProduct () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProduct (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProduct (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopConfig config,
		com.jspgou.cms.entity.Category category,
		com.jspgou.cms.entity.ProductType type,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		
		java.lang.String barcode,
		
		java.lang.Long viewCount,
		java.lang.Integer saleCount,
		java.util.Date createTime
		) {

		this.setId(id);
		this.setConfig(config);
		this.setCategory(category);
		this.setType(type);
		this.setWebsite(website);
		this.setName(name);
		
		this.setBarcode(barcode);
		
		this.setViewCount(viewCount);
		this.setSaleCount(saleCount);
		this.setCreateTime(createTime);
		
		
		
		
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.Long viewCount;
	private java.lang.Integer saleCount;
	private java.util.Date createTime;
	private java.util.Date expireTime;
	private java.lang.Boolean lackRemind;
	private java.lang.Integer score;
	private java.lang.String shareContent;
	private java.lang.Integer alertInventory;
	private java.lang.Integer parentCount;
	private java.lang.Double liRun;
	
	private java.lang.String barcode;

	// one to one
	private com.jspgou.cms.entity.ProductText productText;
	private com.jspgou.cms.entity.ProductExt productExt;
	

	// many to one
	private com.jspgou.cms.entity.Brand brand;
	private com.jspgou.cms.entity.ShopConfig config;
	private com.jspgou.cms.entity.Category category;
	private com.jspgou.cms.entity.ProductType type;
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.Product parent=null;
	// collections
	private java.util.Set<com.jspgou.cms.entity.ProductTag> tags;
	private java.util.Set<com.jspgou.cms.entity.ProductFashion> fashions;
	
	private java.util.List<java.lang.String> keywords;
	private java.util.List<com.jspgou.cms.entity.ProductPicture> pictures;
	private java.util.Map<java.lang.String, java.lang.String> attr;
	private java.util.List<com.jspgou.cms.entity.ProductExended> exendeds;
	private java.util.Set<com.jspgou.cms.entity.PopularityGroup> popularityGroups;



	public java.lang.Integer getParentCount() {
		return parentCount;
	}

	public void setParentCount(java.lang.Integer parentCount) {
		this.parentCount = parentCount;
	}

	public com.jspgou.cms.entity.Product getParent() {
		return parent;
	}

	public void setParent(com.jspgou.cms.entity.Product parent) {
		this.parent = parent;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}


	public java.lang.Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(java.lang.Long viewCount) {
		this.viewCount = viewCount;
	}

	public java.lang.Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(java.lang.Integer saleCount) {
		this.saleCount = saleCount;
	}


	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(java.util.Date expireTime) {
		this.expireTime = expireTime;
	}

	public java.lang.Boolean getLackRemind() {
		return lackRemind;
	}

	public void setLackRemind(java.lang.Boolean lackRemind) {
		this.lackRemind = lackRemind;
	}

	public java.lang.Integer getScore() {
		return score;
	}

	public void setScore(java.lang.Integer score) {
		this.score = score;
	}

	public java.lang.String getShareContent() {
		return shareContent;
	}

	public void setShareContent(java.lang.String shareContent) {
		this.shareContent = shareContent;
	}

	public java.lang.Integer getAlertInventory() {
		return alertInventory;
	}

	public void setAlertInventory(java.lang.Integer alertInventory) {
		this.alertInventory = alertInventory;
	}

	public java.lang.Double getLiRun() {
		return liRun;
	}

	public void setLiRun(java.lang.Double liRun) {
		this.liRun = liRun;
	}

	public com.jspgou.cms.entity.ProductText getProductText() {
		return productText;
	}

	public void setProductText(com.jspgou.cms.entity.ProductText productText) {
		this.productText = productText;
	}

	public com.jspgou.cms.entity.ProductExt getProductExt() {
		return productExt;
	}

	public void setProductExt(com.jspgou.cms.entity.ProductExt productExt) {
		this.productExt = productExt;
	}

	public com.jspgou.cms.entity.Brand getBrand() {
		return brand;
	}

	public void setBrand(com.jspgou.cms.entity.Brand brand) {
		this.brand = brand;
	}

	public com.jspgou.cms.entity.ShopConfig getConfig() {
		return config;
	}

	public void setConfig(com.jspgou.cms.entity.ShopConfig config) {
		this.config = config;
	}

	public com.jspgou.cms.entity.Category getCategory() {
		return category;
	}

	public void setCategory(com.jspgou.cms.entity.Category category) {
		this.category = category;
	}

	public com.jspgou.cms.entity.ProductType getType() {
		return type;
	}

	public void setType(com.jspgou.cms.entity.ProductType type) {
		this.type = type;
	}

	public com.jspgou.core.entity.Website getWebsite() {
		return website;
	}

	public void setWebsite(com.jspgou.core.entity.Website website) {
		this.website = website;
	}

	public java.util.Set<com.jspgou.cms.entity.ProductTag> getTags() {
		return tags;
	}

	public void setTags(java.util.Set<com.jspgou.cms.entity.ProductTag> tags) {
		this.tags = tags;
	}

	public java.util.Set<com.jspgou.cms.entity.ProductFashion> getFashions() {
		return fashions;
	}

	public void setFashions(
			java.util.Set<com.jspgou.cms.entity.ProductFashion> fashions) {
		this.fashions = fashions;
	}

	public java.util.List<java.lang.String> getKeywords() {
		return keywords;
	}

	public void setKeywords(java.util.List<java.lang.String> keywords) {
		this.keywords = keywords;
	}

	public java.util.List<com.jspgou.cms.entity.ProductPicture> getPictures() {
		return pictures;
	}

	public void setPictures(
			java.util.List<com.jspgou.cms.entity.ProductPicture> pictures) {
		this.pictures = pictures;
	}

	public java.util.Map<java.lang.String, java.lang.String> getAttr() {
		return attr;
	}

	public void setAttr(java.util.Map<java.lang.String, java.lang.String> attr) {
		this.attr = attr;
	}

	public java.util.List<com.jspgou.cms.entity.ProductExended> getExendeds() {
		return exendeds;
	}

	public void setExendeds(
			java.util.List<com.jspgou.cms.entity.ProductExended> exendeds) {
		this.exendeds = exendeds;
	}

	public java.util.Set<com.jspgou.cms.entity.PopularityGroup> getPopularityGroups() {
		return popularityGroups;
	}

	public void setPopularityGroups(
			java.util.Set<com.jspgou.cms.entity.PopularityGroup> popularityGroups) {
		this.popularityGroups = popularityGroups;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Product)) return false;
		else {
			com.jspgou.cms.entity.Product product = (com.jspgou.cms.entity.Product) obj;
			if (null == this.getId() || null == product.getId()) return false;
			else return (this.getId().equals(product.getId()));
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

	public java.lang.String getBarcode() {
		return barcode;
	}

	public void setBarcode(java.lang.String barcode) {
		this.barcode = barcode;
	}








}