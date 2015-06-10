package com.zebone.btp.app.role.dao;

import java.util.List;

import com.zebone.btp.app.role.vo.RoleAccountR;
import com.zebone.btp.core.mybatis.Mapper;
@Mapper
public interface RoleAccountRMapper {
	int insert(RoleAccountR record);
	int insertSelective(RoleAccountR record);
	int deleteById(String personnelId);
	int deleteByRoleId(String roleId);
	public List<RoleAccountR> findById(String personnelId);
}