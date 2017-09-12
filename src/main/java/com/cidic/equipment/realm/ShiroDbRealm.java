package com.cidic.equipment.realm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cidic.equipment.exception.CaptchaException;
import com.cidic.equipment.model.PermissionRole;
import com.cidic.equipment.model.User;
import com.cidic.equipment.model.UserRole;
import com.cidic.equipment.service.UserService;
import com.cidic.equipment.servlet.CaptchaServlet;
import com.cidic.equipment.shiro.UsernamePasswordCaptchaToken;

public class ShiroDbRealm extends AuthorizingRealm {
	 
	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;
 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
 
		String username = token.getUsername();
		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}
		
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession()
				.getAttribute(CaptchaServlet.KEY_CAPTCHA);
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
			throw new CaptchaException("��֤�����?");
		}
 
		Optional<User> user = userServiceImpl.findByEmail(username);
		if (!user.isPresent()) {
			throw new UnknownAccountException("No account found for user ["
					+ username + "]");
		}
		return new SimpleAuthenticationInfo(new ShiroUser(user.get().getEmail(),
				user.get().getRealname()), user.get().getPassword(), getName());
 
	}
 
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName())
				.iterator().next();
		Optional<User> user = userServiceImpl.findByEmail(shiroUser.getLoginName());
		if (user.isPresent()) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (UserRole role : user.get().getUserRoles()) {
				
				List<String> permissions = new ArrayList<>();
				for (PermissionRole permissionRole : role.getRole().getPermissionRoles()){
					permissions.add(permissionRole.getPermission().getPermissionName());
				}
				info.addStringPermissions(permissions);
			}
			return info;
		} else {
			return null;
		}
	}
 
	
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
 
	
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
 
	public static class ShiroUser implements Serializable {
 
		private static final long serialVersionUID = -1748602382963711884L;
		private String loginName;
		private String name;
 
		public ShiroUser(String loginName, String name) {
			this.loginName = loginName;
			this.name = name;
		}
 
		public String getLoginName() {
			return loginName;
		}
 
		@Override
		public String toString() {
			return loginName;
		}
 
		public String getName() {
			return name;
		}
	}
}
