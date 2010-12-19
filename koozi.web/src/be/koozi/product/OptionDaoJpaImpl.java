package be.koozi.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class OptionDaoJpaImpl extends EntityDaoJpaImpl<Option, Long> implements OptionDao {

	@Autowired
	public OptionDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Option.class, jpaTemplate);
	}

	@Override
	public List<Option> findByProduct(final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		return find(params);
	}
}
