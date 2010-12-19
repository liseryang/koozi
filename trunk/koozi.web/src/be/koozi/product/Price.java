package be.koozi.product;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	BigDecimal amount;

	Long productId;

	Long optionId;
	
	String currencyCode;

	public Price(BigDecimal amount, Currency currency, Long productId) {
		super();
		this.amount = amount;
		this.currencyCode = currency.getCurrencyCode();
		this.productId = productId;
	}


	public Price(BigDecimal amount, Currency currency, Long optionId, boolean option) {
		super();
		this.amount = amount;
		this.currencyCode = currency.getCurrencyCode();
		this.optionId = optionId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return Currency.getInstance(currencyCode);
	}

	public Long getProductId() {
		return productId;
	}

	public Long getId() {
		return id;
	}
}