package be.koozi.order;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Order {
	@Id
	String id;

	Date timestamp;

	String shippingMethod;

	String paymentMethod;
	
	@OneToOne(fetch = FetchType.EAGER)
	@Embedded
	Value price;
	
	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Order() {
		super();
		timestamp = new Date(System.currentTimeMillis());
	}

	public String getId() {
		return id;
	}

	public Order(String id) {
		this.id = id;
		timestamp = new Date(System.currentTimeMillis());
	}

	public Value getPrice() {
		return price;
	}

	public void setPrice(Value price) {
		this.price = price;
	}
}
