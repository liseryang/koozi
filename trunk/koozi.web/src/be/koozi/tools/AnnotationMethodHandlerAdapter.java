package be.koozi.tools;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpInputMessage;

public class AnnotationMethodHandlerAdapter extends org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter {
	protected HttpInputMessage createHttpInputMessage(HttpServletRequest servletRequest) throws Exception {
		return new ServletServerHttpRequest(servletRequest);
	}
}
