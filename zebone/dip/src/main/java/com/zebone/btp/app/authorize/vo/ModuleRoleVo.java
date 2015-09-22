/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Nov 30, 2012		角色与模块的集合对象
 */
package com.zebone.btp.app.authorize.vo;

import java.util.List;

import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.app.role.vo.Role;

public class ModuleRoleVo {
	private List<BtpModule> btpModules;
	private List<Role> roles;
	public List<BtpModule> getBtpModules() {
		return btpModules;
	}
	public void setBtpModules(List<BtpModule> btpModules) {
		this.btpModules = btpModules;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
