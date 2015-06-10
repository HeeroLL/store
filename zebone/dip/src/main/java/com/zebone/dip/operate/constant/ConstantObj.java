package com.zebone.dip.operate.constant;

public class ConstantObj {

	/**
	 * 服务类型 文档服务
	 */
	public static final String SERVICE_TYPE_FILE = "1";
	/**
	 * 服务类型 资源服务
	 */
	public static final String SERVICE_TYPE_RESOURCE = "2";
	/**
	 * 启动（服务） 
	 */
	public static final String SERVICE_STATE_RUN = "1";
	/**
	 * 停止（服务） 
	 */
	public static final String SERVICE_STATE_STOP = "2";
	/**
	 * 启动（节点） 
	 */
	public static final String NODE_STATE_RUN = "1";
	/**
	 * 停止（节点） 
	 */
	public static final String NODE_STATE_STOP = "2";
	/**
	 * 历史任务没有执行
	 */
	public static final String HISTORY_TASK_RUN = "1";
	/**
	 * 历史任务执行完成
	 */
	public static final String HISTORY_TASK_STOP = "2";
	
	/**
	 * 当前数据选择
	 */
	public static final String CURRENT_DATA_SELECT = "1";
	/**
	 * 当前数据没有选择
	 */
	public static final String CURRENT_DATA_NOT_SELECT = "0";
	/**
	 * 历史数据选择
	 */
	public static final String HISTORY_DATA_SELECT = "1";
	/**
	 * 历史数据没有选择
	 */
	public static final String HISTORY_DATA_NOT_SELECT = "0";
	/**
	 * 订阅停止种类（节点）
	 */
	public static final String EVENT_TYPE_NODE = "1";
	/**
	 * 订阅停止种类（服务）
	 */
	public static final String EVENT_TYPE_SERVICE= "2";
	
}
