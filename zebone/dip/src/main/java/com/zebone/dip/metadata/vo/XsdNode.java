package com.zebone.dip.metadata.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 用来生成xsd文件的类
 * @author 陈阵 
 * @date 2013-7-20 上午9:21:43
 */
public class XsdNode implements Serializable{

	private static final long serialVersionUID = 8013377529478681085L;
	
	/** xml标签名字 **/
	private String nodeName;
	
	/** 父亲结点 **/
	private XsdNode parentNode;
	
	/** 重复性 **/
	private String repeat;
	
	/** 选择性 **/
	private String isSelect;
	
	/** 元素属性的值  **/
	private String nameValue;
	
	/** 儿子结点 **/
	private List<XsdNode> sonNode = new ArrayList<XsdNode>();

	
	public XsdNode() {
		super();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public XsdNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(XsdNode parentNode) {
		this.parentNode = parentNode;
	}

	public List<XsdNode> getSonNode() {
		return sonNode;
	}

	public void setSonNode(List<XsdNode> sonNode) {
		this.sonNode = sonNode;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}

	public String getNameValue() {
		return nameValue;
	}

	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}

    

}
