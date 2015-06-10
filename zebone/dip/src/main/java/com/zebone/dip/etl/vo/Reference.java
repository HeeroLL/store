package com.zebone.dip.etl.vo;

import java.io.Serializable;

public class Reference  implements Serializable{
	public static final String REP_NAME = "rep_name";
	public static final String REP_REF = "rep_ref";
	private String specification_method;
	private String filename;
	private String name;
	private String directory;
	private String object_id;

	public String getSpecification_method() {
		return this.specification_method;
	}

	public void setSpecification_method(String specificationMethod) {
		this.specification_method = specificationMethod;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectory() {
		return this.directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getObject_id() {
		return this.object_id;
	}

	public void setObject_id(String objectId) {
		this.object_id = objectId;
	}
}