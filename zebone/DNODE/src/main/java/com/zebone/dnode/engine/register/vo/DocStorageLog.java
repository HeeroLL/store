package com.zebone.dnode.engine.register.vo;


public class DocStorageLog {
    private String id;

    private String parentId;

    private String createTime;

    private String empiId;

    private String docOperState;

    private String docOrg;

    private String docRegisterState;

    private String docTypeCode;

    private String docNo;

    private String docXml;
    
    private String docParseState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getEmpiId() {
        return empiId;
    }

    public void setEmpiId(String empiId) {
        this.empiId = empiId == null ? null : empiId.trim();
    }

    public String getDocOperState() {
        return docOperState;
    }

    public void setDocOperState(String docOperState) {
        this.docOperState = docOperState == null ? null : docOperState.trim();
    }

    public String getDocOrg() {
        return docOrg;
    }

    public void setDocOrg(String docOrg) {
        this.docOrg = docOrg == null ? null : docOrg.trim();
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode == null ? null : docTypeCode.trim();
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo == null ? null : docNo.trim();
    }

    public String getDocXml() {
        return docXml;
    }

    public void setDocXml(String docXml) {
        this.docXml = docXml == null ? null : docXml.trim();
    }

	public String getDocRegisterState() {
		return docRegisterState;
	}

	public void setDocRegisterState(String docRegisterState) {
		this.docRegisterState = docRegisterState;
	}

	public String getDocParseState() {
		return docParseState;
	}

	public void setDocParseState(String docParseState) {
		this.docParseState = docParseState;
	}
}