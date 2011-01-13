package be.koozi.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class CartItemDaoJpaImpl extends EntityDaoJpaImpl<CartItem, Long> implements CartItemDao {

	@Autowired
	public CartItemDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(CartItem.class, jpaTemplate);
	}

	@Override
	public List<CartItem> findByCart(final String cartId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cartId", cartId);
		return find(params);
	}

}
