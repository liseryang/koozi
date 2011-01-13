package be.koozi.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class PaymentDaoJpaImpl extends EntityDaoJpaImpl<Payment, Long> implements PaymentDao {

	@Autowired
	public PaymentDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Payment.class, jpaTemplate);
	}

	@Override
	public List<Payment> findByOrder(final String orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return find(params);
	}

}
