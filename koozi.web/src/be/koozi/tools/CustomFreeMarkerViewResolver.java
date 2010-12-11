package be.koozi.tools;

import java.util.Locale;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

public class CustomFreeMarkerViewResolver extends FreeMarkerViewResolver
{
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String servletPath = attrs.getRequest().getServletPath();
		attrs.setAttribute("base", servletPath, 0);

		// add context to redirect
		if (viewName.contains("redirect:")) {
			String viewNameRedirect = viewName.substring("redirect:".length());
			View view = super.resolveViewName("redirect:" + servletPath + viewNameRedirect, locale);
			if (view != null) {
				return view;
			}
		}

//		// check if body is already set, try to get the context layout if not.
//		if (attrs.getAttribute("body", 0) == null && attrs.getRequest().getParameter("body") == null) {
//			View view = super.resolveViewName(servletPath + "/layout", locale);
//			if (view != null) {
//				attrs.setAttribute("body", viewName + ".ftl", 0);
//				return view;
//			}
//		}
		
		String viewParam = (String) attrs.getRequest().getParameter("view");
		if(viewParam == null)
			viewParam = "";
		else
			viewParam = "." + viewParam;

		// Check if context view is available
		View view = super.resolveViewName(servletPath + "/" + viewName + viewParam, locale);
		if (view != null) {
			return view;
		}
		
		// Check if default view is available
		view = super.resolveViewName("/default/" + viewName + viewParam, locale);
		if (view != null) {
			return view;
		}
		
		view = super.resolveViewName(servletPath + "/" + viewName, locale);
		if (view != null) {
			return view;
		}
		
		// Check if default view is available
		view = super.resolveViewName("/default/" + viewName, locale);
		if (view != null) {
			return view;
		}

		// return default view.
		return super.resolveViewName(viewName, locale);
	}

   public CustomFreeMarkerViewResolver()
   {
      setViewClass( requiredViewClass() );
   }

   /**
    * Requires {@link FreeMarkerView}.
    */
   @Override
   protected Class requiredViewClass()
   {
      return CustomFreeMarkerView.class;
   }
}