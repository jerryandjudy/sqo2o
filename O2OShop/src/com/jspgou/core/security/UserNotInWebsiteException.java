package com.jspgou.core.security;

import com.jspgou.common.security.AuthenticationException;
/**
* This class should preserve.
* @preserve
*/
public class UserNotInWebsiteException extends AuthenticationException{

    /**
	 * 用户没有激活异常
	 * 
	 *  @author liufang
	 */
	private static final long serialVersionUID = 1L;

	public UserNotInWebsiteException(){}

    public UserNotInWebsiteException(String s){
        super(s);
    }
}
