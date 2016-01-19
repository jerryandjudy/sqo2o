package com.jspgou.cms.entity.base;

import java.io.Serializable;

import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public class BaseShopOrderType  implements Serializable{
	
	public static String REF = "ShopOrderType";
	public static String PROP_TYPE_NAME = "typeName";
	public static String PROP_ID = "id";
	public static String PROP_TYPE_CODE = "typeCode";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_REMARK = "remark";
	public static String PROP_TIME_OUT = "timeOut";
	
	
	
	private Long id;
	private String typeName;
	private String typeCode;
	private int priority;
	private String remark;
	private int timeOut;
	
	
	private User user;
	private Boolean isDisabled;
	private java.util.Date createTime;
	
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
