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
public class MetadataDaoJpaImpl extends EntityDaoJpaImpl<Metadata, Long> implements MetadataDao {

	@Autowired
	public MetadataDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Metadata.class, jpaTemplate);
	}

	@Override
	public Collection<Metadata> findByProduct(final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		return find(params);
	}

	@Override
	public Metadata findByProduct(final String localeId, final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("locale", localeId);
		List<Metadata> all = find(params);
		if (all.size() > 0)
			return all.get(0);
		else
			return null;
	}
}
