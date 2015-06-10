package com.zebone.server;

import javax.xml.ws.Endpoint;

public class Test {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2013-12-24 上午10:53:01 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting Server");
		UploadImg implementor = new UploadImgImpl();
		String address = "http://localhost:9000/UploadImg";
		Endpoint.publish(address, implementor);

	}

}
