package com.zebone.register.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.register.vo.DocViewLog;

import java.util.Map;

/**
 * 文档调阅日志 DAO
 *
 * @author 杨英
 * @version 2013-11-19 上午08:58
 */
@Mapper
public interface DocViewLogMapper {

    /**
     * 插入文档调阅日志
     * @param docViewLog
     * @return
     */
    int insert(DocViewLog docViewLog);

    /**
     * 查询文档调阅日志记录
     * @param oMap
     * @return
     */
    DocViewLog getDocViewLogInfo (Map<String,String> oMap);
}
