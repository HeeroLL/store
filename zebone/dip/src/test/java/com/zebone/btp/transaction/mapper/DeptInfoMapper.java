package com.zebone.btp.transaction.mapper;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.transaction.pojo.DeptInfo;

@Mapper
public interface DeptInfoMapper {
	int deleteById(String deptId);

	int insert(DeptInfo record);

	DeptInfo findById(String deptId);

	int updateById(DeptInfo record);
}