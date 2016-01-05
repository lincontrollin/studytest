package com.lin.studytest.spring.mvc.login;

import java.util.HashSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login2/*")
public class LoginAction {
	
	/**
	 *
	 * @param userName
	 * @param password
	 */
	@RequestMapping("login2")
	public void login(String userName,String password){
		if(userName.equals("linwanqi")){
			return;
		}
		
		
	}

}
