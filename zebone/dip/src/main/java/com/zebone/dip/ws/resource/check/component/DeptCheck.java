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

public class DeptCheck {

	private static final Log log = LogFactory.getLog(DeptCheck.class);
	
	private static final String message = "科室数据不存在";
	
	public CheckResult checkDeptData(Connection con,String org,String checkValue){
		int resultCount = 0;
	    CheckResult checkResult = new CheckResult(true);
		String querySql = "select count(*) as count from p_resource_dept_info t " +
				" where t.dept_code ='"+checkValue+"' and t.ORG_CODE='"+org+"'";
		
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
