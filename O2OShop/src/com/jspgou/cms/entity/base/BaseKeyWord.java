package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_keyword_q table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_keyword_q"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseKeyWord  implements Serializable {

	public static String REF = "KeyWord";
	public static String PROP_KEYWORD = "keyword";
	public static String PROP_TIMES = "times";
	public static String PROP_ID = "id";


	// constructors
	public BaseKeyWord () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseKeyWord (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseKeyWord (
		java.lang.Integer id,
		java.lang.String keyword,
		java.lang.Integer times) {

		this.setId(id);
		this.setKeyword(keyword);
		this.setTimes(times);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String keyword;
	private java.lang.Integer times;



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
	 * Return the value associated with the column: keyword
	 */
	public java.lang.String getKeyword () {
		return keyword;
	}

	/**
	 * Set the value related to the column: keyword
	 * @param keyword the keyword value
	 */
	public void setKeyword (java.lang.String keyword) {
		this.keyword = keyword;
	}


	/**
	 * Return the value associated with the column: times
	 */
	public java.lang.Integer getTimes () {
		return times;
	}

	/**
	 * Set the value related to the column: times
	 * @param times the times value
	 */
	public void setTimes (java.lang.Integer times) {
		this.times = times;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.KeyWord)) return false;
		else {
			com.jspgou.cms.entity.KeyWord keyWord = (com.jspgou.cms.entity.KeyWord) obj;
			if (null == this.getId() || null == keyWord.getId()) return false;
			else return (this.getId().equals(keyWord.getId()));
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