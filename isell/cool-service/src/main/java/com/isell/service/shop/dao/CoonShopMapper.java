package com.isell.service.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.po.CoonShopPartnerInfo;
import com.isell.service.shop.vo.CoonShop;

/**
 * 酷店表Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
@Mapper
public interface CoonShopMapper{   
    /**
     * 根据主键查询酷店信息
     *
     * @param id 主键
     * @return 酷店信息
     */
    CoonShop getCoonShopById(@Param("id")String id); 
    
    /**
     * 根据酷店编码查询酷店信息
     *
     * @param code 酷店编码
     * @return 酷店信息
     */
    CoonShop getCoonShopByCode(@Param("code")String code);     
    
    /**
     * 分页查询酷店信息
     * 
     * @param rowBounds 分页信息
     * @param coonShop 参数
     * @return 酷店列表
     */
    List<CoonShop> getCoonShopListPage(RowBounds rowBounds, CoonShop coonShop);
    
    /**
     * 分页查询有上架商品的酷店信息
     * 
     * @param rowBounds 分页信息
     * @param coonShop 参数
     * @return 酷店列表
     */
    List<CoonShop> getCoonShopPListPage(RowBounds rowBounds, CoonShop coonShop);
    
    /**
     * 根据推荐店铺主键获取二级酷店信息
     * 
     * @param recommendId
     * @return 酷店列表
     */
    List<CoonShop> getCoonShopListByRecommendL2(@Param("recommendId")String recommendId);
    
    /**
     * 根据推荐店铺主键获取三级酷店信息
     * 
     * @param recommendId
     * @return 酷店列表
     */
    List<CoonShop> getCoonShopListByRecommendL3(@Param("recommendId")String recommendId);
    
    /**
     * 查询体验店列表
     * 
     * @return 酷店列表
     */
    List<CoonShop> findAllCoonShopExperience();
    
    /**
     * 根据条件查询体验店列表
     * 
     * @return 酷店列表
     */
    List<CoonShop> getCoonShopExperienceList(CoonShop coonShop);
    
    /**
     * 根据酷店主键和分销等级获取合伙人及奖励
     * 
     * @param shopIds 合伙人店铺主键数组
     * @param devide 分销等级
     * @param shopId 本酷店主键
     * @return 合伙人及奖励列表
     */
    List<CoonShopPartnerInfo> getCoonShopPartnerListByShopIdsAndDevide(@Param("shopIds")String[] shopIds, @Param("devide")String devide, @Param("shopId")String shopId);
    
    /**
     * 获取合伙人奖励明细列表
     * 
     * @param shopId 本酷店主键
     * @param partnerId 合伙人店铺主键
     * @param devide 分销等级
     * @return 合伙人奖励明细列表
     */
    List<CoonShopPartnerInfo> getCoonShopPartnerDetailList(@Param("shopId")String shopId, @Param("partnerId")String partnerId, @Param("devide")String devide);
    
    /**
     * 根据用户名查询酷店信息
     *
     * @param id 主键
     * @return 酷店信息
     */
    CoonShop getCoonShopByUserId(@Param("userId")String userId); 
    
    /**
     * 保存酷店信息
     *
     * @param coonShop 酷店信息
     * @return 成功保存的条数
     */
    int saveCoonShop(CoonShop coonShop);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     *
     * @param coonShop 酷店信息
     * @return 成功修改的条数
     */
    int updateCoonShop(CoonShop coonShop);
    
    /**
     * 根据主键删除
     *
     * @param id 主键id
     * @return 成功删除的条数
     */
    int deleteCoonShop(@Param("id")String id);
    
    /**
     * 根据查询到的数量判断是否达到下个等级
     * 
     * @param id
     * @return 数量
     */
    int getCoonShopNextLevel(@Param("id")String id);
    
    /**
     * 更新酷店等级
     * 
     * @param id
     * @return 
     */
    int updateCoonShopLevel(@Param("id")String id);
}

