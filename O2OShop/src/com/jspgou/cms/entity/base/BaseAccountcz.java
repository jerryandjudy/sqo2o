package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.core.entity.Website;

public  abstract class BaseAccountcz implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private Long id;
	

	private KetaUser ketaUser;
	private Website website;
	
	private Double realValue;
	
	
	private Date addTime;
	
	
	private String organizationType;
	private String userRealname;
	
	private String payType;
	private String payStatus;
	private String addPeople;
	private String addName;
	
	
	public static String PROP_CREATETIME = "addTime";

	// constructors
			public BaseAccountcz () {
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
			public Double getRealValue() {
				return realValue;
			}
			public void setRealValue(Double realValue) {
				this.realValue = realValue;
			}
			public Date getAddTime() {
				return addTime;
			}
			public void setAddTime(Date addTime) {
				this.addTime = addTime;
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
			public String getPayStatus() {
				return payStatus;
			}
			public void setPayStatus(String payStatus) {
				this.payStatus = payStatus;
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
			public Website getWebsite() {
				return website;
			}
			public void setWebsite(Website website) {
				this.website = website;
			}
			public String getUserRealname() {
				return userRealname;
			}
			public void setUserRealname(String userRealname) {
				this.userRealname = userRealname;
			}
	
	
	
	

}
