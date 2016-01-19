package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_brand table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_brand"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseBrand  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Brand";
	public static String PROP_NAME = "name";
	public static String PROP_ALIAS = "alias";
	public static String PROP_WEBSITE = "website";
	public static String PROP_ID = "id";
	public static String PROP_WEB_URL = "webUrl";
	public static String PROP_SIFT = "sift";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_LOGO_PATH = "logoPath";


	// constructors
	public BaseBrand () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBrand (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBrand (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.Integer priority) {

		this.setId(id);
		this.setWebsite(website);
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
	private java.lang.String alias;
	private java.lang.String webUrl;
	private java.lang.String logoPath;
	private java.lang.Integer priority;
	private java.lang.Boolean sift;
	private java.lang.Boolean disabled;
	private java.lang.String firstCharacter;

	// many to one
	private com.jspgou.core.entity.Website website;

	// collections
	private java.util.Set<com.jspgou.cms.entity.Category> categorys;
	private java.util.Set<com.jspgou.cms.entity.BrandText> brandTextSet;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="brand_id"
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



	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	public java.lang.String getFirstCharacter() {
		return firstCharacter;
	}

	public void setFirstCharacter(java.lang.String firstCharacter) {
		this.firstCharacter = firstCharacter;
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
	 * Return the value associated with the column: alias
	 */
	public java.lang.String getAlias () {
		return alias;
	}

	/**
	 * Set the value related to the column: alias
	 * @param alias the alias value
	 */
	public void setAlias (java.lang.String alias) {
		this.alias = alias;
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
	 * Return the value associated with the column: is_sift
	 */
	public java.lang.Boolean getSift () {
		return sift;
	}

	/**
	 * Set the value related to the column: is_sift
	 * @param sift the is_sift value
	 */
	public void setSift (java.lang.Boolean sift) {
		this.sift = sift;
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

	/**
	 * Return the value associated with the column: categorys
	 */
	public java.util.Set<com.jspgou.cms.entity.Category> getCategorys () {
		return categorys;
	}

	/**
	 * Set the value related to the column: categorys
	 * @param categorys the categorys value
	 */
	public void setCategorys (java.util.Set<com.jspgou.cms.entity.Category> categorys) {
		this.categorys = categorys;
	}


	/**
	 * Return the value associated with the column: brandTextSet
	 */
	public java.util.Set<com.jspgou.cms.entity.BrandText> getBrandTextSet () {
		return brandTextSet;
	}

	/**
	 * Set the value related to the column: brandTextSet
	 * @param brandTextSet the brandTextSet value
	 */
	public void setBrandTextSet (java.util.Set<com.jspgou.cms.entity.BrandText> brandTextSet) {
		this.brandTextSet = brandTextSet;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Brand)) return false;
		else {
			com.jspgou.cms.entity.Brand brand = (com.jspgou.cms.entity.Brand) obj;
			if (null == this.getId() || null == brand.getId()) return false;
			else return (this.getId().equals(brand.getId()));
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