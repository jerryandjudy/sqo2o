package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_member_coupon table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_member_coupon"
 */

public abstract class BaseMemberCoupon  implements Serializable {

	public static String REF = "MemberCoupon";
	public static String PROP_MEMBER = "member";
	public static String PROP_COUPON = "coupon";
	public static String PROP_ID = "id";
	public static String PROP_ISUSE = "isuse";


	// constructors
	public BaseMemberCoupon () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMemberCoupon (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Boolean isuse;

	// many to one
	private com.jspgou.cms.entity.ShopMember member;
	private com.jspgou.cms.entity.Coupon coupon;



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
	 * Return the value associated with the column: is_use
	 */
	public java.lang.Boolean getIsuse () {
		return isuse;
	}

	/**
	 * Set the value related to the column: is_use
	 * @param isuse the is_use value
	 */
	public void setIsuse (java.lang.Boolean isuse) {
		this.isuse = isuse;
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


	/**
	 * Return the value associated with the column: coupon_id
	 */
	public com.jspgou.cms.entity.Coupon getCoupon () {
		return coupon;
	}

	/**
	 * Set the value related to the column: coupon_id
	 * @param coupon the coupon_id value
	 */
	public void setCoupon (com.jspgou.cms.entity.Coupon coupon) {
		this.coupon = coupon;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.MemberCoupon)) return false;
		else {
			com.jspgou.cms.entity.MemberCoupon memberCoupon = (com.jspgou.cms.entity.MemberCoupon) obj;
			if (null == this.getId() || null == memberCoupon.getId()) return false;
			else return (this.getId().equals(memberCoupon.getId()));
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