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
     * 根据用户id查询
     */
    public CoolMember getCoolMemberByUserId(@Param("userId")Integer userId); 

    /**
     * 查询出所有记录
     */
    public List<CoolMember> findAllCoolMember();    
    
    /**
     * 根据条件查询
     */
    public List<CoolMember> findCoolMember(CoolMember coolMember);
    
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
    
    /**
     * 获取最大的编号
     */
    public String getMaxCoolMemberNo();
    
    /**
     * 校验身份证
     */
    public int getNumberForCheckIdCard(@Param("userId")Integer id,@Param("idcard")String idcard);
    
    /**
     * 根据用户名查询用户
     */
    public CoolMember getCoolMemberByMobile(@Param("mobile")String mobile);
    
    
}

