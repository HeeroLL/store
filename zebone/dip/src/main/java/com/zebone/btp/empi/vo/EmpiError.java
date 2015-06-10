package com.zebone.btp.empi.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 错误日志表 对应错误表EMPI_ERROR
 * 
 * @author ouyangxin 2013-1-21
 */
public class EmpiError implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 流水号 */
	private String errId;

	/** 证件号 */
	private String empiId;

	/** 姓名 */
	private String userName;

	/** 卡号 */
	private String errCardNo;

	/** 卡类型 */
	private String errCardType;

	/** 产生时间（yyyyMMddhhmmss） */
	private String errCreateDate;

	/**
	 * 错误类型 E01证件格式不正确;E02证件已经注册;E03卡号格式不正确;E04卡已经注册;E05EMPI已经注册;E06卡注册但证件未注册;
	 * E07其他错误
	 */
	private String errType;

	public String getErrId() {
		return errId;
	}

	public void setErrId(String errId) {
		this.errId = errId;
	}

	public String getEmpiId() {
		return empiId;
	}

	public void setEmpiId(String empiId) {
		this.empiId = empiId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getErrCardNo() {
		return errCardNo;
	}

	public void setErrCardNo(String errCardNo) {
		this.errCardNo = errCardNo;
	}

	public String getErrCardType() {
		return errCardType;
	}

	public void setErrCardType(String errCardType) {
		this.errCardType = errCardType;
	}

	public String getErrCreateDate() {
		return errCreateDate;
	}

	public void setErrCreateDate(String errCreateDate) {
		this.errCreateDate = errCreateDate;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	/**
	 * 重写toString方法
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
