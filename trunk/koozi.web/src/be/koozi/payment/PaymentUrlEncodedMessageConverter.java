package be.koozi.payment;

import org.springframework.util.MultiValueMap;

import be.koozi.tools.AbstractUrlEncodedMessageConverter;

public class PaymentUrlEncodedMessageConverter extends AbstractUrlEncodedMessageConverter<Payment> {

	public PaymentUrlEncodedMessageConverter() {
		super(Payment.class);
	}

	@Override
	public Payment readInternal(MultiValueMap<String, String> values) {
		Payment payment = new Payment();

		try {
			Long id = 0L;
			id = new Long(values.getFirst("id"));
			payment.setId(id);
		} catch (Throwable t) {
		}

		return payment;
	}
}
