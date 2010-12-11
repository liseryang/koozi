package be.koozi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class PriceDaoJpaImpl extends EntityDaoJpaImpl<Price, Long> implements PriceDao {

	@Autowired
	public PriceDaoJpaImpl( JpaTemplate jpaTemplate) {
		super(Price.class, jpaTemplate);
	}
}
