package be.koozi.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class PictureDaoJpaImpl extends EntityDaoJpaImpl<Picture, Long> implements PictureDao {

	@Autowired
	public PictureDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Picture.class, jpaTemplate);
	}

	@Override
	public Collection<Picture> findByProduct(final Long productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		return find(params);
	}
}
