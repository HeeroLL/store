package com.zebone.btp.app.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.app.role.dao.RoleAccountRMapper;
import com.zebone.btp.app.role.service.RoleAccountService;
import com.zebone.btp.app.role.vo.RoleAccountR;

@Service
public class RoleAccountServiceImpl implements RoleAccountService {
	@Resource
	private RoleAccountRMapper roleAccount;
	
	
	@Override
	public int deleteById(String personnelId) {
		return roleAccount.deleteById(personnelId);
	}


	@Override
	public int insert(RoleAccountR record) {
		return roleAccount.insert(record);
	}


	@Override
	public List<RoleAccountR> findById(String personnelId) {
		return roleAccount.findById(personnelId);
	}


	@Override
	public int deleteByRoleId(String roleId) {
		return roleAccount.deleteByRoleId(roleId);
	}

}
