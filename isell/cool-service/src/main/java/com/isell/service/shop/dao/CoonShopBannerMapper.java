package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopBanner;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 酷店海报表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-11] 
 */
@Mapper
public interface CoonShopBannerMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopBanner getCoonShopBannerById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopBanner> findAllCoonShopBanner();    
    
    /**
     * 根据酷店主键查询
     */
    public List<CoonShopBanner> findCoonShopBannerBysId(RowBounds rowBounds,@Param("sId")String sId);  
    
    /**
     * 保存
     */
    public int saveCoonShopBanner(CoonShopBanner coonShopBanner);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopBanner(CoonShopBanner coonShopBanner);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopBanner(@Param("id")String id);
}

