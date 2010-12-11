package be.koozi.gae.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import be.koozi.users.Role;
import be.koozi.users.User;
import be.koozi.users.UserDao;
import be.koozi.users.UserRoleDao;

/**
 * A simple authentication provider which interacts with {@code User} returned
 * by the GAE {@code UserService}, and also the local persistent {@code
 * UserRegistry} to build an application user principal.
 * <p>
 * If the user has been authenticated through google accounts, it will check if
 * they are already registered and either load the existing user information or
 * assign them a temporary identity with limited access until they have
 * registered.
 * <p>
 * If the account has been disabled, a {@code DisabledException} will be raised.
 * 
 * @author Luke Taylor
 */
public class GoogleAccountsAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private UserDao userDao;
	private UserRoleDao userRoleDao;

	public GoogleAccountsAuthenticationProvider() {
	}

	@Autowired
	public GoogleAccountsAuthenticationProvider(UserDao userDao, UserRoleDao userRoleDao) {
		this.userDao = userDao;
		this.userRoleDao = userRoleDao;
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		com.google.appengine.api.users.User googleUser = (com.google.appengine.api.users.User) authentication.getPrincipal();
		Collection<GrantedAuthority> roles = null;
		
		User user = null;
		try{
			user = userDao.find(googleUser.getUserId());
		}catch(Throwable e)
		{
			e.printStackTrace();
		}

		if (user == null) {
			roles = new HashSet<GrantedAuthority>();
			roles.add(Role.ROLE_NEW_USER);

			// User not in registry. Needs to register
			user = new User(googleUser.getUserId(), googleUser.getNickname(), googleUser.getEmail(), null, null, true);
		} else {
			roles = userRoleDao.findRoles(user.getUserId());
		}

		// if (!user.isEnabled()) {
		// throw new DisabledException("Account is disabled");
		// }

		return new GaeUserAuthentication(user, roles, authentication.getDetails());
	}

	/**
	 * Indicate that this provider only supports
	 * PreAuthenticatedAuthenticationToken (sub)classes.
	 */
	public final boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setUserRegistry(UserDao userRegistry) {
		this.userDao = userRegistry;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}
}
