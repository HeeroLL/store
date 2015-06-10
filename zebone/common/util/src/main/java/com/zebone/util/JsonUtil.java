package com.zebone.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * json 工具类
 * 
 * @author 宋俊杰
 */
public class JsonUtil {
    private static ObjectMapper defaultObjectMapper = new ObjectMapper();
    static {
        defaultObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * 将规定对象转换成json字符串。日期类型按照 yyyy-MM-dd HH:mm:ss 的格式转换成字符串
     * 
     * @param obj
     * @return
     */
    public static String writeValueAsString(Object obj) {
        try {
            return defaultObjectMapper.writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 将规定对象转换成json字符串。日期类型按照给定的样式转换
     * 
     * @param obj
     * @param dateStyle 日期格式
     * @return
     */
    public static String writeValueAsString(Object obj, String dateStyle) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(dateStyle));
        try {
            return objectMapper.writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 将json字符串转为对象
     * 
     * @param <T> 返回类型
     * @param content json字符串
     * @param valueType 返回类型
     * @return 对象
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return defaultObjectMapper.readValue(content, valueType);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", 50);
        map.put("生日", new Date());
        System.out.println(writeValueAsString(map));
        System.out.println(writeValueAsString(map, "yyyy年MM月dd日HH时mm分"));
    }
}
