package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.core.entity.User;

public  abstract class BasePsqy implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private Long id;
	
	private String description;
	private Address province;
	private Address city;
	private Address country;
	private Address street;
	private User user;
	private Bld bld;
	private Gys gys;
	private Date createTime;
	public static String PROP_CREATETIME = "createTime";

	// constructors
			public BasePsqy () {
				initialize();
			}
			protected void initialize () {}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Bld getBld() {
		return bld;
	}
	public void setBld(Bld bld) {
		this.bld = bld;
	}
	public Gys getGys() {
		return gys;
	}
	public void setGys(Gys gys) {
		this.gys = gys;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Address getProvince() {
		return province;
	}
	public void setProvince(Address province) {
		this.province = province;
	}
	public Address getCity() {
		return city;
	}
	public void setCity(Address city) {
		this.city = city;
	}
	public Address getCountry() {
		return country;
	}
	public void setCountry(Address country) {
		this.country = country;
	}
	public Address getStreet() {
		return street;
	}
	public void setStreet(Address street) {
		this.street = street;
	}

}
