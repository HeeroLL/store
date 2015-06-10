package com.zebone.dip.log.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


/**
 * 类描述：
 * @author: caixl
 * @date： 日期：Sep 6, 2013
 * @version 1.0
 */
@XStreamAlias("syslog")
public class Syslog {
	public static final String TYPE_UPLOAD = "1";
	public static final String TYPE_RECEIVE = "2";
	public static final String TYPE_CHECK = "3";
	public static final String TYPE_STORAGE = "4";
	public static final String TYPE_REGISTER = "5";
	public static final String TYPE_ANALYZE = "6";
	
	public static final String STATUS_SUCCESS = "1";
	public static final String STATUS_FAILURE = "0";
	
	/**日志种类*/
	@XStreamAlias("category")
	@XStreamAsAttribute
	private String category;
	
	/**
	 * 日志类型<br/>
	 * 上传(1)、接收(2)、校验(3)、存储(4)、注册(5)、解析(6)
	 **/
	@XStreamAlias("type")
	private String type;
	
	/**文档编号*/
	@XStreamAlias("docNo")
	private String docNo;
	
	/**日志标识*/
	@XStreamAlias("logID")
	private String logID;
	
	/**时间*/
	@XStreamAlias("logTime")
	private String logTime;
	
	/**文档来源机构编码 */
	@XStreamAlias("deptCode")
	private String deptCode;
	
	/**文档类型代码*/
	@XStreamAlias("doctype")
	private String doctype;
	
	/**状态码 1成功 0失败*/
	@XStreamAlias("status")
	private String status;
	
	/**错误信息*/
	@XStreamAlias("error")
	private Error error;
	
	/**方法执行时间*/
	@XStreamAlias("execTime")
	private long execTime;	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 日志类型<br/>
	 * 上传(1)、接收(2)、校验(3)、存储(4)、注册(5)、解析(6)
	 **/
	public String getType() {
		return type;
	}
	/**
	 * 日志类型<br/>
	 * 上传(1)、接收(2)、校验(3)、存储(4)、注册(5)、解析(6)
	 **/
	public void setType(String type) {
		this.type = type;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	public long getExecTime() {
		return execTime;
	}
	public void setExecTime(long execTime) {
		this.execTime = execTime;
	}
}
