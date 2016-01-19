package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_admin table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_admin"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopAdmin  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ShopAdmin";
	public static String PROP_LASTNAME = "lastname";
	public static String PROP_WEBSITE = "website";
	public static String PROP_FIRSTNAME = "firstname";
	
	public static String PROP_ADMIN = "admin";
	public static String PROP_ID = "id";


	// constructors
	public BaseShopAdmin () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopAdmin (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopAdmin (
		java.lang.Long id,
		com.jspgou.core.entity.Website website) {

		this.setId(id);
		this.setWebsite(website);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String firstname;
	private java.lang.String lastname;
	

	// one to one
	private com.jspgou.core.entity.Admin admin;

	// many to one
	private com.jspgou.core.entity.Website website;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="admin_id"
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
	 * Return the value associated with the column: firstname
	 */
	public java.lang.String getFirstname () {
		return firstname;
	}

	/**
	 * Set the value related to the column: firstname
	 * @param firstname the firstname value
	 */
	public void setFirstname (java.lang.String firstname) {
		this.firstname = firstname;
	}


	/**
	 * Return the value associated with the column: lastname
	 */
	public java.lang.String getLastname () {
		return lastname;
	}

	/**
	 * Set the value related to the column: lastname
	 * @param lastname the lastname value
	 */
	public void setLastname (java.lang.String lastname) {
		this.lastname = lastname;
	}


	/**
	 * Return the value associated with the column: admin
	 */
	public com.jspgou.core.entity.Admin getAdmin () {
		return admin;
	}

	/**
	 * Set the value related to the column: admin
	 * @param admin the admin value
	 */
	public void setAdmin (com.jspgou.core.entity.Admin admin) {
		this.admin = admin;
	}


	/**
	 * Return the value associated with the column: website_id
	 */
	public com.jspgou.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: website_id
	 * @param website the website_id value
	 */
	public void setWebsite (com.jspgou.core.entity.Website website) {
		this.website = website;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopAdmin)) return false;
		else {
			com.jspgou.cms.entity.ShopAdmin shopAdmin = (com.jspgou.cms.entity.ShopAdmin) obj;
			if (null == this.getId() || null == shopAdmin.getId()) return false;
			else return (this.getId().equals(shopAdmin.getId()));
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