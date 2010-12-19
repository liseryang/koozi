package be.koozi.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlType
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	Role role;
	String userId;

	public UserRole(Role role, String userId) {
		this.userId = userId;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
