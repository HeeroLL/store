package com.isell.service.member.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.member.vo.CoolIdentityAuth;

import org.apache.ibatis.annotations.Param;

/**
 * 实名认证mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-12] 
 */
@Mapper
public interface CoolIdentityAuthMapper{   
    /**
     * 根据主键查询
     */
    public CoolIdentityAuth getCoolIdentityAuthById(@Param("id")Integer id); 
    
    /**
     * 根据用户id查询
     */
    public CoolIdentityAuth getCoolIdentityAuthByUserId(@Param("userId")Integer userId); 

    /**
     * 查询出所有记录
     */
    public List<CoolIdentityAuth> findAllCoolIdentityAuth();    
    
    /**
     * 保存
     */
    public int saveCoolIdentityAuth(CoolIdentityAuth coolIdentityAuth);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolIdentityAuth(CoolIdentityAuth coolIdentityAuth);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolIdentityAuth(@Param("id")Integer id);
}

