package com.isell.service.payinfo.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.payinfo.vo.CoolSendPayinfo;

import org.apache.ibatis.annotations.Param;

/**
 * 支付单报关信息Mapper
 * 
 * @author lilin
 * @version [版本号, 2016年1月20日]
 */
@Mapper
public interface CoolSendPayinfoMapper {
    /**
     * 根据主键查询
     */
    CoolSendPayinfo getCoolSendPayinfoById(@Param("id") Integer id);
    
    /**
     * 根据订单号查询
     * 
     * @param orderNo 订单号
     * @return 支付单信息
     */
    CoolSendPayinfo getCoolSendPayinfoByOrderNo(String orderNo);
    
    /**
     * 查询出所有记录
     */
    List<CoolSendPayinfo> findAllCoolSendPayinfo();
    
    /**
     * 保存
     */
    int saveCoolSendPayinfo(CoolSendPayinfo coolSendPayinfo);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolSendPayinfo(CoolSendPayinfo coolSendPayinfo);
    
    /**
     * 根据主键删除
     */
    int deleteCoolSendPayinfo(@Param("id") Integer id);
}
