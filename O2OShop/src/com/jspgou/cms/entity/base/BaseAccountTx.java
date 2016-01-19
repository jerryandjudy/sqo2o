package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.core.entity.Website;

public  abstract class BaseAccountTx implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private Long id;
	

	private KetaUser ketaUser;
	private Website website;
	
	private Double realValue;
	private Double accountBalance;
	
	
	private Date addTime;
	
	
	private String userRealname;
	private String organizationType;
	
	private String payType;
	private String payBank;
	private String payHm;
	private String payBankAccount;
	private String payStatus;
	private Date paySuccessTime;
	
	
	private String addPeople;
	private String addName;
	
	
	public static String PROP_CREATETIME = "addTime";

	// constructors
			public BaseAccountTx () {
				initialize();
			}
			protected void initialize () {}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public KetaUser getKetaUser() {
				return ketaUser;
			}
			public void setKetaUser(KetaUser ketaUser) {
				this.ketaUser = ketaUser;
			}
			public Website getWebsite() {
				return website;
			}
			public void setWebsite(Website website) {
				this.website = website;
			}
			public Double getRealValue() {
				return realValue;
			}
			public void setRealValue(Double realValue) {
				this.realValue = realValue;
			}
			public Double getAccountBalance() {
				return accountBalance;
			}
			public void setAccountBalance(Double accountBalance) {
				this.accountBalance = accountBalance;
			}
			public Date getAddTime() {
				return addTime;
			}
			public void setAddTime(Date addTime) {
				this.addTime = addTime;
			}
			public String getUserRealname() {
				return userRealname;
			}
			public void setUserRealname(String userRealname) {
				this.userRealname = userRealname;
			}
			public String getOrganizationType() {
				return organizationType;
			}
			public void setOrganizationType(String organizationType) {
				this.organizationType = organizationType;
			}
			public String getPayType() {
				return payType;
			}
			public void setPayType(String payType) {
				this.payType = payType;
			}
			public String getPayBank() {
				return payBank;
			}
			public void setPayBank(String payBank) {
				this.payBank = payBank;
			}
			public String getPayHm() {
				return payHm;
			}
			public void setPayHm(String payHm) {
				this.payHm = payHm;
			}
			public String getPayBankAccount() {
				return payBankAccount;
			}
			public void setPayBankAccount(String payBankAccount) {
				this.payBankAccount = payBankAccount;
			}
			public String getPayStatus() {
				return payStatus;
			}
			public void setPayStatus(String payStatus) {
				this.payStatus = payStatus;
			}
			public Date getPaySuccessTime() {
				return paySuccessTime;
			}
			public void setPaySuccessTime(Date paySuccessTime) {
				this.paySuccessTime = paySuccessTime;
			}
			public String getAddPeople() {
				return addPeople;
			}
			public void setAddPeople(String addPeople) {
				this.addPeople = addPeople;
			}
			public String getAddName() {
				return addName;
			}
			public void setAddName(String addName) {
				this.addName = addName;
			}
			
	
	

}
