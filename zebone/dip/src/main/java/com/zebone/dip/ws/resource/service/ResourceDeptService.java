package com.zebone.dip.ws.resource.service;

import com.zebone.dip.ws.resource.pojo.OfficeRequest;
import com.zebone.dip.ws.resource.pojo.OfficeResponse;

public interface ResourceDeptService {
	
	public OfficeResponse saveDept(OfficeRequest officeRequest);
	
	
	public OfficeResponse delDept(OfficeRequest officeRequest);
	
	
	public OfficeResponse searchDept(OfficeRequest officeRequest);
	
	
	public OfficeResponse updateDept(OfficeRequest officeRequest);

}
