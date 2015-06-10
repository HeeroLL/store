package com.zebone.dnode.engine.validation.enu;

/**
 * 校验错误类型
 * @author 陈阵 
 * @date 2013-8-2 下午12:13:07
 */
public enum ErrorType {
	 /** Xsd **/
     XSD("C_006"),
     
     /** 值域  **/
     VALUE_FIELD("C_005"),
     
     /** 业务 **/
     BUSINESS("C_001"),
     
     /** 数据格式 **/
     DATA_FORMAT("C_002"),
     
     /** 唯一性校验**/
     ONLY("C_004"),
     
     /** 元数据  **/
     METADATA("C_003"),
     
     /** 系统错误  **/
     SYSTEM("C_999");
     
     /** 错误类型 **/
     private String type;
     
     private ErrorType(String type){
    	this.type = type;
     }
     
     public String getType(){
    	 return type;
     }
     

     
}
