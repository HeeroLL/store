package com.zebone.dip.ws.dataset.service;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService
public interface DataSetService {
	
	public String requestDataSet(@WebParam(name="requestPara") String requestParam);

}
