package be.koozi.tools;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ModelResponse extends HttpServletResponseWrapper {

	public ModelResponse(HttpServletResponse httpResponse) {
		super(httpResponse);
	}

	Map<String, Object> model;

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
}
