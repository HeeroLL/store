package com.zebone.dip.metadata.vo;

import java.io.Serializable;

/**
 * 文档数据映射用于显示表格的对象
 * @author cz
 *
 */
public class DocDataMap implements Serializable{
	
	private static final long serialVersionUID = 4710030714943655213L;
    
	/** id编号 用于显示树状时用的 **/
	private int id;
	
	/** 结点的中文名称  **/
	private String nodeCName;
	
	/** 结点的英文名称  **/
	private String nodeEName;
	
	/** 结点位于的层次  **/
	private int level;
	
	/** 父结点的编号  **/
	private int pid;
	
	/** 父亲结点的中文名称 **/
	private String pNodeCName;
	
	/** 父亲结点的英文名称 **/
	private String pNodeEName;
	
	/** 对应的源数据  **/
	private String fieldId;
	
	/** 对应的表的名称  **/
	private String tableName;
	
	/** 对应的表主键id **/
	private String tableId;
	
	/** 对应的列的名称 **/
	private String colName;
	
	/**  对应的列的id **/
	private String colId;
	
	/** 是否是slot元素 **/
	private boolean floor;
	
	/** 对应的显示操作代码显示  新装外键映射 **/
	private String ope;
	
	/** table列显示的内容 **/
	private String table;
	
	/** 节点的xpath **/
	private String xpath;
	
	/** col列显示的内容  **/
    private String col;
    
    /** 对应的组**/
    private String group;
    
    /** 对应的文档结构映射表主键  **/
    private String mappingId;
    

	public DocDataMap() {
		super();
	}

	public String getNodeCName() {
		return nodeCName;
	}

	public void setNodeCName(String nodeCName) {
		this.nodeCName = nodeCName;
	}

	public String getNodeEName() {
		return nodeEName;
	}

	public void setNodeEName(String nodeEName) {
		this.nodeEName = nodeEName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getpNodeCName() {
		return pNodeCName;
	}

	public void setpNodeCName(String pNodeCName) {
		this.pNodeCName = pNodeCName;
	}

	public String getpNodeEName() {
		return pNodeEName;
	}

	public void setpNodeEName(String pNodeEName) {
		this.pNodeEName = pNodeEName;
	}


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColId() {
		return colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isFloor() {
		return floor;
	}

	public void setFloor(boolean floor) {
		this.floor = floor;
	}

	public String getOpe() {
		return ope;
	}

	public void setOpe(String ope) {
		this.ope = ope;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMappingId() {
		return mappingId;
	}

	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}

}
