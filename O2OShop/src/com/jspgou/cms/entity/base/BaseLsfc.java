package com.jspgou.cms.entity.base;

import java.io.Serializable;

import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public class BaseLsfc  implements Serializable{
	
	public static String REF = "Lsfc";
	public static String PROP_ID = "id";
	public static String PROP_ED = "ed";
	public static String PROP_PTFC = "ptFc";
	public static String PROP_DLSFC = "dlsFc";
	public static String PROP_DL = "bl";
	public static String PROP_FCTYPEID = "fctypeId";
	
	
	
	private Long id;

	private Double ed;//额度
	private Double ptFc;//平台分成
	private Double dlsFc;//代理商分成
	private Double bl;//抽成比例
	private com.jspgou.cms.entity.ShopDictionary  fctypeId;//分成类型字典id
	
	
	
	private Website website;
	private User user;
	private Boolean isDisabled;
	private java.util.Date createTime;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getEd() {
		return ed;
	}
	public void setEd(Double ed) {
		this.ed = ed;
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
	public Double getBl() {
		return bl;
	}
	public void setBl(Double bl) {
		this.bl = bl;
	}
	public Website getWebsite() {
		return website;
	}
	public void setWebsite(Website website) {
		this.website = website;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getIsDisabled() {
		isDisabled = false;//在po的get方法中赋予默认值，这样，Spring获取到对象的时候，才不会是有null属性
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public com.jspgou.cms.entity.ShopDictionary getFctypeId() {
		return fctypeId;
	}
	public void setFctypeId(com.jspgou.cms.entity.ShopDictionary fctypeId) {
		this.fctypeId = fctypeId;
	}
	
	
	
	
	
	
	
	

}
