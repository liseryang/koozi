package be.koozi.sms;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class SMSResult {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	Long id;

    @Enumerated(EnumType.STRING)
	SMSStatusCode statusCode;
	String statusMessage;
	String callId;
	Date timestamp;
	Long smsId;

	public SMSResult(SMSStatusCode statusCode, String statusMessage, String callId, Long smsId) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.callId = callId;
		this.smsId = smsId;
		this.timestamp = new Date(System.currentTimeMillis());
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public SMSStatusCode getStatusCode() {
		return statusCode;
	}

	public String getCallId() {
		return callId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Long getSmsId() {
		return smsId;
	}
}
