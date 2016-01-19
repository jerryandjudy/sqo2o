package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;


/**
 * This is an object that contains data related to the jc_address table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_core_Message"
 * This class should preserve.
 * @preserve
 */
public abstract class BaseMessage  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Message";
	public static String PROP_ID = "id";


	// constructors
	public BaseMessage () {
		initialize();
	}


	protected void initialize () {}



	private Long id ;
	private String tel;
	private String yzm;
	private Date yxq;
	private Integer types;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	public Date getYxq() {
		return yxq;
	}
	public void setYxq(Date yxq) {
		this.yxq = yxq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getTypes() {
		return types;
	}


	public void setTypes(Integer types) {
		this.types = types;
	}



}