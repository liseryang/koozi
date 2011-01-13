package be.koozi.customer;

import org.springframework.util.MultiValueMap;

import be.koozi.tools.AbstractUrlEncodedMessageConverter;

public class CustomerUrlEncodedMessageConverter extends AbstractUrlEncodedMessageConverter<Customer> {

	public CustomerUrlEncodedMessageConverter() {
		super(Customer.class);
	}

	@Override
	public Customer readInternal(MultiValueMap<String, String> values) {

		Identity identity = new Identity();
		identity.setName(values.getFirst("identity.name"));
		identity.setFirstName(values.getFirst("identity.firstname"));

		Address address = new Address();
		address.setCity(values.getFirst("address.city"));
		address.setCountryCode(values.getFirst("address.countryCode"));
		address.setPostCode(values.getFirst("address.postCode"));
		address.setStreetName(values.getFirst("address.streetName"));
		address.setStreetNumber(values.getFirst("address.streetNumber"));

		Company company = new Company();
		company.setEnterpriseNumber(values.getFirst("company.enterpriseNumber"));
		company.setName(values.getFirst("company.name"));

		Customer customer = new Customer();
		Long id = 0L;
		try {
			id = new Long(values.getFirst("id"));
		} catch (Throwable t) {
		}
		customer.setId(id);
		customer.setAddress(address);
		customer.setIdentity(identity);
		customer.setCompany(company);
		customer.setEmail(new Email(values.getFirst("email.value")));
		customer.setPhoneNumber(new PhoneNumber(values.getFirst("phoneNumber.value")));
		return customer;
	}
}
