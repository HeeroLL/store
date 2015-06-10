package com.zebone.pubsub.server.data.plugin;

public class OraclePageParameterCal extends PageParameterCal {

	@Override
	public int[] calPageParameter(int curPage) {
		// TODO Auto-generated method stub
		int start = curPage*BATCH_NUM+1;
		int end = (curPage+1)*BATCH_NUM;
        return new int[]{start,end};
	}

}
