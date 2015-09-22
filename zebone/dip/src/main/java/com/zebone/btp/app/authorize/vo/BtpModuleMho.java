package com.zebone.btp.app.authorize.vo;

import java.util.Date;
/**
 * 模块与机构间关系的基类
 * @author 蔡祥龙
 * 2012-11-23
 */
public class BtpModuleMho{
	/**机构id*/
	private String mhoId;
	/**模块id*/
	private String moduleId;
	/**关系创建者*/
	private String creatorId;
	/**关系创建时间*/
	private Date createTime;

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMhoId() {
		return mhoId;
	}

	public void setMhoId(String mhoId) {
		this.mhoId = mhoId == null ? null : mhoId.trim();
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId == null ? null : moduleId.trim();
	}
}