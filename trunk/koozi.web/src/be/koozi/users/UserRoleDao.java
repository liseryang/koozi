package be.koozi.users;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import be.koozi.entity.EntityDao;

public interface UserRoleDao extends EntityDao<UserRole, Long> {
	List<UserRole> findByUser(String userId);

	Collection<GrantedAuthority> findRoles(String userId);

}
