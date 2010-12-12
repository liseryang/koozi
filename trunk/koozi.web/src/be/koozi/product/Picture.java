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

	Long productId;

	public Picture(String href, Long productId) {
		super();
		this.href = href;
		this.productId = productId;
	}

	public String getHref() {
		return href;
	}

	
	public void setHref(String href) {
		this.href = href;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getId() {
		return id;
	}
	
}