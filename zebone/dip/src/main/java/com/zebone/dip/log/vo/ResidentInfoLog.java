package com.zebone.dip.log.vo;


public class ResidentInfoLog extends ResidentInfo {
   
    private String id;
    
    /**
     * 1.新增 2.更新 3.删除
     */
    private String operState;

	public String getOperState() {
		return operState;
	}

	/**
	 * 
	 * @param operState ResidentInfoEnum
	 */
	public void setOperState(String operState) {
		this.operState = operState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	 
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
     * 查询开始时间
     */
    private String startSearchTime;
    
    /**
     * 查询结束时间
     */
    private String endSearchTime;

	public String getStartSearchTime() {
		return startSearchTime;
	}

	public void setStartSearchTime(String startSearchTime) {
		this.startSearchTime = startSearchTime;
	}

	public String getEndSearchTime() {
		return endSearchTime;
	}

	public void setEndSearchTime(String endSearchTime) {
		this.endSearchTime = endSearchTime;
	}
    
    
}