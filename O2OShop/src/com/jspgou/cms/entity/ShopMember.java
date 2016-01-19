package com.jspgou.cms.entity;

import java.util.Date;

import com.jspgou.cms.entity.base.BaseShopMember;
/**
* This class should preserve.
* @preserve
*/
public class ShopMember extends BaseShopMember {
	private static final long serialVersionUID = 1L;
	
	/* [CONSTRUCTOR MARKER BEGIN] */
	public ShopMember () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopMember (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopMember (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.ShopMemberGroup group,
		java.lang.Integer score) {

		super (
			id,
			website,
			group,
			score);
	}
	
	public void init() {
		if (getScore() == null) {
			setScore(0);
		}
	}

	public Date getCreateTime() {
		return getMember().getCreateTime();
	}

	public Boolean getDisabled() {
		return getMember().getDisabled();
	}

	public String getUsername() {
		return getMember().getUsername();
	}

	public String getEmail() {
		return getMember().getEmail();
	}

	public String getPassword() {
		return getMember().getPassword();
	}

	public Long getLoginCount() {
		return getMember().getLoginCount();
	}

	public String getRegisterIp() {
		return getMember().getRegisterIp();
	}

	public java.util.Date getLastLoginTime() {
		return getMember().getLastLoginTime();
	}

	public String getLastLoginIp() {
		return getMember().getLastLoginIp();
	}

	public Date getCurrentLoginTime() {
		return getMember().getCurrentLoginTime();
	}

	public String getCurrentLoginIp() {
		return getMember().getCurrentLoginIp();
	}
}