package com.zebone.dip.dictionary.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典值转换类
 * @author 杨英
 * @version 2014-7-24 上午11:07
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
