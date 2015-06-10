package com.zebone.dip.resources.vo;

public class ResourceData {
	
	/**
	 * 资源id
	 */
	private String id;
	
	/**
	 * 资源编码
	 */
	private String code;
	
	/**
	 * 资源名称
	 */
	private String name;
	
	/**
	 * 资源xml机构
	 */
	private String resourceXml;
	
	/**
	 * 资源xsd结构
	 */
	private String resourceXsd;
	
	/**
	 * 描述
	 */
	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceXml() {
		return resourceXml;
	}

	public void setResourceXml(String resourceXml) {
		this.resourceXml = resourceXml;
	}

	public String getResourceXsd() {
		return resourceXsd;
	}

	public void setResourceXsd(String resourceXsd) {
		this.resourceXsd = resourceXsd;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
