package com.zebone.btp.empi.mapper;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.empi.vo.EmpiConfig;

@Mapper
public interface EmpiConfigMapper {

	int updateEmpiConfig(EmpiConfig empiConfig);
	
	EmpiConfig getEmpiConfig();
}
