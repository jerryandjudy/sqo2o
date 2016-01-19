/**
 * 吉林省艾利特信息技术有限公司
 * 进销存管理系统
 * @date 2015年6月3日
 * @author liuwang
 * @version 1.0
 */
package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.KetaUser;

public class BaseSqService implements Serializable {
	private java.lang.Long id;
	private String price;
	private Category category;
	private String name;
	private String descriptions;
	private KetaUser ketaUser;
	private Date addTime;
	private String servicePersonName;
	private boolean groom;
	private boolean status;// 是否上架
	private String contactTel;
	private Address province;// 省
	private Address city;// 市
	private Address country;// 区/县
	private Address street;// 详细地址
	private com.jspgou.core.entity.Website website;
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public KetaUser getKetaUser() {
		return ketaUser;
	}
	public void setKetaUser(KetaUser ketaUser) {
		this.ketaUser = ketaUser;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getServicePersonName() {
		return servicePersonName;
	}
	public void setServicePersonName(String servicePersonName) {
		this.servicePersonName = servicePersonName;
	}
	public boolean isGroom() {
		return groom;
	}
	public void setGroom(boolean groom) {
		this.groom = groom;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
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
	public com.jspgou.core.entity.Website getWebsite() {
		return website;
	}
	public void setWebsite(com.jspgou.core.entity.Website website) {
		this.website = website;
	}

}
