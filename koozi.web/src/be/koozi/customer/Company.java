package be.koozi.customer;

import javax.persistence.Embeddable;

@Embeddable
public class Company {
	String name;
	String enterpriseNumber;

	public Company() {
	}

	public Company(String name, String enterpriseNumber) {
		super();
		this.name = name;
		this.enterpriseNumber = enterpriseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnterpriseNumber() {
		return enterpriseNumber;
	}

	public void setEnterpriseNumber(String enterpriseNumber) {
		this.enterpriseNumber = enterpriseNumber;
	}
}
