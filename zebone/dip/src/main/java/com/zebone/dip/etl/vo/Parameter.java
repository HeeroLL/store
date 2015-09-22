package com.zebone.dip.etl.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("parameter")
public class Parameter implements Serializable {
	private String name;
	private String value;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}