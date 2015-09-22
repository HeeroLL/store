package com.zebone.btp.core.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zebone.util.JsonUtil;

public class JsonUtilTest {
	@Test
	public void toJsonForInteger(){
		Map map = new HashMap();
//		map.put("key1", 0);
//		map.put("key2", new Integer(0));
//		map.put("key3", null);
		String json = JsonUtil.writeValueAsString(map);
		System.out.println(json);
	}
}
