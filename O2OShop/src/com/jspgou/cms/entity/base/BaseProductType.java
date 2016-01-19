package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_ptype table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_ptype"
 * This class should preserve.
 * @preserve
 */

@SuppressWarnings("serial")
public abstract class BaseProductType  implements Serializable {

	public static String REF = "ProductType";
	public static String PROP_LIST_IMG_HEIGHT = "listImgHeight";
	public static String PROP_WEBSITE = "website";
	public static String PROP_DETAIL_IMG_WIDTH = "detailImgWidth";
	public static String PROP_LIST_IMG_WIDTH = "listImgWidth";
	public static String PROP_MIN_IMG_HEIGHT = "minImgHeight";
	public static String PROP_PATH = "path";
	public static String PROP_DETAIL_IMG_HEIGHT = "detailImgHeight";
	public static String PROP_NAME = "name";
	public static String PROP_ALIAS = "alias";
	public static String PROP_PROPS = "props";
	public static String PROP_ID = "id";
	public static String PROP_MIN_IMG_WIDTH = "minImgWidth";
	public static String PROP_CONTENT_PREFIX = "contentPrefix";
	public static String PROP_REF_BRAND = "refBrand";
	public static String PROP_CHANNEL_PREFIX = "channelPrefix";


	// constructors
	public BaseProductType () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProductType (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductType (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.String channelPrefix,
		java.lang.String contentPrefix,
		java.lang.Boolean refBrand,
		java.lang.Integer detailImgWidth,
		java.lang.Integer detailImgHeight,
		java.lang.Integer listImgWidth,
		java.lang.Integer listImgHeight,
		java.lang.Integer minImgWidth,
		java.lang.Integer minImgHeight) {

		this.setId(id);
		this.setWebsite(website);
		this.setName(name);
		this.setChannelPrefix(channelPrefix);
		this.setContentPrefix(contentPrefix);
		this.setDetailImgWidth(detailImgWidth);
		this.setDetailImgHeight(detailImgHeight);
		this.setListImgWidth(listImgWidth);
		this.setListImgHeight(listImgHeight);
		this.setMinImgWidth(minImgWidth);
		this.setMinImgHeight(minImgHeight);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String alias;
	private java.lang.String channelPrefix;
	private java.lang.String contentPrefix;
	private java.lang.String props;
	private java.lang.Integer detailImgWidth;
	private java.lang.Integer detailImgHeight;
	private java.lang.Integer listImgWidth;
	private java.lang.Integer listImgHeight;
	private java.lang.Integer minImgWidth;
	private java.lang.Integer minImgHeight;

	// many to one
	private com.jspgou.core.entity.Website website;
	// collections
	private java.util.Set<com.jspgou.cms.entity.ProductTypeProperty> properties;
	private java.util.Set<com.jspgou.cms.entity.Exended> exendeds;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="ptype_id"
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
	 * Return the value associated with the column: channel_prefix
	 */
	public java.lang.String getChannelPrefix () {
		return channelPrefix;
	}

	/**
	 * Set the value related to the column: channel_prefix
	 * @param channelPrefix the channel_prefix value
	 */
	public void setChannelPrefix (java.lang.String channelPrefix) {
		this.channelPrefix = channelPrefix;
	}


	/**
	 * Return the value associated with the column: content_prefix
	 */
	public java.lang.String getContentPrefix () {
		return contentPrefix;
	}

	/**
	 * Set the value related to the column: content_prefix
	 * @param contentPrefix the content_prefix value
	 */
	public void setContentPrefix (java.lang.String contentPrefix) {
		this.contentPrefix = contentPrefix;
	}


	/**
	 * Return the value associated with the column: props
	 */
	public java.lang.String getProps () {
		return props;
	}

	/**
	 * Set the value related to the column: props
	 * @param props the props value
	 */
	public void setProps (java.lang.String props) {
		this.props = props;
	}

	/**
	 * Return the value associated with the column: detail_img_width
	 */
	public java.lang.Integer getDetailImgWidth () {
		return detailImgWidth;
	}

	/**
	 * Set the value related to the column: detail_img_width
	 * @param detailImgWidth the detail_img_width value
	 */
	public void setDetailImgWidth (java.lang.Integer detailImgWidth) {
		this.detailImgWidth = detailImgWidth;
	}


	/**
	 * Return the value associated with the column: detail_img_height
	 */
	public java.lang.Integer getDetailImgHeight () {
		return detailImgHeight;
	}

	/**
	 * Set the value related to the column: detail_img_height
	 * @param detailImgHeight the detail_img_height value
	 */
	public void setDetailImgHeight (java.lang.Integer detailImgHeight) {
		this.detailImgHeight = detailImgHeight;
	}


	/**
	 * Return the value associated with the column: list_img_width
	 */
	public java.lang.Integer getListImgWidth () {
		return listImgWidth;
	}

	/**
	 * Set the value related to the column: list_img_width
	 * @param listImgWidth the list_img_width value
	 */
	public void setListImgWidth (java.lang.Integer listImgWidth) {
		this.listImgWidth = listImgWidth;
	}


	/**
	 * Return the value associated with the column: list_img_height
	 */
	public java.lang.Integer getListImgHeight () {
		return listImgHeight;
	}

	/**
	 * Set the value related to the column: list_img_height
	 * @param listImgHeight the list_img_height value
	 */
	public void setListImgHeight (java.lang.Integer listImgHeight) {
		this.listImgHeight = listImgHeight;
	}


	/**
	 * Return the value associated with the column: min_img_width
	 */
	public java.lang.Integer getMinImgWidth () {
		return minImgWidth;
	}

	/**
	 * Set the value related to the column: min_img_width
	 * @param minImgWidth the min_img_width value
	 */
	public void setMinImgWidth (java.lang.Integer minImgWidth) {
		this.minImgWidth = minImgWidth;
	}


	/**
	 * Return the value associated with the column: min_img_height
	 */
	public java.lang.Integer getMinImgHeight () {
		return minImgHeight;
	}

	/**
	 * Set the value related to the column: min_img_height
	 * @param minImgHeight the min_img_height value
	 */
	public void setMinImgHeight (java.lang.Integer minImgHeight) {
		this.minImgHeight = minImgHeight;
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

	public java.util.Set<com.jspgou.cms.entity.ProductTypeProperty> getProperties() {
		return properties;
	}

	public void setProperties(
			java.util.Set<com.jspgou.cms.entity.ProductTypeProperty> properties) {
		this.properties = properties;
	}

	public void setExendeds(java.util.Set<com.jspgou.cms.entity.Exended> exendeds) {
		this.exendeds = exendeds;
	}

	public java.util.Set<com.jspgou.cms.entity.Exended> getExendeds() {
		return exendeds;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ProductType)) return false;
		else {
			com.jspgou.cms.entity.ProductType productType = (com.jspgou.cms.entity.ProductType) obj;
			if (null == this.getId() || null == productType.getId()) return false;
			else return (this.getId().equals(productType.getId()));
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