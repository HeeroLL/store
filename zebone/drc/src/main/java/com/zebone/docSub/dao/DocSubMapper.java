package com.zebone.docSub.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.docSub.vo.DocSubMessage;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 杨英
 * @version 2014-08-12 下午02:45
 */
@Mapper
public interface DocSubMapper {
    /**
     * 订阅文档信息
     * @param oMap
     * @return
     */
    List<DocSubMessage> getSubMessage (Map<String,Object> oMap);
}
