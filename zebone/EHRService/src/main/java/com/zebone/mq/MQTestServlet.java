package com.zebone.mq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MQTestServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String path = System.getProperty("java.library.path");
//		path += ";D:\\ProgramFiles\\Tongtech\\TLQCli8\\bin;D:\\ProgramFiles\\Tongtech\\TLQCli8\\samples\\bin;D:\\ProgramFiles\\Tongtech\\TLQ8\\bin;D:\\ProgramFiles\\Tongtech\\TLQ8\\samples\\bin;.;D:\\ProgramFiles\\jdk1.6.0_26\\bin;D:\\ProgramFiles\\jdk1.6.0_26\\jre\\bin;C:\\windows\\system32;C:\\windows;C:\\windows\\System32\\Wbem;C:\\windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Program Files (x86)\\Intel\\OpenCL SDK\\2.0\\bin\\x86;C:\\Program Files (x86)\\Intel\\OpenCL SDK\\2.0\\bin\\x64;C:\\Program Files (x86)\\Intel\\Services\\IPT\\;D:\\ProgramFiles\\MySQL Server 5.5\\bin;";
//		System.setProperty("java.library.path", path);
		
//		Sender.send("����һ������!");
	}

}
