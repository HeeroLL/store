package com.isell.ei.shop.meilishuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.ei.shop.meilishuo.vo.CoolLogisticsCompanyMls;

/**
 * 美丽说物流公司信息表
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
@Mapper
public interface CoolLogisticsCompanyMlsMapper{   
    /**
     * 根据主键查询
     */
    public CoolLogisticsCompanyMls getCoolLogisticsCompanyMlsById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolLogisticsCompanyMls> findAllCoolLogisticsCompanyMls();    
    
    /**
     * 保存
     */
    public int saveCoolLogisticsCompanyMls(CoolLogisticsCompanyMls coolLogisticsCompanyMls);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolLogisticsCompanyMls(CoolLogisticsCompanyMls coolLogisticsCompanyMls);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolLogisticsCompanyMls(@Param("id")Integer id);
}

