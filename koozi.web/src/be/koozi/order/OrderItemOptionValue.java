package be.koozi.order;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderItemOptionValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	Long orderItemId;
	String value;
	Long optionId;

	@Embedded
	@OneToOne(cascade = CascadeType.ALL)
	Value price;

	public OrderItemOptionValue(String value, Long orderItemId, Long optionId, Value price) {
		super();
		this.value = value;
		this.optionId = optionId;
		this.orderItemId = orderItemId;
		this.price = price;
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

	public Long getOrderItemId() {
		return orderItemId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public Value getPrice() {
		return price;
	}

}