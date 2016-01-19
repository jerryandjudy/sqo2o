package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_article_content table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_article_content"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopArticleContent  implements Serializable {

	public static String REF = "ShopArticleContent";
	public static String PROP_CONTENT = "content";
	public static String PROP_ID = "id";
	public static String PROP_ARTICLE = "article";


	// constructors
	public BaseShopArticleContent () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopArticleContent (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private String content;

	// one to one
	private com.jspgou.cms.entity.ShopArticle article;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
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
	 * Return the value associated with the column: content
	 */
	public String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content
	 * @param content the content value
	 */
	public void setContent (String content) {
		this.content = content;
	}


	/**
	 * Return the value associated with the column: article
	 */
	public com.jspgou.cms.entity.ShopArticle getArticle () {
		return article;
	}

	/**
	 * Set the value related to the column: article
	 * @param article the article value
	 */
	public void setArticle (com.jspgou.cms.entity.ShopArticle article) {
		this.article = article;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopArticleContent)) return false;
		else {
			com.jspgou.cms.entity.ShopArticleContent shopArticleContent = (com.jspgou.cms.entity.ShopArticleContent) obj;
			if (null == this.getId() || null == shopArticleContent.getId()) return false;
			else return (this.getId().equals(shopArticleContent.getId()));
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