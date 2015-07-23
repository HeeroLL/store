package com.sell.core.util;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * json 工具类
 * 
 * @author 宋俊杰
 */
public class JsonUtil {
    private static ObjectMapper defaultObjectMapper;
    static {
        defaultObjectMapper = getObjectMapper();
        defaultObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    
    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 字段值为null的不序列化成json
        objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
        return objectMapper;
    }
    
    /**
     * 将规定对象转换成json字符串。日期类型按照 yyyy-MM-dd HH:mm:ss 的格式转换成字符串
     * 
     * @param obj 转换对象
     * @return json
     */
    public static String writeValueAsString(Object obj) {
        try {
            return defaultObjectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 将规定对象转换成json字符串。日期类型按照给定的样式转换
     * 
     * @param obj 转换对象
     * @param dateStyle 日期格式
     * @return json
     */
    public static String writeValueAsString(Object obj, String dateStyle) {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(dateStyle));
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
