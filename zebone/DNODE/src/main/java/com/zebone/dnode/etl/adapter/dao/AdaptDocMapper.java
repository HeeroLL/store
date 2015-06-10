package com.zebone.dnode.etl.adapter.dao;

import com.zebone.btp.core.mybatis.EtlMapper;
import com.zebone.dnode.etl.adapter.vo.AdaptDoc;
@EtlMapper
public interface AdaptDocMapper {

    int insert(AdaptDoc record);
    int insertSelective(AdaptDoc record);
    
}