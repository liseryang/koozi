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

	int stock;
	String value;
	long productId;
	String key;
	String type;

	public Option(int stock, String key, String type, String value, long productId) {
		super();
		this.stock = stock;
		this.value = value;
		this.type = type;
		this.key = key;
		this.productId = productId;
	}

	public int getStock() {
		return stock;
	}

	public String getValue() {
		return value;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getProductId() {
		return productId;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setValue(String type) {
		this.value = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}