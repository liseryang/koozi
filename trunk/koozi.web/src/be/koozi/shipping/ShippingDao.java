package be.koozi.shipping;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface ShippingDao extends EntityDao<Shipping, Long> {
	List<Shipping> findByOrder(String orderId);
}
