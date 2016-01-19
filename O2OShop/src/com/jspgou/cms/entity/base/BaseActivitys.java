package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_coupon table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_coupon"
 */

public abstract class BaseActivitys  implements Serializable {

	

	// constructors
	public BaseActivitys () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivitys (java.lang.Long id) {
		this.setId(id);
		initialize();
	}



	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String texts;
	private java.lang.String buyName;
	private java.lang.String giveName;
	private java.lang.String gysNames;
	private java.util.Date beginTime;
	private java.util.Date endTime;
	private java.util.Date addTime;
	private java.lang.Boolean isusing;
	private java.lang.Integer buyCount;
	private java.lang.Integer giveCount;

	// many to one
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.ProductSite buyProductSite;
	private com.jspgou.cms.entity.ProductSite giveProductSite;
    //one to many
	private java.util.Set<com.jspgou.cms.entity.Gys> gyss;



	public java.lang.String getGysNames() {
		return gysNames;
	}

	public void setGysNames(java.lang.String gysNames) {
		this.gysNames = gysNames;
	}

	public java.lang.String getTexts() {
		return texts;
	}

	public void setTexts(java.lang.String texts) {
		this.texts = texts;
	}

	public java.util.Date getAddTime() {
		return addTime;
	}

	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}

	public java.lang.Integer getGiveCount() {
		return giveCount;
	}

	public void setGiveCount(java.lang.Integer giveCount) {
		this.giveCount = giveCount;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}


	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.util.Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(java.util.Date beginTime) {
		this.beginTime = beginTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public java.lang.Boolean getIsusing() {
		return isusing;
	}

	public void setIsusing(java.lang.Boolean isusing) {
		this.isusing = isusing;
	}

	public java.lang.Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(java.lang.Integer buyCount) {
		this.buyCount = buyCount;
	}

	public com.jspgou.core.entity.Website getWebsite() {
		return website;
	}

	public void setWebsite(com.jspgou.core.entity.Website website) {
		this.website = website;
	}

	public com.jspgou.cms.entity.ProductSite getBuyProductSite() {
		return buyProductSite;
	}

	public void setBuyProductSite(com.jspgou.cms.entity.ProductSite buyProductSite) {
		this.buyProductSite = buyProductSite;
	}

	public com.jspgou.cms.entity.ProductSite getGiveProductSite() {
		return giveProductSite;
	}

	public void setGiveProductSite(com.jspgou.cms.entity.ProductSite giveProductSite) {
		this.giveProductSite = giveProductSite;
	}

	public java.util.Set<com.jspgou.cms.entity.Gys> getGyss() {
		return gyss;
	}

	public void setGyss(java.util.Set<com.jspgou.cms.entity.Gys> gyss) {
		this.gyss = gyss;
	}


	public java.lang.String getBuyName() {
		return buyName;
	}

	public void setBuyName(java.lang.String buyName) {
		this.buyName = buyName;
	}

	public java.lang.String getGiveName() {
		return giveName;
	}

	public void setGiveName(java.lang.String giveName) {
		this.giveName = giveName;
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