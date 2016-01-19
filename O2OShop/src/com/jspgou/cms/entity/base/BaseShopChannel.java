package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_channel table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_channel"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopChannel  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ShopChannel";
	public static String PROP_TYPE = "type";
	public static String PROP_CHANNEL_CONTENT = "channelContent";
	public static String PROP_PARAM1 = "param1";
	public static String PROP_RGT = "rgt";
	public static String PROP_WEBSITE = "website";
	public static String PROP_TPL_CHANNEL = "tplChannel";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_TPL_CONTENT = "tplContent";
	public static String PROP_OUTER_URL = "outerUrl";
	public static String PROP_BLANK = "blank";
	public static String PROP_PARAM3 = "param3";
	public static String PROP_LFT = "lft";
	public static String PROP_PARENT = "parent";
	public static String PROP_PATH = "path";
	public static String PROP_DISPLAY = "display";
	public static String PROP_NAME = "name";
	public static String PROP_PARAM2 = "param2";
	public static String PROP_ID = "id";


	// constructors
	public BaseShopChannel () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopChannel (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopChannel (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.Integer lft,
		java.lang.Integer rgt,
		java.lang.Integer type,
		java.lang.String name,
		java.lang.Integer priority) {

		this.setId(id);
		this.setWebsite(website);
		this.setLft(lft);
		this.setRgt(rgt);
		this.setType(type);
		this.setName(name);
		this.setPriority(priority);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer lft;
	private java.lang.Integer rgt;
	private java.lang.Integer type;
	private java.lang.String name;
	private java.lang.String path;
	private java.lang.String outerUrl;
	private java.lang.String tplChannel;
	private java.lang.String tplContent;
	private java.lang.Integer priority;
	private java.lang.Boolean blank;
	private java.lang.Boolean display;
	private java.lang.String param1;
	private java.lang.String param2;
	private java.lang.String param3;

	// one to one
	private com.jspgou.cms.entity.ShopChannelContent channelContent;

	// many to one
	private com.jspgou.cms.entity.ShopChannel parent;
	private com.jspgou.core.entity.Website website;

	// collections
	private java.util.Set<com.jspgou.cms.entity.ShopChannel> child;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="channel_id"
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
	 * Return the value associated with the column: type
	 */
	public java.lang.Integer getType () {
		return type;
	}

	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType (java.lang.Integer type) {
		this.type = type;
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
	 * Return the value associated with the column: outer_url
	 */
	public java.lang.String getOuterUrl () {
		return outerUrl;
	}

	/**
	 * Set the value related to the column: outer_url
	 * @param outerUrl the outer_url value
	 */
	public void setOuterUrl (java.lang.String outerUrl) {
		this.outerUrl = outerUrl;
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
	 * Return the value associated with the column: is_blank
	 */
	public java.lang.Boolean getBlank () {
		return blank;
	}

	/**
	 * Set the value related to the column: is_blank
	 * @param blank the is_blank value
	 */
	public void setBlank (java.lang.Boolean blank) {
		this.blank = blank;
	}


	/**
	 * Return the value associated with the column: is_display
	 */
	public java.lang.Boolean getDisplay () {
		return display;
	}

	/**
	 * Set the value related to the column: is_display
	 * @param display the is_display value
	 */
	public void setDisplay (java.lang.Boolean display) {
		this.display = display;
	}


	/**
	 * Return the value associated with the column: param1
	 */
	public java.lang.String getParam1 () {
		return param1;
	}

	/**
	 * Set the value related to the column: param1
	 * @param param1 the param1 value
	 */
	public void setParam1 (java.lang.String param1) {
		this.param1 = param1;
	}


	/**
	 * Return the value associated with the column: param2
	 */
	public java.lang.String getParam2 () {
		return param2;
	}

	/**
	 * Set the value related to the column: param2
	 * @param param2 the param2 value
	 */
	public void setParam2 (java.lang.String param2) {
		this.param2 = param2;
	}


	/**
	 * Return the value associated with the column: param3
	 */
	public java.lang.String getParam3 () {
		return param3;
	}

	/**
	 * Set the value related to the column: param3
	 * @param param3 the param3 value
	 */
	public void setParam3 (java.lang.String param3) {
		this.param3 = param3;
	}


	/**
	 * Return the value associated with the column: channelContent
	 */
	public com.jspgou.cms.entity.ShopChannelContent getChannelContent () {
		return channelContent;
	}

	/**
	 * Set the value related to the column: channelContent
	 * @param channelContent the channelContent value
	 */
	public void setChannelContent (com.jspgou.cms.entity.ShopChannelContent channelContent) {
		this.channelContent = channelContent;
	}


	/**
	 * Return the value associated with the column: parent_id
	 */
	public com.jspgou.cms.entity.ShopChannel getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: parent_id
	 * @param parent the parent_id value
	 */
	public void setParent (com.jspgou.cms.entity.ShopChannel parent) {
		this.parent = parent;
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
	public java.util.Set<com.jspgou.cms.entity.ShopChannel> getChild () {
		return child;
	}

	/**
	 * Set the value related to the column: child
	 * @param child the child value
	 */
	public void setChild (java.util.Set<com.jspgou.cms.entity.ShopChannel> child) {
		this.child = child;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopChannel)) return false;
		else {
			com.jspgou.cms.entity.ShopChannel shopChannel = (com.jspgou.cms.entity.ShopChannel) obj;
			if (null == this.getId() || null == shopChannel.getId()) return false;
			else return (this.getId().equals(shopChannel.getId()));
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