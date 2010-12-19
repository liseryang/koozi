package be.koozi.tools;

import java.util.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CurrencyChangeInterceptor extends HandlerInterceptorAdapter {

	public static final String DEFAULT_PARAM_NAME = "currency";

	private String paramName = DEFAULT_PARAM_NAME;

	private CurrencyResolver currencyResolver;

	public CurrencyChangeInterceptor(CurrencyResolver currencyResolver) {
		this.currencyResolver = currencyResolver;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamName() {
		return this.paramName;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

		String newLocale = request.getParameter(this.paramName);
		if (newLocale != null) {
			currencyResolver.setCurrency(request, response, Currency.getInstance(newLocale));
		}
		// Proceed in any case.
		return true;
	}

}
