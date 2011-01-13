package be.koozi.order;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Embeddable;

import be.koozi.product.Price;

@Embeddable
public class Value {

	BigDecimal amount;

	String currencyCode;

	public Value(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currencyCode = currency.getCurrencyCode();
	}

	public Value(Price price) {
		this.amount = price.getAmount();
		this.currencyCode = price.getCurrency().getCurrencyCode();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return Currency.getInstance(currencyCode);
	}

}
