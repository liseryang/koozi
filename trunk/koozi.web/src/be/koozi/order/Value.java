package be.koozi.order;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import be.koozi.product.Price;

@Embeddable
public class Value {
	
	BigDecimal amount;

	String currencyCode;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

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
