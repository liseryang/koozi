package be.koozi.product;

import java.util.Collection;

import be.koozi.entity.EntityDao;

public interface MetadataDao extends EntityDao<Metadata, Long> {
	Collection<Metadata> findByProduct(Long productId);

	Metadata findByProduct(String localeId, Long productId);
}
