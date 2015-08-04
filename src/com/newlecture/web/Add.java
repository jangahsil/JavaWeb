package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Add")
public class Add extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		service(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		service(request, response);

	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int x = 0;
		int y = 0;
		int result =0;
		
		if(request.getMethod().equals("POST")){
			
			String _result = request.getParameter("result");
			if(_result !=null){
				result = Integer.parseInt(_result);
			}
			
			String _btn = request.getParameter("btn");
			
			if(_btn.equals("Application")){
				ServletContext Application = request.getServletContext(); // 전체 서블릿 저장소 
				Application.setAttribute("a", result);
				out.write("	<p>Application에 저장 완료</p>");
			}
			else if(_btn.equals("Session")){
				HttpSession Session = request.getSession();
				Session.setAttribute("s", result);
				out.write("	<p>Session에 저장 완료</p>");
			}
			else if(_btn.equals("Cookie"))
			{
				Cookie cookie = new Cookie("c",String.valueOf(result));
				cookie.setMaxAge(24*60*60);
				response.addCookie(cookie);
				out.write("	<p>Cookie에 저장 완료</p>");
			}

			else 
			{  
				
			String _x = request.getParameter("x");
			String _y = request.getParameter("y");
			
			if (_x != null && !_x.equals(""))
				x = Integer.parseInt(_x);
			if (_y != null && !_y.equals(""))
				y = Integer.parseInt(_y);
			
			result = x + y;
			
			}
		}
		
		out.write("<form action=\"Add\" method=\"post\">");
		out.write("	<ul>");
		out.printf("		<li><label for=\"x\">X:</label><input name=\"x\" value=\"%d\" /></li>", x);
		out.printf("		<li><label for=\"y\">Y:</label><input name=\"y\" value=\"%d\" /></li>", y);
		out.printf("		<li><label for=\"result\">Result:</label><input name=\"result\" value=\"%d\"/></li>", result);
		out.write("	</ul>");
		out.printf("	<p><input type=\"hidden\" name=\"result\" value=\"%d\" />",result);
		out.write("	<input type=\"submit\" name=\"btn\" value=\"add\" />");
		out.write("	<input type=\"submit\" name=\"btn\" value=\"Application\" />");
		out.write("	<input type=\"submit\" name=\"btn\" value=\"Session\" />");
		out.write("	<input type=\"submit\" name=\"btn\" value=\"Cookie\" />");
		out.write("	</p>");
		out.write("</form>");
		
		out.printf("덧셈 결과는 : %d</br>",result);
		out.println("<a href=\"MyPage\">마이페이지</a>");
	
	}
}
