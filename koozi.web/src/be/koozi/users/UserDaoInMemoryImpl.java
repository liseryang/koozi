package be.koozi.users;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.util.Assert;


/**
 * @author Luke Taylor
 */
public class UserDaoInMemoryImpl implements UserDao {
    private final Logger logger = Logger.getLogger(getClass().getName());

    private final Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());

    public User findUser(String userId) {
        return users.get(userId);
    }

    public void registerUser(User newUser) {
        logger.fine("Attempting to create new user " + newUser);

        Assert.state(!users.containsKey(newUser.getUserId()));

        users.put(newUser.getUserId(), newUser);
    }

    public void removeUser(String userId) {
        users.remove(userId);
    }
}
