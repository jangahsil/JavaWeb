package com.newlecture.web.controller.joinus;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisMemberDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.vo.Member;
import com.newlecture.web.vo.Notice;

@WebServlet("/joinus/login")
public class LoginController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getMethod().equals("POST")) {

			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");

			MemberDao memberDao = new MyBatisMemberDao();
			Member member = memberDao.getMember(uid);

			if (member == null) { // 회원이 가입한 적 없을 때
				// 가입한 적이 없다는 오류메시지 띄우기
				request.setAttribute("error", "아이디 또는 비밀번호가 잘 못 되었습니다.");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
				dispatcher.forward(request, response);
				
			} else if (!member.getPwd().equals(pwd)) { // id는 있는데 패스워드가 같지 않을 때
				// 비번오류 메시지 띄우기
				request.setAttribute("error", "아이디 또는 비밀번호가 잘 못 되었습니다.");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
				dispatcher.forward(request, response);
				
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("uid", uid);
				response.sendRedirect("../customer/notice");
			}
			
		} else {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
