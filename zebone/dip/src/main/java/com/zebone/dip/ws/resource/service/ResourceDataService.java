package com.zebone.dip.ws.resource.service;

import javax.jws.WebParam;
import javax.jws.WebService;


@WebService
public interface ResourceDataService {
	
	public String request(@WebParam(name="requestParam") String requestParam);

}
