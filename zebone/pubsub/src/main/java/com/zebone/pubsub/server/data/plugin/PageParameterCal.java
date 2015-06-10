package com.zebone.pubsub.server.data.plugin;

public abstract class PageParameterCal{
	
	public static final int BATCH_NUM = 1000;

	abstract int[] calPageParameter(int curPage);
	
	public int calPage(int dataCount){
		int totalPage = 0;
		int mod = dataCount % BATCH_NUM;
		if (mod == 0) {
			totalPage = dataCount / BATCH_NUM;
		} else {
			totalPage = dataCount / BATCH_NUM + 1;
		}
		return totalPage;
	}
      
}
