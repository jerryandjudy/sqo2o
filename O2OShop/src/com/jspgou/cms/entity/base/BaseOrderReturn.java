package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_order_return table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_order_return"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseOrderReturn  implements Serializable {

	public static String REF = "OrderReturn";
	public static String PROP_SELLER_MONEY = "sellerMoney";
	public static String PROP_MONEY = "money";
	public static String PROP_SHOP_DICTIONARY = "shopDictionary";
	public static String PROP_ORDER = "order";
	public static String PROP_ALIPAY_ID = "alipayId";
	public static String PROP_RETURN_TYPE = "returnType";
	public static String PROP_CODE = "code";
	public static String PROP_STATUS = "status";
	public static String PROP_PAY_TYPE = "PayType";
	public static String PROP_FINISHED_TIME = "finishedTime";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ID = "id";
	public static String PROP_REASON = "reason";


	// constructors
	public BaseOrderReturn () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseOrderReturn (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseOrderReturn (
		java.lang.Long id,
		com.jspgou.cms.entity.Order order,
		com.jspgou.cms.entity.ShopDictionary shopDictionary,
		java.lang.Integer payType,
		java.lang.Integer status,
		java.lang.Double money,
		java.lang.Integer returnType,
		java.util.Date createTime) {

		this.setId(id);
		this.setOrder(order);
		this.setShopDictionary(shopDictionary);
		this.setPayType(payType);
		this.setStatus(status);
		this.setMoney(money);
		this.setReturnType(returnType);
		this.setCreateTime(createTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String code;
	private java.lang.String reason;
	private java.lang.String alipayId;
	private java.lang.Integer status;
	private java.lang.Double money;
	private java.lang.Double sellerMoney;
	private java.lang.Integer returnType;
	private java.util.Date createTime;
	private java.util.Date finishedTime;
	private java.lang.Integer payType;
	
	// many to one
	private com.jspgou.cms.entity.Order order;
	private com.jspgou.cms.entity.ShopDictionary shopDictionary;

	// collections
	private java.util.List<com.jspgou.cms.entity.OrderReturnPicture> pictures;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="return_id"
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
	 * Return the value associated with the column: code
	 */
	public java.lang.String getCode () {
		return code;
	}

	/**
	 * Set the value related to the column: code
	 * @param code the code value
	 */
	public void setCode (java.lang.String code) {
		this.code = code;
	}


	/**
	 * Return the value associated with the column: reason
	 */
	public java.lang.String getReason () {
		return reason;
	}

	/**
	 * Set the value related to the column: reason
	 * @param reason the reason value
	 */
	public void setReason (java.lang.String reason) {
		this.reason = reason;
	}


	/**
	 * Return the value associated with the column: alipayId
	 */
	public java.lang.String getAlipayId () {
		return alipayId;
	}

	/**
	 * Set the value related to the column: alipayId
	 * @param alipayId the alipayId value
	 */
	public void setAlipayId (java.lang.String alipayId) {
		this.alipayId = alipayId;
	}


	/**
	 * Return the value associated with the column: status
	 */
	public java.lang.Integer getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (java.lang.Integer status) {
		this.status = status;
	}


	/**
	 * Return the value associated with the column: money
	 */
	public java.lang.Double getMoney () {
		return money;
	}

	/**
	 * Set the value related to the column: money
	 * @param money the money value
	 */
	public void setMoney (java.lang.Double money) {
		this.money = money;
	}


	/**
	 * Return the value associated with the column: sellerMoney
	 */
	public java.lang.Double getSellerMoney () {
		return sellerMoney;
	}

	/**
	 * Set the value related to the column: sellerMoney
	 * @param sellerMoney the sellerMoney value
	 */
	public void setSellerMoney (java.lang.Double sellerMoney) {
		this.sellerMoney = sellerMoney;
	}


	/**
	 * Return the value associated with the column: returnType
	 */
	public java.lang.Integer getReturnType () {
		return returnType;
	}

	/**
	 * Set the value related to the column: returnType
	 * @param returnType the returnType value
	 */
	public void setReturnType (java.lang.Integer returnType) {
		this.returnType = returnType;
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
	 * Return the value associated with the column: finished_time
	 */
	public java.util.Date getFinishedTime () {
		return finishedTime;
	}

	/**
	 * Set the value related to the column: finished_time
	 * @param finishedTime the finished_time value
	 */
	public void setFinishedTime (java.util.Date finishedTime) {
		this.finishedTime = finishedTime;
	}


	/**
	 * Return the value associated with the column: order_id
	 */
	public com.jspgou.cms.entity.Order getOrder () {
		return order;
	}

	/**
	 * Set the value related to the column: order_id
	 * @param order the order_id value
	 */
	public void setOrder (com.jspgou.cms.entity.Order order) {
		this.order = order;
	}


	/**
	 * Return the value associated with the column: reason_id
	 */
	public com.jspgou.cms.entity.ShopDictionary getShopDictionary () {
		return shopDictionary;
	}

	/**
	 * Set the value related to the column: reason_id
	 * @param shopDictionary the reason_id value
	 */
	public void setShopDictionary (com.jspgou.cms.entity.ShopDictionary shopDictionary) {
		this.shopDictionary = shopDictionary;
	}


	/**
	 * Return the value associated with the column: pictures
	 */
	public java.util.List<com.jspgou.cms.entity.OrderReturnPicture> getPictures () {
		return pictures;
	}

	/**
	 * Set the value related to the column: pictures
	 * @param pictures the pictures value
	 */
	public void setPictures (java.util.List<com.jspgou.cms.entity.OrderReturnPicture> pictures) {
		this.pictures = pictures;
	}



	public void setPayType(java.lang.Integer payType) {
		this.payType = payType;
	}

	public java.lang.Integer getPayType() {
		return payType;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.OrderReturn)) return false;
		else {
			com.jspgou.cms.entity.OrderReturn orderReturn = (com.jspgou.cms.entity.OrderReturn) obj;
			if (null == this.getId() || null == orderReturn.getId()) return false;
			else return (this.getId().equals(orderReturn.getId()));
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