package com.jspgou.cms.entity.base;

import java.io.Serializable;

import com.jspgou.cms.web.Excel;
import com.jspgou.common.util.HtmlRegexpUtil;
import com.jspgou.common.util.ResourceUtil;


/**
 * This is an object that contains data related to the jc_shop_order table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_order"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseOrder  implements Serializable {

	public static String REF = "Order";
	public static String PROP_IP = "ip";
	public static String PROP_MEMBER = "member";
	public static String PROP_COMMENTS = "comments";
	public static String PROP_WEBSITE = "website";
	public static String PROP_RETURN_REASON = "returnReason";
	public static String PROP_FREIGHT = "freight";
	public static String PROP_CODE = "code";
	public static String PROP_PAYMENT = "payment";
	public static String PROP_PRODUCT_PRICE = "productPrice";
	public static String PROP_COUPON_PRICE = "couponPrice";
	public static String PROP_STATUS = "status";
	public static String PROP_SHIPPING_TIME = "shippingTime";
	public static String PROP_FINISHED_TIME = "finishedTime";
	public static String PROP_WEIGHT = "weight";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ID = "id";
	public static String PROP_SHOP_DIRECTORY = "shopDirectory";
	public static String PROP_SHIPPING = "shipping";
	public static String PROP_PRODUCT_NAME = "productName";
	public static String PROP_LAST_MODIFIED = "lastModified";
	public static String PROP_SCORE = "score";
	public static String PROP_TOTAL = "total";


	// constructors
	public BaseOrder () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseOrder (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseOrder (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.Payment payment,
		com.jspgou.cms.entity.Shipping shipping,
		com.jspgou.cms.entity.Shipping shopDirectory,
		long code,
		java.lang.String ip,
		java.util.Date createTime,
		java.util.Date lastModified,
		java.lang.Double total,
		java.lang.Integer score,
		java.lang.Double weight) {

		this.setId(id);
		this.setWebsite(website);
		this.setMember(member);
		this.setPayment(payment);
		this.setShipping(shipping);
		
		this.setCode(code);
		this.setIp(ip);
		this.setCreateTime(createTime);
		this.setLastModified(lastModified);
		this.setTotal(total);
		this.setScore(score);
		this.setWeight(weight);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	@Excel(exportName="订单号", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)  
	private java.lang.Long code;
	@Excel(exportName="备注", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)  
	private java.lang.String comments;
	private java.lang.String ip;
	@Excel(exportName="下单时间", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)  
	private java.util.Date createTime;
	@Excel(exportName="发货日期", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.util.Date shippingTime;
	@Excel(exportName="结单日期", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.util.Date finishedTime;
	@Excel(exportName="最后修改时间", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.util.Date lastModified;
	@Excel(exportName="商品总价格", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.Double productPrice;
	private java.lang.Double freight;
	@Excel(exportName="订单总额", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.Double total;
	@Excel(exportName="流水抽成", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.Double lsccMoney;
	private java.lang.Integer score;
	private java.lang.Double weight;
	private java.lang.Double couponPrice;
	@Excel(exportName="商品名字组合", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.String productName;
	@Excel(exportName="系统备注", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)  
	private java.lang.String sysComment;
	@Excel(exportName="支付状态", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)  
	private java.lang.Integer paymentStatus;
	@Excel(exportName="发货状态", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)  
	private java.lang.Integer shippingStatus;
	@Excel(exportName="订单状态", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)  
	private java.lang.Integer status;
	@Excel(exportName="收货人", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.String receiveName;
	@Excel(exportName="收货地址", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.String receiveAddress;
	private java.lang.String receiveZip;
	@Excel(exportName="电话", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.String receivePhone;
	@Excel(exportName="联系方式", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0)
	private java.lang.String receiveMobile;
	private java.lang.String tradeNo;
	private java.lang.String paymentCode;
	@Excel(exportName="订单类型", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)  
	private java.lang.Integer ordeRType;

	// many to one
	private com.jspgou.core.entity.Website website;
	@Excel(exportName="买家", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)  
	private com.jspgou.cms.entity.ShopMember member;
	@Excel(exportName="卖家", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)
	private com.jspgou.cms.entity.Gys gys;
	private com.jspgou.cms.entity.Bld bld;
	@Excel(exportName="支付方式", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)
	private com.jspgou.cms.entity.Payment payment;
	@Excel(exportName="配送方式", exportConvertSign = 1, exportFieldWidth = 100, importConvertSign = 0)
	private com.jspgou.cms.entity.Shipping shipping;
	private com.jspgou.cms.entity.OrderReturn returnOrder;
	private com.jspgou.cms.entity.EcOrderJnl ecOrderJnl;
	private com.jspgou.cms.entity.Order parent;
	
	// collections
	private java.util.Set<com.jspgou.cms.entity.OrderItem> items;
	private java.util.Set<com.jspgou.cms.entity.Order> child;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="order_id"
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
	public long getCode () {
		return code;
	}

	/**
	 * Set the value related to the column: code
	 * @param code the code value
	 */
	public void setCode (java.lang.Long code) {
		this.code = code;
	}

	/**
	 * Return the value associated with the column: comments
	 */
	public java.lang.String getComments () {
		return comments;
	}

	/**
	 * Set the value related to the column: comments
	 * @param comments the comments value
	 */
	public void setComments (java.lang.String comments) {
		this.comments = comments;
	}


	/**
	 * Return the value associated with the column: ip
	 */
	public java.lang.String getIp () {
		return ip;
	}

	/**
	 * Set the value related to the column: ip
	 * @param ip the ip value
	 */
	public void setIp (java.lang.String ip) {
		this.ip = ip;
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
	 * Return the value associated with the column: shipping_time
	 */
	public java.util.Date getShippingTime () {
		return shippingTime;
	}

	/**
	 * Set the value related to the column: shipping_time
	 * @param shippingTime the shipping_time value
	 */
	public void setShippingTime (java.util.Date shippingTime) {
		this.shippingTime = shippingTime;
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
	 * Return the value associated with the column: last_modified
	 */
	public java.util.Date getLastModified () {
		return lastModified;
	}

	/**
	 * Set the value related to the column: last_modified
	 * @param lastModified the last_modified value
	 */
	public void setLastModified (java.util.Date lastModified) {
		this.lastModified = lastModified;
	}


	/**
	 * Return the value associated with the column: product_price
	 */
	public java.lang.Double getProductPrice () {
		return productPrice;
	}

	/**
	 * Set the value related to the column: product_price
	 * @param productPrice the product_price value
	 */
	public void setProductPrice (java.lang.Double productPrice) {
		this.productPrice = productPrice;
	}


	/**
	 * Return the value associated with the column: freight
	 */
	public java.lang.Double getFreight () {
		return freight;
	}

	/**
	 * Set the value related to the column: freight
	 * @param freight the freight value
	 */
	public void setFreight (java.lang.Double freight) {
		this.freight = freight;
	}


	/**
	 * Return the value associated with the column: total
	 */
	public java.lang.Double getTotal () {
		return total;
	}

	/**
	 * Set the value related to the column: total
	 * @param total the total value
	 */
	public void setTotal (java.lang.Double total) {
		this.total = total;
	}


	/**
	 * Return the value associated with the column: score
	 */
	public java.lang.Integer getScore () {
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
	 * Return the value associated with the column: weight
	 */
	public java.lang.Double getWeight () {
		return weight;
	}

	/**
	 * Set the value related to the column: weight
	 * @param weight the weight value
	 */
	public void setWeight (java.lang.Double weight) {
		this.weight = weight;
	}

	/**
	 * Return the value associated with the column: coupon_price
	 */
	public java.lang.Double getCouponPrice () {
		return couponPrice;
	}

	/**
	 * Set the value related to the column: coupon_price
	 * @param couponPrice the coupon_price value
	 */
	public void setCouponPrice (java.lang.Double couponPrice) {
		this.couponPrice = couponPrice;
	}


	/**
	 * Return the value associated with the column: productName
	 */
	public java.lang.String getProductName () {
		return productName;
	}

	/**
	 * Set the value related to the column: productName
	 * @param productName the productName value
	 */
	public void setProductName (java.lang.String productName) {
		this.productName = productName;
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


	/**
	 * Return the value associated with the column: member_id
	 */
	public com.jspgou.cms.entity.ShopMember getMember () {
		return member;
	}
	public String getMemberConvert() {
		String res="";
		if(this.getOrdeRType()==1){
			res=getMember().getUsername();
		}else if(this.getOrdeRType()==2){
			if(this.getBld()!=null && this.getBld().getCompanyName()!=null){
				res= this.getBld().getCompanyName();
			}
		}
		return res;
	}

	/**
	 * Set the value related to the column: member_id
	 * @param member the member_id value
	 */
	public void setMember (com.jspgou.cms.entity.ShopMember member) {
		this.member = member;
	}


	/**
	 * Return the value associated with the column: payment_id
	 */
	public com.jspgou.cms.entity.Payment getPayment () {
		return payment;
	}
	/**
	 * Return the value associated with the column: payment_id
	 */
	public String getPaymentConvert () {
		return this.getPayment().getName();
	}

	/**
	 * Set the value related to the column: payment_id
	 * @param payment the payment_id value
	 */
	public void setPayment (com.jspgou.cms.entity.Payment payment) {
		this.payment = payment;
	}


	/**
	 * Return the value associated with the column: shipping_id
	 */
	public com.jspgou.cms.entity.Shipping getShipping () {
		return shipping;
	}
	/**
	 * Return the value associated with the column: shipping_id
	 */
	public String getShippingConvert() {
		return this.getShipping().getName();
	}

	/**
	 * Set the value related to the column: shipping_id
	 * @param shipping the shipping_id value
	 */
	public void setShipping (com.jspgou.cms.entity.Shipping shipping) {
		this.shipping = shipping;
	}

	/**
	 * Return the value associated with the column: items
	 */
	public java.util.Set<com.jspgou.cms.entity.OrderItem> getItems () {
		return items;
	}

	/**
	 * Set the value related to the column: items
	 * @param items the items value
	 */
	public void setItems (java.util.Set<com.jspgou.cms.entity.OrderItem> items) {
		this.items = items;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Order)) return false;
		else {
			com.jspgou.cms.entity.Order order = (com.jspgou.cms.entity.Order) obj;
			if (null == this.getId() || null == order.getId()) return false;
			else return (this.getId().equals(order.getId()));
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

	public void setPaymentStatus(java.lang.Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public java.lang.Integer getPaymentStatus() {
		return paymentStatus;
	}
	public String getPaymentStatusConvert() {
		return new ResourceUtil().porper("languages/jspgou_admin/messages_zh_CN.properties", "order.paymentStatus."+this.getPaymentStatus());
	}

	public void setShippingStatus(java.lang.Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public java.lang.Integer getShippingStatus() {
		return shippingStatus;
	}
	public String getShippingStatusConvert() {
		return new HtmlRegexpUtil().replaceTag(new ResourceUtil().porper("languages/jspgou_admin/messages_zh_CN.properties", "order.status."+this.getShippingStatus()));
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.Integer getStatus() {
		return status;
	}
	public String getStatusConvert() {
		
		return new ResourceUtil().porper("languages/jspgou_admin/messages_zh_CN.properties", "order.status."+this.getStatus());
	}

	public void setReturnOrder(com.jspgou.cms.entity.OrderReturn returnOrder) {
		this.returnOrder = returnOrder;
	}

	public com.jspgou.cms.entity.OrderReturn getReturnOrder() {
		return returnOrder;
	}

	public void setReceiveName(java.lang.String receiveName) {
		this.receiveName = receiveName;
	}

	public java.lang.String getReceiveName() {
		return receiveName;
	}

	public void setReceiveAddress(java.lang.String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public java.lang.String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveZip(java.lang.String receiveZip) {
		this.receiveZip = receiveZip;
	}

	public java.lang.String getReceiveZip() {
		return receiveZip;
	}

	public void setReceivePhone(java.lang.String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public java.lang.String getReceivePhone() {
		return receivePhone;
	}

	public void setReceiveMobile(java.lang.String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public java.lang.String getReceiveMobile() {
		return receiveMobile;
	}

	public void setTradeNo(java.lang.String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public java.lang.String getTradeNo() {
		return tradeNo;
	}

	public void setPaymentCode(java.lang.String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public java.lang.String getPaymentCode() {
		return paymentCode;
	}

	public java.lang.Integer getOrdeRType() {
		return ordeRType;
	}
	public String getOrdeRTypeConvert() {
		String res="";
		if(this.getOrdeRType()==1){
			res="商城订单";
		}else if(this.getOrdeRType()==2){
			res="进销存订单";
		}
		return res;
	}

	public void setOrdeRType(java.lang.Integer ordeRType) {
		this.ordeRType = ordeRType;
	}

	public String getGysConvert() {
		String res="";
		if(this.getOrdeRType()==1 && this.getBld()!=null&& this.getBld().getCompanyName()!=null){
			res=this.getBld().getCompanyName();
		}else if(this.getOrdeRType()==2&&this.getGys()!=null&& this.getGys().getCompanyName()!=null){
				res= this.getGys().getCompanyName();
		}
		return res;
	}
	public com.jspgou.cms.entity.Gys getGys() {
		return gys;
	}

	public void setGys(com.jspgou.cms.entity.Gys gys) {
		this.gys = gys;
	}

	public com.jspgou.cms.entity.Bld getBld() {
		return bld;
	}

	public void setBld(com.jspgou.cms.entity.Bld bld) {
		this.bld = bld;
	}

	public java.lang.String getSysComment() {
		return sysComment;
	}

	public void setSysComment(java.lang.String sysComment) {
		this.sysComment = sysComment;
	}

	public java.lang.Double getLsccMoney() {
		return lsccMoney;
	}

	public void setLsccMoney(java.lang.Double lsccMoney) {
		this.lsccMoney = lsccMoney;
	}

	public com.jspgou.cms.entity.Order getParent() {
		return parent;
	}

	public void setParent(com.jspgou.cms.entity.Order parent) {
		this.parent = parent;
	}

	public java.util.Set<com.jspgou.cms.entity.Order> getChild() {
		return child;
	}

	public void setChild(java.util.Set<com.jspgou.cms.entity.Order> child) {
		this.child = child;
	}

	public com.jspgou.cms.entity.EcOrderJnl getEcOrderJnl() {
		return ecOrderJnl;
	}

	public void setEcOrderJnl(com.jspgou.cms.entity.EcOrderJnl ecOrderJnl) {
		this.ecOrderJnl = ecOrderJnl;
	}


}