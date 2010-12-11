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

	Currency currency;

	Long productId;

	public Price(BigDecimal amount, Currency currency, Long productId) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.productId = productId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getId() {
		return id;
	}
	
}