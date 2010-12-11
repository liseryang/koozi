package be.koozi.gae.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import be.koozi.users.Role;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

public class GaeAuthenticationFilter extends GenericFilterBean {
	private static final String REGISTRATION_URL = "/admin/users/new";

	private final Logger logger = Logger.getLogger(getClass().getName());

	private final AuthenticationDetailsSource ads = new WebAuthenticationDetailsSource();
	private AuthenticationManager authenticationManager;
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			User googleUser = UserServiceFactory.getUserService().getCurrentUser();

			if (googleUser != null) {
				logger.fine("Currently logged on to GAE as user " + googleUser);
				logger.fine("Authenticating to Spring Security");
				// User has returned after authenticating via GAE. Need to
				// authenticate through Spring Security.
				PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(googleUser, null);
				token.setDetails(ads.buildDetails((HttpServletRequest) request));

				try {
					authentication = authenticationManager.authenticate(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);

					if (authentication.getAuthorities().contains(Role.ROLE_NEW_USER)) {
						logger.fine("New user authenticated. Redirecting to registration page");
						((HttpServletResponse) response).sendRedirect(REGISTRATION_URL);

						return;
					}

				} catch (AuthenticationException e) {
					SecurityContextHolder.getContext().setAuthentication(null);
					failureHandler.onAuthenticationFailure((HttpServletRequest) request, (HttpServletResponse) response, e);

					return;
				}
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void afterPropertiesSet() throws ServletException {
		Assert.notNull(authenticationManager, "AuthenticationManager must be set");
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
		this.failureHandler = failureHandler;
	}
}
