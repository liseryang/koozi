package be.koozi.gae.security;

import org.springframework.security.core.context.SecurityContextHolder;

import be.koozi.users.User;

public class AuthenticatedUserDetails {
	/**
	 * Get the user name of the logged in user.
	 * 
	 * @return the user name of the user
	 */
	public User getPrincipal() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (obj instanceof User) {
				return ((User) obj);
			} else {
				return null;
			}
		} else
			return null;

	}
}
