package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_article table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_article"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopArticle  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ShopArticle";
	public static String PROP_ARTICLE_CONTENT = "articleContent";
	public static String PROP_PUBLISH_TIME = "publishTime";
	public static String PROP_LINK = "link";
	public static String PROP_WEBSITE = "website";
	public static String PROP_PARAM3 = "param3";
	public static String PROP_DISABLED = "disabled";
	public static String PROP_CHANNEL = "channel";
	public static String PROP_TITLE = "title";
	public static String PROP_PARAM2 = "param2";
	public static String PROP_ID = "id";


	// constructors
	public BaseShopArticle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopArticle (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopArticle (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.ShopChannel channel,
		java.lang.String title,
		java.util.Date publishTime,
		java.lang.Boolean disabled) {

		this.setId(id);
		this.setWebsite(website);
		this.setChannel(channel);
		this.setTitle(title);
		this.setPublishTime(publishTime);
		this.setDisabled(disabled);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String title;
	private java.util.Date publishTime;
	private java.lang.Boolean disabled;
	private java.lang.String link;
	private java.lang.String titleImg;
	private java.lang.String param2;
	private java.lang.String param3;

	// one to one
	private com.jspgou.cms.entity.ShopArticleContent articleContent;

	// many to one
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.ShopChannel channel;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="article_id"
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
	 * Return the value associated with the column: publish_time
	 */
	public java.util.Date getPublishTime () {
		return publishTime;
	}

	/**
	 * Set the value related to the column: publish_time
	 * @param publishTime the publish_time value
	 */
	public void setPublishTime (java.util.Date publishTime) {
		this.publishTime = publishTime;
	}


	/**
	 * Return the value associated with the column: is_disabled
	 */
	public java.lang.Boolean getDisabled () {
		return disabled;
	}

	/**
	 * Set the value related to the column: is_disabled
	 * @param disabled the is_disabled value
	 */
	public void setDisabled (java.lang.Boolean disabled) {
		this.disabled = disabled;
	}


	/**
	 * Return the value associated with the column: link
	 */
	public java.lang.String getLink () {
		return link;
	}

	/**
	 * Set the value related to the column: param1
	 * @param param1 the link value
	 */
	public void setLink (java.lang.String link) {
		this.link = link;
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
	 * Return the value associated with the column: articleContent
	 */
	public com.jspgou.cms.entity.ShopArticleContent getArticleContent () {
		return articleContent;
	}

	/**
	 * Set the value related to the column: articleContent
	 * @param articleContent the articleContent value
	 */
	public void setArticleContent (com.jspgou.cms.entity.ShopArticleContent articleContent) {
		this.articleContent = articleContent;
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
	 * Return the value associated with the column: channel_id
	 */
	public com.jspgou.cms.entity.ShopChannel getChannel () {
		return channel;
	}

	/**
	 * Set the value related to the column: channel_id
	 * @param channel the channel_id value
	 */
	public void setChannel (com.jspgou.cms.entity.ShopChannel channel) {
		this.channel = channel;
	}





	public java.lang.String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(java.lang.String titleImg) {
		this.titleImg = titleImg;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopArticle)) return false;
		else {
			com.jspgou.cms.entity.ShopArticle shopArticle = (com.jspgou.cms.entity.ShopArticle) obj;
			if (null == this.getId() || null == shopArticle.getId()) return false;
			else return (this.getId().equals(shopArticle.getId()));
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