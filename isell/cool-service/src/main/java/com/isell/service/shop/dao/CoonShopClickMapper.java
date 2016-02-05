package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopClick;

import org.apache.ibatis.annotations.Param;

/**
 * 酷店访问表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09] 
 */
@Mapper
public interface CoonShopClickMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopClick getCoonShopClickById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopClick> findAllCoonShopClick();    
    
    /**
     * 保存
     */
    public int saveCoonShopClick(CoonShopClick coonShopClick);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopClick(CoonShopClick coonShopClick);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopClick(@Param("id")Integer id);
    
    /**
     * 获取访客数
     * 
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param shopId 酷店id
     * @return 访客数量
     */
    public int getCountUV(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("shopId")String shopId);
    
    /**
     * 获取浏览数
     * 
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param shopId 酷店id
     * @return 浏览数量
     */
    public int getCountPV(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("shopId")String shopId);
}

