package com.zebone.dip.metadata.vo;

import java.util.List;

/**
 * 文档数据映射表单form
 * @author cz
 *
 */
public class DocDataMapForm {
    
	private String docId;
	
	public List<FDocMapping> ddm;

	public List<FDocMapping> getDdm() {
		return ddm;
	}

	public void setDdm(List<FDocMapping> ddm) {
		this.ddm = ddm;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
}
