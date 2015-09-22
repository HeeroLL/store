package com.zebone.btp.empi.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

public class ImportData {

	MultipartFile file;

	private String name;

	private String template;

	private String fileType;

	private String loginName;

	/** 带路径的文件名 */
	private String absolutePath;

	/** EMPI 类型 */
	private String empiType;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getEmpiType() {
		return empiType;
	}

	public void setEmpiType(String empiType) {
		this.empiType = empiType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
