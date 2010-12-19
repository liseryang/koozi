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
public class TagDaoJpaImpl extends EntityDaoJpaImpl<Tag, Long> implements TagDao {

	@Autowired
	public TagDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Tag.class, jpaTemplate);
	}

	@Override
	public Collection<Tag> findByProduct(final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		return find(params);
	}

	@Override
	public Collection<Tag> findByTagValue(final String value) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", value);
		return find(params);
	}

	@Override
	public Collection<String> findAllValues() {
		Collection<String> result = new LinkedList<String>();
		Collection<Tag> allTags = super.findAll();
		for (Iterator<Tag> iterator = allTags.iterator(); iterator.hasNext();) {
			Tag productTag = (Tag) iterator.next();
			result.add(productTag.getValue());
		}
		return result;
	}
}
