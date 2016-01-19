package com.jspgou.cms.service.impl;

import static com.jspgou.core.web.Constants.SESSION_ADMIN_ID_KEY;
import static com.jspgou.core.web.Constants.SESSION_MEMBER_ID_KEY;
import static com.jspgou.core.web.Constants.SESSION_USER_ID_KEY;
import static com.jspgou.core.web.CookieConstants.FULLNAME;
import static com.jspgou.core.web.CookieConstants.USERNAME;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspgou.common.security.BadCredentialsException;
import com.jspgou.common.security.UserNotAcitveException;
import com.jspgou.common.security.UsernameNotFoundException;
import com.jspgou.common.security.encoder.PwdEncoder;
import com.jspgou.common.security.rememberme.CookieTheftException;
import com.jspgou.common.security.rememberme.RememberMeService;
import com.jspgou.common.web.session.SessionProvider;
import com.jspgou.core.entity.Member;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.MemberMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.security.UserNotInWebsiteException;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ShopAdminMng;
import com.jspgou.cms.manager.ShopConfigMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.service.ShoppingSvc;
/**
* This class should preserve.
* @preserve
*/
@Service
public class LoginSvcImpl implements LoginSvc {
	private static final Logger log = LoggerFactory
			.getLogger(LoginSvcImpl.class);

	public ShopMember memberLogin(HttpServletRequest request,
			HttpServletResponse response, Website web, String username,
			String password) throws UsernameNotFoundException,
			BadCredentialsException, UserNotInWebsiteException,UserNotAcitveException {
		Long webId = web.getId();
		// 先退出登录
		logout(request, response);
		User user = login(username, password);
		ShopMember member = shopMemberMng.getByUserId(webId, user.getId());
		if (member == null) {
			ShopConfig config = shopConfigMng.findById(webId);
			if (config.getRegisterAuto()) {
				// 自动注册
				member = shopMemberMng.join(user, webId, config.getRegisterGroup());
			} else {
				throw new UserNotInWebsiteException("user '"
						+ user.getUsername() + "' not in Website '" + webId
						+ "'");
			}
		}else{
			if(!member.getMember().getActive()){
				throw new UserNotAcitveException("user '"
						+ user.getUsername() + "' not Active '" + webId
						+ "'");
			}
		}
		userMng.updateLoginInfo(user.getId(), request.getRemoteAddr());
		rememberMeService.loginSuccess(request, response, member.getMember());
		session.setAttribute(request, response, SESSION_USER_ID_KEY, user.getId());
		session.setAttribute(request, response, SESSION_MEMBER_ID_KEY, member.getId());
		addUsernameCookie(member.getUsername(), null, null, request, response);
		shoppingSvc.addCookie(member, request, response);
		return member;
	}
	public ShopMember memberLogin(HttpServletRequest request,
			HttpServletResponse response,  String username) throws UsernameNotFoundException,
			BadCredentialsException, UserNotInWebsiteException,UserNotAcitveException {
//		Long webId = web.getId();
		// 先退出登录
		logout(request, response);
		User user = userMng.getByUsername(username);
		ShopMember member = shopMemberMng.getByUsername(username);
		if (member == null) {
		
				throw new UserNotInWebsiteException("user '"
						+ user.getUsername() );
			
		}else{
			if(!member.getMember().getActive()){
				throw new UserNotAcitveException("user '"
						+ user.getUsername() );
			}
		}
		userMng.updateLoginInfo(user.getId(), request.getRemoteAddr());
		rememberMeService.loginSuccess(request, response, member.getMember());
		session.setAttribute(request, response, SESSION_USER_ID_KEY, user.getId());
		session.setAttribute(request, response, SESSION_MEMBER_ID_KEY, member.getId());
		addUsernameCookie(member.getUsername(), null, null, request, response);
		shoppingSvc.addCookie(member, request, response);
		return member;
	}

	public ShopMember getMember(HttpServletRequest request,
			HttpServletResponse response, Website web) {
		ShopMember member = getMemberFromSession(request, response, web);
		return member != null ? member : getMemberFromCookie(request, response,
				web);
	}

	private ShopMember getMemberFromCookie(HttpServletRequest request,
			HttpServletResponse response, Website web) {
		Member coreMember;
		try {
			coreMember = (Member) rememberMeService
					.autoLogin(request, response);
			if (coreMember == null) {
				return null;
			}
		} catch (CookieTheftException e) {
			log.warn("remember me cookie theft: {}", e.getMessage());
			return null;
		}
		// 无法自动登录
		if (coreMember == null) {
			return null;
		}
		Long webId = web.getId();
		Long userId = coreMember.getUser().getId();
		ShopMember member = null;
		// 改变站点
		boolean change = false;
		// 其他站点会员
		if (!coreMember.getWebsite().getId().equals(webId)) {
			coreMember = memberMng.getByUserId(webId, userId);
			change = true;
		}
		if (coreMember == null) {
			// 考虑自动注册
			ShopConfig config = shopConfigMng.findById(webId);
			if (config.getRegisterAuto()) {
				member = shopMemberMng.join(userId, webId, config
						.getRegisterGroup());
				log.debug("shop member auto login. username= {}", member
						.getUsername());
			} else {
				log.debug("shop member not allow auto login.");
			}
		} else {
			member = shopMemberMng.findById(coreMember.getId());
			// 不应该为null，可能在某些地方有错误。
			if (member == null) {
				throw new IllegalStateException(
						"This is JspGou's BUG, ShopMember here should not be null.");
			}
		}
		if (member != null) {
			userMng.updateLoginInfo(userId, request.getRemoteAddr());
			session.setAttribute(request, response, SESSION_USER_ID_KEY, member
					.getMember().getUser().getId());
			session.setAttribute(request, response, SESSION_MEMBER_ID_KEY,
					member.getMember().getId());
//			addUsernameCookie(member.getUsername(), member.getFirstname(),
//					member.getLastname(), request, response);
			addUsernameCookie(member.getUsername(), null, null, request, response);
			if (change) {
				// 不用实现的这么复杂
				// rememberMeService.loginChange(request, response, member
				// .getMember());
			}
		}
		return member;
	}

	private ShopMember getMemberFromSession(HttpServletRequest request,
			HttpServletResponse response, Website web) {
		Long memberId = (Long) session.getAttribute(request,
				SESSION_MEMBER_ID_KEY);
		System.out.println(session.getSessionId(request, response));
		ShopMember member = null;
		Long webId = web.getId();
		if (memberId != null) {
			member = shopMemberMng.findById(memberId);
			// 本站会员
			/*if (member != null && member.getWebsite().getId().equals(webId)) {
				return member;
			}*/
			//全站会员
			if (member != null ) {
				return member;
			}
		}
		Long userId = (Long) session.getAttribute(request, SESSION_USER_ID_KEY);
		if (userId != null) {
			member = shopMemberMng.getByUserId(webId, userId);
			if (member == null) {
				ShopConfig config = shopConfigMng.findById(webId);
				// 自动注册
				if (config.getRegisterAuto()) {
					member = shopMemberMng.join(userId, webId, config
							.getRegisterGroup());
				}
			}
			if (member != null) {
				session.setAttribute(request, response, SESSION_MEMBER_ID_KEY,
						member.getId());
			}
		}
		return member;
	}

	private void addUsernameCookie(String username, String firstname,
			String lastname, HttpServletRequest request,
			HttpServletResponse response) {
		StringBuilder un = new StringBuilder(new String(Base64
				.encodeBase64(username.getBytes())));
		while (un.charAt(un.length() - 1) == '=') {
			un.deleteCharAt(un.length() - 1);
		}
		response.addCookie(createCookie(USERNAME, un.toString(), request));

		StringBuilder fn = new StringBuilder();
		if (!StringUtils.isBlank(firstname)) {
			fn.append(firstname);
		}
		fn.append(":");
		if (!StringUtils.isBlank(lastname)) {
			fn.append(lastname);
		}
		String value = fn.toString();
		fn = new StringBuilder(
				new String(Base64.encodeBase64(value.getBytes())));
		while (fn.charAt(fn.length() - 1) == '=') {
			fn.deleteCharAt(fn.length() - 1);
		}
		Cookie c = createCookie(FULLNAME, fn.toString(), request);
		c.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(c);
	}

	private void deleteUsernameCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie username = createCookie(USERNAME, null, request);
		username.setMaxAge(0);
		Cookie fullname = createCookie(FULLNAME, null, request);
		fullname.setMaxAge(0);
		response.addCookie(username);
		response.addCookie(fullname);
	}

	private Cookie createCookie(String name, String value,
			HttpServletRequest request) {
		Cookie cookie = new Cookie(name, value);
		String ctx = request.getContextPath();
		cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
		return cookie;
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		rememberMeService.logout(request, response);
		session.logout(request, response);
		clearCookie(request, response);
	}

	public void clearCookie(HttpServletRequest request,
			HttpServletResponse response) {
		shoppingSvc.clearCookie(request, response);
		deleteUsernameCookie(request, response);
	}

	public ShopAdmin adminLogin(HttpServletRequest request,
			HttpServletResponse response, Website web, String username,
			String password) throws UsernameNotFoundException,
			BadCredentialsException, UserNotInWebsiteException {
		Long webId = web.getId();
		// 先退出登录
		logout(request, response);
		User user = login(username, password);
		ShopAdmin admin = shopAdminMng.getByUserId(user.getId(), webId);
		if (admin == null) {
			throw new UserNotInWebsiteException("user '" + user.getUsername()
					+ "' not in Website '" + webId + "'");
		}
		userMng.updateLoginInfo(user.getId(), request.getRemoteAddr());
		session.setAttribute(request, response, SESSION_USER_ID_KEY, user
				.getId());
		session.setAttribute(request, response, SESSION_ADMIN_ID_KEY, admin
				.getId());
		addUsernameCookie(admin.getUsername(), null, null, request, response);
		return admin;
	}

	public ShopAdmin getAdmin(HttpServletRequest request,
			HttpServletResponse response, Website web) {
		Long webId = web.getId();
		Long adminId = (Long) session.getAttribute(request,
				SESSION_ADMIN_ID_KEY);

		ShopAdmin admin;
		if (adminId != null) {
			admin = shopAdminMng.findById(adminId);
			if (admin != null && admin.getWebsite().getId().equals(webId)) {
				// 本站管理员
				return admin;
			} else {
				// 其他站点跳转而来
				Long userId = admin.getAdmin().getUser().getId();
				admin = shopAdminMng.getByUserId(userId, webId);
				return admin;
			}
		}
		return null;
	}

	private User login(String username, String password)
			throws UsernameNotFoundException, BadCredentialsException {
		User user = userMng.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: "
					+ username);
		}
		if (!pwdEncoder.isPasswordValid(user.getPassword(), password)) {
			throw new BadCredentialsException("password invalid!");
		}
		return user;
	}

	private ShopMemberMng shopMemberMng;
	private ShopAdminMng shopAdminMng;
	private UserMng userMng;
	private MemberMng memberMng;
	private ShopConfigMng shopConfigMng;
	private PwdEncoder pwdEncoder;
	private RememberMeService rememberMeService;
	private ShoppingSvc shoppingSvc;
	private SessionProvider session;

	@Autowired
	public void setShopMemberMng(ShopMemberMng shopMemberMng) {
		this.shopMemberMng = shopMemberMng;
	}

	@Autowired
	public void setShopConfigMng(ShopConfigMng shopConfigMng) {
		this.shopConfigMng = shopConfigMng;
	}

	@Autowired
	public void setShopAdminMng(ShopAdminMng shopAdminMng) {
		this.shopAdminMng = shopAdminMng;
	}

	@Autowired
	public void setUserMng(UserMng userMng) {
		this.userMng = userMng;
	}

	@Autowired
	public void setPwdEncoder(PwdEncoder pwdEncoder) {
		this.pwdEncoder = pwdEncoder;
	}

	@Autowired
	public void setRememberMeService(RememberMeService rememberMeService) {
		this.rememberMeService = rememberMeService;
	}

	@Autowired
	public void setMemberMng(MemberMng memberMng) {
		this.memberMng = memberMng;
	}

	@Autowired
	public void setSessionProvider(SessionProvider session) {
		this.session = session;
	}

	@Autowired
	public void setShoppingSvc(ShoppingSvc shoppingSvc) {
		this.shoppingSvc = shoppingSvc;
	}

}
