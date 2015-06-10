package com.zebone.empi.vo;

import java.util.Map;

public class DictionaryMap {
	private String name;
	private Map<String,String> keyValue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(Map<String, String> keyValue) {
		this.keyValue = keyValue;
	}
	
}
