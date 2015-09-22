package com.zebone.empi.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.ResidentUpdateApply;

/**
 * 实名信息更新申请数据库操作映射接口
 *
 * @author 杨英
 * @version 2014-7-23 上午9:05
 */
@Mapper
public interface ResidentUpdateApplyMapper {

    /**
     * 保存实名信息更新申请记录
     * @param record
     * @return
     */
    int saveUpdateApplyInfo(ResidentUpdateApply record);

    /**
     * 根据主索引号获取未处理的申请记录
     * @param empiNo
     * @return
     */
    ResidentUpdateApply getUpdateApplyRecord(String empiNo);

    /**
     * 更新审核状态
     * @param updateApply
     * @return
     */
    int updateAuditStatus(ResidentUpdateApply updateApply);
}
