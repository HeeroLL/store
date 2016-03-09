package com.isell.ei.pay.yijifu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.ei.pay.yijifu.bean.CoolYjfRefundMessage;

/**
 * 易极付跨境退款申请异步返回信息
 * 
 * @author wangpeng
 * @version [版本号, 2016年2月23日]
 */
@Mapper
public interface CoolYjfRefundMessageMapper{   
    /**
     * 根据主键查询
     */
    public CoolYjfRefundMessage getCoolYjfRefundMessageById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolYjfRefundMessage> findAllCoolYjfRefundMessage();    
    
    /**
     * 保存
     */
    public int saveCoolYjfRefundMessage(CoolYjfRefundMessage coolYjfRefundMessage);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolYjfRefundMessage(CoolYjfRefundMessage coolYjfRefundMessage);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolYjfRefundMessage(@Param("id")Integer id);
}

