package be.koozi.shipping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class KialaShippingDaoJpaImpl extends EntityDaoJpaImpl<KialaShipping, Long> implements KialaShippingDao {

	@Autowired
	public KialaShippingDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(KialaShipping.class, jpaTemplate);
	}

	@Override
	public List<KialaShipping> findByOrder(final String orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return find(params);
	}

}
