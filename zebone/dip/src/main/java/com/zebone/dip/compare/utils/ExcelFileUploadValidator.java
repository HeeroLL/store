package com.zebone.dip.compare.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zebone.dip.compare.vo.FileUpload;

@Component
public class ExcelFileUploadValidator implements Validator {

	@Override
	public void validate(Object uploadedFile, Errors errors) {

		FileUpload file = (FileUpload) uploadedFile;

		if (file.getFile()!=null && file.getFile().getSize() == 0) {
			errors.rejectValue("file", "uploadForm.salectFile", "Please select a file!");
		}
		
		//System.out.println("file.getFile().getSize()"+file.getFile().getSize());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
}