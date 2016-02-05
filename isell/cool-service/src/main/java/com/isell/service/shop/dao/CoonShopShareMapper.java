package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopShare;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 酷店分享表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-11] 
 */
@Mapper
public interface CoonShopShareMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopShare getCoonShopShareById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopShare> findAllCoonShopShare();    
    
    /**
     * 根据查询条件查询
     */
    public List<CoonShopShare> findCoonShopShareList(RowBounds rowBounds,CoonShopShare coonShopShare); 
    
    /**
     * 根据查询条件查询
     */
    public List<CoonShopShare> findCoonShopShareList(CoonShopShare coonShopShare); 
    
    /**
     * 保存
     */
    public int saveCoonShopShare(CoonShopShare coonShopShare);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopShare(CoonShopShare coonShopShare);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopShare(@Param("id")String id);
}

