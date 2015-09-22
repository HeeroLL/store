package com.zebone.pubsub.server;

import java.util.HashMap;
import java.util.Map;

public class FreqUtil {
	  
	private static final Map<String,String> freqMap = new HashMap<String,String>();
	
	static{
		freqMap.put("1", "0 0/10 * * * ?");
		freqMap.put("2", "0 0/5 * * * ?");
	}
	
	
	public static String getFre(String freq){
		return freqMap.get(freq);
	}
}
