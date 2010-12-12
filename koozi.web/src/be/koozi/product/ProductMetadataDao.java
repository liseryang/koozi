package be.koozi.product;

import java.util.Collection;

import be.koozi.entity.EntityDao;

public interface ProductMetadataDao extends EntityDao<ProductMetadata, Long> {
	Collection<ProductMetadata> findByProduct(Long productId);
	ProductMetadata findByProduct(String localeId, Long productId);
}
