package be.koozi.tools;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.module.sitemesh.RequestConstants;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

public class ContextSiteMeshFilter extends SiteMeshFilter {

	public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) rq;

		request.setAttribute(RequestConstants.DECORATOR, request.getContextPath());

		super.doFilter(rq, rs, chain);
	}
}
