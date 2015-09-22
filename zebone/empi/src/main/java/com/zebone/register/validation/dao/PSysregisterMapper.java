package com.zebone.register.validation.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.register.validation.domain.PSysregister;

import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * @author 
 */
@DipMapper
public interface PSysregisterMapper{   
    /**
     * 根据主键查询
     */
    public PSysregister getPSysregisterById(); 

    /**
     * 查询出所有记录
     */
    public List<PSysregister> findAllPSysregister();    
    
    /**
     * 保存
     */
    public int savePSysregister(PSysregister pSysregister);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updatePSysregister(PSysregister pSysregister);
    
    /**
     * 根据主键删除
     */
    public int deletePSysregister();

    /**
      * @Title: getJurisdiction 
      * @Description: 查询业务系统是否已经注册
      * @author LinBin
      * @param sysCode
      * @param docOrgCode
      * @return
      * @throws
     */
	public int getJurisdiction(@Param("sysCode") String sysCode, @Param("docOrgCode")String docOrgCode);
}

