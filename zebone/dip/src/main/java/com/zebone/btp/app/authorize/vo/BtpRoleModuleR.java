package com.zebone.btp.app.authorize.vo;

import java.util.Date;
/**
 * 模块与角色关系基类
 * @author 蔡祥龙
 * 2012-11-23
 */
public class BtpRoleModuleR{
	/**关系创建者*/
	private String creatorId;
	/**关系创建时间*/
	private Date createTime;
	/**模块id*/
	private String moduleId;
	/**角色id*/
	private String roleId;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId == null ? null : moduleId.trim();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}
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
}