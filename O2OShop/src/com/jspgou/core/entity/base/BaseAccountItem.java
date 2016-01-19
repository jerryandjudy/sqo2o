package com.jspgou.core.entity.base;

import java.util.Date;

import com.jspgou.cms.entity.Order;
import com.jspgou.core.entity.Account;

public class BaseAccountItem {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Double money;
	private java.util.Date moneyTime;
	private java.lang.Integer moneyType;
	private boolean useStatus;
	private boolean status;
	private java.lang.String remark;
	private Account account;
	private Order order;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public java.util.Date getMoneyTime() {
		return moneyTime;
	}
	public void setMoneyTime(java.util.Date moneyTime) {
		this.moneyTime = moneyTime;
	}
	public java.lang.Integer getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(java.lang.Integer moneyType) {
		this.moneyType = moneyType;
	}
	public boolean isUseStatus() {
		return useStatus;
	}
	public void setUseStatus(boolean useStatus) {
		this.useStatus = useStatus;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
