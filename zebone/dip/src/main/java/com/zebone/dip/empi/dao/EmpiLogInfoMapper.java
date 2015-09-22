package com.zebone.dip.empi.dao;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.empi.vo.EmpiCount;
import com.zebone.dip.empi.vo.EmpiLogInfo;
import com.zebone.dip.empi.vo.EmpiOperateLog;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 主索引日志信息查询DAO
 * @author 杨英
 * @version 2014-7-17 上午10:38
 */
@EmpiMapper
public interface EmpiLogInfoMapper {

    /**
     * 获取该主索引对应的操作明细列表
     * @param empiNo
     * @return
     */
    List<EmpiLogInfo> operateList(RowBounds rowBounds, String empiNo);

    /**
     * 获取该主索引所对应的操作明细列表总数
     * @param empiNo
     * @return
     */
    int operateTotalCount(String empiNo);

    /**
     * 获取主索引统计信息列表
     * @param empiCount
     * @param rowBounds
     * @return
     */
    List<EmpiCount> getEmpiCountList(EmpiCount empiCount, RowBounds rowBounds);

    /**
     * 获取主索引统计信息列表总数
     * @param empiCount
     * @return
     * int
     */
    int getEmpiCountListNum(EmpiCount empiCount);

}
