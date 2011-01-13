package be.koozi.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class OrderItemOptionValueDaoJpaImpl extends EntityDaoJpaImpl<OrderItemOptionValue, Long> implements OrderItemOptionValueDao {

	@Autowired
	public OrderItemOptionValueDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(OrderItemOptionValue.class, jpaTemplate);
	}

	@Override
	public List<OrderItemOptionValue> findByOrderItem(final Long orderItemId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderItemId", orderItemId);
		return find(params);
	}
}
