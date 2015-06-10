package com.zebone.register.analyze.vo;

import java.util.List;

/**
 * 解析数据信息，保存文档编号，表名，字段列表，以及字段值
 * 
 * @author songjunjie
 * @version 2013-8-19 上午08:49:49
 */
public class AnalyzeDataInfo {
	/**
	 * 文档编号
	 */
	private String docNo;
    /**
     * 文档来源机构
     */
    private String sourceOrg;
	/**
	 * 文档对应的EMPI 编号
	 */
	private String empiId;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 字段列表
	 */
	private List<String> columnList;
	/**
	 * 字段值
	 */
	private List<List<String>> paramList;
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getEmpiId() {
		return empiId;
	}
	public void setEmpiId(String empiId) {
		this.empiId = empiId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<String> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
	public List<List<String>> getParamList() {
		return paramList;
	}
	public void setParamList(List<List<String>> paramList) {
		this.paramList = paramList;
	}
    public String getSourceOrg() {
        return sourceOrg;
    }

    public void setSourceOrg(String sourceOrg) {
        this.sourceOrg = sourceOrg;
    }
}
