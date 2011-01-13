package be.koozi.customer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)
	Address address;

	@OneToOne(cascade = CascadeType.ALL)
	Company company;

	@OneToOne(cascade = CascadeType.ALL)
	Email email;

	@OneToOne(cascade = CascadeType.ALL)
	Identity identity;

	@OneToOne(cascade = CascadeType.ALL)
	PhoneNumber phoneNumber;

	String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer() {
	};

	public Customer(Long id, Address address, Company company, Email email, Identity identity, PhoneNumber phoneNumber) {
		super();
		this.id = id;
		this.address = address;
		this.company = company;
		this.email = email;
		this.identity = identity;
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}
}
