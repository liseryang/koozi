package be.koozi;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class JsonRequestTest {
	@Test
	public void testRequest() {
		try {
			HttpURLConnection request = (HttpURLConnection) new URL("http://localhost:8080/shop/products/2").openConnection();
			request.setRequestProperty("Accept", "application/json");
			request.connect();
			InputStream is = request.getInputStream();
			Reader r = new InputStreamReader(is);
			char[] bytes = new char[1000];
			r.read(bytes);
			String result = new String(bytes);
			System.out.print(result);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
