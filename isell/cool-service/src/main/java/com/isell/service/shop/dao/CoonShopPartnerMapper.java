package com.isell.service.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopPartner;

/**
 * 
 * 酷店合伙人佣金mapper
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-21]
 */
@Mapper
public interface CoonShopPartnerMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopPartner getCoonShopPartnerById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopPartner> findAllCoonShopPartner();    
    
    /**
     * 保存
     */
    public int saveCoonShopPartner(CoonShopPartner coonShopPartner);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopPartner(CoonShopPartner coonShopPartner);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopPartner(@Param("id")String id);
}

