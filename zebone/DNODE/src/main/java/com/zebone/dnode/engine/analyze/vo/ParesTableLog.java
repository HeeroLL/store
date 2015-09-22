package com.zebone.dnode.engine.analyze.vo;

/**
 * 解析文档到业务表后，要记录此项操作的日志到数据库中以便通知其他模块哪些表已经发生变化了。
 * @author songjunjie
 * @version 2013-9-12 上午10:48:09
 */
public class ParesTableLog {
	private String id;
	private String createTime;
	private String tableName;
	private String empiNo;
	private String docNo;
	private String dataFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEmpiNo() {
		return empiNo;
	}

	public void setEmpiNo(String empiNo) {
		this.empiNo = empiNo;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}
}
