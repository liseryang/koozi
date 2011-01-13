package be.koozi.customer;

import javax.persistence.Embeddable;

@Embeddable
public class Identity {
	String nickName;
	String name;
	String firstName;

	public Identity() {

	}

	public Identity(String nickName, String name, String firstName) {
		super();
		this.nickName = nickName;
		this.name = name;
		this.firstName = firstName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
