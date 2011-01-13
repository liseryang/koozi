package be.koozi.payment;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface PaymentDao extends EntityDao<Payment, Long> {
	List<Payment> findByOrder(String orderId);
}
