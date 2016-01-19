package com.jspgou.cms.entity.base;

import java.io.Serializable;

import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public class BaseJmf  implements Serializable{
	
	public static String REF = "Jmf";
	public static String PROP_ID = "id";
	public static String PROP_JE = "je";
	public static String PROP_PTFC = "ptFc";
	public static String PROP_DLSFC = "dlsFc";
	public static String PROP_DL = "bl";
	public static String PROP_JMFTYPEID = "jmftypeId";
	
	
	
	private Long id;

	private Double je;//金额
	private Double ptFc;//平台分成
	private Double dlsFc;//代理商分成
	private Double bl;//抽成比例
	private com.jspgou.cms.entity.ShopDictionary  jmftypeId;//加盟费类型字典id
	
	
	
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
		if(isDisabled==null){
			isDisabled = false;//在po的get方法中赋予默认值，这样，Spring获取到对象的时候，才不会是有null属性
		}
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
	
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public com.jspgou.cms.entity.ShopDictionary getJmftypeId() {
		return jmftypeId;
	}
	public void setJmftypeId(com.jspgou.cms.entity.ShopDictionary jmftypeId) {
		this.jmftypeId = jmftypeId;
	}
	
	
	
	
	
	
	
	

}
