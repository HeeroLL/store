package com.zebone.dnode.engine.analyze.vo;

/**
 * 文档信息。对应文档参数表(D_DOC_STORAGE)中的字段
 * 
 * @author songjunjie
 * @version 2013-8-13 下午04:02:04
 */
public class DocumentInfo {
	private String id;
	/**
	 * 创建时间
	 */
	private String createTimie;
	/**
	 * EMPI ID
	 */
	private String empiId;
	/**
	 * xml格式的文档数据
	 */
	private String docxml;
	/**
	 * 操作状态（新增1，更新2，删除0）
	 */
	private String operatorState;
	/**
	 * 文档来源机构
	 */
	private String organ;
	/**
	 * 注册状态(1已注册；2已解析)
	 */
	private String registerState;
	/**
	 * 文档类型代码
	 */
	private String typeCode;
	/**
	 * 文档编号
	 */
	private String no;
	/**
	 * 文档解析状态（0 未解析 1已解析 2 重新解析 3 已重新解析）
	 */
	private String parseState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTimie() {
		return createTimie;
	}

	public void setCreateTimie(String createTimie) {
		this.createTimie = createTimie;
	}

	public String getEmpiId() {
		return empiId;
	}

	public void setEmpiId(String empiId) {
		this.empiId = empiId;
	}

	public String getDocxml() {
		return docxml;
	}

	public void setDocxml(String docxml) {
		this.docxml = docxml;
	}

	public String getOperatorState() {
		return operatorState;
	}

	public void setOperatorState(String operatorState) {
		this.operatorState = operatorState;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public String getRegisterState() {
		return registerState;
	}

	public void setRegisterState(String registerState) {
		this.registerState = registerState;
	}

	/**
	 * 文档类型代码
	 * @return
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * 文档类型代码
	 * @param typeCode
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getParseState() {
		return parseState;
	}

	public void setParseState(String parseState) {
		this.parseState = parseState;
	}

}
