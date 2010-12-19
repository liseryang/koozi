package be.koozi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class ProductDaoJpaImpl extends EntityDaoJpaImpl<Product, Long> implements ProductDao {

	@Autowired
	public ProductDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(Product.class, jpaTemplate);
	}
}
