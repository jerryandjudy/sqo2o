package com.jspgou.core.entity;

import com.jspgou.core.entity.base.BaseAdmin;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
* This class should preserve.
* @preserve
*/
public class Admin extends BaseAdmin{
    private static final long serialVersionUID = 1L;
    
    public void init(){
        if(getCreateTime() == null)
            setCreateTime(new Date());
        if(getDisabled() == null)
            setDisabled(Boolean.valueOf(false));
    }
    
    public Set<String> getRolesPerms() {
		Set<Role> roles = getRoles();
		if (roles == null) {
			return null;
		}
		Set<String> allPerms = new HashSet<String>();
		for (Role role : getRoles()) {
			allPerms.addAll(role.getPerms());
		}
		return allPerms;
	}
    
    public Integer[] getRoleIds() {
		Set<Role> roles = getRoles();
		return Role.fetchIds(roles);
	}
    
	public void addToRoles(Role role) {
		if (role == null) {
			return;
		}
		Set<Role> set = getRoles();
		if (set == null) {
			set = new HashSet<Role>();
			setRoles(set);
		}
		set.add(role);
	}
	
	public boolean isSuper() {
		Set<Role> roles = getRoles();
		if (roles == null  ) {
			return false;
		}
		for (Role role : roles) {
			if (role.getSuper()) {
				return true;
			}
		}
		return false;
	}
    
	/* [CONSTRUCTOR MARKER BEGIN] */
    public Admin(){
    	super();
    }
	/**
	 * Constructor for primary key
	 */
    public Admin(Long id){
        super(id);
    }

	/**
	 * Constructor for required fields
	 */
    public Admin(Long id,
    		User user, 
    		Website website, 
    		Date date, 
    		Boolean boolean1){
        super(id, 
        	  user, 
        	  website, 
        	  date, 
        	  boolean1);
    }
    
	/* [CONSTRUCTOR MARKER END] */

    public String getUsername(){
        User user = getUser();
        if(user != null){
            return user.getUsername();
        }else{
            return null;
        }
    }

    public String getEmail(){
        User user = getUser();
        if(user != null){
            return user.getEmail();
        }else{
            return null;
        }
    }

    public String getLastLoginIp()
    {
        User user = getUser();
        if(user != null)
            return user.getLastLoginIp();
        else
            return null;
    }

    public Date getLastLoginTime(){
        User user = getUser();
        if(user != null){
            return user.getLastLoginTime();
        }else{
            return null;
        }
    }

    public String getCurrentLoginIp(){
        User user = getUser();
        if(user != null){
            return user.getCurrentLoginIp();
        }else{
            return null;
        }
    }

    public Date getCurrentLoginTime(){
        User user = getUser();
        if(user != null){
            return user.getCurrentLoginTime();
        }else{
            return null;
        }
    }
}
