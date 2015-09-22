package com.zebone.dip.compare.vo;

/**
 * 模板管理类
 * @author charmyin
 *
 */
public class Module {
	private String name;
	private String code;
	private String path;
	
	public Module(String name, String code, String path) {
		this.name = name;
		this.code = code;
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
