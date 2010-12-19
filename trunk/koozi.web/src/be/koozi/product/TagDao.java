package be.koozi.product;

import java.util.Collection;

import be.koozi.entity.EntityDao;

public interface TagDao extends EntityDao<Tag, Long> {

	Collection<Tag> findByProduct(Long productId);

	Collection<Tag> findByTagValue(String tag);

	Collection<String> findAllValues();
}
