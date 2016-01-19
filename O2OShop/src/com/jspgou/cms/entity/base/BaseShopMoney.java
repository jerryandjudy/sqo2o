package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_money table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_money"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopMoney  implements Serializable {

	public static String REF = "ShopMoney";
	public static String PROP_NAME = "name";
	public static String PROP_STATUS = "status";
	public static String PROP_MEMBER = "member";
	public static String PROP_MONEY = "money";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ID = "id";
	public static String PROP_REMARK = "remark";


	// constructors
	public BaseShopMoney () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopMoney (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopMoney (
		java.lang.Long id,
		java.lang.String name,
		java.lang.Double money,
		boolean status,
		java.util.Date createTime) {

		this.setId(id);
		this.setName(name);
		this.setMoney(money);
		this.setStatus(status);
		this.setCreateTime(createTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.Double money;
	private boolean status;
	private java.util.Date createTime;
	private java.lang.String remark;

	// many to one
	private com.jspgou.cms.entity.ShopMember member;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="Id"
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
	 * Return the value associated with the column: money
	 */
	public java.lang.Double getMoney () {
		return money;
	}

	/**
	 * Set the value related to the column: money
	 * @param money the money value
	 */
	public void setMoney (java.lang.Double money) {
		this.money = money;
	}


	/**
	 * Return the value associated with the column: status
	 */
	public boolean isStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (boolean status) {
		this.status = status;
	}


	/**
	 * Return the value associated with the column: create_time
	 */
	public java.util.Date getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: create_time
	 * @param createTime the create_time value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}


	/**
	 * Return the value associated with the column: remark
	 */
	public java.lang.String getRemark () {
		return remark;
	}

	/**
	 * Set the value related to the column: remark
	 * @param remark the remark value
	 */
	public void setRemark (java.lang.String remark) {
		this.remark = remark;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopMoney)) return false;
		else {
			com.jspgou.cms.entity.ShopMoney shopMoney = (com.jspgou.cms.entity.ShopMoney) obj;
			if (null == this.getId() || null == shopMoney.getId()) return false;
			else return (this.getId().equals(shopMoney.getId()));
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