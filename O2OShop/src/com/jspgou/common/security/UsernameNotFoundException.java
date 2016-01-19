package com.jspgou.common.security;

/**
 * 用户名没有找到异常
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
*/
@SuppressWarnings("serial")
public class UsernameNotFoundException extends AuthenticationException {
	public UsernameNotFoundException() {
	}

	public UsernameNotFoundException(String msg) {
		super(msg);
	}
}