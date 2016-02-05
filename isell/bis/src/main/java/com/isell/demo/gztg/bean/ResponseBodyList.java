package com.isell.demo.gztg.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseBodyList {
	
	@XmlElement(name="ResponseBody")
	private List<ResponseBody> responseBody;

	public List<ResponseBody> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(List<ResponseBody> responseBody) {
		this.responseBody = responseBody;
	}
	
	
}
