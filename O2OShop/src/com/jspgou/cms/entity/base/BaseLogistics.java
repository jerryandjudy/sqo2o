package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_logistics table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_logistics"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseLogistics  implements Serializable {

	public static String REF = "Logistics";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_WEB_URL = "webUrl";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_LOGO_PATH = "logoPath";


	// constructors
	public BaseLogistics () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseLogistics (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseLogistics (
		java.lang.Long id,
		java.lang.String name,
		java.lang.Integer priority) {

		this.setId(id);
		this.setName(name);
		this.setPriority(priority);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String webUrl;
	private java.lang.String logoPath;
	private java.lang.Integer priority;

	// collections
	private java.util.Set<com.jspgou.cms.entity.LogisticsText> logisticsTextSet;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="logistics_id"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}


	/**
	 * Return the value associated with the column: web_url
	 */
	public java.lang.String getWebUrl () {
		return webUrl;
	}

	/**
	 * Set the value related to the column: web_url
	 * @param webUrl the web_url value
	 */
	public void setWebUrl (java.lang.String webUrl) {
		this.webUrl = webUrl;
	}


	/**
	 * Return the value associated with the column: logo_path
	 */
	public java.lang.String getLogoPath () {
		return logoPath;
	}

	/**
	 * Set the value related to the column: logo_path
	 * @param logoPath the logo_path value
	 */
	public void setLogoPath (java.lang.String logoPath) {
		this.logoPath = logoPath;
	}


	/**
	 * Return the value associated with the column: priority
	 */
	public java.lang.Integer getPriority () {
		return priority;
	}

	/**
	 * Set the value related to the column: priority
	 * @param priority the priority value
	 */
	public void setPriority (java.lang.Integer priority) {
		this.priority = priority;
	}


	/**
	 * Return the value associated with the column: logisticsTextSet
	 */
	public java.util.Set<com.jspgou.cms.entity.LogisticsText> getLogisticsTextSet () {
		return logisticsTextSet;
	}

	/**
	 * Set the value related to the column: logisticsTextSet
	 * @param logisticsTextSet the logisticsTextSet value
	 */
	public void setLogisticsTextSet (java.util.Set<com.jspgou.cms.entity.LogisticsText> logisticsTextSet) {
		this.logisticsTextSet = logisticsTextSet;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Logistics)) return false;
		else {
			com.jspgou.cms.entity.Logistics logistics = (com.jspgou.cms.entity.Logistics) obj;
			if (null == this.getId() || null == logistics.getId()) return false;
			else return (this.getId().equals(logistics.getId()));
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