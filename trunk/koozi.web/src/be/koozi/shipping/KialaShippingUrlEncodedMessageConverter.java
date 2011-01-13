package be.koozi.shipping;

import org.springframework.util.MultiValueMap;

import be.koozi.customer.Address;
import be.koozi.tools.AbstractUrlEncodedMessageConverter;

public class KialaShippingUrlEncodedMessageConverter extends AbstractUrlEncodedMessageConverter<KialaShipping> {

	public KialaShippingUrlEncodedMessageConverter() {
		super(KialaShipping.class);
	}

	@Override
	public KialaShipping readInternal(MultiValueMap<String, String> values) {
		KialaShipping shipping = new KialaShipping();

		Long id = 0L;
		try {
			id = new Long(values.getFirst("id"));
		} catch (Throwable t) {
		}

		boolean shipToBillingAddress = false;
		String shipToBillingAdressStr = values.getFirst("address.city");
		if (shipToBillingAdressStr != null && shipToBillingAdressStr.equalsIgnoreCase("true")) {
			shipToBillingAddress = true;

			Address address = new Address();

			address.setCity(values.getFirst("address.city"));
			address.setCountryCode(values.getFirst("address.countryCode"));
			address.setPostCode(values.getFirst("address.postCode"));
			address.setStreetName(values.getFirst("address.streetName"));
			address.setStreetNumber(values.getFirst("address.streetNumber"));

		//	shipping.setShipToBillingAddress(shipToBillingAddress);
		//	shipping.setAddress(address);
		}
		shipping.setId(id);
		return shipping;
	}
}
