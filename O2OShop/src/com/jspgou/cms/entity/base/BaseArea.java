/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月10日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.entity.base;

import java.util.Date;

import com.jspgou.cms.entity.Address;
import com.jspgou.core.entity.Website;

public class BaseArea {
	// primary key
		private java.lang.Long id;
		private String imei;
		private Address street;
		private Website website;
		private Date lastTime;
		public java.lang.Long getId() {
			return id;
		}
		public void setId(java.lang.Long id) {
			this.id = id;
		}
		public String getImei() {
			return imei;
		}
		public void setImei(String imei) {
			this.imei = imei;
		}
		public Address getStreet() {
			return street;
		}
		public void setStreet(Address street) {
			this.street = street;
		}
		public Website getWebsite() {
			return website;
		}
		public void setWebsite(Website website) {
			this.website = website;
		}
		public Date getLastTime() {
			return lastTime;
		}
		public void setLastTime(Date lastTime) {
			this.lastTime = lastTime;
		}
		
}
