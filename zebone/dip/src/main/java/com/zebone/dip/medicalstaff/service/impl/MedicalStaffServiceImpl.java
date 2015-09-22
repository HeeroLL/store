package com.zebone.dip.medicalstaff.service.impl;

import org.springframework.stereotype.Service;

import com.zebone.dip.medicalstaff.service.MedicalStaffService;

@Service("medicalStaffService")
public class MedicalStaffServiceImpl implements MedicalStaffService {

	@Override
	public String getMedicalStaffNameByid(String staffId) {
		// TODO Auto-generated method stub
		//return null;
		
		return "测试人员";
	}

}
