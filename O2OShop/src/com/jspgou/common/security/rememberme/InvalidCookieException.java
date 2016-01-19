package com.jspgou.common.security.rememberme;
/**
* This class should preserve.
* @preserve
*/
@SuppressWarnings("serial")
public class InvalidCookieException extends RememberMeAuthenticationException {
	public InvalidCookieException() {
	}

	public InvalidCookieException(String msg) {
		super(msg);
	}
}
