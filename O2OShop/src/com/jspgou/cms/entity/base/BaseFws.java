package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public  abstract class BaseFws implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private String id;
	private String companyName;
	private String khh;
	private String khhzh;
	private String hm;
	
	private String mapX;
	private String mapY;
	private String fwsAddr;
	private String legalPerson;
	private String contact;
	private String contactTel;
	private String orgCodeCert;
	private String fjHzyxs;
	private String fjBzs;
	private String fjKhxkz;
	private String fjRzkzfm;
	private String fjRzsqs;
	private String fjJmht;
	private Integer fwsType;
	
	
	
	private String phone;
	private String fwsCategory;
	private Website website;
	private KetaUser ketaUser;
	private User user;
	private Boolean isDisabled;
	private Date createTime;
	private Account account;
	public static String PROP_CREATETIME = "createTime";

	// constructors
			public BaseFws () {
				initialize();
			}
			protected void initialize () {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Website getWebsite() {
		return website;
	}
	public void setWebsite(Website website) {
		this.website = website;
	}
	public KetaUser getKetaUser() {
		return ketaUser;
	}
	public void setKetaUser(KetaUser ketaUser) {
		this.ketaUser = ketaUser;
	}
	public Boolean getIsDisabled() {
		if(isDisabled==null){
			isDisabled=false;
		}
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFwsAddr() {
		return fwsAddr;
	}
	public void setFwsAddr(String fwsAddr) {
		this.fwsAddr = fwsAddr;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getOrgCodeCert() {
		return orgCodeCert;
	}
	public void setOrgCodeCert(String orgCodeCert) {
		this.orgCodeCert = orgCodeCert;
	}
	public String getFjHzyxs() {
		return fjHzyxs;
	}
	public void setFjHzyxs(String fjHzyxs) {
		this.fjHzyxs = fjHzyxs;
	}
	public String getFjBzs() {
		return fjBzs;
	}
	public void setFjBzs(String fjBzs) {
		this.fjBzs = fjBzs;
	}
	public String getFjKhxkz() {
		return fjKhxkz;
	}
	public void setFjKhxkz(String fjKhxkz) {
		this.fjKhxkz = fjKhxkz;
	}
	public String getFjRzkzfm() {
		return fjRzkzfm;
	}
	public void setFjRzkzfm(String fjRzkzfm) {
		this.fjRzkzfm = fjRzkzfm;
	}
	public String getFjRzsqs() {
		return fjRzsqs;
	}
	public void setFjRzsqs(String fjRzsqs) {
		this.fjRzsqs = fjRzsqs;
	}
	public String getFjJmht() {
		return fjJmht;
	}
	public void setFjJmht(String fjJmht) {
		this.fjJmht = fjJmht;
	}
	public String getMapX() {
		return mapX;
	}
	public void setMapX(String mapX) {
		this.mapX = mapX;
	}
	public String getMapY() {
		return mapY;
	}
	public void setMapY(String mapY) {
		this.mapY = mapY;
	}
	public String getKhh() {
		return khh;
	}
	public void setKhh(String khh) {
		this.khh = khh;
	}
	public String getKhhzh() {
		return khhzh;
	}
	public void setKhhzh(String khhzh) {
		this.khhzh = khhzh;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
	}
	public Integer getFwsType() {
		return fwsType;
	}
	public void setFwsType(Integer fwsType) {
		this.fwsType = fwsType;
	}
	public String getFwsCategory() {
		return fwsCategory;
	}
	public void setFwsCategory(String fwsCategory) {
		this.fwsCategory = fwsCategory;
	}
	
	

}
