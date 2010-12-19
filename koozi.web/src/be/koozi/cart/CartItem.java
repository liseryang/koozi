package be.koozi.cart;

import java.util.List;

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
	List<Long> options; 
	
	public CartItem(Long productId, String cartId, Long amount)
	{
		this.cartId = cartId;
		this.productId = productId;
		this.amount = amount;
	}

	public Long getProductId() {
		return productId;
	}
	
	public String getCartId()
	{
		return cartId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public List<Long> getOptions() {
		return options;
	}

	public void setOptions(List<Long> options) {
		this.options = options;
	}

	public Long getId() {
		return id;
	}
	
	
}
