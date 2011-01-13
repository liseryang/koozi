package be.koozi.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class OptionValueDaoJpaImpl extends EntityDaoJpaImpl<OptionValue, Long> implements OptionValueDao {

	@Autowired
	public OptionValueDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(OptionValue.class, jpaTemplate);
	}

	@Override
	public List<OptionValue> findByCartItem(final Long cartItemId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cartItemId", cartItemId);
		return find(params);
	}
}
