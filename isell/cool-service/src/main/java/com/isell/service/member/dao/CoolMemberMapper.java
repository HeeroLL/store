package com.isell.service.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.member.vo.CoolMember;

/**
 * 会员mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoolMemberMapper{   
    /**
     * 根据主键查询
     */
    public CoolMember getCoolMemberById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolMember> findAllCoolMember();    
    
    /**
     * 保存
     */
    public int saveCoolMember(CoolMember coolMember);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolMember(CoolMember coolMember);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolMember(@Param("id")Integer id);
}

