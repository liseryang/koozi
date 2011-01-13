package be.koozi.product;

import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class PriceDaoJpaImpl extends EntityDaoJpaImpl<Price, Long> implements PriceDao {

	@Autowired
	public PriceDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Price.class, jpaTemplate);
	}

	@Override
	public Collection<Price> findByProduct(final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		return find(params);
	}

	@Override
	public Collection<Price> findByOption(final Long optionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("optionId", optionId);
		return find(params);
	}

	@Override
	public Price findByProduct(final Currency currency, final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("currencyCode", currency.getCurrencyCode());
		List<Price> all = find(params);
		if (all.size() > 0)
			return all.get(0);
		else
			return null;
	}

	@Override
	public Price findByOption(final Currency currency, final Long optionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("optionId", optionId);
		params.put("currencyCode", currency.getCurrencyCode());
		List<Price> all = find(params);
		if (all.size() > 0)
			return all.get(0);
		else
			return null;
	}
}
