package be.koozi.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class UserDaoJpaImpl extends EntityDaoJpaImpl<User, String> implements UserDao {

	@Autowired
	public UserDaoJpaImpl(  JpaTemplate jpaTemplate) {
		super(User.class, jpaTemplate);
	}
	
	
	public User find(final String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return find(params).iterator().next();
	}
	
}
