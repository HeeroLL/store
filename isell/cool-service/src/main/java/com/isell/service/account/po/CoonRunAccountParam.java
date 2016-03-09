package com.isell.service.account.po;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 
 * 账单流水记录表
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-04]
 */
public class CoonRunAccountParam extends PageConfig{
	
	/**
	 * 店铺编码
	 */
	private String code;
	
	/**
	 * 类型 1：余额 2：提现中 3：已提现 4：累计收益
	 */
	private String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}