package be.koozi.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String href;

	String description;

	String productId;

	public Picture(String href, String description, String productId) {
		super();
		this.href = href;
		this.description = description;
		this.productId = productId;
	}

	public String getHref() {
		return href;
	}

	public String getDescription() {
		return description;
	}

	public String getProductId() {
		return productId;
	}

	public Long getId() {
		return id;
	}
	
}