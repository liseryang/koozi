package be.koozi.cart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	Long productId;
	String cartId;
	Long amount;

	public CartItem(Long productId, String cartId, Long amount) {
		this.cartId = cartId;
		this.productId = productId;
		this.amount = amount;
	}

	public Long getProductId() {
		return productId;
	}

	public String getCartId() {
		return cartId;
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

}
