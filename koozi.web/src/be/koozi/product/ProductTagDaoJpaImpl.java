package be.koozi.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class ProductTagDaoJpaImpl extends EntityDaoJpaImpl<ProductTag, Long> implements ProductTagDao {

	@Autowired
	public ProductTagDaoJpaImpl(  JpaTemplate jpaTemplate) {
		super(ProductTag.class, jpaTemplate);
	}

	@Override
	public Collection<ProductTag> findByProduct(final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		return find(params);
	}

	@Override
	public Collection<ProductTag> findByTagValue(final String tag) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tag", tag);
		return find(params);
	}

	@Override
	public Collection<String> findAllValues() {
		Collection<String> result = new LinkedList<String>();
		Collection<ProductTag> allTags = super.findAll();
		for (Iterator<ProductTag> iterator = allTags.iterator(); iterator.hasNext();) {
			ProductTag productTag = (ProductTag) iterator.next();
			result.add(productTag.getTag());
		}
		return result;
	}
}
