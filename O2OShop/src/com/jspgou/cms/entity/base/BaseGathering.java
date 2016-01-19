package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_gathering table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_gathering"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseGathering  implements Serializable {

	public static String REF = "Gathering";
	public static String PROP_DRAWEE = "drawee";
	public static String PROP_ACCOUNTS = "accounts";
	public static String PROP_MONEY = "money";
	public static String PROP_BANK = "bank";
	public static String PROP_COMMENT = "comment";
	public static String PROP_ID = "id";
	public static String PROP_SHOP_ADMIN = "shopAdmin";
	public static String PROP_INDENT = "indent";


	// constructors
	public BaseGathering () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseGathering (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseGathering (
		java.lang.Long id,
		com.jspgou.cms.entity.Order indent,
		com.jspgou.cms.entity.ShopAdmin shopAdmin,
		java.lang.String bank,
		java.lang.String accounts,
		java.lang.String drawee,
		java.lang.String comment) {

		this.setId(id);
		this.setIndent(indent);
		this.setShopAdmin(shopAdmin);
		this.setBank(bank);
		this.setAccounts(accounts);
		this.setDrawee(drawee);
		this.setComment(comment);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String bank;
	private java.lang.String accounts;
	private double money;
	private java.lang.String drawee;
	private java.lang.String comment;

	// many to one
	private com.jspgou.cms.entity.Order indent;
	private com.jspgou.cms.entity.ShopAdmin shopAdmin;



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
	 * Return the value associated with the column: bank
	 */
	public java.lang.String getBank () {
		return bank;
	}

	/**
	 * Set the value related to the column: bank
	 * @param bank the bank value
	 */
	public void setBank (java.lang.String bank) {
		this.bank = bank;
	}


	/**
	 * Return the value associated with the column: accounts
	 */
	public java.lang.String getAccounts () {
		return accounts;
	}

	/**
	 * Set the value related to the column: accounts
	 * @param accounts the accounts value
	 */
	public void setAccounts (java.lang.String accounts) {
		this.accounts = accounts;
	}


	/**
	 * Return the value associated with the column: money
	 */
	public double getMoney () {
		return money;
	}

	/**
	 * Set the value related to the column: money
	 * @param money the money value
	 */
	public void setMoney (double money) {
		this.money = money;
	}


	/**
	 * Return the value associated with the column: drawee
	 */
	public java.lang.String getDrawee () {
		return drawee;
	}

	/**
	 * Set the value related to the column: drawee
	 * @param drawee the drawee value
	 */
	public void setDrawee (java.lang.String drawee) {
		this.drawee = drawee;
	}


	/**
	 * Return the value associated with the column: comment
	 */
	public java.lang.String getComment () {
		return comment;
	}

	/**
	 * Set the value related to the column: comment
	 * @param comment the comment value
	 */
	public void setComment (java.lang.String comment) {
		this.comment = comment;
	}


	/**
	 * Return the value associated with the column: order_id
	 */
	public com.jspgou.cms.entity.Order getIndent () {
		return indent;
	}

	/**
	 * Set the value related to the column: order_id
	 * @param indent the order_id value
	 */
	public void setIndent (com.jspgou.cms.entity.Order indent) {
		this.indent = indent;
	}


	/**
	 * Return the value associated with the column: admin_id
	 */
	public com.jspgou.cms.entity.ShopAdmin getShopAdmin () {
		return shopAdmin;
	}

	/**
	 * Set the value related to the column: admin_id
	 * @param shopAdmin the admin_id value
	 */
	public void setShopAdmin (com.jspgou.cms.entity.ShopAdmin shopAdmin) {
		this.shopAdmin = shopAdmin;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Gathering)) return false;
		else {
			com.jspgou.cms.entity.Gathering gathering = (com.jspgou.cms.entity.Gathering) obj;
			if (null == this.getId() || null == gathering.getId()) return false;
			else return (this.getId().equals(gathering.getId()));
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