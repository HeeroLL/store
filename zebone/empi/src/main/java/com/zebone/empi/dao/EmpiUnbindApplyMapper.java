package com.zebone.empi.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.EmpiUnbindApply;
import com.zebone.empi.vo.ResidentCard;

/**
 * 主索引解绑申请数据库操作映射接口
 *
 * @author 杨英
 * @version 2014-8-5 上午10:23
 */
@Mapper
public interface EmpiUnbindApplyMapper {
    /**
     * 保存主索引解绑申请记录
     * @param record
     * @return
     */
    int saveUnbindApplyInfo(EmpiUnbindApply record);

    /**
     * 获取未处理的解绑申请记录
     * @param residentCard
     * @return
     */
    EmpiUnbindApply getUnbindApplyRecord(ResidentCard residentCard);

    /**
     * 更新审核状态
     * @param empiUnbindApply
     * @return
     */
    int updateUnbindAuditStatus(EmpiUnbindApply empiUnbindApply);
}
