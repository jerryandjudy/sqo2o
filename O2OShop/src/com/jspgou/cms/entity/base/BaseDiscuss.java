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

public abstract class BaseDiscuss  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Discuss";
	public static String PROP_MEMBER = "member";
	public static String PROP_TIME = "time";
	public static String PROP_PRODUCT = "product";
	public static String PROP_ID = "id";
	public static String PROP_CONTENT = "content";


	// constructors
	public BaseDiscuss () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDiscuss (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseDiscuss (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.ProductSite productSite,
		java.lang.String content) {

		this.setId(id);
		this.setMember(member);
		this.setProductSite(productSite);
		this.setContent(content);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String content;
	private java.util.Date time;

	// many to one
	private com.jspgou.cms.entity.ShopMember member;
	private com.jspgou.cms.entity.ProductSite productSite;
	private SqService sqService;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
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
	public java.lang.String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content
	 * @param content the content value
	 */
	public void setContent (java.lang.String content) {
		this.content = content;
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
	 * Return the value associated with the column: member_id
	 */
	public com.jspgou.cms.entity.ShopMember getMember () {
		return member;
	}

	/**
	 * Set the value related to the column: member_id
	 * @param member the member_id value
	 */
	public void setMember (com.jspgou.cms.entity.ShopMember member) {
		this.member = member;
	}





	public com.jspgou.cms.entity.ProductSite getProductSite() {
		return productSite;
	}

	public void setProductSite(com.jspgou.cms.entity.ProductSite productSite) {
		this.productSite = productSite;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Discuss)) return false;
		else {
			com.jspgou.cms.entity.Discuss discuss = (com.jspgou.cms.entity.Discuss) obj;
			if (null == this.getId() || null == discuss.getId()) return false;
			else return (this.getId().equals(discuss.getId()));
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

	public SqService getSqService() {
		return sqService;
	}

	public void setSqService(SqService sqService) {
		this.sqService = sqService;
	}


}