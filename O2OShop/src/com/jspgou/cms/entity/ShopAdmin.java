package com.jspgou.cms.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.jspgou.core.entity.Admin;
import com.jspgou.core.entity.Role;
import com.jspgou.cms.entity.base.BaseShopAdmin;
/**
* This class should preserve.
* @preserve
*/
public class ShopAdmin extends BaseShopAdmin {
	private static final long serialVersionUID = 1L;

	public Set<String> getPerms() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getRolesPerms();
		} else {
			return null;
		}
	}

	public String getUsername() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getUsername();
		} else {
			return null;
		}
	}
	public Boolean getIsSuper() {
		Admin admin = getAdmin();
		boolean flag=false;
		if (admin != null) {
			Set<Role> roles = admin.getRoles();
			if(!roles.isEmpty()){
			 Iterator<Role> it = roles.iterator();
			 while(it.hasNext()){
				Role role = it.next(); 
				if(role.getSuper()){
					flag=true;
					break;
				}
			 }
			}
			return flag;
		} else {
			return null;
		}
	}

	public String getEmail() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getEmail();
		} else {
			return null;
		}
	}

	public Date getLastLoginTime() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getLastLoginTime();
		} else {
			return null;
		}
	}

	public String getLastLoginIp() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getLastLoginIp();
		} else {
			return null;
		}
	}
	
	public Boolean getViewonlyAdmin() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getViewonlyAdmin();
		} else {
			return null;
		}
	}

	public Date getCurrentLoginTime() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getCurrentLoginTime();
		} else {
			return null;
		}
	}

	public String getCurrentLoginIp() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getCurrentLoginIp();
		} else {
			return null;
		}
	}

	public Boolean getDisabled() {
		Admin admin = getAdmin();
		if (admin != null) {
			return admin.getDisabled();
		} else {
			return null;
		}
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public ShopAdmin() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopAdmin(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopAdmin(java.lang.Long id, com.jspgou.core.entity.Website website) {

		super(id, website);
	}

	/* [CONSTRUCTOR MARKER END] */
}