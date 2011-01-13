package be.koozi.shipping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class ShippingDaoJpaImpl extends EntityDaoJpaImpl<Shipping, Long> implements ShippingDao {

	@Autowired
	public ShippingDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Shipping.class, jpaTemplate);
	}

	@Override
	public List<Shipping> findByOrder(final String orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return find(params);
	}

}
