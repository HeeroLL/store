package com.isell.service.bi.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.bi.vo.BiCoonShop;

import org.apache.ibatis.annotations.Param;

/**
 * 酷店统计表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09]
 */
@Mapper
public interface BiCoonShopMapper{   
    /**
     * 根据主键查询
     */
    public BiCoonShop getBiCoonShopById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<BiCoonShop> findAllBiCoonShop();    
    
    /**
     * 保存
     */
    public int saveBiCoonShop(BiCoonShop biCoonShop);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateBiCoonShop(BiCoonShop biCoonShop);
    
    /**
     * 根据主键删除
     */
    public int deleteBiCoonShop(@Param("id")Integer id);
}

