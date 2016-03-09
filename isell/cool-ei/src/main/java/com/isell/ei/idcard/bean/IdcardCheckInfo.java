package com.isell.ei.idcard.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 身份证验证返回信息
 * 
 * @author wangpeng
 * @version [版本号, 2016年3月3日]
 */
public class IdcardCheckInfo {
	
	/**
	 * 0：成功 -1：失败
	 */
	private int errNum;
	
	/**
	 * 返回信息
	 */
	private String retMsg;
	
	/**
	 * 身份证信息
	 */
	private IdcardRetData retData;
	
	 /**
     * 重写 toString
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

	/**
	 * 0：成功 -1：失败
	 */
	public int getErrNum() {
		return errNum;
	}

	/**
	 * 0：成功 -1：失败
	 */
	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}

	/**
	 * 返回信息
	 */
	public String getRetMsg() {
		return retMsg;
	}

	/**
	 * 返回信息
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	/**
	 * 身份证信息
	 */
	public IdcardRetData getRetData() {
		return retData;
	}
	
	/**
	 * 身份证信息
	 */
	public void setRetData(IdcardRetData retData) {
		this.retData = retData;
	}
	
	

}
