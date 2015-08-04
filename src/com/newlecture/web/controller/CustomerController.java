package com.newlecture.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.Notice;
import com.newlecture.web.vo.NoticeFile;

@Controller
// 사용자가 요청한 url이 있는지 보는데, 그 url은 함수이름에 매핑되어 있음
@RequestMapping("/customer/*")
public class CustomerController {

	private NoticeDao noticeDao;
	private NoticeFileDao noticeFileDao;

	@Autowired
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Autowired
	public void setNoticeFileDao(NoticeFileDao noticeFileDao) {
		this.noticeFileDao = noticeFileDao;
	}

	@RequestMapping("notice")
	// 스프링이 실행, 참조할 것이 있으면 매개변수로 받으면 된다. , url 매핑
	public String notice(Model model) {

		// NoticeDao noticeDao = new MyBatisNoticeDao();
		List<Notice> list = noticeDao.getNotices();
		// request.setAttribute("list", list);

		/*
		 * ModelAndView mv = new ModelAndView();
		 * mv.setViewName("/WEB-INF/view/customer/notice.jsp");
		 * mv.addObject("list", list);
		 */

		model.addAttribute("list", list);

		return "customer.notice";
		//return "/WEB-INF/view/customer/notice.jsp";
	}

	@RequestMapping("noticeDetail")
	// code = model
	public String noticeDetail(String c, Model model) {

		// NoticeDao noticeDao = new MyBatisNoticeDao();
		Notice n = noticeDao.getNotice(c);

		model.addAttribute("n", n);

		return "customer.noticeDetail";
	}

	@RequestMapping(value = "noticeReg", method = RequestMethod.GET)
	public String noticeReg() {
		return "customer.noticeReg"; // forward
	}

	@RequestMapping(value="noticeReg", method=RequestMethod.POST)
	public String noticeReg(Notice n, MultipartFile file, Principal principal, 
			HttpServletRequest request, 
			//SecurityContextHolder securityContextHolder,
			SecurityContext securityContext) throws IOException{
		
		if(request.isUserInRole("ROLE_ADMIN")){
			
		}
		//securityContextHolder.getContext();
		securityContext.getAuthentication().getAuthorities(); // 목록
		securityContext.getAuthentication().isAuthenticated(); //인증 됐는지
			
		n.setWriter(principal.getName());
		noticeDao.addNotice(n);
		String lastcode = noticeDao.getLastCode();
		
		if(!file.isEmpty()){
			//Part part = request.getPart("file");
			ServletContext application = request.getServletContext();		
			
			String url = "/customer/upload";
			String path = application.getRealPath(url);
			
			String temp = file.getOriginalFilename();
			String fname = temp.substring(temp.lastIndexOf("\\") + 1);
			String fpath = path + "\\" +  fname;

			InputStream ins = file.getInputStream();
			OutputStream outs = new FileOutputStream(fpath);

			byte[] 대야 = new byte[1024];
			int len = 0;

			while ((len = ins.read(대야, 0, 1024)) >= 0)
				outs.write(대야, 0, len);
			
			outs.flush();
			outs.close();
			ins.close();
			
			// 이부분은 트랜잭션으로 처리 하는 것이 좋고, spring 으로 나중에 처리하자
			NoticeFile noticefile = new NoticeFile();
			noticefile.setNoticeCode(lastcode);
			noticefile.setName(fname);
			noticeFileDao.addNoticeFile(noticefile);
		}

		return "redirect:notice";
		//redirect
	}
}
