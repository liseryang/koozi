package be.koozi.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class CartDaoJpaImpl extends EntityDaoJpaImpl<Cart, Long> implements CartDao {

	@Autowired
	public CartDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Cart.class, jpaTemplate);
	}
}
