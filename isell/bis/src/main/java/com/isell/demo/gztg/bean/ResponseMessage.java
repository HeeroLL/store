package com.isell.demo.gztg.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ResponseMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseMessage {

	@XmlElement(name="ResponseHead")
	private ResponseHead responseHead;
	
	@XmlElement(name="ResponseBodyList")
	private ResponseBodyList responseBodyList;

	public ResponseHead getResponseHead() {
		return responseHead;
	}

	public void setResponseHead(ResponseHead responseHead) {
		this.responseHead = responseHead;
	}

	public ResponseBodyList getResponseBodyList() {
		return responseBodyList;
	}

	public void setResponseBodyList(ResponseBodyList responseBodyList) {
		this.responseBodyList = responseBodyList;
	}
	
	
}
