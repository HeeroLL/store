package com.zebone.register.validation.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


/**
 * 文档配置
 * @author 陈阵 
 * @date 2013-7-29 上午9:34:23
 */
@Alias("docConf1")
public class DocConf implements Serializable{
    
	private static final long serialVersionUID = 3693896654755283286L;

	/** 文档主键 **/
    private String id;
    
    /** 文档对应的xsd内容  **/
    private String docXsd;
    
   
	public DocConf() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocXsd() {
		return docXsd;
	}

	public void setDocXsd(String docXsd) {
		this.docXsd = docXsd;
	}
    
    

  
}