package com.zebone.btp.security;

import java.util.List;

import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.app.role.vo.Role;

/**
 * 登录人员信息。
 * @author 宋俊杰
 *
 */
public class UserDetails {
	private String personnelId;
	private String loginName;
	private String password;
	private String fullname;
	private String skin;
	/** 登录人ip */
	private String ip;
	/** 登录人所属医疗机构 */ 
	private String levelCode;
	
	/**
	 * 所属医疗机构
	 */
	private List<Mho> mhoList;
	
	private List<Role> roleList;
	/**
	 * 医疗人员任职信息。(医疗人员与医疗机构的关系信息)
	 */
	private List<PersonnelMhoR> personnelMhoList;
	
	

	/**
	 * 医疗人员ID
	 * @return
	 */
	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}
	
	/**
	 * 返回用户的登录名
	 * 
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}


	/**
	 * 得到人员的全名
	 * @return
	 */
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 返回用户密码
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	/**
	 * 医疗人员任职信息。(医疗人员与医疗机构的关系信息)
	 * @return
	 */
	public List<PersonnelMhoR> getPersonnelMhoList() {
		return personnelMhoList;
	}

	public void setPersonnelMhoList(List<PersonnelMhoR> personnelMhoList) {
		this.personnelMhoList = personnelMhoList;
	}

	/**
	 * 角色列表
	 * @return
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 得到所属医疗机构列表。
	 * @return
	 */
	public List<Mho> getMhoList() {
		return mhoList;
	}

	public void setMhoList(List<Mho> mhoList) {
		this.mhoList = mhoList;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}
	
}
