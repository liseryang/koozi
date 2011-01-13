package be.koozi.shipping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.koozi.customer.Address;

@Entity
public class Shipping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String orderId;

	boolean shipToBillingAddress;

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isShipToBillingAddress() {
		return shipToBillingAddress;
	}

	public void setShipToBillingAddress(boolean shipToBillingAddress) {
		this.shipToBillingAddress = shipToBillingAddress;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
