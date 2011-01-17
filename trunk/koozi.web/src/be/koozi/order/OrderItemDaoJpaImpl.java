package be.koozi.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.bcel.internal.generic.NEW;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class OrderItemDaoJpaImpl extends EntityDaoJpaImpl<OrderItem, Long> implements OrderItemDao {

	@Autowired
	public OrderItemDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(OrderItem.class, jpaTemplate);
	}

	@Override
	public List<OrderItem> findByOrder(final String orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return find(params);
	}
}
