package com.zebone.dip.metadata.vo;

import java.io.Serializable;

/**
 * 解析xml生成的结点对象
 * @author cz
 *
 */
public class MdNode implements Serializable {
	
	private static final long serialVersionUID = -6131805742280716444L;
    
	/** 结点的中文名称  **/
	private String nodeCName;
    
	/** 结点的英文名称  **/
	private String nodeEName;
	
	/** 结点对应的元数据编码  **/
	private String nodeCode;
	
	/** 结点对应的元数据表的主键id **/
	private String fieldId;
	
	/** 结点对应的层次  0是最顶层  **/
	private int level;
	
	/** 对应的父亲节点  如果没有父亲就为null **/
	private MdNode pNode;
	
	/** 文档元数据节点  一般为 slot标签节点 **/
	private boolean isFloor;
	
	/** 节点的xpath **/
	private String xpath;
	
	/** 节点id 无实际意义**/
	private int id;

	
	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public MdNode() {
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

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public MdNode getpNode() {
		return pNode;
	}

	public void setpNode(MdNode pNode) {
		this.pNode = pNode;
	}

	public boolean isFloor() {
		return isFloor;
	}

	public void setFloor(boolean isFloor) {
		this.isFloor = isFloor;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
