package com.jspgou.core.entity.base;

import java.util.Date;

public class BaseAccount {
	 private static final long serialVersionUID = 1L;
	 private String id;
	 private String username;
	 private Double money;
	 private Double frozenMoney;
	 private Date createTime;
	 private Integer status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMoney() {
		if(money==null){
			money=0d;
		}
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreateTime() {
		if(createTime==null){
			createTime=new Date();
		}
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Double getFrozenMoney() {
		if(frozenMoney==null){
			frozenMoney=0d;
		}
		return frozenMoney;
	}
	public void setFrozenMoney(Double frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	 
}
