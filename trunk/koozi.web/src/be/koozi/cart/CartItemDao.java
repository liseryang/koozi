package be.koozi.cart;

import java.util.Collection;
import java.util.List;

import be.koozi.entity.EntityDao;

public interface CartItemDao extends EntityDao<CartItem, Long> {
	List<CartItem> findByCart(String cartId);
}
