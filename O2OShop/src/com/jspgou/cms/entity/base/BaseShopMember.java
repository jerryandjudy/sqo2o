package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;


/**
 * This is an object that contains data related to the jc_shop_member table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_member"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseShopMember  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ShopMember";
	public static String PROP_STREET = "street";
	public static String PROP_MEMBER = "member";
	public static String PROP_WEBSITE = "website";
	public static String PROP_FAX = "fax";
	public static String PROP_AVATAR = "avatar";
	public static String PROP_REAL_NAME = "realName";
	public static String PROP_CITY = "city";
	public static String PROP_GROUP = "group";
	public static String PROP_BIRTHDAY = "birthday";
	public static String PROP_STATE = "state";
	public static String PROP_DISTRICT = "district";
	public static String PROP_GENDER = "gender";
	public static String PROP_SUBURB = "suburb";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_FIRSTNAME = "firstname";
	public static String PROP_LASTNAME = "lastname";
	public static String PROP_ID = "id";
	public static String PROP_ZIP = "zip";
	public static String PROP_COMPANY = "company";
	public static String PROP_SCORE = "score";
	public static String PROP_TEL = "tel";


	// constructors
	public BaseShopMember () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseShopMember (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseShopMember (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.ShopMemberGroup group,
		java.lang.Integer score) {

		this.setId(id);
		this.setWebsite(website);
		this.setGroup(group);
		this.setScore(score);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String realName;
	private java.lang.Boolean gender;
	private java.lang.Integer score;
	private java.lang.Integer freezeScore;
	private java.lang.Double money;
	private java.util.Date birthday;
	private java.lang.String tel;
	private java.lang.String mobile;
	private java.lang.String avatar;
	private java.lang.Boolean marriage;
	private java.lang.String company;
	private java.lang.Boolean isCar;
	private java.lang.String position;
	private java.lang.String address;
	private java.lang.String schoolTag;
	private java.util.Date schoolTagDate;
	private java.lang.String favoriteBrand;
	private java.lang.String favoriteStar;
	private java.lang.String favoriteMovie;
	private java.lang.String favoritePersonage;
	
	// one to one
	private com.jspgou.core.entity.Member member;

	// many to one
	private com.jspgou.core.entity.Website website;
	private com.jspgou.cms.entity.ShopMemberGroup group;
	
	private com.jspgou.cms.entity.ShopDictionary userDegree;
	private com.jspgou.cms.entity.ShopDictionary familyMembers;
	private com.jspgou.cms.entity.ShopDictionary incomeDesc;
	private com.jspgou.cms.entity.ShopDictionary workSeniority;
	private com.jspgou.cms.entity.ShopDictionary degree;

	// collections
	private java.util.Set<com.jspgou.cms.entity.Product> favorites;
	
	



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="member_id"
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
	 * Return the value associated with the column: realname
	 */
	public java.lang.String getRealName () {
		return realName;
	}

	/**
	 * Set the value related to the column: realname
	 * @param realName the realname value
	 */
	public void setRealName (java.lang.String realName) {
		this.realName = realName;
	}
	/**
	 * Return the value associated with the column: gender
	 */
	public java.lang.Boolean getGender () {
		return gender;
	}

	/**
	 * Set the value related to the column: gender
	 * @param gender the gender value
	 */
	public void setGender (java.lang.Boolean gender) {
		this.gender = gender;
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
	
	public java.lang.Integer getFreezeScore() {
		return freezeScore;
	}

	public void setFreezeScore(java.lang.Integer freezeScore) {
		this.freezeScore = freezeScore;
	}

	public java.lang.Double getMoney() {
		return money;
	}

	public void setMoney(java.lang.Double money) {
		this.money = money;
	}

	/**
	 * Return the value associated with the column: birthday
	 */
	public java.util.Date getBirthday () {
		return birthday;
	}
	/**
	 * Set the value related to the column: birthday
	 * @param birthday the birthday value
	 */
	public void setBirthday (java.util.Date birthday) {
		this.birthday = birthday;
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
	 * Return the value associated with the column: mobile
	 */
	public java.lang.String getMobile () {
		return mobile;
	}
	/**
	 * Set the value related to the column: mobile
	 * @param mobile the mobile value
	 */
	public void setMobile (java.lang.String mobile) {
		this.mobile = mobile;
	}
	/**
	 * Return the value associated with the column: marriage
	 */
	public java.lang.Boolean getMarriage () {
		return marriage;
	}
	/**
	 * Set the value related to the column: marriage
	 * @param marriage the marriage value
	 */
	public void setMarriage (java.lang.Boolean marriage) {
		this.marriage = marriage;
	}
	/**
	 * Return the value associated with the column: company
	 */
	public java.lang.String getCompany () {
		return company;
	}
	/**
	 * Set the value related to the column: company
	 * @param company the company value
	 */
	public void setCompany (java.lang.String company) {
		this.company = company;
	}
	/**
	 * Return the value associated with the column: avatar
	 */
	public java.lang.String getAvatar () {
		return avatar;
	}
	/**
	 * Set the value related to the column: avatar
	 * @param avatar the avatar value
	 */
	public void setAvatar (java.lang.String avatar) {
		this.avatar = avatar;
	}
	/**
	 * Return the value associated with the column: isCar
	 */
	public java.lang.Boolean getIsCar() {
		return isCar;
	}

	/**
	 * Set the value related to the column: isCar
	 * @param isCar the isCar value
	 */
	public void setIsCar (java.lang.Boolean isCar) {
		this.isCar = isCar;
	}
	
	/**
	 * Return the value associated with the column: position
	 */
	public java.lang.String getPosition () {
		return position;
	}
	/**
	 * Set the value related to the column: position
	 * @param position the position value
	 */
	public void setPosition (java.lang.String position) {
		this.position = position;
	}
	/**
	 * Return the value associated with the column: schoolTag
	 */
	public java.lang.String getSchoolTag () {
		return schoolTag;
	}
	/**
	 * Set the value related to the column: schoolTag
	 * @param schoolTag the schoolTag value
	 */
	public void setSchoolTag (java.lang.String schoolTag) {
		this.schoolTag = schoolTag;
	}
	/**
	 * Return the value associated with the column: schoolTagDate
	 */
	public java.util.Date getSchoolTagDate () {
		return schoolTagDate;
	}
	/**
	 * Set the value related to the column: schoolTagDate
	 * @param schoolTagDate the schoolTagDate value
	 */
	public void setSchoolTagDate (Date schoolTagDate) {
		this.schoolTagDate = schoolTagDate;
	}
	/**
	 * Return the value associated with the column: favoriteBrand
	 */
	public java.lang.String getFavoriteBrand () {
		return favoriteBrand;
	}
	/**
	 * Set the value related to the column: favoriteBrand
	 * @param favoriteBrand the favoriteBrand value
	 */
	public void setFavoriteBrand (java.lang.String favoriteBrand) {
		this.favoriteBrand = favoriteBrand;
	}
	
	/**
	 * Return the value associated with the column: favoriteStar
	 */
	public java.lang.String getFavoriteStar () {
		return favoriteStar;
	}
	/**
	 * Set the value related to the column: favoriteBrand
	 * @param favoriteStar the favoriteStar value
	 */
	public void setFavoriteStar (java.lang.String favoriteStar) {
		this.favoriteStar = favoriteStar;
	}
	
	/**
	 * Return the value associated with the column: favoriteMovie
	 */
	public java.lang.String getFavoriteMovie () {
		return favoriteMovie;
	}
	/**
	 * Set the value related to the column: favoriteMovie
	 * @param favoriteMovie the favoriteMovie value
	 */
	public void setFavoriteMovie (java.lang.String favoriteMovie) {
		this.favoriteMovie = favoriteMovie;
	}
	
	/**
	 * Return the value associated with the column: favoritePersonage
	 */
	public java.lang.String getFavoritePersonage() {
		return favoritePersonage;
	}
	/**
	 * Set the value related to the column: favoritePersonage
	 * @param favoritePersonage the favoritePersonage value
	 */
	public void setFavoritePersonage (java.lang.String favoritePersonage) {
		this.favoritePersonage = favoritePersonage;
	}

	/**
	 * Return the value associated with the column: member
	 */
	public com.jspgou.core.entity.Member getMember () {
		return member;
	}

	/**
	 * Set the value related to the column: member
	 * @param member the member value
	 */
	public void setMember (com.jspgou.core.entity.Member member) {
		this.member = member;
	}


	/**
	 * Return the value associated with the column: website_id
	 */
	public com.jspgou.core.entity.Website getWebsite () {
		return website;
	}

	/**
	 * Set the value related to the column: website_id
	 * @param website the website_id value
	 */
	public void setWebsite (com.jspgou.core.entity.Website website) {
		this.website = website;
	}


	/**
	 * Return the value associated with the column: group_id
	 */
	public com.jspgou.cms.entity.ShopMemberGroup getGroup () {
		return group;
	}

	/**
	 * Set the value related to the column: group_id
	 * @param group the group_id value
	 */
	public void setGroup (com.jspgou.cms.entity.ShopMemberGroup group) {
		this.group = group;
	}


	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public com.jspgou.cms.entity.ShopDictionary getUserDegree() {
		return userDegree;
	}

	public void setUserDegree(com.jspgou.cms.entity.ShopDictionary userDegree) {
		this.userDegree = userDegree;
	}

	public com.jspgou.cms.entity.ShopDictionary getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(com.jspgou.cms.entity.ShopDictionary familyMembers) {
		this.familyMembers = familyMembers;
	}

	public com.jspgou.cms.entity.ShopDictionary getIncomeDesc() {
		return incomeDesc;
	}

	public void setIncomeDesc(com.jspgou.cms.entity.ShopDictionary incomeDesc) {
		this.incomeDesc = incomeDesc;
	}

	public com.jspgou.cms.entity.ShopDictionary getWorkSeniority() {
		return workSeniority;
	}

	public void setWorkSeniority(com.jspgou.cms.entity.ShopDictionary workSeniority) {
		this.workSeniority = workSeniority;
	}

	public com.jspgou.cms.entity.ShopDictionary getDegree() {
		return degree;
	}

	public void setDegree(com.jspgou.cms.entity.ShopDictionary degree) {
		this.degree = degree;
	}

	/**
	 * Return the value associated with the column: favorites
	 */
	public java.util.Set<com.jspgou.cms.entity.Product> getFavorites () {
		return favorites;
	}

	/**
	 * Set the value related to the column: favorites
	 * @param favorites the favorites value
	 */
	public void setFavorites (java.util.Set<com.jspgou.cms.entity.Product> favorites) {
		this.favorites = favorites;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ShopMember)) return false;
		else {
			com.jspgou.cms.entity.ShopMember shopMember = (com.jspgou.cms.entity.ShopMember) obj;
			if (null == this.getId() || null == shopMember.getId()) return false;
			else return (this.getId().equals(shopMember.getId()));
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