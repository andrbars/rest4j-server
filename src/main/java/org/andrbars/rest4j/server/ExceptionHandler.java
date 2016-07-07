package org.andrbars.rest4j.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.andrbars.rest4j.ExceptionInfo;
import org.andrbars.rest4j.RestConst;

public class ExceptionHandler extends HttpServlet
{

	private static final String MESSAGE = "javax.servlet.error.message";
	private static final String SERVLET_NAME = "javax.servlet.error.servlet_name";
	private static final String REQUEST_URI = "javax.servlet.error.request_uri";
	private static final String EXCEPTION_TYPE = "javax.servlet.error.exception_type";
	private static final String STATUS_CODE = "javax.servlet.error.status_code";

	private static final ObjectMapper mapper = new ObjectMapper();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		Integer statusCode = (Integer)request.getAttribute(STATUS_CODE);
		String exceptionType = ((Class)request.getAttribute(EXCEPTION_TYPE)).getName();
		String requestUri = (String)request.getAttribute(REQUEST_URI);
		String servletName = (String)request.getAttribute(SERVLET_NAME);
		String message = (String)request.getAttribute(MESSAGE);

		ExceptionInfo exceptionInfo = new ExceptionInfo(statusCode, exceptionType, requestUri,
			servletName, message);

		response.setContentType(RestConst.APPLICATION_JSON_UTF_8);
		try (PrintWriter out = response.getWriter())
		{
			out.print(mapper.writeValueAsString(exceptionInfo));
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods">
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		processRequest(request, response);
	}

	@Override
	public String getServletInfo()
	{
		return "rest4j exception handler";
	}// </editor-fold>

}
