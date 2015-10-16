package com.isell.service.alipay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.alipay.vo.CoolAlipay;

/**
 * TODO
 * @author 
 */
@Mapper
public interface CoolAlipayMapper{   
    /**
     * 根据主键查询
     */
    public CoolAlipay getCoolAlipayById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolAlipay> findAllCoolAlipay();    
    
    /**
     * 根据条件查询
     */
    public List<CoolAlipay> findCoolAlipay(CoolAlipay coolAlipay);    
    
    /**
     * 保存
     */
    public int saveCoolAlipay(CoolAlipay coolAlipay);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolAlipay(CoolAlipay coolAlipay);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolAlipay(@Param("id")Integer id);
}

