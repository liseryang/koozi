package be.koozi.cart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
	@Id
	String id;
	
	public void CartItem(String id)
	{
		this.id = id;
	}

	
}
