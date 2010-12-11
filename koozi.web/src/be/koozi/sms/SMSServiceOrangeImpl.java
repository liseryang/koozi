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

public class SMSServiceOrangeImpl implements SMSService {

	private final RestTemplate restTemplate;
	private XPathOperations template = new Jaxp13XPathTemplate();

	@Autowired
	public SMSServiceOrangeImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public SMSResult sendSMS(SMS sms) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("content", sms.getContent());
		map.add("to", sms.getNumber().getNumber());
		map.add("ack", "false");
		map.add("id", "74c1e4b0e7e");
		String xml = restTemplate.postForObject("http://sms.beta.orange-api.net/sms/sendSMS.xml", map, String.class);

		Document xmlDocument = null;
		try {
			xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
		} catch (Throwable e) {
			throw new RuntimeException("Could not parse result.", e);
		}

		DOMSource domSource = new DOMSource(xmlDocument);

		String message = null;
		String statusCodeStr = template.evaluateAsString("//response/status/status_code/text()", domSource);
		SMSStatusCode statusCode = SMSStatusCode.get(new Integer(statusCodeStr));

		message = null;
		if (statusCode == SMSStatusCode.OK)
			message = "SMS is sent.";
		else {
			message = "SMS is not sent because: " + template.evaluateAsString("//response/status/status_msg/text()", domSource);
		}

		String callId = template.evaluateAsString("//response/call_info/call_id/text()", domSource);

		return new SMSResult(statusCode, message, callId, sms.getId());
	}
}
