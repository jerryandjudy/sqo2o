/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年8月4日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.entity.base;

public abstract class BaseActivitysGys {
	// primary key
		private java.lang.Long id;
	// many to one
		private com.jspgou.cms.entity.Activitys activitys;
		private com.jspgou.cms.entity.Gys gys;
		public java.lang.Long getId() {
			return id;
		}
		public void setId(java.lang.Long id) {
			this.id = id;
		}
		public com.jspgou.cms.entity.Activitys getActivitys() {
			return activitys;
		}
		public void setActivitys(com.jspgou.cms.entity.Activitys activitys) {
			this.activitys = activitys;
		}
		public com.jspgou.cms.entity.Gys getGys() {
			return gys;
		}
		public void setGys(com.jspgou.cms.entity.Gys gys) {
			this.gys = gys;
		}

}
