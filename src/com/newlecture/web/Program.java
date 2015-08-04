package com.newlecture.web;

import java.applet.AppletContext;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.dao.mybatis.SqlNewlecSessionFactory;
import com.newlecture.web.vo.Notice;

public class Program {

	static {
		SqlNewlecSessionFactory factory = new SqlNewlecSessionFactory();
		try {
			factory.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ServletException {
		
		/*NoticeDao noticeDao = new MyBatisNoticeDao(); 
		// 수정하는 부분을 사용하는 쪽으로 옮겨 온것
		// -> spring은 이마저도 빼버려야 비로소 완성! (설정으로 빼버림)
		
		NoticeConsole console = new NoticeConsole();
		console.setNoticeDao(noticeDao); // injection
*/		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/web/spring-context.xml");
		NoticeConsole console = (NoticeConsole)context.getBean("console"); // object 형을 NoticeConsole형으로 변경
		console.print();

		
	}
}
