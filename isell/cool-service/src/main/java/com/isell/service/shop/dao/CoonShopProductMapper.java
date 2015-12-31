package com.isell.service.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.po.CoonShopProductInfo;
import com.isell.service.shop.po.CoonShopProductParam;
import com.isell.service.shop.vo.CoonShopProduct;

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
     * 根据酷店最大的商品排序号
     */
    public Integer getMaxOrderByShopId(@Param("sId")String sId); 
    
    /**
     * 根据条件查询
     */
    public CoonShopProduct getCoonShopProduct(CoonShopProduct coonShopProduct); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopProduct> findAllCoonShopProduct();    
    
    /**
     * 查询热销商品列表
     * 
     * @param coonShopProductParam
     * @return 热销商品列表
     */
    public List<CoonShopProductInfo> getHotSellProductList(CoonShopProductParam coonShopProductParam);
    
    /**
     * 分页查询商品信息	
     * 
     * @param rowBounds
     * @param coonShopProductParam
     * @return 商品分页信息
     */
    public List<CoonShopProductInfo> getCoonShopProductInfoListPage(RowBounds rowBounds,CoonShopProductParam coonShopProductParam);
    
    /**
     * 分页查询已上架商品信息	
     * 
     * @param rowBounds
     * @param coonShopProductParam
     * @return 商品分页信息
     */
    public List<CoonShopProductInfo> getCoonShopProductAddedListPage(RowBounds rowBounds,CoonShopProductParam coonShopProductParam);
    
    /**
     * 分页查询未上架商品信息	
     * 
     * @param rowBounds
     * @param coonShopProductParam
     * @return 商品分页信息
     */
    public List<CoonShopProductInfo> getCoonShopProductNoAddedListPage(RowBounds rowBounds,CoonShopProductParam coonShopProductParam);
    
   /**
    * 根据商品主键获取上架店铺数
    * 
    * @param pId 商品主键
    * @return 数量
    */
    public int getCoonShopProductAddedCountByGoodsId(@Param("pId")String pId);
    
    /**
     * 根据商品主键和酷店主键获取上架数
     * 
     * @param pId 商品主键
     * @return 数量
     */
     public int getCoonShopProductAddedCountByGoodsIdAndShopId(@Param("pId")String pId,@Param("sId")String sId);
    
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

