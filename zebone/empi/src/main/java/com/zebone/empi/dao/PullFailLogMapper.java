package com.zebone.empi.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.oneCard.PullFailLog;

/**
 * 信息推送失败日志数据库操作映射接口
 *
 * @author 杨英
 * @version 2014-6-12 下午3:51
 */
@Mapper
public interface PullFailLogMapper {

    /**
     * 插入失败日志记录
     * @param record
     * @return
     */
    int insertPullFailLog(PullFailLog record);
}
