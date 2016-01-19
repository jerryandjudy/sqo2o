package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public  abstract class BaseKhgx implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private Long id;
	
	private Website website;
	
	private User user;
	private Gys gys;
	private Bld bld;
	
	private Date createTime;
	
	public static String PROP_CREATETIME = "createTime";

	// constructors
			public BaseKhgx () {
				initialize();
			}
			protected void initialize () {}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
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
			public Gys getGys() {
				return gys;
			}
			public void setGys(Gys gys) {
				this.gys = gys;
			}
			public Bld getBld() {
				return bld;
			}
			public void setBld(Bld bld) {
				this.bld = bld;
			}
			public Date getCreateTime() {
				return createTime;
			}
			public void setCreateTime(Date createTime) {
				this.createTime = createTime;
			}

}
