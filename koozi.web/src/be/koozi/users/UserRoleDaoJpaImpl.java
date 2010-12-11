package be.koozi.users;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
@SuppressWarnings("unchecked")
public class UserRoleDaoJpaImpl extends EntityDaoJpaImpl<UserRole, Long> implements UserRoleDao {

	@Autowired
	public UserRoleDaoJpaImpl( JpaTemplate jpaTemplate) {
		super(UserRole.class, jpaTemplate);
	}

	@Override
	public List<UserRole> findByUser(final String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return find(params);
	}

	@Override
	public Collection<GrantedAuthority> findRoles(String userId) {
		Collection<UserRole> userRoles =  findAll(); //(userId);
			//

		Collection<GrantedAuthority> roles = new LinkedList<GrantedAuthority>();

		for (Iterator iterator = userRoles.iterator(); iterator.hasNext();) {
			UserRole userRole = (UserRole) iterator.next();
			roles.add(userRole.getRole());
		}
		return roles;
	}
}
