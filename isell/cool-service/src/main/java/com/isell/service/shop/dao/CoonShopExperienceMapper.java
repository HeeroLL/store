package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopExperience;

import org.apache.ibatis.annotations.Param;

/**
 * 体验店表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-07] 
 */
@Mapper
public interface CoonShopExperienceMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopExperience getCoonShopExperienceById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopExperience> findAllCoonShopExperience();    
    
    /**
     * 保存
     */
    public int saveCoonShopExperience(CoonShopExperience coonShopExperience);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopExperience(CoonShopExperience coonShopExperience);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopExperience(@Param("id")String id);
}

