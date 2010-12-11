package be.koozi.sms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embeddable;

public enum SMSStatusCode {
	OK(200), ID_IS_MISSING(400), UNAUTHORISED_ID(401), INSUFFICIENT_CREDIT(402), CONTENT_IS_MISSING(430), FROM_PARAMETER_IS_INVALID(431), PHONE_NUMBER_IS_INVALLID(433), CONTENT_IS_TOO_LONG(440), UNAUTHORISED_DESTINATION(441), UNAUTHORISED_SOURCE_FOR_THIS_DESTINATION(442), UNREACHEABLE_DESTINATION(
			447), INTERNA_ERROR(500);

	private final int code;

	SMSStatusCode(int returnCode) {
		this.code = returnCode;
	}

	public int getStatusCode() {
		return code;
	}

	private static final Map<Integer, SMSStatusCode> lookup = new HashMap<Integer, SMSStatusCode>();
	static {
		lookup.put(OK.getStatusCode(), OK);
		lookup.put(ID_IS_MISSING.getStatusCode(), ID_IS_MISSING);
		lookup.put(UNAUTHORISED_ID.getStatusCode(), UNAUTHORISED_ID);
		lookup.put(INSUFFICIENT_CREDIT.getStatusCode(), INSUFFICIENT_CREDIT);
		lookup.put(CONTENT_IS_MISSING.getStatusCode(), CONTENT_IS_MISSING);
		lookup.put(FROM_PARAMETER_IS_INVALID.getStatusCode(), FROM_PARAMETER_IS_INVALID);
		lookup.put(PHONE_NUMBER_IS_INVALLID.getStatusCode(), PHONE_NUMBER_IS_INVALLID);
		lookup.put(CONTENT_IS_TOO_LONG.getStatusCode(), CONTENT_IS_TOO_LONG);
		lookup.put(UNAUTHORISED_DESTINATION.getStatusCode(), UNAUTHORISED_DESTINATION);
		lookup.put(UNAUTHORISED_SOURCE_FOR_THIS_DESTINATION.getStatusCode(), UNAUTHORISED_SOURCE_FOR_THIS_DESTINATION);
		lookup.put(UNREACHEABLE_DESTINATION.getStatusCode(), UNREACHEABLE_DESTINATION);
		lookup.put(INTERNA_ERROR.getStatusCode(), INTERNA_ERROR);

	}

	public static SMSStatusCode get(int returnCode) {
		return lookup.get(returnCode);
	}
}
