package be.koozi.product;

import java.util.Collection;
import java.util.Currency;

import be.koozi.entity.EntityDao;

public interface PriceDao extends EntityDao<Price, Long> {
	Collection<Price> findByProduct(Long productId);

	Collection<Price> findByOption(Long optionId);

	Price findByProduct(Currency currency, Long productId);

	Price findByOption(Currency currency, Long optionId);
}
