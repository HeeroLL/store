package com.zebone.dip.empi.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.EmpiMapper;
import com.zebone.dip.empi.vo.ResidentUpdateApply;
import com.zebone.dip.empi.vo.UpdateApply;

/**
 * 主索引实名信息更新申请DAO
 * @author 杨英
 * @version 2014-7-29 下午3:00
 */
@EmpiMapper
public interface UpdateApplyMapper {

    /**
     * 获取申请列表
     * @param updateApply
     * @return
     */
    List<UpdateApply> getUpdateApplyList(UpdateApply updateApply, RowBounds rowBounds);

    /**
     * 获取更新申请列表总数
     * @param updateApply
     * @return
     * int
     */
    int getUpdateApplyCount(UpdateApply updateApply);

    /**
     * 根据主索引号获取实名信息更新详情
     * @param empiNo
     * @return
     */
    UpdateApply getUpdateApplyInfo(String empiNo);
    
    
    
    /**
     * 实名更新申请
     * @param residentUpdateApply
     * @return
     * @author 陈阵 
     * @date 2014-10-10 下午1:56:26
     */
    int saveUpdateApplyInfo(ResidentUpdateApply residentUpdateApply);
}
