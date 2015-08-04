package com.newlecture.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeFileDao;
import com.newlecture.web.vo.Notice;
import com.newlecture.web.vo.NoticeFile;
import com.sun.glass.ui.Application;

//@WebServlet("/customer/noticeReg")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class NoticeRegController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getMethod().equals("POST")) {
			
			Part part = request.getPart("file");
			ServletContext application = request.getServletContext();		
			
			String url = "/customer/upload";
			String path = application.getRealPath(url);
			
			String temp = part.getSubmittedFileName();
			String fname = temp.substring(temp.lastIndexOf("\\") + 1);
			String fpath = path + "\\" +  fname;
			response.getWriter().println(fpath);

			InputStream ins = part.getInputStream();
			OutputStream outs = new FileOutputStream(fpath);

			byte[] 대야 = new byte[1024];
			int len = 0;

			while ((len = ins.read(대야, 0, 1024)) >= 0)
				outs.write(대야, 0, len);
			
			outs.flush();
			outs.close();
			ins.close();

			// StringBuilder sb = new StringBuilder();
			// sb.append("D:\\2015-04-08\\");
			// sb.toString(); 전체문자열 출력

			// String title = new
			// String(request.getParameter("title").getByte(),"UTF-8";
			
			  String title = request.getParameter("title"); 
			  String file = request.getParameter("file"); 
			  String content = request.getParameter("content");
			  
			  Notice notice = new Notice(); //notice.setCode(code);
			  notice.setTitle(title); notice.setWriter("jass");
			  notice.setContent(content);
			  
			  NoticeDao noticeDao = new MyBatisNoticeDao();
			  noticeDao.addNotice(notice);
			  
			  NoticeFile noticeFile = new NoticeFile();
			  noticeFile.setName(fname);
			  noticeFile.setNoticeCode(noticeDao.getLastCode());
			  
			  NoticeFileDao noticeFileDao = new MyBatisNoticeFileDao();
			  noticeFileDao.addNoticeFile(noticeFile);
			  
			 response.sendRedirect("notice"); // 이건 view .jsp가 아니라 controller를호출함.
			 } 
			else {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/view/customer/noticeReg.jsp");
			dispatcher.forward(request, response);
		}
	}
}
