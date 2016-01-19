package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;

public  abstract class BaseRepertory implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private Long id;
	
	private String name;
	private String remarks;
	private KetaUser ketaUser;
	private Date addTime;
	public static String PROP_CREATETIME = "addTime";

	// constructors
			public BaseRepertory () {
				initialize();
			}
			protected void initialize () {}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getRemarks() {
				return remarks;
			}
			public void setRemarks(String remarks) {
				this.remarks = remarks;
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

}
