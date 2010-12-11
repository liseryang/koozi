package be.koozi.product;

import java.util.Collection;

import be.koozi.entity.EntityDao;

public interface ProductTagDao extends EntityDao<ProductTag, Long> {

	Collection<ProductTag> findByProduct(Long productId);

	Collection<ProductTag> findByTagValue(String tag);

	Collection<String> findAllValues();
}
