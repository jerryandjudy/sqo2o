package com.jspgou.cms.entity.base;

import java.io.Serializable;

import com.jspgou.cms.entity.SqService;


/**
 * This is an object that contains data related to the jc_shop_diss table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_discuss"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopMessage  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// constructors
	public BaseShopMessage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopMessage (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String content;
	private java.lang.String contactName;
	private java.lang.String contactTel;
	private java.util.Date time;

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getContactName() {
		return contactName;
	}

	public void setContactName(java.lang.String contactName) {
		this.contactName = contactName;
	}

	public java.lang.String getContactTel() {
		return contactTel;
	}

	public void setContactTel(java.lang.String contactTel) {
		this.contactTel = contactTel;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}





}