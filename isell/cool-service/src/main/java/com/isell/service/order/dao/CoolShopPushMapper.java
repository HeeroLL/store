package com.isell.service.order.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolShopPush;

import org.apache.ibatis.annotations.Param;

/**
 * 订单推送表(通用)
 * 
 * @author maoweijie
 * @version [版本号, 2016年2月25日]
 */
@Mapper
public interface CoolShopPushMapper{   
    /**
     * 根据主键查询
     */
    public CoolShopPush getCoolShopPushById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolShopPush> findAllCoolShopPush();    
    
    /**
     * 保存
     */
    public int saveCoolShopPush(CoolShopPush coolShopPush);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolShopPush(CoolShopPush coolShopPush);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolShopPush(@Param("id")Integer id);
    
    /**
     * 获取推送后订单详细信息
     * @return
     */
	public List<CoolOrder> getShopPushOrder();
    /**
     * 逻辑删除已推送订单
     * @param orderNo
     */
	public void deleteByOrderNo(@Param("orderNo")String[] orderNo);
}

