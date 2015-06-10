package com.zebone.btp.empi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiConfig;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.ImportData;

public interface EmpiDataService {

	boolean proImportEmpiData(MultipartFile file, ImportData data);

	//List<EmpiUser> proExcelData();

	//boolean proXMLData();

	//boolean proCSVData();
	
	void addEmpiError(Exception e, EmpiUser user, EmpiCard card,
			String type);
	
	boolean addEmpiData(List<EmpiUser> userList, EmpiConfig ec);
}
