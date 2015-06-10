package com.zebone.dip.etl.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("argument")
public class Argument implements Serializable{
	private String name;
	private String value;

	public String getName() {
		return this.name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	protected void setValue(String value) {
		this.value = value;
	}
}