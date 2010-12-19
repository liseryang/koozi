package be.koozi.tools;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyContext {
	HttpServletRequest request;
	HttpServletResponse response;
	Map<String, Object> model;

	public MyContext(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {

		this.model = model;
		this.request = request;
		this.response = response;
	}

	public void fetch(String path) {
		ModelResponse modelResponse = new ModelResponse(response);

		try {
			request.getRequestDispatcher(path).include(request, modelResponse);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, Object> localModel = modelResponse.getModel();
	//	if(localModel == null)
		this.model.putAll(localModel);
	}

	public Object getModelObject(String modelName) {
		if (this.model != null) {
			return this.model.get(modelName);
		} else {
			return this.request.getAttribute(modelName);
		}
	}
}
