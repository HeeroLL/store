package com.zebone.dip.ws.resource.service;

import com.zebone.dip.ws.resource.pojo.FamilyRequest;
import com.zebone.dip.ws.resource.pojo.FamilyResponse;

public interface ResourceFamilyService {

	FamilyResponse saveFamily(FamilyRequest familyRequest);
	
	FamilyResponse delFamily(FamilyRequest familyRequest);
	
	FamilyResponse searchFamily(FamilyRequest familyRequest);
	
	FamilyResponse updateFamily(FamilyRequest familyRequest);
}
