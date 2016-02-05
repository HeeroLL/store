package com.isell.service.custorms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.custorms.po.CoolProductCustomsNbyb;

/**
 * 宁波优贝海关备案Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
@Mapper
public interface CoolProductCustomsNbybMapper {
    /**
     * 根据主键查询
     */
    CoolProductCustomsNbyb getCoolProductCustomsNbybById(@Param("id") Integer id);
    
    /**
     * 根据主键查询
     */
    CoolProductCustomsNbyb getCoolProductCustomsNbybByCode(@Param("code") String code);
    
    /**
     * 通过规格id获取商品备案信息
     *
     * @param gid 规格id
     * @return 商品备案信息
     */
    CoolProductCustomsNbyb getCustomsInfoByGid(Integer gid);
    
    /**
     * 查询出所有记录
     */
    List<CoolProductCustomsNbyb> findAllCoolProductCustomsNbyb();
    
    /**
     * 保存
     */
    int saveCoolProductCustomsNbyb(CoolProductCustomsNbyb coolProductCustomsNbyb);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolProductCustomsNbyb(CoolProductCustomsNbyb coolProductCustomsNbyb);
    
    /**
     * 根据主键删除
     */
    int deleteCoolProductCustomsNbyb(@Param("id") Integer id);
}
