package com.isell.ei.pay.yijifu.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.ei.pay.yijifu.bean.CoolYjfRemit;

import org.apache.ibatis.annotations.Param;

/**
 * 易极付跨境汇款申请Mapper
 * 
 * @author lilin
 * @version [版本号, 2016年2月23日]
 */
@Mapper
public interface CoolYjfRemitMapper {
    /**
     * 根据主键查询
     */
    CoolYjfRemit getCoolYjfRemitById(@Param("id") Integer id);
    
    /**
     * 根据批次号查询
     *
     * @param remittranceBatchno 批次号
     * @return 汇款申请信息
     */
    CoolYjfRemit getCoolYjfRemitByBatchno(String remittranceBatchno);
    
    /**
     * 更新批次总金额
     *
     * @param remittranceBatchno 批次号
     * @return 更新成功的条数
     */
    int updatePayAmountByBatchno(String remittranceBatchno);
    
    /**
     * 查询出所有记录
     */
    List<CoolYjfRemit> findAllCoolYjfRemit();
    
    /**
     * 保存
     */
    int saveCoolYjfRemit(CoolYjfRemit coolYjfRemit);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolYjfRemit(CoolYjfRemit coolYjfRemit);
    
    /**
     * 根据主键删除
     */
    int deleteCoolYjfRemit(@Param("id") Integer id);
}
