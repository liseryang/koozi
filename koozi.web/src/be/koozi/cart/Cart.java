package be.koozi.cart;

import javax.persistence.Id;

public class Cart {
	@Id
	String id;

	public Cart(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
