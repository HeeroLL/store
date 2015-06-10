package com.zebone.dip.empi.dao;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.empi.vo.UnbindApply;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 主索引解绑申请DAO
 * @author 杨英
 * @version 2014-8-6 上午10:57
 */
@EmpiMapper
public interface UnbindApplyMapper {

    /**
     * 获取EMPI解绑申请列表
     * @param unbindApply
     * @return
     */
    List<UnbindApply> getUnbindApplyList(UnbindApply unbindApply, RowBounds rowBounds);

    /**
     * 获取EMPI解绑申请列表总数
     * @param unbindApply
     * @return
     * int
     */
    int getUnbindApplyCount(UnbindApply unbindApply);

    /**
     * 根据id获取EMPI解绑申请记录详情
     * @param id
     * @return
     */
    UnbindApply getUnbindApplyInfo(String id);

    /**
     * 根据二级标识获取EMPI解绑申请记录详情
     * @param unbindApply
     * @return
     */
    UnbindApply  getUnbindApplyRecord(UnbindApply unbindApply);
}
