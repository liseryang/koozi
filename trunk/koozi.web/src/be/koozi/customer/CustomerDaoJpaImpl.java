package be.koozi.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class CustomerDaoJpaImpl extends EntityDaoJpaImpl<Customer, Long> implements CustomerDao {

	@Autowired
	public CustomerDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Customer.class, jpaTemplate);
	}

	@Override
	public List<Customer> findByOrder(final String orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return find(params);
	}

}
