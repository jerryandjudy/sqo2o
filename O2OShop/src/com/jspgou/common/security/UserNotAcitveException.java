package com.jspgou.common.security;

/**
 * 用户没有激活异常
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
*/
@SuppressWarnings("serial")
public class UserNotAcitveException extends AccountStatusException {
	public UserNotAcitveException(){}


    public UserNotAcitveException(String s){
        super(s);
    }
}
