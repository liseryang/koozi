package be.koozi.cart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OptionValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	Long cartItemId;
	String value;
	Long optionId;

	public OptionValue(String value, Long cartItemId, Long optionId) {
		super();
		this.value = value;
		this.optionId = optionId;
		this.cartItemId = cartItemId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public Long getOptionId() {
		return optionId;
	}

}