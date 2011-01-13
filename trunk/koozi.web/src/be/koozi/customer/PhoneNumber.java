package be.koozi.customer;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {
	String value;

	public PhoneNumber() {
	}

	public PhoneNumber(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
