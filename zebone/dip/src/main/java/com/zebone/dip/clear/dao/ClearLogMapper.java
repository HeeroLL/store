package com.zebone.dip.clear.dao;

import com.zebone.dip.clear.vo.ClearLog;

public interface ClearLogMapper {
	int deleteById(String id);

	int insert(ClearLog record);

	ClearLog findById(String id);

	int updateById(ClearLog record);
}