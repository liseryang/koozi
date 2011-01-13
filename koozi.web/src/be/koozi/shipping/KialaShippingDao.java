package be.koozi.shipping;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface KialaShippingDao extends EntityDao<KialaShipping, Long> {
	List<KialaShipping> findByOrder(String orderId);
}
