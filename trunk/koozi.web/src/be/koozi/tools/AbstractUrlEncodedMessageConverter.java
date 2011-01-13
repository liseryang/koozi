package be.koozi.tools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

public abstract class AbstractUrlEncodedMessageConverter<T> implements HttpMessageConverter<T> {

	private Charset charset = Charset.forName(WebUtils.DEFAULT_CHARACTER_ENCODING);

	private final Class<T> clazz;

	public AbstractUrlEncodedMessageConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	public abstract T readInternal(MultiValueMap<String, String> value);

	@Override
	public T read(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		MultiValueMap<String, String> urlEncodedMap = readURLEncoded(inputMessage);

		HttpServletRequest httpServletRequest;
		if (inputMessage instanceof ServletServerHttpRequest) {
			httpServletRequest = ((ServletServerHttpRequest) inputMessage).getServletRequest();
			Map<String, String[]> values = httpServletRequest.getParameterMap();

			for (Entry<String, String[]> entry : values.entrySet()) {
				for (int i = 0; i < entry.getValue().length; i++) {
					String value = entry.getValue()[i];
					urlEncodedMap.add(entry.getKey(), value);
				}
			}
		}

		return readInternal(urlEncodedMap);
	}

	@Override
	public void write(T t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		throw new RuntimeException("Not implemented");
	}

	protected MultiValueMap<String, String> readURLEncoded(HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

		MediaType contentType = inputMessage.getHeaders().getContentType();
		Charset charset = contentType.getCharSet() != null ? contentType.getCharSet() : this.charset;
		String body = FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));

		String[] pairs = StringUtils.tokenizeToStringArray(body, "&");

		MultiValueMap<String, String> result = new LinkedMultiValueMap<String, String>(pairs.length);

		for (String pair : pairs) {
			int idx = pair.indexOf('=');
			if (idx == -1) {
				result.add(URLDecoder.decode(pair, charset.name()), null);
			} else {
				String name = URLDecoder.decode(pair.substring(0, idx), charset.name());
				String value = URLDecoder.decode(pair.substring(idx + 1), charset.name());
				result.add(name, value);
			}
		}
		return result;
	}

	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		if (!this.clazz.isAssignableFrom(clazz)) {
			return false;
		}
		if (mediaType != null) {
			return MediaType.APPLICATION_FORM_URLENCODED.includes(mediaType);
		} else {
			return true;
		}
	}

	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		if (!this.clazz.isAssignableFrom(clazz)) {
			return false;
		}
		if (mediaType != null) {
			return mediaType.isCompatibleWith(MediaType.APPLICATION_FORM_URLENCODED) || mediaType.isCompatibleWith(MediaType.MULTIPART_FORM_DATA);
		} else {
			return true;
		}
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED);
	}

}