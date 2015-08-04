package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class Nana extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String _cnt = request.getParameter("cnt");
		int cnt = 10;
		if (_cnt != null && _cnt.equals("") == false)
			cnt = Integer.parseInt(_cnt);

		PrintWriter out = response.getWriter();
		for (int i = 0; i < cnt; i++)
			out.println("hahaÇÏ<br/>");

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String _x = request.getParameter("x");
		String _y = request.getParameter("y");

		int x = 0;
		int y = 0;

		if (_x != null && !_x.equals(""))
			x = Integer.parseInt(_x);
		if (_y != null && !_y.equals(""))
			y = Integer.parseInt(_y);

		int result = x + y;

		PrintWriter out = response.getWriter();
		
		out.write("<form action=\"hi\" method=\"post\">");
		out.write("	<ul>");
		out.printf("		<li><label for=\"x\">X:</label><input name=\"x\" value=\"%d\" /></li>", x);
		out.printf("		<li><label for=\"y\">Y:</label><input name=\"y\" value=\"%d\" /></li>", y);
		out.printf("		<li><label for=\"result\">Result:</label><input name=\"result\" value=\"%d\"/></li>", result);
		out.write("	</ul>");
		out.write("	<input type=\"submit\" value=\"µ¡¼À\" />");
		out.write("	<input type=\"submit\" value=\"Application\" />");
		out.write("	<input type=\"submit\" value=\"Session\" />");
		out.write("	<input type=\"submit\" value=\"Cookie\" />");
		out.write("</form>");
		//out.printf("%d+%d=%d\n", x, y, result);

	}

}
