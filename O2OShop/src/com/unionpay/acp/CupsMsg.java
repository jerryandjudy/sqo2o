package com.unionpay.acp;


public class CupsMsg implements Msg {
	private String version            ;//消息版本号      
	private String charset            ;//字符编码        
	private String signMethod         ;//签名方法        
	private String signature          ;//签名信息        
	private String transType          ;//交易类型        
	private String merAbbr            ;//商户名称        
	private String merId              ;//商户代码        
	private String merCode            ;//商户类型        
	private String backEndUrl         ;//通知URL         
	private String frontEndUrl        ;//返回URL         
	private String acqCode            ;//收单机构代码    
	private String orderTime          ;//交易开始日期时间
	private String orderNumber        ;//商户订单号      
	private String commodityName      ;//商品名称        
	private String commodityUrl       ;//商品URL         
	private String commodityUnitPrice ;//商品单价        
	private String commodityQuantity  ;//商品数量        
	private String transferFee        ;//运输费用        
	private String commodityDiscount  ;//优惠信息        
	private String orderAmount        ;//交易金额        
	private String orderCurrency      ;//交易币种        
	private String customerName       ;//持卡人姓名      
	private String defaultPayType     ;//默认支付方式    
	private String defaultBankNumber  ;//默认银行编码    
	private String transTimeout       ;//交易超时时间    
	private String customerIp         ;//持卡人IP        
	private String origQid            ;//原始交易流水号  
	private String merReserved        ;//商户保留域      
	private String reqaction          ;//银联请求action

	private String respCode      ;//响应码      
	private String respMsg       ;//响应信息    
	private String traceNumber   ;//系统跟踪号  
	private String traceTime     ;//系统跟踪时间
	private String qid           ;//交易流水号  
	private String respTime      ;//交易完成时间
	private String settleAmount  ;//清算金额    
	private String settleCurrency;//清算币种    
	private String settleDate    ;//清算日期    
	private String exchangeRate  ;//清算汇率    
	private String exchangeDate  ;//兑换日期    
	private String cupReserved   ;//系统保留域  
	private String queryResult   ;//查询结果 
	
	private String cardType;      //交易卡类型
	private String cardNumber;    //交易卡号

	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getTraceNumber() {
		return traceNumber;
	}
	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}
	public String getTraceTime() {
		return traceTime;
	}
	public void setTraceTime(String traceTime) {
		this.traceTime = traceTime;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getRespTime() {
		return respTime;
	}
	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}
	public String getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getCupReserved() {
		return cupReserved;
	}
	public void setCupReserved(String cupReserved) {
		this.cupReserved = cupReserved;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getMerAbbr() {
		return merAbbr;
	}
	public void setMerAbbr(String merAbbr) {
		this.merAbbr = merAbbr;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	public String getBackEndUrl() {
		return backEndUrl;
	}
	public void setBackEndUrl(String backEndUrl) {
		this.backEndUrl = backEndUrl;
	}
	public String getFrontEndUrl() {
		return frontEndUrl;
	}
	public void setFrontEndUrl(String frontEndUrl) {
		this.frontEndUrl = frontEndUrl;
	}
	public String getAcqCode() {
		return acqCode;
	}
	public void setAcqCode(String acqCode) {
		this.acqCode = acqCode;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityUrl() {
		return commodityUrl;
	}
	public void setCommodityUrl(String commodityUrl) {
		this.commodityUrl = commodityUrl;
	}
	public String getCommodityUnitPrice() {
		return commodityUnitPrice;
	}
	public void setCommodityUnitPrice(String commodityUnitPrice) {
		this.commodityUnitPrice = commodityUnitPrice;
	}
	public String getCommodityQuantity() {
		return commodityQuantity;
	}
	public void setCommodityQuantity(String commodityQuantity) {
		this.commodityQuantity = commodityQuantity;
	}
	public String getTransferFee() {
		return transferFee;
	}
	public void setTransferFee(String transferFee) {
		this.transferFee = transferFee;
	}
	public String getCommodityDiscount() {
		return commodityDiscount;
	}
	public void setCommodityDiscount(String commodityDiscount) {
		this.commodityDiscount = commodityDiscount;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDefaultPayType() {
		return defaultPayType;
	}
	public void setDefaultPayType(String defaultPayType) {
		this.defaultPayType = defaultPayType;
	}
	public String getDefaultBankNumber() {
		return defaultBankNumber;
	}
	public void setDefaultBankNumber(String defaultBankNumber) {
		this.defaultBankNumber = defaultBankNumber;
	}
	public String getTransTimeout() {
		return transTimeout;
	}
	public void setTransTimeout(String transTimeout) {
		this.transTimeout = transTimeout;
	}
	public String getCustomerIp() {
		return customerIp;
	}
	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}
	public String getOrigQid() {
		return origQid;
	}
	public void setOrigQid(String origQid) {
		this.origQid = origQid;
	}
	public String getMerReserved() {
		return merReserved;
	}
	public void setMerReserved(String merReserved) {
		this.merReserved = merReserved;
	}
	public void setReqaction(String reqaction) {
		this.reqaction = reqaction;
	}
	public String getReqaction() {
		return reqaction;
	}
	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}
	public String getQueryResult() {
		return queryResult;
	}

}