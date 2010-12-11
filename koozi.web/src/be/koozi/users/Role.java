package be.koozi.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Luke Taylor
 */
public enum Role implements GrantedAuthority {
	ROLE_ADMIN(0, "ROLE_ADMIN"), ROLE_NEW_USER(1, "ROLE_NEW_USER"), ROLE_USER(2, "ROLE_USER"), ROLE_SUPER_USER(3, "ROLE_SUPER_USER");

	private final int bit;

	private final String name;

	/**
	 * Creates an authority with a specific bit representation. It's important
	 * that this doesn't change as it will be used in the database. The enum
	 * ordinal is less reliable as the enum may be reordered or have new roles
	 * inserted which would change the ordinal values.
	 * 
	 * @param bit
	 *            the permission bit which will represent this authority in the
	 *            datastore.
	 */
	Role(int bit, String name) {
		this.bit = bit;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getBit() {
		return bit;
	}

	public String getAuthority() {
		return toString();
	}

	private static final Map<String, Role> lookup = new HashMap<String, Role>();
	static {
		lookup.put(ROLE_ADMIN.getName(), ROLE_ADMIN);
		lookup.put(ROLE_NEW_USER.getName(), ROLE_NEW_USER);
		lookup.put(ROLE_USER.getName(), ROLE_USER);
		lookup.put(ROLE_SUPER_USER.getName(), ROLE_SUPER_USER);
	}

	public static Role get(String name) {
		return lookup.get(name);
	}
}
