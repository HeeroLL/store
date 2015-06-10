package com.zebone.dnode.engine.clean.pojo;


/**
 * 清洗配置表
 * P_CLEAR_CONF
 * @author cz
 *
 */
public class ClearConfig {
	
	private String id;
	
	/** 表配置ID **/
	private String table_id;
	
	/** 发布标志 **/
	private String deployFlag; 
	
	/** 重新清洗标志 **/
	private String againFlag;
	
	/** 任务ID **/
	private String taskId;
    

	public ClearConfig() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}

	public String getDeployFlag() {
		return deployFlag;
	}

	public void setDeployFlag(String deployFlag) {
		this.deployFlag = deployFlag;
	}

	public String getAgainFlag() {
		return againFlag;
	}

	public void setAgainFlag(String againFlag) {
		this.againFlag = againFlag;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	
	
	
}
