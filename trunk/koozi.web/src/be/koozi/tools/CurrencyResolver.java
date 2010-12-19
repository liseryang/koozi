package be.koozi.tools;

import java.util.Currency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CurrencyResolver {

	Currency resolveCurrency(HttpServletRequest request);

	void setCurrency(HttpServletRequest request, HttpServletResponse response, Currency currency);

}
