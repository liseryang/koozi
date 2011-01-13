package be.koozi.product;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface OptionDao extends EntityDao<Option, Long> {
	List<Option> findByProduct(Long productId);

	List<Option> findByOption(final Long optionId);
}
