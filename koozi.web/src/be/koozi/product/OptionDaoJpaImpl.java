package be.koozi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class OptionDaoJpaImpl extends EntityDaoJpaImpl<Option, Long> implements OptionDao {

	@Autowired
	public OptionDaoJpaImpl( JpaTemplate jpaTemplate) {
		super(Option.class, jpaTemplate);
	}
}
