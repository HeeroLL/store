package com.zebone.dip.operate.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 订阅数据频率
 * @author LinBin
 * May 8, 2014
 */
public class SubFrequ {
	
	private Map<String, String> sub = new HashMap<String, String>();
	
	private String lastSubTime;

	public String getLastSubTime() {
		return lastSubTime;
	}

	public void setLastSubTime(String lastSubTime) {
		this.lastSubTime = lastSubTime;
	}

	public Map<String, String> getSub() {
		return sub;
	}

	public void setSub(Map<String, String> sub) {
		this.sub = sub;
	}
	
}
