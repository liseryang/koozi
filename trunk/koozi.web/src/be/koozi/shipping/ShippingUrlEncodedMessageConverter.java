package be.koozi.shipping;

import org.springframework.util.MultiValueMap;

import be.koozi.customer.Address;
import be.koozi.tools.AbstractUrlEncodedMessageConverter;

public class ShippingUrlEncodedMessageConverter extends AbstractUrlEncodedMessageConverter<Shipping> {

	public ShippingUrlEncodedMessageConverter() {
		super(Shipping.class);
	}

	@Override
	public Shipping readInternal(MultiValueMap<String, String> values) {
		Shipping shipping = new Shipping();
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

			shipping.setShipToBillingAddress(shipToBillingAddress);
			shipping.setAddress(address);
		}
		shipping.setId(id);
		return shipping;
	}
}
