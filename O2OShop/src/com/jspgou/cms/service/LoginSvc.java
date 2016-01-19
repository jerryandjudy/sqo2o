package com.jspgou.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspgou.common.security.BadCredentialsException;
import com.jspgou.common.security.UserNotAcitveException;
import com.jspgou.common.security.UsernameNotFoundException;
import com.jspgou.core.entity.Website;
import com.jspgou.core.security.UserNotInWebsiteException;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopMember;
/**
* This class should preserve.
* @preserve
*/
public interface LoginSvc {
	public ShopMember memberLogin(HttpServletRequest request,
			HttpServletResponse response, Website web, String username,
			String password) throws UsernameNotFoundException,
			BadCredentialsException, UserNotInWebsiteException, UserNotAcitveException;
	public ShopMember memberLogin(HttpServletRequest request,
			HttpServletResponse response,  String username) throws UsernameNotFoundException,
			BadCredentialsException, UserNotInWebsiteException, UserNotAcitveException;

	public ShopMember getMember(HttpServletRequest request,
			HttpServletResponse response, Website web);

	public ShopAdmin adminLogin(HttpServletRequest request,
			HttpServletResponse response, Website web, String username,
			String password) throws UsernameNotFoundException,
			BadCredentialsException, UserNotInWebsiteException;

	public ShopAdmin getAdmin(HttpServletRequest request,
			HttpServletResponse response, Website web);

	public void logout(HttpServletRequest request, HttpServletResponse response);

	public void clearCookie(HttpServletRequest request,
			HttpServletResponse response);
}
