package com.jspgou.core.security;


import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.core.entity.Member;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.MemberMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.web.CmsThreadVariable;
import com.jspgou.core.web.Digests;
import com.jspgou.core.web.Encodes;

/**
 * 自定义DB Realm
 * 
 */
public class CmsAuthorizingRealm extends AuthorizingRealm {
	private static final int INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static final String ALGORITHM = "SHA-1";
	/**
	 * 登录认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = cmsUserMng.getByUsername(token.getUsername());
		if (user != null) {
			User ser = cmsUserMng.findById(user.getId());
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		Member member = memberMng.getByUsername(username);
		Website site=CmsThreadVariable.getSite();
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
//		if (member != null) {
//			Set<String>viewPermissionSet=new HashSet<String>();
//			Set<String> perms = user.getPerms(site.getId(),viewPermissionSet);
//			if (!CollectionUtils.isEmpty(perms)) {
//				// 权限加入AuthorizationInfo认证对象
//				auth.setStringPermissions(perms);
//			}
//		}
		return auth;
	}
	
	public void removeUserAuthorizationInfoCache(String username){
		  SimplePrincipalCollection pc = new SimplePrincipalCollection();
		  pc.add(username, super.getName()); 
		  super.clearCachedAuthorizationInfo(pc);
	}
	public static class HashPassword {
		public String salt;
		public String password;
	}
	public static HashPassword encryptPassword(String plainPassword) {
		HashPassword result = new HashPassword();
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		result.salt = Encodes.encodeHex(salt);

		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, INTERATIONS);
		result.password = Encodes.encodeHex(hashPassword);
		return result;
	}

	protected UserMng cmsUserMng;
	protected MemberMng memberMng;
	@Autowired
	public void setMemberMng(MemberMng memberMng) {
		this.memberMng = memberMng;
	}

	@Autowired
	public void setUserMng(UserMng cmsUserMng) {
		this.cmsUserMng = cmsUserMng;
	}


}
