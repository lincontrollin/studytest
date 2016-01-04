package com.lin.studytest.spring.mvc.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@RequestMapping("/register/*")
@Controller
public class RegisterAction {
	@RequestMapping(value="register",method=RequestMethod.GET)
	public void register(){
		System.out.println("register comming");
		
	}

}
