package be.koozi.product;

import java.util.Collection;
import java.util.Currency;

import be.koozi.entity.EntityDao;

public interface PriceDao extends EntityDao<Price, Long> {
	Collection<Price> findByProduct(Long productId);

	Price findByProduct(Currency currency, Long productId);
}
