package com.isell.service.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.vo.CoolProductGg;

import org.apache.ibatis.annotations.Param;

/**
 * 商品规格mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 */
@Mapper
public interface CoolProductGgMapper {
    /**
     * 根据主键查询
     */
    CoolProductGg getCoolProductGgById(@Param("id") Integer id);
    
    /**
     * 根据商品id查询规格列表
     *
     * @param goodsId 商品id
     * @return 规格列表
     */
    List<CoolProductGg> findCoolProductGgList(Integer goodsId);
    
    /**
     * 保存
     */
    int saveCoolProductGg(CoolProductGg coolProductGg);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolProductGg(CoolProductGg coolProductGg);
    
    /**
     * 根据主键删除
     */
    int deleteCoolProductGg(@Param("id") Integer id);
}
