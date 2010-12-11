package be.koozi.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;

	private String email;
	private String nickname;
	private String forename;
	private String surname;
	private boolean enabled;

	public User(String userId, String nickname, String email) {
		this.userId = userId;
		this.nickname = nickname;
		this.forename = null;
		this.surname = null;
		this.email = email;
		this.enabled = true;
	}

	public User(String userId, String nickname, String email, String forename, String surname, boolean enabled) {
		this.userId = userId;
		this.nickname = nickname;
		this.email = email;
		this.forename = forename;
		this.surname = surname;
		this.enabled = enabled;
	}

	public String getUserId() {
		return userId;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getForename() {
		return forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
