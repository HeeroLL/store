package com.zebone.register.vo;

public class RegisterMain {
    private String id;

    private String empiId;

    private String docDataSource;

    private String docOrg;

    private String docState;

    private String docOperState;

    private String docTypeCode;

    private String docNo;

    private String docWebUrl;

    private String registerTime;
    
    private String docVersion;

    //文档集序列号
    private String docSerialNo;

    //文档管理机构
    private String docManageOrg;

    //文档标题
    private String docTitle;

    //父文档编号
    private String parentDocNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEmpiId() {
        return empiId;
    }

    public void setEmpiId(String empiId) {
        this.empiId = empiId == null ? null : empiId.trim();
    }

    public String getDocDataSource() {
        return docDataSource;
    }

    public void setDocDataSource(String docDataSource) {
        this.docDataSource = docDataSource == null ? null : docDataSource.trim();
    }

    public String getDocOrg() {
        return docOrg;
    }

    public void setDocOrg(String docOrg) {
        this.docOrg = docOrg == null ? null : docOrg.trim();
    }

    public String getDocState() {
        return docState;
    }

    public void setDocState(String docState) {
        this.docState = docState == null ? null : docState.trim();
    }

    public String getDocOperState() {
        return docOperState;
    }

    public void setDocOperState(String docOperState) {
        this.docOperState = docOperState == null ? null : docOperState.trim();
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

    public String getDocWebUrl() {
        return docWebUrl;
    }

    public void setDocWebUrl(String docWebUrl) {
        this.docWebUrl = docWebUrl == null ? null : docWebUrl.trim();
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime == null ? null : registerTime.trim();
    }

	public String getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}

    public String getDocSerialNo() {
        return docSerialNo;
    }

    public void setDocSerialNo(String docSerialNo) {
        this.docSerialNo = docSerialNo == null ? null : docSerialNo.trim();
    }

    public String getDocManageOrg() {
        return docManageOrg;
    }

    public void setDocManageOrg(String docManageOrg) {
        this.docManageOrg = docManageOrg == null ? null : docManageOrg.trim();
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle == null ? null : docTitle.trim();
    }

    public String getParentDocNo() {
        return parentDocNo;
    }

    public void setParentDocNo(String parentDocNo) {
        this.parentDocNo = parentDocNo == null ? null : parentDocNo.trim();
    }
}