package com.isell.service.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.po.CoolProductAliUnit;
import com.isell.service.product.po.CoolProductExternal;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.shop.po.CoonShopProductInfo;
import com.isell.service.shop.po.CoonShopProductParam;

/**
 * 商品mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 */
@Mapper
public interface CoolProductMapper {
    /**
     * 根据主键查询
     */
    CoolProduct getCoolProductById(@Param("id") Integer id);
    
    /**
     * 查询出所有记录
     */
    List<CoolProduct> findAllCoolProduct();
    
    /**
     * 查询热门搜索商品列表
     * 
     * @param 
     * @return 热门搜索商品列表
     */
    public List<CoonShopProductInfo> getHotTrendProductList();
    
    /**
     * 保存
     */
    int saveCoolProduct(CoolProduct coolProduct);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolProduct(CoolProduct coolProduct);
    
    /**
     * 根据主键删除
     */
    int deleteCoolProduct(@Param("id") Integer id);
    
    /**
     * 根据条件分页查询商品信息
     * 
     * @param rowBounds
     * @param coolProduct
     * @return
     */
    List<CoolProduct> getCoolProductPageList(RowBounds rowBounds, CoolProductSelect coolProductSelect);
    
    /**
     * 根据条件分页查询商品信息(不包含规格)
     * 
     * @param rowBounds
     * @param coonShopProductParam
     * @return
     */
    List<CoolProduct> getCoolProductPageListNoGg(RowBounds rowBounds, CoonShopProductParam coonShopProductParam);
    
    /**
     * 根据分页查询商品数量
     * 
     * @param coolProduct
     * @return
     */
    int getCoolProductPageListCount(CoolProductSelect coolProductSelect);
    
    /**
     * 根据产品Id或者name查询
     * @param product
     * @return
     */
    List<CoolProductExternal> getProductInfo(CoolProductExternal product);
    /**
     * 根据商品id查询阿里商品规格
     * @param coolProductExternal
     * @return
     */
    List<CoolProductAliUnit>queryGoodsAliUnit(CoolProductExternal coolProductExternal);
}
