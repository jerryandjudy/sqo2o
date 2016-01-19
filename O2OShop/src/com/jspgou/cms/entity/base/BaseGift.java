package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_gift table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_gift"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseGift  implements Serializable {

	public static String REF = "Gift";
	public static String PROP_GIFT_STOCK = "giftStock";
	public static String PROP_GIFT_NAME = "giftName";
	public static String PROP_GIFT_PICTURE = "giftPicture";
	public static String PROP_GIFT_SCORE = "giftScore";
	public static String PROP_ID = "id";
	public static String PROP_ISGIFT = "isgift";


	// constructors
	public BaseGift () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseGift (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String giftName;
	private java.lang.Integer giftScore;
	private java.lang.Integer giftStock;
	private java.lang.String giftPicture;
	private java.lang.Boolean isgift;
	private String introduced;

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
	 * Return the value associated with the column: giftName
	 */
	public java.lang.String getGiftName () {
		return giftName;
	}

	/**
	 * Set the value related to the column: giftName
	 * @param giftName the giftName value
	 */
	public void setGiftName (java.lang.String giftName) {
		this.giftName = giftName;
	}


	/**
	 * Return the value associated with the column: giftScore
	 */
	public java.lang.Integer getGiftScore () {
		return giftScore;
	}

	/**
	 * Set the value related to the column: giftScore
	 * @param giftScore the giftScore value
	 */
	public void setGiftScore (java.lang.Integer giftScore) {
		this.giftScore = giftScore;
	}


	/**
	 * Return the value associated with the column: giftStock
	 */
	public java.lang.Integer getGiftStock () {
		return giftStock;
	}

	/**
	 * Set the value related to the column: giftStock
	 * @param giftStock the giftStock value
	 */
	public void setGiftStock (java.lang.Integer giftStock) {
		this.giftStock = giftStock;
	}


	/**
	 * Return the value associated with the column: giftPicture
	 */
	public java.lang.String getGiftPicture () {
		return giftPicture;
	}

	/**
	 * Set the value related to the column: giftPicture
	 * @param giftPicture the giftPicture value
	 */
	public void setGiftPicture (java.lang.String giftPicture) {
		this.giftPicture = giftPicture;
	}


	/**
	 * Return the value associated with the column: isgift
	 */
	public java.lang.Boolean getIsgift () {
		return isgift;
	}

	/**
	 * Set the value related to the column: isgift
	 * @param isgift the isgift value
	 */
	public void setIsgift (java.lang.Boolean isgift) {
		this.isgift = isgift;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Gift)) return false;
		else {
			com.jspgou.cms.entity.Gift gift = (com.jspgou.cms.entity.Gift) obj;
			if (null == this.getId() || null == gift.getId()) return false;
			else return (this.getId().equals(gift.getId()));
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

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getIntroduced() {
		return introduced;
	}


}