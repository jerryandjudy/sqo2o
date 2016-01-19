package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.core.entity.Website;

public  abstract class BaseJmfRecharge implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private Long id;
	

	private KetaUser ketaUser;
	private Website website;
	
	private Double referenceValue;
	private Double ptFc;
	private Double dlsFc;
	private Double realValue;
	
	
	private Date validFrom;
	private Date validUntil;
	private Date addTime;
	
	
	private String organizationType;
	private String userRealname;
	
	private String payType;
	private String payStatus;
	private String addPeople;
	private String addName;
	
	
	public static String PROP_CREATETIME = "addTime";

	// constructors
			public BaseJmfRecharge () {
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
			public Double getReferenceValue() {
				return referenceValue;
			}
			public void setReferenceValue(Double referenceValue) {
				this.referenceValue = referenceValue;
			}
			public Double getPtFc() {
				return ptFc;
			}
			public void setPtFc(Double ptFc) {
				this.ptFc = ptFc;
			}
			public Double getDlsFc() {
				return dlsFc;
			}
			public void setDlsFc(Double dlsFc) {
				this.dlsFc = dlsFc;
			}
			public Double getRealValue() {
				return realValue;
			}
			public void setRealValue(Double realValue) {
				this.realValue = realValue;
			}
			public Date getValidFrom() {
				return validFrom;
			}
			public void setValidFrom(Date validFrom) {
				this.validFrom = validFrom;
			}
			public Date getValidUntil() {
				return validUntil;
			}
			public void setValidUntil(Date validUntil) {
				this.validUntil = validUntil;
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
