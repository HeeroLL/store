package com.zebone.register.validation.enu;



/**
 * 操作类型
 * @author 林彬 
 * @date 2015-6-3 上午9:31:33
 */
public enum OpeType {
	
    DATA_INSERT("1","数据新增"),
    
    DATA_UPDATE("2","数据更新"),
    
    DATA_DELETE("3","数据删除"),
    
    DATA_QUERY("4","数据查询");
    

    
	private String errorCode;
	
	private String errorMsg;
	
	private OpeType(String errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	
}
