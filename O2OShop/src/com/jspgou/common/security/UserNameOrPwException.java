package com.jspgou.common.security;

/**
 * 用户被禁用异常
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
*/
@SuppressWarnings("serial")
public class UserNameOrPwException extends AccountStatusException {
	public UserNameOrPwException() {
	}

	public UserNameOrPwException(String msg) {
		super(msg);
	}

	public UserNameOrPwException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}
