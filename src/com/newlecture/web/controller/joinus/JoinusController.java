package com.newlecture.web.controller.joinus;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/joinus/*")
public class JoinusController {
	
	@RequestMapping("login")
	public String joinus() {
		return "/WEB-INF/view/joinus/login.jsp";
	}

}
