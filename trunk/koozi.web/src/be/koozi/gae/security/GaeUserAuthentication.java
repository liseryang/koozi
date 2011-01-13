package be.koozi.gae.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import be.koozi.users.User;

/**
 * Authentication object representing a fully-authenticated user.
 * 
 * @author Luke Taylor
 */
public class GaeUserAuthentication implements Authentication {
	private static final long serialVersionUID = -3064910134369234964L;
	private final be.koozi.users.User principal;
	private final Object details;
	private boolean authenticated;
	private final Collection<GrantedAuthority> authorities;

	public GaeUserAuthentication(User principal, Collection<GrantedAuthority> userRoles, Object details) {
		this.principal = principal;
		this.details = details;
		authenticated = true;
		authorities = userRoles;

	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Object getCredentials() {
		throw new UnsupportedOperationException();
	}

	public Object getDetails() {
		return null;
	}

	public Object getPrincipal() {
		return principal;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		authenticated = isAuthenticated;
	}

	public String getName() {
		return principal.getUserId();
	}

	@Override
	public String toString() {
		return "GaeUserAuthentication{" + "principal=" + principal + ", details=" + details + ", authenticated=" + authenticated + '}';
	}
}
