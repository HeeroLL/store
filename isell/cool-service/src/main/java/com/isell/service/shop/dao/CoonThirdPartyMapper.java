package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonThirdParty;

import org.apache.ibatis.annotations.Param;

/**
 * 一件代发申请表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-16] 
 */
@Mapper
public interface CoonThirdPartyMapper{   
    /**
     * 根据主键查询
     */
    public CoonThirdParty getCoonThirdPartyById(@Param("id")String id); 
    
    /**
     * 根据酷店id查询
     */
    public CoonThirdParty getCoonThirdPartyByShopId(@Param("shopId")String shopId); 
    
    /**
     * 根据用户id查询
     */
    public CoonThirdParty getCoonThirdPartyByUserId(@Param("userId")String userId); 

    /**
     * 查询出所有记录
     */
    public List<CoonThirdParty> findAllCoonThirdParty();    
    
    /**
     * 保存
     */
    public int saveCoonThirdParty(CoonThirdParty coonThirdParty);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonThirdParty(CoonThirdParty coonThirdParty);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonThirdParty(@Param("id")String id);
}

