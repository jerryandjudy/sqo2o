package com.jspgou.common.security.rememberme;

import com.jspgou.common.security.AuthenticationException;
/**
* This class should preserve.
* @preserve
*/
@SuppressWarnings("serial")
public class RememberMeAuthenticationException extends AuthenticationException {
	public RememberMeAuthenticationException() {
	}

	public RememberMeAuthenticationException(String msg) {
		super(msg);
	}
}
