package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_pay table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_pay"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopPay  implements Serializable {

	public static String REF = "ShopPay";
	public static String PROP_BANK_NUM = "bankNum";
	public static String PROP_BANKID = "bankid";
	public static String PROP_ADDRESS = "address";
	public static String PROP_ID = "id";
	public static String PROP_BANKKEY = "bankkey";


	// constructors
	public BaseShopPay () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopPay (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String address;
	private java.lang.String bankNum;
	private java.lang.String bankid;
	private java.lang.String bankkey;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: address
	 */
	public java.lang.String getAddress () {
		return address;
	}

	/**
	 * Set the value related to the column: address
	 * @param address the address value
	 */
	public void setAddress (java.lang.String address) {
		this.address = address;
	}


	/**
	 * Return the value associated with the column: bankNum
	 */
	public java.lang.String getBankNum () {
		return bankNum;
	}

	/**
	 * Set the value related to the column: bankNum
	 * @param bankNum the bankNum value
	 */
	public void setBankNum (java.lang.String bankNum) {
		this.bankNum = bankNum;
	}


	/**
	 * Return the value associated with the column: bankid
	 */
	public java.lang.String getBankid () {
		return bankid;
	}

	/**
	 * Set the value related to the column: bankid
	 * @param bankid the bankid value
	 */
	public void setBankid (java.lang.String bankid) {
		this.bankid = bankid;
	}


	/**
	 * Return the value associated with the column: bankkey
	 */
	public java.lang.String getBankkey () {
		return bankkey;
	}

	/**
	 * Set the value related to the column: bankkey
	 * @param bankkey the bankkey value
	 */
	public void setBankkey (java.lang.String bankkey) {
		this.bankkey = bankkey;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopPay)) return false;
		else {
			com.jspgou.cms.entity.ShopPay shopPay = (com.jspgou.cms.entity.ShopPay) obj;
			if (null == this.getId() || null == shopPay.getId()) return false;
			else return (this.getId().equals(shopPay.getId()));
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