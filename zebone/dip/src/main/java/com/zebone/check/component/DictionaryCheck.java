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

public class DictionaryCheck {
	
	private static final Log log = LogFactory.getLog(DictionaryCheck.class);
	
	private static final String message = "字典类型编码不存在";

	/**
	 * 检查字典是否存在
	 * LinBin
	 * Apr 17, 2014
	 * @param con
	 * @param typeCode
	 * @param checkValue
	 * @return
	 */
	public CheckResult checkDictionary(Connection con,String typeCode,String checkValue){
		int resultCount = 0;
	    CheckResult checkResult = new CheckResult(true);
		String querySql = "SELECT COUNT(*) AS count FROM P_DICTIONARY_TYPE pdt LEFT JOIN P_DICTIONARY pd " +
				" ON pdt.TYPE_ID = pd.DICTTYPE_ID " +
				" WHERE pdt.STANDARD_CODE='"+typeCode+"' AND PDT.IS_DELETED='0' "+
				" AND DICT_CODE='"+checkValue+"'";
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
	
	/**
	 * 检查字典是否存在（多选）
	 * LinBin
	 * Apr 17, 2014
	 * @param con
	 * @param typeCode
	 * @param checkValue
	 * @return
	 */
	public CheckResult checkDictionaryList(Connection con,String typeCode,String checkValue){
		CheckResult checkResult = new CheckResult(true);
		int resultCount = 0;
		String[] values = checkValue.split(",");
		try {
			for(String str : values){
				resultCount = 0;
				String querySql = "SELECT COUNT(*) AS count FROM P_DICTIONARY_TYPE pdt LEFT JOIN P_DICTIONARY pd " +
						" ON pdt.TYPE_ID = pd.DICTTYPE_ID " +
						" WHERE pdt.STANDARD_CODE='"+typeCode+"' "+
						" AND DICT_CODE='"+str+"'";
				PreparedStatement ps = con.prepareStatement(querySql);
				ResultSet rs =  ps.executeQuery();
				if(rs.next()){
					resultCount = rs.getInt("count");
				}
				rs.close();
				ps.close();
				if(resultCount == 0){
					return checkResult;
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
