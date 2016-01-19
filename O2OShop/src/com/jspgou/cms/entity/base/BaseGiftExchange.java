package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_gift_exchange table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_gift_exchange"
 */

public abstract class BaseGiftExchange  implements Serializable {

	public static String REF = "GiftExchange";
	public static String PROP_STATUS = "status";
	public static String PROP_MEMBER = "member";
	public static String PROP_AMOUNT = "amount";
	public static String PROP_DETAILADDRESS = "detailaddress";
	public static String PROP_GIFT = "gift";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_WAYBILL = "waybill";
	public static String PROP_TOTAL_SCORE = "totalScore";
	public static String PROP_ID = "id";
	public static String PROP_SCORE = "score";


	// constructors
	public BaseGiftExchange () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseGiftExchange (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseGiftExchange (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.Gift gift,
		java.util.Date createTime,
		java.lang.Integer status) {

		this.setId(id);
		this.setMember(member);
		this.setGift(gift);
		this.setCreateTime(createTime);
		this.setStatus(status);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Integer score;
	private java.lang.Integer amount;
	private java.util.Date createTime;
	private java.lang.Integer totalScore;
	private java.lang.String detailaddress;
	private java.lang.Integer status;
	private java.lang.String waybill;

	// many to one
	private com.jspgou.cms.entity.ShopMember member;
	private com.jspgou.cms.entity.Gift gift;



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
	 * Return the value associated with the column: score
	 */
	public java.lang.Integer getScore () {
		return score;
	}

	/**
	 * Set the value related to the column: score
	 * @param score the score value
	 */
	public void setScore (java.lang.Integer score) {
		this.score = score;
	}


	/**
	 * Return the value associated with the column: amount
	 */
	public java.lang.Integer getAmount () {
		return amount;
	}

	/**
	 * Set the value related to the column: amount
	 * @param amount the amount value
	 */
	public void setAmount (java.lang.Integer amount) {
		this.amount = amount;
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
	 * Return the value associated with the column: total_score
	 */
	public java.lang.Integer getTotalScore () {
		return totalScore;
	}

	/**
	 * Set the value related to the column: total_score
	 * @param totalScore the total_score value
	 */
	public void setTotalScore (java.lang.Integer totalScore) {
		this.totalScore = totalScore;
	}


	/**
	 * Return the value associated with the column: detailaddress
	 */
	public java.lang.String getDetailaddress () {
		return detailaddress;
	}

	/**
	 * Set the value related to the column: detailaddress
	 * @param detailaddress the detailaddress value
	 */
	public void setDetailaddress (java.lang.String detailaddress) {
		this.detailaddress = detailaddress;
	}


	/**
	 * Return the value associated with the column: status
	 */
	public java.lang.Integer getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (java.lang.Integer status) {
		this.status = status;
	}


	/**
	 * Return the value associated with the column: waybill
	 */
	public java.lang.String getWaybill () {
		return waybill;
	}

	/**
	 * Set the value related to the column: waybill
	 * @param waybill the waybill value
	 */
	public void setWaybill (java.lang.String waybill) {
		this.waybill = waybill;
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
	 * Return the value associated with the column: gift_id
	 */
	public com.jspgou.cms.entity.Gift getGift () {
		return gift;
	}

	/**
	 * Set the value related to the column: gift_id
	 * @param gift the gift_id value
	 */
	public void setGift (com.jspgou.cms.entity.Gift gift) {
		this.gift = gift;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.GiftExchange)) return false;
		else {
			com.jspgou.cms.entity.GiftExchange giftExchange = (com.jspgou.cms.entity.GiftExchange) obj;
			if (null == this.getId() || null == giftExchange.getId()) return false;
			else return (this.getId().equals(giftExchange.getId()));
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