package com.zebone.server;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.activation.DataHandler;
import javax.jws.WebService;

import org.apache.commons.io.FileUtils;


@WebService(endpointInterface = "com.zebone.server.UploadImg")
public class UploadImgImpl implements UploadImg {

	@Override
	public void upImg(List<Attachment> as) {
		// TODO Auto-generated method stub
		//TODO  FileUtils.copyInputStreamToFile
		/**
		System.out.println("###################");
		try {
			for(Attachment ass : as){
				DataHandler handler = ass.getImageData();
				FileUtils.copyInputStreamToFile(handler.getInputStream(), new File("c:\\"+ass.getFileName()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
	}

	@Override
	public void upImg2(Attachment as) {
		// TODO Auto-generated method stub
		//TODO  FileUtils.copyInputStreamToFile
		/**
		try {
			DataHandler handler = as.getImageData();
			FileUtils.copyInputStreamToFile(handler.getInputStream(), new File("c:\\"+as.getFileName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
	}

}
