package com.zebone.btp.security;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityTestController {
	
	@RequestMapping("testRolepermission")
	public String testRolepermission() {
		
//		SecurityUtils.getSubject()
		return "testRolepermission";
	}
}
