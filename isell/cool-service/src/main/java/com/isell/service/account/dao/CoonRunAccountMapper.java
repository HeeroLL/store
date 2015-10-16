package com.isell.service.account.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.account.vo.CoonRunAccount;

import org.apache.ibatis.annotations.Param;

/**
 * 收入流水信息表Mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoonRunAccountMapper{   
    /**
     * 根据主键查询
     */
    public CoonRunAccount getCoonRunAccountById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonRunAccount> findAllCoonRunAccount();    
    
    /**
     * 保存
     */
    public int saveCoonRunAccount(CoonRunAccount coonRunAccount);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonRunAccount(CoonRunAccount coonRunAccount);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonRunAccount(@Param("id")String id);
}

