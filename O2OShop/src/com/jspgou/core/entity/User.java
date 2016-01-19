package com.jspgou.core.entity;

import com.jspgou.core.entity.base.BaseUser;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
/**
* This class should preserve.
* @preserve
*/
public class User extends BaseUser{
    private static final long serialVersionUID = 1L;

    public void init(){
        if(StringUtils.isBlank(getEmail())){
            setEmail(null);
        }
        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
        setLoginCount(Long.valueOf(0L));
    }

	/* [CONSTRUCTOR MARKER BEGIN] */
    public User(){
    	super();
    }
	/**
	 * Constructor for primary key
	 */
    public User(Long id){
        super(id);
    }

	/**
	 * Constructor for required fields
	 */
    public User(Long id, 
    		    String username,
    		    String password,
    		    Date createTime, 
    		    Long long2){
        super(id, 
        	  username, 
        	  password, 
        	  createTime, 
        	  long2);
    }
	/* [CONSTRUCTOR MARKER END] */
}
