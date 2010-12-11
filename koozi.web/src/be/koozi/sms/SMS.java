package be.koozi.sms;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SMS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String content;
	
	@Embedded
	@OneToOne(cascade=CascadeType.ALL)
	PhoneNumber phoneNumber;
	String userId;
	@Embedded
	@OneToOne(cascade=CascadeType.ALL)
	SMSResult smsResult;

	public SMS(String content, PhoneNumber number) {
		this.content = content;
		this.phoneNumber = number;
	}

	public SMS(String content, PhoneNumber number, String userId) {
		super();
		this.content = content;
		this.phoneNumber = number;
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public PhoneNumber getNumber() {
		return phoneNumber;
	}

	public String getSenderUserId() {
		return userId;
	}

	public void setSenderUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public SMSResult getSmsResult() {
		return smsResult;
	}

	public void setSmsResult(SMSResult smsResult) {
		this.smsResult = smsResult;
	}

}
