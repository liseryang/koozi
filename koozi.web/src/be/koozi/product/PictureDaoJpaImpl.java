package be.koozi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class PictureDaoJpaImpl extends EntityDaoJpaImpl<Picture, Long> implements PictureDao {

	@Autowired
	public PictureDaoJpaImpl( JpaTemplate jpaTemplate) {
		super(Picture.class, jpaTemplate);
	}
}
