package com.zebone.dnode.task.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务枚举类
 * @author cz
 */
public enum TaskType {
	SQL(2), // sql任务
	ETL(1), // etl任务
	JAVA(3), //java类
	CLEAN(5), //清洗任务
	OTHER(10), // 其它任务
	TEST(4); //判断前置机的运行状态

	private final int itype;
	
	private final static Map<String,TaskType> map = new HashMap<String, TaskType>();
	
	static{
		for(TaskType tt : values()){
			map.put(String.valueOf(tt.getItype()), tt);
		}
	}

	TaskType(int type) {
		this.itype = type;
	}
	
	public int getItype(){
		return itype;
	}
	
	public static TaskType getTypeFromString(String str){
		return map.get(str);
	}
}
