package com.isell.service.account.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.account.vo.CoonRunAccount;

/**
 * 
 * 账单流水记录表mapper
 * 
 * @author wangpegn
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
     * 分页查询
     */
    public List<CoonRunAccount> findCoonRunAccountListPage(RowBounds rowBounds, CoonRunAccount coonRunAccount);    
    
    /**
     * 根据条件查询
     */
    public List<CoonRunAccount> findCoonRunAccountList(CoonRunAccount coonRunAccount);   
    
    /**
     * 获取账单金额
     * 
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param shopId 酷店主键
     * @return 总金额
     */
    public BigDecimal getSumAmount(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("shopId")String shopId);
    
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
    
    /**
     * 根据解冻日期判断查询
     * @param string
     * @return
     */
	public List<CoonRunAccount> findByEndDate(@Param("date")String date);
}

