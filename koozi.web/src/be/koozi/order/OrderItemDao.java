package be.koozi.order;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface OrderItemDao extends EntityDao<OrderItem, Long> {
	List<OrderItem> findByOrder(String orderId);
}
