package be.koozi.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class OrderDaoJpaImpl extends EntityDaoJpaImpl<Order, String> implements OrderDao {

	@Autowired
	public OrderDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Order.class, jpaTemplate);
	}
}
