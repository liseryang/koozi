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
public class ProductMetadataDaoJpaImpl extends EntityDaoJpaImpl<ProductMetadata, Long> implements ProductMetadataDao {

	@Autowired
	public ProductMetadataDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(ProductMetadata.class, jpaTemplate);
	}

	@Override
	public Collection<ProductMetadata> findByProduct(final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		return find(params);
	}

	@Override
	public ProductMetadata findByProduct(final String localeId, final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("locale", localeId);
		List<ProductMetadata> all = find(params);
		if (all.size() > 0)
			return all.get(0);
		else
			return null;
	}
}
