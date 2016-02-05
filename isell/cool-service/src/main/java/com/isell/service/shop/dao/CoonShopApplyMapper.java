package com.isell.service.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopApply;

/**
 * 酷店申请表mapper
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
@Mapper
public interface CoonShopApplyMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopApply getCoonShopApplyById(@Param("id")String id); 
    
    /**
     * 根据用户主键查询
     */
    public CoonShopApply getCoonShopApplyByUserId(@Param("userId")String userId); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopApply> findAllCoonShopApply();    
    
    /**
     * 保存
     */
    public int saveCoonShopApply(CoonShopApply coonShopApply);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopApply(CoonShopApply coonShopApply);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopApply(@Param("id")String id);
}

