package com.isell.service.product.service;

import java.util.List;

import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.util.Record;
import com.isell.service.product.po.CoolProductExternal;
import com.isell.service.product.po.CoolProductExternalStock;
import com.isell.service.product.po.CoolProductExternalStockSelect;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductReview;
import com.isell.service.shop.po.CoonShopProductParam;
import com.isell.service.shop.vo.CoonShopProduct;


/**
 * 商品接口层
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-09]
 */
public interface ProductService {
	
	/**
	 * 分页批量获取商品数据
	 */
    PageInfo<CoolProduct> getCoolProductPageList(CoolProductSelect coolProductSelect);
    
    /**
     * 根据查询条件获取商品信息
     *
     * @param param 查询条件
     * @return 商品详情
     */
    CoolProduct getCoolProductById(CoolProductSelect param);
    
    /**
     * 分页获取商品列表
     * 
     * @param  coonShopProductPo 商品查询条件
     * @return  商品列表
     */
    Record getProductList(CoonShopProductParam coonShopProductParam);
    
    /**
     * 获取商品分类列表
     * 
     * @return  商品分类列表
     */
    Record getProductTypeList();
    
    /**
     * 获取商品详情
     * 
     * @param  coonShopProductParam 商品查询条件
     * @return  商品列表
     */
    Record getProductDetail(CoonShopProductParam coonShopProductParam);
    
    /**
     * 分页查询商品评价列表
     * 
     * @param  coolProductReview 商品评价查询条件
     * @return  商品评价列表
     */
    Record getProductReviewListPage(CoolProductReview coolProductReview);
    
    /**
     * 保存商品上架（含批量）
     * 该方法配合APP，忽略仓库，直接上架
     * 
     * @param  coonShopProductList 商品上架信息
     * @return  是否保存成功
     */
    Record saveShopProduct(List<CoonShopProduct> coonShopProductList);
    
    /**
     * 更新商品排序
     * 
     * @param  coonShopProductList 商品上架信息
     * @return  是否更新成功
     */
    Record updateShopProductOrder(List<CoonShopProduct> coonShopProductList);
    
    /**
     * 保存新品上线
     * 
     * @param  coonShopProductList 商品上架信息
     * @return  是否保存成功
     */
    Record saveShopProductNew(List<CoonShopProduct> coonShopProductList);
    
    /**
     * 分页	获取已上架商品列表
     * 
     * @param  coonShopProductParam 商品查询条件
     * @return  已上架商品列表
     */
    Record getShopProductListPage(CoonShopProductParam coonShopProductParam);
    
    /**
     * 分页	获取未上架商品列表
     * 
     * @param  coonShopProductParam 商品查询条件
     * @return  已上架商品列表
     */
    Record getShopProductNoList(CoonShopProductParam coonShopProductParam);
    
    /**
     * 获取热门商品列表
     * 
     * @return  商品列表
     */
    Record getHotTrendProductList();
    /**
     * 根据商品主键或者name查询
     * @return
     */
    List<CoolProductExternal> getProductInfo(CoolProductExternal product);
    /**
     * 对外查询库存
     * @param product
     * @return
     */
	List<CoolProductExternalStockSelect> getProductStock(CoolProductExternalStock product);
		
}
