package be.koozi.customer;

import javax.persistence.Embeddable;

@Embeddable
public class Email {
	String value;

	public Email() {
	}

	public Email(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
