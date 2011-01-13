package be.koozi.customer;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface CustomerDao extends EntityDao<Customer, Long> {
	List<Customer> findByOrder(String orderId);
}
