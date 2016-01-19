package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public  abstract class BaseBld implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private String id;
	private String companyName;
	private String khh;
	private String khhzh;
	private String hm;
	
	private String mapX;
	private String mapY;
	private String bldAddr;
	private String chainInfo;
	private String legalPerson;
	private String contact;
	private String contactTel;
	private String orgCodeCert;
	private String fjLlrAuthorize;
	private String fjHzyxs;
	private String fjBzs;
	private String fjQyyyzz;
	private String fjZzzgdm;
	private String fjSwdjz;
	private String fjKhxkz;
	private String fjFrffrSfz;
	private String fjShzp;
	private String fjRzkzfm;
	private String fjRzsqs;
	private String fjFrFfrYhk;
	private String fjJmht;
	
	
	
	
	private String phone;
	private Website website;
	private KetaUser ketaUser;
	private User user;
	private Boolean isDisabled;
	private Boolean isDef;
	private Date createTime;
	private Account account;
	public static String PROP_CREATETIME = "createTime";

	// constructors
			public BaseBld () {
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
	public String getBldAddr() {
		return bldAddr;
	}
	public void setBldAddr(String bldAddr) {
		this.bldAddr = bldAddr;
	}
	public String getChainInfo() {
		return chainInfo;
	}
	public void setChainInfo(String chainInfo) {
		this.chainInfo = chainInfo;
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
	public String getFjLlrAuthorize() {
		return fjLlrAuthorize;
	}
	public void setFjLlrAuthorize(String fjLlrAuthorize) {
		this.fjLlrAuthorize = fjLlrAuthorize;
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
	public String getFjQyyyzz() {
		return fjQyyyzz;
	}
	public void setFjQyyyzz(String fjQyyyzz) {
		this.fjQyyyzz = fjQyyyzz;
	}
	public String getFjZzzgdm() {
		return fjZzzgdm;
	}
	public void setFjZzzgdm(String fjZzzgdm) {
		this.fjZzzgdm = fjZzzgdm;
	}
	public String getFjSwdjz() {
		return fjSwdjz;
	}
	public void setFjSwdjz(String fjSwdjz) {
		this.fjSwdjz = fjSwdjz;
	}
	public String getFjKhxkz() {
		return fjKhxkz;
	}
	public void setFjKhxkz(String fjKhxkz) {
		this.fjKhxkz = fjKhxkz;
	}
	public String getFjFrffrSfz() {
		return fjFrffrSfz;
	}
	public void setFjFrffrSfz(String fjFrffrSfz) {
		this.fjFrffrSfz = fjFrffrSfz;
	}
	public String getFjShzp() {
		return fjShzp;
	}
	public void setFjShzp(String fjShzp) {
		this.fjShzp = fjShzp;
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
	public String getFjFrFfrYhk() {
		return fjFrFfrYhk;
	}
	public void setFjFrFfrYhk(String fjFrFfrYhk) {
		this.fjFrFfrYhk = fjFrFfrYhk;
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
	public Boolean getIsDef() {
		if(isDef==null){
			isDef=false;
		}
		return isDef;
	}
	public void setIsDef(Boolean isDef) {
		this.isDef = isDef;
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
	
	

}
