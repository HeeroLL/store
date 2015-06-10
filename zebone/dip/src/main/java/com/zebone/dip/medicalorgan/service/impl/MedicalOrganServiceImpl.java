package com.zebone.dip.medicalorgan.service.impl;

import org.springframework.stereotype.Service;

import com.zebone.dip.medicalorgan.service.MedicalOrganService;

@Service("medicalOrganService")
public class MedicalOrganServiceImpl implements MedicalOrganService {

	@Override
	public String getMedicalOrganNameByCode(String code) {
		// TODO Auto-generated method stub
		//return null;
		return "牛塘卫生院";
		
	}

}
