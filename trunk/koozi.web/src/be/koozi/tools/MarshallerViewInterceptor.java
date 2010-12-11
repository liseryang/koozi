package be.koozi.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MarshallerViewInterceptor extends HandlerInterceptorAdapter {

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		MarshallingViewKey marshallingViewKey = AnnotationUtils.findAnnotation(handler.getClass(), MarshallingViewKey.class);
		if (marshallingViewKey != null) {
			String rootElement = marshallingViewKey.value();
			modelAndView.getModel().put("rootElement", rootElement);
		}
	}
}
