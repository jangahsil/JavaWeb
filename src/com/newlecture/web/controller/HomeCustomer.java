package com.newlecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class HomeCustomer {

	@RequestMapping("index")
	public String index(){
		return "home.index";
	} 
}
