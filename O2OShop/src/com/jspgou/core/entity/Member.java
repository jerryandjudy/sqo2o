package com.jspgou.core.entity;

import com.jspgou.common.security.userdetails.UserDetails;
import com.jspgou.core.entity.base.BaseMember;
import java.util.Date;

/**
* This class should preserve.
* @preserve
*/
public class Member extends BaseMember implements UserDetails{
    private static final long serialVersionUID = 1L;
    
    public void init(){
    	if(getActive ()==null){
    		setActive(Boolean.valueOf(true));
    	}
        if(getCreateTime() == null)
            setCreateTime(new Date());
        if(getDisabled() == null)
            setDisabled(Boolean.valueOf(false));
    }

	/* [CONSTRUCTOR MARKER BEGIN] */
    public Member(){
        super();
    }
    
    public Member(Long id){
        super(id);
    }

    public Member(Long id, 
    		User user, 
    		Website website, 
    		Date createTime,
    		Boolean disabled,
    		Boolean active,
	        String activationCode){
        super(id, 
        	  user, 
        	  website, 
        	  createTime,
        	  disabled,
        	  active,
        	  activationCode);
    }
	/* [CONSTRUCTOR MARKER END] */

    public boolean isAccountNonExpired(){
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    }

    public boolean isEnabled(){
        return !getDisabled().booleanValue();
    }

    public boolean isCredentialsNonExpired(){
        return true;
    }

    public String getUsername(){
        return getUser().getUsername();
    }

    public String getEmail(){
        return getUser().getEmail();
    }

    public String getPassword(){
        return getUser().getPassword();
    }

    public Long getLoginCount(){
        return getUser().getLoginCount();
    }

    public String getRegisterIp(){
        return getUser().getRegisterIp();
    }

    public Date getLastLoginTime(){
        return getUser().getLastLoginTime();
    }

    public String getLastLoginIp(){
        return getUser().getLastLoginIp();
    }

    public Date getCurrentLoginTime(){
        return getUser().getCurrentLoginTime();
    }

    public String getCurrentLoginIp(){
        return getUser().getCurrentLoginIp();
    }
    
}
