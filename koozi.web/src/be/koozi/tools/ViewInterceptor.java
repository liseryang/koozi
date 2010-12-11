package be.koozi.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ViewInterceptor extends HandlerInterceptorAdapter {

	public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		String view = (String) modelAndView.getModel().get("view");
		if (view != null)
			modelAndView.setViewName(view + "." + view);
	}
}
