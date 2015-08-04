package com.newlecture.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/practice")
public class practice extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("hello");
	}

/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		service(request,response);

	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
	}
*/	
	@Override
	public void init() throws ServletException {
		System.out.println("servlet"+this.getServletName()+"has started");
	}
	
	@Override
	public void destroy() {
		System.out.println("servlet"+this.getServletName()+"has stopped");
	}
}
