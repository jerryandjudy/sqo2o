package com.jspgou.common.security.rememberme;

/**
 * @author Luke Taylor
 * @version $Id: CookieTheftException.java,v 1.1 2014/08/11 01:46:28 Administrator Exp $
 * This class should preserve.
 * @preserve
*/
@SuppressWarnings("serial")
public class CookieTheftException extends RememberMeAuthenticationException {
	public CookieTheftException(String message) {
		super(message);
	}
}
