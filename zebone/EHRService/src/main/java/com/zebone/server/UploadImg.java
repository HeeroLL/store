package com.zebone.server;

import java.util.List;

import javax.jws.WebService;


@WebService
public interface UploadImg {
	
	void upImg(List<Attachment> as);
	
	void upImg2(Attachment as);
}
