package be.koozi.sms;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {
	
	@Basic
	private String number;

	public PhoneNumber(String number) {
		if (number.indexOf('+') != -1)
			number = number.substring(number.indexOf('+') + 1);
		if (number.indexOf('0') != -1 && number.indexOf('0') == 0)
			number = number.substring(number.indexOf('0') + 1);
		if (number.indexOf('0') != -1 && number.indexOf('0') == 0)
			number = number.substring(number.indexOf('0') + 1);
		this.number = number;
	}

	public String getNumber() {
		return number;
	}
}
