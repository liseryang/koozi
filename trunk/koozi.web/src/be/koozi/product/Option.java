package be.koozi.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	Price price;
	int stock;
	String type;
	int productId;

	public Option(Price price, int stock, String type, int productId) {
		super();
		this.price = price;
		this.stock = stock;
		this.type = type;
		this.productId = productId;
	}

	public int getStock() {
		return stock;
	}

	public String getType() {
		return type;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}
}