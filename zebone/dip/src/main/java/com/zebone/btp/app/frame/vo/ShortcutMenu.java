package com.zebone.btp.app.frame.vo;

/**
 * 快捷方式VO
 * @author 宋俊杰
 * @date 2012-12-13
 */
public class ShortcutMenu {
	private String moduleId;
	private String personnelId;
	private String name;
	private String url;
	private String maxicon;
	private String minicon;
	private Integer orderNo;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMaxicon() {
		return maxicon;
	}

	public void setMaxicon(String maxicon) {
		this.maxicon = maxicon;
	}

	public String getMinicon() {
		return minicon;
	}

	public void setMinicon(String minicon) {
		this.minicon = minicon;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
