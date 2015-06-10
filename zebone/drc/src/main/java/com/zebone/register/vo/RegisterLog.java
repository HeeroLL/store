package com.zebone.register.vo;

public class RegisterLog {
    private String id;

    private String mainId;

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

    public String getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
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
}