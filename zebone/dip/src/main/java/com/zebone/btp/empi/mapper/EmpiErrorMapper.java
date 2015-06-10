package com.zebone.btp.empi.mapper;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.empi.vo.EmpiError;

@Mapper
public interface EmpiErrorMapper {

	int addEmpiError(EmpiError empiError);
	
	int removeBatch(EmpiError empiError);
	
	List<EmpiError> queryEEList(EmpiError empiError);
	
}
