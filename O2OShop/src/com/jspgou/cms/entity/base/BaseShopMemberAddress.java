package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_member_address table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_member_address"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopMemberAddress  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ShopMemberAddress";
	public static String PROP_PHONE = "phone";
	public static String PROP_MEMBER = "member";
	public static String PROP_IS_DEFAULT = "isDefault";
	public static String PROP_PROVINCE = "province";
	public static String PROP_COUNTRY = "country";
	public static String PROP_CITY = "city";
	public static String PROP_AREA_CODE = "areaCode";
	public static String PROP_DETAILADDRESS = "detailaddress";
	public static String PROP_POST_CODE = "postCode";
	public static String PROP_USERNAME = "username";
	public static String PROP_GENDER = "gender";
	public static String PROP_EXT_NUMBER = "extNumber";
	public static String PROP_ID = "id";
	public static String PROP_TEL = "tel";


	// constructors
	public BaseShopMemberAddress () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopMemberAddress (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopMemberAddress (
		java.lang.Long id,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.Address province,
		com.jspgou.cms.entity.Address city,
		com.jspgou.cms.entity.Address country,
		java.lang.String username,
		java.lang.String detailaddress,
		boolean isDefault) {

		this.setId(id);
		this.setMember(member);
		this.setProvince(province);
		this.setCity(city);
		this.setCountry(country);
		this.setUsername(username);
		this.setDetailaddress(detailaddress);
		this.setIsDefault(isDefault);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String username;
	private boolean gender;
	private java.lang.String detailaddress;
	private java.lang.String postCode;
	private java.lang.String tel;
	private java.lang.String areaCode;
	private java.lang.String phone;
	private java.lang.String extNumber;
	private boolean isDefault;

	// many to one
	private com.jspgou.cms.entity.ShopMember member;
	private com.jspgou.cms.entity.Address province;
	private com.jspgou.cms.entity.Address city;
	private com.jspgou.cms.entity.Address country;
	private com.jspgou.cms.entity.Address street;
	// many to one
	private com.jspgou.core.entity.Website website;


	public com.jspgou.core.entity.Website getWebsite() {
		return website;
	}

	public void setWebsite(com.jspgou.core.entity.Website website) {
		this.website = website;
	}

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="address_id"
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
	 * Return the value associated with the column: username
	 */
	public java.lang.String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * @param username the username value
	 */
	public void setUsername (java.lang.String username) {
		this.username = username;
	}


	/**
	 * Return the value associated with the column: gender
	 */
	public boolean getGender () {
		return gender;
	}

	/**
	 * Set the value related to the column: gender
	 * @param gender the gender value
	 */
	public void setGender (boolean gender) {
		this.gender = gender;
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
	 * Return the value associated with the column: postcode
	 */
	public java.lang.String getPostCode () {
		return postCode;
	}

	/**
	 * Set the value related to the column: postcode
	 * @param postCode the postcode value
	 */
	public void setPostCode (java.lang.String postCode) {
		this.postCode = postCode;
	}


	/**
	 * Return the value associated with the column: tel
	 */
	public java.lang.String getTel () {
		return tel;
	}

	/**
	 * Set the value related to the column: tel
	 * @param tel the tel value
	 */
	public void setTel (java.lang.String tel) {
		this.tel = tel;
	}


	/**
	 * Return the value associated with the column: areacode
	 */
	public java.lang.String getAreaCode () {
		return areaCode;
	}

	/**
	 * Set the value related to the column: areacode
	 * @param areaCode the areacode value
	 */
	public void setAreaCode (java.lang.String areaCode) {
		this.areaCode = areaCode;
	}


	/**
	 * Return the value associated with the column: phone
	 */
	public java.lang.String getPhone () {
		return phone;
	}

	/**
	 * Set the value related to the column: phone
	 * @param phone the phone value
	 */
	public void setPhone (java.lang.String phone) {
		this.phone = phone;
	}


	/**
	 * Return the value associated with the column: extnumber
	 */
	public java.lang.String getExtNumber () {
		return extNumber;
	}

	/**
	 * Set the value related to the column: extnumber
	 * @param extNumber the extnumber value
	 */
	public void setExtNumber (java.lang.String extNumber) {
		this.extNumber = extNumber;
	}


	/**
	 * Return the value associated with the column: is_default
	 */
	public boolean getIsDefault () {
		return isDefault;
	}

	/**
	 * Set the value related to the column: is_default
	 * @param isDefault the is_default value
	 */
	public void setIsDefault (boolean isDefault) {
		this.isDefault = isDefault;
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
	 * Return the value associated with the column: province_id
	 */
	public com.jspgou.cms.entity.Address getProvince () {
		return province;
	}

	/**
	 * Set the value related to the column: province_id
	 * @param province the province_id value
	 */
	public void setProvince (com.jspgou.cms.entity.Address province) {
		this.province = province;
	}


	/**
	 * Return the value associated with the column: city_id
	 */
	public com.jspgou.cms.entity.Address getCity () {
		return city;
	}

	/**
	 * Set the value related to the column: city_id
	 * @param city the city_id value
	 */
	public void setCity (com.jspgou.cms.entity.Address city) {
		this.city = city;
	}


	/**
	 * Return the value associated with the column: country_id
	 */
	public com.jspgou.cms.entity.Address getCountry () {
		return country;
	}

	/**
	 * Set the value related to the column: country_id
	 * @param country the country_id value
	 */
	public void setCountry (com.jspgou.cms.entity.Address country) {
		this.country = country;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopMemberAddress)) return false;
		else {
			com.jspgou.cms.entity.ShopMemberAddress shopMemberAddress = (com.jspgou.cms.entity.ShopMemberAddress) obj;
			if (null == this.getId() || null == shopMemberAddress.getId()) return false;
			else return (this.getId().equals(shopMemberAddress.getId()));
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

	public com.jspgou.cms.entity.Address getStreet() {
		return street;
	}

	public void setStreet(com.jspgou.cms.entity.Address street) {
		this.street = street;
	}


}