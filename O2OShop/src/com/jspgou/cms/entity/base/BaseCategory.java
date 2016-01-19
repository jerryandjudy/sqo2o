package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_category table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_category"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseCategory  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Category";
	public static String PROP_RGT = "rgt";
	public static String PROP_PARENT = "parent";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_WEBSITE = "website";
	public static String PROP_TPL_CHANNEL = "tplChannel";
	public static String PROP_TYPE = "type";
	public static String PROP_TITLE = "title";
	public static String PROP_PATH = "path";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_LFT = "lft";
	public static String PROP_IMAGE_PATH = "imagePath";
	public static String PROP_KEYWORDS = "keywords";
	public static String PROP_TPL_CONTENT = "tplContent";
	
	public static String PROP_PTFC = "ptFc";
	public static String PROP_DLSFC = "dlsFC";


	// constructors
	public BaseCategory () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCategory (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCategory (
		java.lang.Long id,
		com.jspgou.cms.entity.ProductType type,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.String path,
		java.lang.Integer lft,
		java.lang.Integer rgt,
		
		java.lang.Float ptFc,
		java.lang.Float dlsFc,
		
		
		java.lang.Integer priority) {

		this.setId(id);
		this.setType(type);
		this.setWebsite(website);
		this.setName(name);
		this.setPath(path);
		this.setLft(lft);
		this.setRgt(rgt);
		
		this.setPtFc(ptFc);
		this.setDlsFc(dlsFc);
		
		this.setPriority(priority);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String path;
	private java.lang.String tplChannel;
	private java.lang.String tplContent;
	private java.lang.Integer lft;
	private java.lang.Integer rgt;
	private java.lang.Integer priority;
	private java.lang.String title;
	private java.lang.String imagePath;
	private java.lang.String icon;
	private java.lang.String keywords;
	private java.lang.String description;
	private java.lang.Boolean colorsize;
	private java.lang.Boolean isDisplay;
	private java.lang.Boolean isJp;
	
	private java.lang.Float ptFc;
	private java.lang.Float dlsFc;
	private java.lang.Double ordeRCqje;
	

	// many to one
	private com.jspgou.cms.entity.Category parent;
	private com.jspgou.cms.entity.ProductType type;
	private com.jspgou.core.entity.Website website;

	// collections
	private java.util.Set<com.jspgou.cms.entity.Category> child;
	private java.util.Set<com.jspgou.cms.entity.Brand> brands;
	private java.util.Set<com.jspgou.cms.entity.StandardType> standardType;
	
	private java.util.Map<java.lang.String, java.lang.String> attr;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="category_id"
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
	 * Return the value associated with the column: path
	 */
	public java.lang.String getPath () {
		return path;
	}

	/**
	 * Set the value related to the column: path
	 * @param path the path value
	 */
	public void setPath (java.lang.String path) {
		this.path = path;
	}


	/**
	 * Return the value associated with the column: tpl_channel
	 */
	public java.lang.String getTplChannel () {
		return tplChannel;
	}

	/**
	 * Set the value related to the column: tpl_channel
	 * @param tplChannel the tpl_channel value
	 */
	public void setTplChannel (java.lang.String tplChannel) {
		this.tplChannel = tplChannel;
	}


	/**
	 * Return the value associated with the column: tpl_content
	 */
	public java.lang.String getTplContent () {
		return tplContent;
	}

	/**
	 * Set the value related to the column: tpl_content
	 * @param tplContent the tpl_content value
	 */
	public void setTplContent (java.lang.String tplContent) {
		this.tplContent = tplContent;
	}


	/**
	 * Return the value associated with the column: lft
	 */
	public java.lang.Integer getLft () {
		return lft;
	}

	/**
	 * Set the value related to the column: lft
	 * @param lft the lft value
	 */
	public void setLft (java.lang.Integer lft) {
		this.lft = lft;
	}


	/**
	 * Return the value associated with the column: rgt
	 */
	public java.lang.Integer getRgt () {
		return rgt;
	}

	/**
	 * Set the value related to the column: rgt
	 * @param rgt the rgt value
	 */
	public void setRgt (java.lang.Integer rgt) {
		this.rgt = rgt;
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
	

	public java.lang.Boolean getColorsize() {
		if(colorsize==null){
			colorsize=false;
		}
		return colorsize;
	}

	public void setColorsize(java.lang.Boolean colorsize) {
		this.colorsize = colorsize;
	}


	/**
	 * Return the value associated with the column: title
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: title
	 * @param title the title value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
	}


	/**
	 * Return the value associated with the column: image_path
	 */
	public java.lang.String getImagePath () {
		return imagePath;
	}

	/**
	 * Set the value related to the column: image_path
	 * @param imagePath the image_path value
	 */
	public void setImagePath (java.lang.String imagePath) {
		this.imagePath = imagePath;
	}


	/**
	 * Return the value associated with the column: keywords
	 */
	public java.lang.String getKeywords () {
		return keywords;
	}

	/**
	 * Set the value related to the column: keywords
	 * @param keywords the keywords value
	 */
	public void setKeywords (java.lang.String keywords) {
		this.keywords = keywords;
	}


	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}


	/**
	 * Return the value associated with the column: parent_id
	 */
	public com.jspgou.cms.entity.Category getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: parent_id
	 * @param parent the parent_id value
	 */
	public void setParent (com.jspgou.cms.entity.Category parent) {
		this.parent = parent;
	}


	/**
	 * Return the value associated with the column: ptype_id
	 */
	public com.jspgou.cms.entity.ProductType getType () {
		return type;
	}

	/**
	 * Set the value related to the column: ptype_id
	 * @param type the ptype_id value
	 */
	public void setType (com.jspgou.cms.entity.ProductType type) {
		this.type = type;
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
	 * Return the value associated with the column: child
	 */
	public java.util.Set<com.jspgou.cms.entity.Category> getChild () {
		return child;
	}

	/**
	 * Set the value related to the column: child
	 * @param child the child value
	 */
	public void setChild (java.util.Set<com.jspgou.cms.entity.Category> child) {
		this.child = child;
	}


	/**
	 * Return the value associated with the column: brands
	 */
	public java.util.Set<com.jspgou.cms.entity.Brand> getBrands () {
		return brands;
	}

	/**
	 * Set the value related to the column: brands
	 * @param brands the brands value
	 */
	public void setBrands (java.util.Set<com.jspgou.cms.entity.Brand> brands) {
		this.brands = brands;
	}

	public java.util.Set<com.jspgou.cms.entity.StandardType> getStandardType() {
		return standardType;
	}

	public void setStandardType(
			java.util.Set<com.jspgou.cms.entity.StandardType> standardType) {
		this.standardType = standardType;
	}
	
	/**
	 * Return the value associated with the column: attr
	 */
	public java.util.Map<java.lang.String, java.lang.String> getAttr () {
		return attr;
	}

	/**
	 * Set the value related to the column: attr
	 * @param attr the attr value
	 */
	public void setAttr (java.util.Map<java.lang.String, java.lang.String> attr) {
		this.attr = attr;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Category)) return false;
		else {
			com.jspgou.cms.entity.Category category = (com.jspgou.cms.entity.Category) obj;
			if (null == this.getId() || null == category.getId()) return false;
			else return (this.getId().equals(category.getId()));
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

	public java.lang.Float getPtFc() {
		return ptFc;
	}

	public void setPtFc(java.lang.Float ptFc) {
		this.ptFc = ptFc;
	}

	public java.lang.Float getDlsFc() {
		return dlsFc;
	}

	public void setDlsFc(java.lang.Float dlsFc) {
		this.dlsFc = dlsFc;
	}

	public java.lang.Boolean getIsDisplay() {
		if(isDisplay==null){
			isDisplay=false;
		}
		return isDisplay;
	}

	public void setIsDisplay(java.lang.Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	public java.lang.Boolean getIsJp() {
		if(isJp==null){
			isJp=false;
		}
		return isJp;
	}

	public void setIsJp(java.lang.Boolean isJp) {
		this.isJp = isJp;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public java.lang.Double getOrdeRCqje() {
		return ordeRCqje;
	}

	public void setOrdeRCqje(java.lang.Double ordeRCqje) {
		this.ordeRCqje = ordeRCqje;
	}


}