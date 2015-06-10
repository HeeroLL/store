package com.zebone.pubsub.server.pojo;

public class DcDataJobParameter {
   
	private String orgCode;
		
	private String beginDate;
	
	private String endDate;
	
	private String docTypeCode;
	
	private boolean isHistory;
	
	private String pubSubserviceId;


	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}



	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public boolean isHistory() {
		return isHistory;
	}

	public void setHistory(boolean isHistory) {
		this.isHistory = isHistory;
	}

	public String getPubSubserviceId() {
		return pubSubserviceId;
	}

	public void setPubSubserviceId(String pubSubserviceId) {
		this.pubSubserviceId = pubSubserviceId;
	}
	

}
