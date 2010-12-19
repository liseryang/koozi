package be.koozi.cart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

public class CookieCartResolver extends CookieGenerator implements CartResolver {

	public static final String CART_REQUEST_ATTRIBUTE_NAME = CookieCartResolver.class.getName() + ".CART";

	public static final String DEFAULT_COOKIE_NAME = CookieCartResolver.class.getName() + ".CART";

	public CookieCartResolver() {
		setCookieName(DEFAULT_COOKIE_NAME);
	}

	public String resolveCartId(HttpServletRequest request) {
		// Check request for pre-parsed or preset locale.
		String cartId = (String) request.getAttribute(CART_REQUEST_ATTRIBUTE_NAME);
		if (cartId != null) {
			return cartId;
		}

		// Retrieve and parse cookie value.
		Cookie cookie = WebUtils.getCookie(request, getCookieName());
		if (cookie != null) {
			cartId = cookie.getValue();
			if (logger.isDebugEnabled()) {
				logger.debug("Parsed cookie value [" + cookie.getValue() + "] into cartId '" + cartId + "'");
			}
			if (cartId != null) {
				request.setAttribute(CART_REQUEST_ATTRIBUTE_NAME, cartId);
				return cartId;
			}
		}

		return null;
	}

	public void setCartId(HttpServletRequest request, HttpServletResponse response, String cartId) {
		// Set request attribute and add cookie.
		request.setAttribute(CART_REQUEST_ATTRIBUTE_NAME, cartId);
		addCookie(response, cartId);
	}

}
