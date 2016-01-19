package com.jspgou.common.web.session;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* This class should preserve.
* @preserve
*/
public interface SessionProvider{
    public static final String JSESSION_COOKIE = "JSESSIONID";
    public static final String JSESSION_URL = "jsessionid";

    public Serializable getAttribute(HttpServletRequest request, String name);

    public void setAttribute(HttpServletRequest request,
			HttpServletResponse response, String name, Serializable value);

    public String getSessionId(HttpServletRequest request,
			HttpServletResponse response);

    public void logout(HttpServletRequest request, HttpServletResponse response);


}
