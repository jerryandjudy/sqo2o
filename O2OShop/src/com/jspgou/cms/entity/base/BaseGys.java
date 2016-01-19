package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.ContentPicture;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public  abstract class BaseGys implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private String id;
	private String companyName;
	private String jgdm;
	private String fr;
	private String lxr;
	private String phone;
	private String bgdz;
	private String khh;
	private String khhzh;
	private String hm;
	private String gsyyzz;
	private String swdjz;
	private String zzjgdmz;
	private String zlbzs;
	private String gyssqs;
	private String mapX;
	private String mapY;
	private Website website;
	private KetaUser ketaUser;
	private User user;
	private Boolean isDisabled;
	private Date createTime;
	private Account account;
	private java.util.List<ContentPicture> pictures;
	private java.util.List<ContentPicture> psqy;
	public static String PROP_CREATETIME = "createTime";

	// constructors
			public BaseGys () {
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
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	public java.util.List<ContentPicture> getPictures() {
		return pictures;
	}
	public void setPictures(java.util.List<ContentPicture> pictures) {
		this.pictures = pictures;
	}
	public String getFr() {
		return fr;
	}
	public void setFr(String fr) {
		this.fr = fr;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBgdz() {
		return bgdz;
	}
	public void setBgdz(String bgdz) {
		this.bgdz = bgdz;
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
	public String getGsyyzz() {
		return gsyyzz;
	}
	public void setGsyyzz(String gsyyzz) {
		this.gsyyzz = gsyyzz;
	}
	public String getSwdjz() {
		return swdjz;
	}
	public void setSwdjz(String swdjz) {
		this.swdjz = swdjz;
	}
	public String getZzjgdmz() {
		return zzjgdmz;
	}
	public void setZzjgdmz(String zzjgdmz) {
		this.zzjgdmz = zzjgdmz;
	}
	public String getZlbzs() {
		return zlbzs;
	}
	public void setZlbzs(String zlbzs) {
		this.zlbzs = zlbzs;
	}
	public String getGyssqs() {
		return gyssqs;
	}
	public void setGyssqs(String gyssqs) {
		this.gyssqs = gyssqs;
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
	public java.util.List<ContentPicture> getPsqy() {
		return psqy;
	}
	public void setPsqy(java.util.List<ContentPicture> psqy) {
		this.psqy = psqy;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
	}

}
