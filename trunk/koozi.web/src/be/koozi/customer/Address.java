package be.koozi.customer;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	String streetName;
	String streetNumber;
	String postCode;
	String city;
	String countryCode;

	public Address() {
	}

	public Address(String streetName, String streetNumber, String postCode, String city, String countryCode) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.postCode = postCode;
		this.city = city;
		this.countryCode = countryCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
