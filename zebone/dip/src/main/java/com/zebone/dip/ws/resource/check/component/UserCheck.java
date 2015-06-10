package com.zebone.dip.ws.resource.check.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zebone.check.CheckResult;
import com.zebone.log.ErrorType;
import com.zebone.log.RangeError;

public class UserCheck {

	private static final Log log = LogFactory.getLog(UserCheck.class);
	
	private static final String message = "人员数据不存在";
	
	public CheckResult checkUserData(Connection con,String org,String checkValue){
		int resultCount = 0;
	    CheckResult checkResult = new CheckResult(true);
		String querySql = "select count(*) as count from p_resource_user_info t " +
				" where t.code='"+checkValue+"' and t.MEDICAL_ORGAN_CODE='"+org+"'";
		
		try {
			PreparedStatement ps = con.prepareStatement(querySql);
			ResultSet rs =  ps.executeQuery();
			if(rs.next()){
				resultCount = rs.getInt("count");
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
