package com.zebone.dip.log.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 类描述：
 * @author: caixl
 * @date： 日期：Sep 6, 2013
 * @version 1.0
 */
@XStreamAlias("error")
public class Error {
	/**错误代码*/
	@XStreamAlias("code")
	private String code;
	/**错误详细描述*/
	@XStreamAlias("desc")
	private String desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
