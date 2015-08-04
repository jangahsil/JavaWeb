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

import org.apache.jasper.compiler.ServletWriter;

@WebServlet("/MyPage")
public class MyPage extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		ServletContext Application = request.getServletContext(); // 전체 서블릿 저장소
		HttpSession Session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String _c = null;

		if (cookies != null) {
			for (Cookie cookie : cookies)
				if ("c".equals(cookie.getName()))
					_c = cookie.getValue();
		}

		int a = 0;
		int s = 0;
		int c = 0;

		Object _a = Application.getAttribute("a"); // key 는 a
		Object _s = Session.getAttribute("s");

		/*
		 * if(_a != null && !_a.equals("")){ a = Integer.valueOf((String) _a); }
		 * if(_s != null && !_s.equals("")){ s = Integer.valueOf((String) _s); }
		 */
		if (_a != null) {
			a = (int) _a;
		}
		if (_s != null) {
			s = (int) _s;

			if (_c != null) {
				c = Integer.parseInt(_c);
			}

			out.printf("Application : %d <br/>", a);
			out.printf("Session : %d <br/>", s);
			out.printf("Cookie : %d <br/>", c);
			out.printf("<a href=\"Add\">계산하기</a>");

		}
	}
}
