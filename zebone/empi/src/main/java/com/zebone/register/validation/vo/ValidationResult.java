package com.zebone.register.validation.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;


/**
 * 校验结果
 * @author 陈阵 
 * @date 2013-7-31 下午2:06:49
 */
public class ValidationResult {
	
	/** 文档id **/
	private String docId;
	
	/** 返回的数据文档  document **/
	private Document dataDocument;
	
	/** 是否成功 **/
	private boolean sucess = true;
	
	/** 错误主信息 **/
	private ErrorMsg errorMsg = new ErrorMsg();
	
	/** 错误明细信息 **/
	private List<ErrorMsgDetail> errorMsgDetial = new ArrayList<ErrorMsgDetail>();
	
	/** 头部信息map **/
	private Map<String,String> headerMap = new HashMap<String,String>();
	

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public ErrorMsg getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(ErrorMsg errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<ErrorMsgDetail> getErrorMsgDetial() {
		return errorMsgDetial;
	}

	public void setErrorMsgDetial(List<ErrorMsgDetail> errorMsgDetial) {
		this.errorMsgDetial = errorMsgDetial;
	}

	public Document getDataDocument() {
		return dataDocument;
	}

	public void setDataDocument(Document dataDocument) {
		this.dataDocument = dataDocument;
	}

	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	
	
	
}
