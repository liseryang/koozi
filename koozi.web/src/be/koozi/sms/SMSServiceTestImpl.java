package be.koozi.sms;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class SMSServiceTestImpl implements SMSService {

	private final RestTemplate restTemplate;
	private XPathOperations template = new Jaxp13XPathTemplate();

	@Autowired
	public SMSServiceTestImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public SMSResult sendSMS(SMS sms) {
		String message = "This is test.";
		SMSStatusCode statusCode = SMSStatusCode.OK;
		
		message = null;
		if (statusCode == SMSStatusCode.OK)
			message = "SMS is sent.";

		String callId = "test";

		return new SMSResult(statusCode, message, callId, sms.getId());
	}
}
