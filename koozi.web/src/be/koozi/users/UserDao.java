package be.koozi.users;

import be.koozi.entity.EntityDao;

public interface UserDao extends EntityDao<User, String> {

	User find(final String userId);
}
