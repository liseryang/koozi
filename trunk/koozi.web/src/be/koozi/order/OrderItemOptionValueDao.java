package be.koozi.order;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface OrderItemOptionValueDao extends EntityDao<OrderItemOptionValue, Long> {
	List<OrderItemOptionValue> findByOrderItem(Long orderItemId);
}
