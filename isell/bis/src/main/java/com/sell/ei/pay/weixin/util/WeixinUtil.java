package com.sell.ei.pay.weixin.util;

import java.lang.reflect.Field;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.sell.core.util.Coder;

/**
 * 微信支付工具类
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
public final class WeixinUtil {
    
    /**
     * 获取参数
     * 
     * @param map map
     * @return 拼装后的map
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
        return WeixinUtil.getParameter(map);
    }
    
    /**
     * 生成加密后的签名
     * 
     * @param param 参数
     * @param key 密钥
     * @return 加密后的签名
     */
    public static String encryptString(String param, String key) {
        String stringSignTemp = param + "@key=" + key;
        return Coder.encodeMd5(stringSignTemp).toUpperCase();
    }
}
