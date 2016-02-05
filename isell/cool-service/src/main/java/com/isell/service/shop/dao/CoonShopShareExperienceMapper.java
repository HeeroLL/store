package com.isell.service.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.po.CoonShopShareParam;
import com.isell.service.shop.vo.CoonShopShareExperience;

/**
 * 酷店分享（经验心得）表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2016-01-25]
 */
@Mapper
public interface CoonShopShareExperienceMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopShareExperience getCoonShopShareExperienceById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopShareExperience> findAllCoonShopShareExperience();    
    
    /**
     * 分页查询分享经验心得
     */
    public List<CoonShopShareExperience> getCoonShopShareExperiencePage(RowBounds rowBounds,CoonShopShareParam param);  
    
    /**
     * 保存
     */
    public int saveCoonShopShareExperience(CoonShopShareExperience coonShopShareExperience);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopShareExperience(CoonShopShareExperience coonShopShareExperience);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopShareExperience(@Param("id")String id);
}

