package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jspgou.cms.entity.Order;
/***
 * 支付订单
 * @author Administrator
 *
 */
@Entity
@Table(name = "jc_shop_order_jnl")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BaseEcOrderJnl implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigInteger id;

	private String paymentNo; // 支付订单号
	private String transType; // 交易类型 01消费，04退货
	private BigDecimal paymentAmount; // 支付金额

	private String qid; // 交易流水号
	private String traceNo; // 系统跟踪号
	private String traceDate; // 系统跟踪日期时间

	private String buyerIp; // 支付人IP
	private String curType; // 交易币种

	private String rspcode1; // 页面返回码
	private String rspcode2; // 后台通知返回码
	private String rspcode3; // 查询返回码

	private String qsAmount; // 清算金额
	private String qsCurrency; // 清算币种
	private String qsDate; // 清算日期，买家付款日期
	private String respTime; // 交易返回时间

	private String origQid; // 原始交易流水号
	private String branchId; // 成员行编号

	private String cardType; // 支付卡类型 银联值为01（借记卡）或02（贷记卡）
	private String cardNumber;
	//private EnumsOlpMethod olpMethod; // 支付网关(该笔订单使用的支付方式)
	private String orderDate; // 订单日期
	private String orderTime; // 订单时间
	private Order oRder; // 订单时间
	
	
	public Order getoRder() {
		return oRder;
	}

	public void setoRder(Order oRder) {
		this.oRder = oRder;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getTraceDate() {
		return traceDate;
	}

	public void setTraceDate(String traceDate) {
		this.traceDate = traceDate;
	}

	public String getBuyerIp() {
		return buyerIp;
	}

	public void setBuyerIp(String buyerIp) {
		this.buyerIp = buyerIp;
	}

	public String getCurType() {
		return curType;
	}

	public void setCurType(String curType) {
		this.curType = curType;
	}

	public String getRspcode1() {
		return rspcode1;
	}

	public void setRspcode1(String rspcode1) {
		this.rspcode1 = rspcode1;
	}

	public String getRspcode2() {
		return rspcode2;
	}

	public void setRspcode2(String rspcode2) {
		this.rspcode2 = rspcode2;
	}

	public String getRspcode3() {
		return rspcode3;
	}

	public void setRspcode3(String rspcode3) {
		this.rspcode3 = rspcode3;
	}

	public String getQsAmount() {
		return qsAmount;
	}

	public void setQsAmount(String qsAmount) {
		this.qsAmount = qsAmount;
	}

	public String getQsCurrency() {
		return qsCurrency;
	}

	public void setQsCurrency(String qsCurrency) {
		this.qsCurrency = qsCurrency;
	}

	public String getQsDate() {
		return qsDate;
	}

	public void setQsDate(String qsDate) {
		this.qsDate = qsDate;
	}

	public String getRespTime() {
		return respTime;
	}

	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	public String getOrigQid() {
		return origQid;
	}

	public void setOrigQid(String origQid) {
		this.origQid = origQid;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}



	

}
