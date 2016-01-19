package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_score table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_score"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopScore  implements Serializable {

	public static String REF = "ShopScore";
	public static String PROP_NAME = "name";
	public static String PROP_STATUS = "status";
	public static String PROP_MEMBER = "member";
	public static String PROP_ORDER_ITEM = "orderItem";
	public static String PROP_ORDER = "order";
	public static String PROP_ID = "id";
	public static String PROP_SCORE_TIME = "scoreTime";
	public static String PROP_REMARK = "remark";
	public static String PROP_SCORE_TYPE = "scoreType";
	public static String PROP_SCORE = "score";
	public static String PROP_USE_STATUS = "useStatus";


	// constructors
	public BaseShopScore () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopScore (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopScore (
		java.lang.Long id,
		java.lang.Integer scoreType,
		boolean useStatus,
		boolean status) {

		this.setId(id);
		this.setScoreType(scoreType);
		this.setUseStatus(useStatus);
		this.setStatus(status);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.Integer score;
	private java.util.Date scoreTime;
	private java.lang.Integer scoreType;
	private boolean useStatus;
	private boolean status;
	private java.lang.String remark;
	private java.lang.String code;

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
	 * Return the value associated with the column: scoreTime
	 */
	public java.util.Date getScoreTime () {
		return scoreTime;
	}

	/**
	 * Set the value related to the column: scoreTime
	 * @param scoreTime the scoreTime value
	 */
	public void setScoreTime (java.util.Date scoreTime) {
		this.scoreTime = scoreTime;
	}


	/**
	 * Return the value associated with the column: scoreType
	 */
	public java.lang.Integer getScoreType () {
		return scoreType;
	}

	/**
	 * Set the value related to the column: scoreType
	 * @param scoreType the scoreType value
	 */
	public void setScoreType (java.lang.Integer scoreType) {
		this.scoreType = scoreType;
	}


	/**
	 * Return the value associated with the column: useStatus
	 */
	public boolean getUseStatus () {
		return useStatus;
	}

	/**
	 * Set the value related to the column: useStatus
	 * @param useStatus the useStatus value
	 */
	public void setUseStatus (boolean useStatus) {
		this.useStatus = useStatus;
	}


	/**
	 * Return the value associated with the column: status
	 */
	public boolean getStatus () {
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


	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getCode() {
		return code;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopScore)) return false;
		else {
			com.jspgou.cms.entity.ShopScore shopScore = (com.jspgou.cms.entity.ShopScore) obj;
			if (null == this.getId() || null == shopScore.getId()) return false;
			else return (this.getId().equals(shopScore.getId()));
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