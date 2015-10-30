package com.isell.service.member.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.member.vo.CoolUser;

import org.apache.ibatis.annotations.Param;

/**
 * 用户mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-14] 
 */
@Mapper
public interface CoolUserMapper{   
    /**
     * 根据主键查询
     */
    public CoolUser getCoolUserById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolUser> findAllCoolUser();    
    
    /**
     * 保存
     */
    public int saveCoolUser(CoolUser coolUser);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolUser(CoolUser coolUser);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolUser(@Param("id")Integer id);
    
    /**
     * 根据用户名查询注册用户数量
     * 
     * @param userName
     * @return
     */
    public int getRegisterNumberByUserName(@Param("userName")String userName);
    
    /**
     * 平台用户检验
     * 
     * @param userName
     * @return
     */
    public int getNumberForCheckUser(@Param("id")Integer id, @Param("email")String email);
}

