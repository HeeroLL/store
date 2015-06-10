package com.zebone.btp.app.role.service;

import java.util.List;

import com.zebone.btp.app.role.vo.RoleAccountR;

public interface RoleAccountService {
	public int deleteById(String personnelId);
	public int insert(RoleAccountR record);
	public int deleteByRoleId(String roleId);
	public List<RoleAccountR> findById(String personnelId);
}
