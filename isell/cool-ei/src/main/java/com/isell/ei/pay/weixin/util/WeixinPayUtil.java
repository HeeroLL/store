package com.isell.ei.pay.weixin.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.isell.core.util.Coder;

/**
 * 微信支付工具类
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
public final class WeixinPayUtil {
    
    /**
     * 将map转成xml
     *
     * @param map map
     * @return xml字符串
     */
    public static String transMapToXml(Map<String, Object> map) {
        StringBuilder builder = new StringBuilder("<xml>");
        for (Entry<String, Object> entry : map.entrySet()) {
            builder.append('<').append(entry.getKey()).append('>');
            builder.append(entry.getValue());
            builder.append("</").append(entry.getKey()).append('>');
        }
        
        builder.append("</xml>");
        return builder.toString();
    }
    
    /**
     * 将xml转map
     *
     * @param xml xml字符串
     * @return treeMap
     */
    public static TreeMap<String, Object> transXmlToMap(String xml) {
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        try {
            Document document = DocumentHelper.parseText(xml);
            List<?> list = document.getRootElement().elements();
            for (Object obj : list) {
                Element element = (Element)obj;
                if (StringUtils.isNotEmpty(element.getText())) {
                    map.put(element.getName(), element.getText());
                }
            }
            
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
            
        return map;
    }
    
    /**
     * 获取参数
     * 
     * @param map map
     * @return 拼装后的参数
     */
    public static String getParameter(TreeMap<String, Object> map) {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        for (Entry<String, Object> entry : map.entrySet()) {
            if (index++ != 0) {
                builder.append('&');
            }
            builder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return builder.toString();
    }
    
    /**
     * 获取参数
     * 
     * @param map map
     * @return 拼装后的参数
     */
    public static String getCustomsParameter(TreeMap<String, String> map) {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> entry : map.entrySet()) {
            if (index++ != 0) {
                builder.append('&');
            }
            builder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return builder.toString();
    }
    
    /**
     * 生成签名
     * 
     * @param obj 参数对象
     * @return 签名
     */
    public static String generateSign(Object obj) {
        if (obj == null) {
            return null;
        }
        // 反射获取所有字段
        Field[] fields = obj.getClass().getDeclaredFields();
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        try {
            
            for (Field field : fields) {
                field.setAccessible(true);
                if ("sign".equals(field.getName())) {
                    continue;
                }
                Object value = field.get(obj);
                if (value == null || "".equals(String.valueOf(value))) {
                    continue;
                }
                map.put(field.getName(), value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return WeixinPayUtil.getParameter(map);
    }
    
    /**
     * 生成加密后的签名(转大写)
     * 
     * @param param 参数
     * @param key 密钥
     * @return 加密后的签名
     */
    public static String encryptString(String param, String key) {
        String stringSignTemp = param + "&key=" + key;
        return Coder.encodeMd5(stringSignTemp).toUpperCase();
    }
}
