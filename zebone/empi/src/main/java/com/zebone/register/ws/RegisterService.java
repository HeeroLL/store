package com.zebone.register.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface RegisterService {

	public String requestRegister(@WebParam(name="docXml") String docXml);
	
}
