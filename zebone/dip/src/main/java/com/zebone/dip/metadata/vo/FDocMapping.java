package com.zebone.dip.metadata.vo;

import java.util.List;


/**
 * 文档数据映射表单参数
 * @author cz
 *
 */
public class FDocMapping {

	/** 主键  **/
    private String id;


    /** 文档主键   **/
    private String docId;


    /** 数据源主键 **/
    private String fieldId;


    /** 节点的xpath **/
    private String xpath;


    /** 映射的列主键 id **/
    private String cloumnId;


    /** 重复  **/
    private String repeat;


    /** 可选  **/
    private String isSelect;


    /** 是否是元数据 **/
    private String isFeild;
    
    /** 函数   **/
    private String func;
    
    /** 组名 **/
    private String group;
    
    List<DocMap2> dm2;

    
    public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDocId() {
        return docId;
    }


    public void setDocId(String docId) {
        this.docId = docId == null ? null : docId.trim();
    }

    public String getFieldId() {
        return fieldId;
    }


    public void setFieldId(String fieldId) {
        this.fieldId = fieldId == null ? null : fieldId.trim();
    }


    public String getXpath() {
        return xpath;
    }


    public void setXpath(String xpath) {
        this.xpath = xpath == null ? null : xpath.trim();
    }


    public String getCloumnId() {
        return cloumnId;
    }


    public void setCloumnId(String cloumnId) {
        this.cloumnId = cloumnId == null ? null : cloumnId.trim();
    }


    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat == null ? null : repeat.trim();
    }


    public String getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect == null ? null : isSelect.trim();
    }

    public String getIsFeild() {
        return isFeild;
    }

    public void setIsFeild(String isFeild) {
        this.isFeild = isFeild == null ? null : isFeild.trim();
    }

	public List<DocMap2> getDm2() {
		return dm2;
	}

	public void setDm2(List<DocMap2> dm2) {
		this.dm2 = dm2;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
    
	
    
}
