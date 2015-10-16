package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopProduct;

import org.apache.ibatis.annotations.Param;

/**
 * 酷店商品表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoonShopProductMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopProduct getCoonShopProductById(@Param("id")String id); 
    
    /**
     * 根据条件查询
     */
    public CoonShopProduct getCoonShopProduct(CoonShopProduct coonShopProduct); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopProduct> findAllCoonShopProduct();    
    
    /**
     * 保存
     */
    public int saveCoonShopProduct(CoonShopProduct coonShopProduct);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopProduct(CoonShopProduct coonShopProduct);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopProduct(@Param("id")String id);
}

