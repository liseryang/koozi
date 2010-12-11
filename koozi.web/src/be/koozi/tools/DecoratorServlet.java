/*
 * Title: FreemarkerDecoratorServlet Description:
 * 
 * This software is published under the terms of the OpenSymphony Software License version 1.1, of which a copy has been included
 * with this distribution in the LICENSE.txt file.
 */

package be.koozi.tools;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.koozi.gae.security.AuthenticatedUserDetails;

import com.opensymphony.module.sitemesh.HTMLPage;
import com.opensymphony.module.sitemesh.RequestConstants;

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

public class DecoratorServlet extends FreemarkerServlet
{
	
	protected boolean preTemplateProcess(
		HttpServletRequest request,
		HttpServletResponse response,
		Template template,
		TemplateModel templateModel)
		throws ServletException, IOException
	{
		boolean result=super.preTemplateProcess(request, response, template, templateModel);
		
		SimpleHash hash = (SimpleHash) templateModel;
		
		HTMLPage htmlPage = (HTMLPage) request.getAttribute(RequestConstants.PAGE);

		String title, body, head;

		if(htmlPage==null)
		{
			title="No Title";
			body="No Body";
			head="<!-- No head -->";
		}
		else
		{
			title=htmlPage.getTitle();
			
			StringWriter buffer = new StringWriter();
			htmlPage.writeBody(buffer);
			body=buffer.toString();
			
			buffer = new StringWriter();
			htmlPage.writeHead(buffer);
			head=buffer.toString();

			hash.put("page",htmlPage);
		}
		
		hash.put("title",title);
		hash.put("body",body);
		hash.put("head",head);
		hash.put("base",request.getAttribute("base"));
		hash.put("authentication", new AuthenticatedUserDetails());
		
		return result;
	}
}
