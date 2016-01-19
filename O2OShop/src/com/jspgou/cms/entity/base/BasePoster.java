package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_poster table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_poster"
 * This class should preserve.
 * @preserve
 */

public abstract class BasePoster  implements Serializable {

	public static String REF = "Poster";
	public static String PROP_TIME = "time";
	public static String PROP_PIC_URL = "picUrl";
	public static String PROP_INTER_URL = "interUrl";
	public static String PROP_ID = "id";


	// constructors
	public BasePoster () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePoster (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePoster (
		java.lang.Integer id,
		java.lang.String picUrl) {

		this.setId(id);
		this.setPicUrl(picUrl);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String picUrl;
	private java.util.Date time;
	private java.lang.String interUrl;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: picUrl
	 */
	public java.lang.String getPicUrl () {
		return picUrl;
	}

	/**
	 * Set the value related to the column: picUrl
	 * @param picUrl the picUrl value
	 */
	public void setPicUrl (java.lang.String picUrl) {
		this.picUrl = picUrl;
	}


	/**
	 * Return the value associated with the column: time
	 */
	public java.util.Date getTime () {
		return time;
	}

	/**
	 * Set the value related to the column: time
	 * @param time the time value
	 */
	public void setTime (java.util.Date time) {
		this.time = time;
	}


	/**
	 * Return the value associated with the column: interUrl
	 */
	public java.lang.String getInterUrl () {
		return interUrl;
	}

	/**
	 * Set the value related to the column: interUrl
	 * @param interUrl the interUrl value
	 */
	public void setInterUrl (java.lang.String interUrl) {
		this.interUrl = interUrl;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Poster)) return false;
		else {
			com.jspgou.cms.entity.Poster poster = (com.jspgou.cms.entity.Poster) obj;
			if (null == this.getId() || null == poster.getId()) return false;
			else return (this.getId().equals(poster.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}