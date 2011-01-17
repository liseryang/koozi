package be.koozi.order;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Embedded
	@OneToOne(cascade = CascadeType.ALL)
	Value price;
	
	Long productId;
	String orderId;
	Long amount;

	public OrderItem(Long productId, String orderId, Long amount, Value price) {
		this.orderId = orderId;
		this.productId = productId;
		this.amount = amount;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public Value getPrice() {
		return price;
	}

	public String getOrderId() {
		return orderId;
	}

}
