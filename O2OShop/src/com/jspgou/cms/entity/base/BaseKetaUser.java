package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.Organization;

public  abstract class BaseKetaUser implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private java.lang.Long id;
	private String email;
	private String password;
	private String phone;
	private String realname;
	private String salt;
	private String username;
	private String userType;
	private String painPass;
	private String status;
	private Date yhyxq;
	private Date createTime;
	private Organization organization;
	public static String PROP_CREATETIME = "createTime";
	// constructors
			public BaseKetaUser () {
				initialize();
			}
			protected void initialize () {}
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPainPass() {
		if(painPass==null){
			painPass="123456";
		}
		return painPass;
	}
	public void setPainPass(String painPass) {
		this.painPass = painPass;
	}
	public Date getYhyxq() {
		return yhyxq;
	}
	public void setYhyxq(Date yhyxq) {
		this.yhyxq = yhyxq;
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
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getStatus() {
		if(status==null){
			status="enabled";
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
