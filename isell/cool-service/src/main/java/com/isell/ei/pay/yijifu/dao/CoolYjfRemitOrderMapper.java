package com.isell.ei.pay.yijifu.dao;

import java.math.BigDecimal;
import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.ei.pay.yijifu.bean.CoolYjfRemitOrder;

import org.apache.ibatis.annotations.Param;

/**
 * 易极付跨境汇款订单Mapper
 * 
 * @author lilin
 * @version [版本号, 2016年2月23日]
 */
@Mapper
public interface CoolYjfRemitOrderMapper {
    /**
     * 根据主键查询
     */
    CoolYjfRemitOrder getCoolYjfRemitOrderById(@Param("id") Integer id);
    
    /**
     * 根据批次号查询订单信息
     *
     * @param remittranceBatchno 批次号
     * @return 订单信息列表
     */
    List<CoolYjfRemitOrder> findYjfRemitOrderByBatchno(String remittranceBatchno);
    
    /**
     * 获取批次总金额
     *
     * @param remittranceBatchno 批次号
     * @return 批次总金额
     */
    BigDecimal getSumAmountByBatchno(String remittranceBatchno);
    
    /**
     * 保存
     */
    int saveCoolYjfRemitOrder(CoolYjfRemitOrder coolYjfRemitOrder);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolYjfRemitOrder(CoolYjfRemitOrder coolYjfRemitOrder);
    
    /**
     * 根据主键删除
     */
    int deleteCoolYjfRemitOrder(@Param("id") Integer id);
}
