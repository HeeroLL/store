package com.zebone.check.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zebone.check.CheckResult;
import com.zebone.log.ErrorType;
import com.zebone.log.RangeError;

public class MasterDataCheck {
	
	private static final Log log = LogFactory.getLog(MasterDataCheck.class);
	
	private static final String message = "主数据不存在";
	
	public CheckResult checkMasterData(Connection con,String code,String checkValue){
		int resultCount = 0;
		String tableName = null;
	    CheckResult checkResult = new CheckResult(true);
		String queryTableNameSql = " SELECT TABLE_NAME AS tableName FROM P_MASTER_DATA WHERE CODE = '"+code+"'";
		ResultSet rs = null;		  		       
		try {
			PreparedStatement ps = con.prepareStatement(queryTableNameSql);
			rs = ps.executeQuery();
			if(rs.next()){
				tableName = rs.getString("tableName");
				String queryMasterDataCountSql = "SELECT COUNT(*) AS count FROM " +  tableName + " WHERE MD_CODE='"+checkValue+"'";
				PreparedStatement psMD = con.prepareStatement(queryMasterDataCountSql);
				ResultSet rsMD = psMD.executeQuery();
				if(rsMD.next()){
					resultCount = rsMD.getInt("count");
				}
			}
		} catch (SQLException e) {
			checkResult.setSuccess(false);
			checkResult.setErrorCode(ErrorType.SYSTEM.getErrorCode());
			checkResult.setErrorMessage(ErrorType.SYSTEM.getErrorDesc());
			log.error(e.getMessage(),e);
			return checkResult;
		}finally{
			if(resultCount == 0){
				checkResult.setSuccess(false);
				checkResult.setErrorCode(ErrorType.VALUE_CHECK.getErrorCode()+"-"+RangeError.VALUE_NO.getErrorCode());
				checkResult.setErrorMessage(ErrorType.VALUE_CHECK.getErrorDesc()+"--"+RangeError.VALUE_NO.getErrorDesc()+"--"+message);
			}
		}
		return checkResult;
	   
	}
}
