package be.koozi.order;

import java.util.HashMap;
import java.util.Map;

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
	
	@Override
	public Order find(final String entityId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", entityId);
		return find(params).get(0);
	}
}
