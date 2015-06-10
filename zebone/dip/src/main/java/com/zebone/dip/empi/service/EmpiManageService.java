package com.zebone.dip.empi.service;

import java.util.List;
import java.util.Map;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.empi.vo.EmpiCount;
import com.zebone.dip.empi.vo.EmpiInfo;
import com.zebone.dip.empi.vo.EmpiLogInfo;
import com.zebone.dip.empi.vo.ResidentCard;
import com.zebone.dip.empi.vo.ResidentUpdateApply;
import com.zebone.dip.empi.vo.UnbindApply;
import com.zebone.dip.empi.vo.UnbindApplyParameter;
import com.zebone.dip.empi.vo.UpdateApply;

/**
 * 主索引管理服务
 *
 * @author 杨英
 * @version 2014-7-15 下午3:06
 */
public interface EmpiManageService {

    /**
     * 获取主索引信息列表
     *
     */
    Pagination<EmpiInfo> empiInfoPage(Pagination<EmpiInfo> page,ResidentCard residentCard);

    /**
     * 获取操作明细列表
     *
     */
    Pagination<EmpiLogInfo> operateList(Pagination<EmpiLogInfo> page, EmpiInfo empiInfo);

    /**
     * 根据主索引号获取主索引详情
     *
     */
    EmpiInfo getEmpiDetails(String empiNo);

    /**
     * 根据主索引号获取标识信息列表
     *
     */
    List<ResidentCard> getCardList(String empiNo);

    /**
     * 获取统计信息列表
     *
     */
    Pagination<EmpiCount> empiCountList(Pagination<EmpiCount> page, EmpiCount empiCount);

    /**
     * 获取实名信息更新申请列表
     *
     */
    Pagination<UpdateApply> updateApplyList(Pagination<UpdateApply> page, UpdateApply updateApply);

    /**
     * 根据主索引号获取未处理的实名信息更新申请信息
     *
     */
    UpdateApply getUpdateApplyInfo(String empiNo);

    /**
     * 审核实名信息更新申请
     *
     */
    int auditRealInfoApply(UpdateApply updateApply,String auditResult);

    /**
     * 获取实名信息更新申请列表
     *
     */
    Pagination<UnbindApply> unbindApplyList(Pagination<UnbindApply> page, UnbindApply unbindApply);

    /**
     * 获取未处理的EMPI解绑申请信息
     *
     */
    UnbindApply getUnbindApplyInfo(String id);

    /**
     * 审核主索引解绑申请
     *
     */
    int auditUnbindApply(UnbindApply unbindApply,String auditResult);
    
    
    /**
     * 保存实名修改信息
     * @param residentUpdateApply
     * @return
     * @author 陈阵 
     * @date 2014-10-10 下午2:18:34
     */
    Map<String,Object> saveUpdateApply(ResidentUpdateApply  residentUpdateApply);
    
    
    
    /**
     * 保存解绑信息
     * @param residentUpdateApply
     * @return
     * @author 陈阵 
     * @date 2014-10-14 下午2:15:48
     */
    Map<String,Object> saveUnbindApply(UnbindApplyParameter unbindApplyParameter);

}
