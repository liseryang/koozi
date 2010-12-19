package be.koozi.tools;

import java.util.Currency;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

public class CookieCurrencyResolver extends CookieGenerator implements CurrencyResolver {

	public static final String CURRENCY_REQUEST_ATTRIBUTE_NAME = CookieCurrencyResolver.class.getName() + ".CURRENCY";

	public static final String DEFAULT_COOKIE_NAME = CookieCurrencyResolver.class.getName() + ".CURRENCY";

	private Currency defaultCurrency;

	public CookieCurrencyResolver() {
		setCookieName(DEFAULT_COOKIE_NAME);
	}

	public void setDefaultCurrency(Currency defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	protected Currency getDefaultCurrency() {
		return this.defaultCurrency;
	}

	public Currency resolveCurrency(HttpServletRequest request) {
		// Check request for pre-parsed or preset locale.
		Currency currency = (Currency) request.getAttribute(CURRENCY_REQUEST_ATTRIBUTE_NAME);
		if (currency != null) {
			return currency;
		}

		// Retrieve and parse cookie value.
		Cookie cookie = WebUtils.getCookie(request, getCookieName());
		if (cookie != null) {
			currency = Currency.getInstance(cookie.getValue());
			if (logger.isDebugEnabled()) {
				logger.debug("Parsed cookie value [" + cookie.getValue() + "] into currency '" + currency + "'");
			}
			if (currency != null) {
				request.setAttribute(CURRENCY_REQUEST_ATTRIBUTE_NAME, currency);
				return currency;
			}
		}

		return getDefaultCurrency();
	}

	public void setCurrency(HttpServletRequest request, HttpServletResponse response, Currency currency) {
		if (currency != null) {
			// Set request attribute and add cookie.
			request.setAttribute(CURRENCY_REQUEST_ATTRIBUTE_NAME, currency);
			addCookie(response, currency.getCurrencyCode());
		} else {
			// Set request attribute to fallback locale and remove cookie.
			request.setAttribute(CURRENCY_REQUEST_ATTRIBUTE_NAME, getDefaultCurrency());
			removeCookie(response);
		}
	}

}
