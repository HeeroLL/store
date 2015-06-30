package com.sell.core.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree的Json对象，用于处理前端Tree的传值
 * @author 王聚金
 * @date 2014-01-02
 */
public class JsonTree {

	private String id;
	private String text;
	private String state = "closed";
	private int type;
	private String iconCls;
	private List<JsonTree> children = new ArrayList<JsonTree>();
	
	public JsonTree(){}
	
	public JsonTree(String id, String text, String iconCls){
		this.id = id;
		this.text = text;			
		this.iconCls = iconCls;
	}
	
	/**
	 * @param id 
	 * @param text 显示名称
	 * @param iconCls 图标
	 * @param bool 节点是否打开
	 */
	public JsonTree(String id, String text, String iconCls, boolean bool){
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.state = bool?"open":"closed";
		this.type = bool?1:2;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public List<JsonTree> getChildren() {
		return children;
	}

	public void setChildren(List<JsonTree> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

}
