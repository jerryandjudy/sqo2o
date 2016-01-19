package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_order_item table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_order_item"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseOrderItem  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "OrderItem";
	public static String PROP_SECKILLPRICE = "seckillprice";
	public static String PROP_COUNT = "count";
	public static String PROP_PRODUCT = "product";
	public static String PROP_SALE_PRICE = "salePrice";
	public static String PROP_PRODUCT_FASH = "productFash";
	public static String PROP_WEBSITE = "website";
	public static String PROP_ORDER = "order";
	public static String PROP_ID = "id";
	public static String PROP_MEMBER_PRICE = "memberPrice";
	public static String PROP_PRODUCT_NAME = "productName";
	public static String PROP_FINAL_PRICE = "finalPrice";
	public static String PROP_COST_PRICE = "costPrice";


	// constructors
	public BaseOrderItem () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseOrderItem (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseOrderItem (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.ProductSite productSite,
		com.jspgou.cms.entity.Order ordeR,
		java.lang.Integer count) {

		this.setId(id);
		this.setWebsite(website);
		this.setProductSite(productSite);
		this.setOrdeR(ordeR);
		this.setCount(count);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer count;
	private java.lang.Double salePrice;
	private java.lang.Double memberPrice;
	private java.lang.Double costPrice;
	private java.lang.Double finalPrice;
	private java.lang.Double seckillprice;
	private Boolean isZp;

	// many to one
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.ProductSite productSite;
	private com.jspgou.cms.entity.Order ordeR;
	private com.jspgou.cms.entity.ProductFashion productFash;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="orderitem_id"
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
	 * Return the value associated with the column: count
	 */
	public java.lang.Integer getCount () {
		return count;
	}

	/**
	 * Set the value related to the column: count
	 * @param count the count value
	 */
	public void setCount (java.lang.Integer count) {
		this.count = count;
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
	 * Return the value associated with the column: member_price
	 */
	public java.lang.Double getMemberPrice () {
		return memberPrice;
	}

	/**
	 * Set the value related to the column: member_price
	 * @param memberPrice the member_price value
	 */
	public void setMemberPrice (java.lang.Double memberPrice) {
		this.memberPrice = memberPrice;
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
	 * Return the value associated with the column: final_price
	 */
	public java.lang.Double getFinalPrice () {
		return finalPrice;
	}

	/**
	 * Set the value related to the column: final_price
	 * @param finalPrice the final_price value
	 */
	public void setFinalPrice (java.lang.Double finalPrice) {
		this.finalPrice = finalPrice;
	}


	/**
	 * Return the value associated with the column: seckillprice
	 */
	public java.lang.Double getSeckillprice () {
		return seckillprice;
	}

	/**
	 * Set the value related to the column: seckillprice
	 * @param seckillprice the seckillprice value
	 */
	public void setSeckillprice (java.lang.Double seckillprice) {
		this.seckillprice = seckillprice;
	}


	/**
	 * Return the value associated with the column: website_id
	 */
	public com.jspgou.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: website_id
	 * @param website the website_id value
	 */
	public void setWebsite (com.jspgou.core.entity.Website website) {
		this.website = website;
	}




	public com.jspgou.cms.entity.ProductSite getProductSite() {
		return productSite;
	}

	public void setProductSite(com.jspgou.cms.entity.ProductSite productSite) {
		this.productSite = productSite;
	}

	/**
	 * Return the value associated with the column: order_id
	 */
	public com.jspgou.cms.entity.Order getOrdeR () {
		return ordeR;
	}

	/**
	 * Set the value related to the column: order_id
	 * @param order the order_id value
	 */
	public void setOrdeR (com.jspgou.cms.entity.Order ordeR) {
		this.ordeR = ordeR;
	}


	/**
	 * Return the value associated with the column: productFash_id
	 */
	public com.jspgou.cms.entity.ProductFashion getProductFash () {
		return productFash;
	}

	/**
	 * Set the value related to the column: productFash_id
	 * @param productFash the productFash_id value
	 */
	public void setProductFash (com.jspgou.cms.entity.ProductFashion productFash) {
		this.productFash = productFash;
	}



	public Boolean getIsZp() {
		if(isZp==null){
			isZp=false;	
		}
		return isZp;
	}

	public void setIsZp(Boolean isZp) {
		this.isZp = isZp;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.OrderItem)) return false;
		else {
			com.jspgou.cms.entity.OrderItem orderItem = (com.jspgou.cms.entity.OrderItem) obj;
			if (null == this.getId() || null == orderItem.getId()) return false;
			else return (this.getId().equals(orderItem.getId()));
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