package com.zebone.dip.etl.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("webresult")
public class WebResult implements Serializable {
	public static final String OK = "OK";
	public static final String ERROR = "ERROR";
	private String result;
	private String message;
	private String id;

	public String getResult() {
		return this.result;
	}

	public String getMessage() {
		return this.message;
	}

	public String getId() {
		return this.id;
	}
	

	public void setResult(String result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return "WebResult [id=" + this.id + ", message=" + this.message
				+ ", result=" + this.result + "]";
	}
}