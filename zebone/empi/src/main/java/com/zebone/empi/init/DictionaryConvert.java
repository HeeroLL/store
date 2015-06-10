package com.zebone.empi.init;

import java.util.HashMap;
import java.util.Map;

/**
 * 转换值类型
 * @author YinCM
 * @since 2013-9-8 20:38:44
 */
public class DictionaryConvert {
	private static Map<String,Map<String,String>> dictionary = new HashMap<String,Map<String,String>>();

	public static Map<String, Map<String, String>> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<String, Map<String, String>> dictionary) {
		DictionaryConvert.dictionary = dictionary;
	}
	
}
