package com.isell.ei.pay.alipay.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.isell.core.util.Coder;

/**
 * 支付宝支付工具类
 * 
 * @author lilin
 * @version [版本号, 2015年7月26日]
 */
public final class AlipayUtil {
    
    /**
     * 除去数组中的空值和签名参数后排序
     * 
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static TreeMap<String, String> paraFilter(Map<String, String> sArray) {
        TreeMap<String, String> result = new TreeMap<String, String>();
        
        for (Entry<String, String> entry : sArray.entrySet()) {
            if (StringUtils.isEmpty(entry.getValue())) {
                continue;
            }
            result.put(entry.getKey(), entry.getValue());
        }
        
        return result;
    }
    
    /**
     * 获取拼接后的参数并去除无效参数
     * 
     * @param map map
     * @return 拼装后的参数
     */
    public static String getParameter(Map<String, String> map) {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("sign") || entry.getKey().equalsIgnoreCase("sign_type")) {
                continue;
            }
            if (index++ != 0) {
                builder.append('&');
            }
            builder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        
        return builder.toString();
    }
    
    /**
     * 生成加密后的签名
     * 
     * @param param 参数
     * @param key 密钥
     * @return 加密后的签名
     */
    public static String encryptString(String param, String key) {
        String stringSignTemp = param + key;
        return Coder.encodeMd5(stringSignTemp);
    }
}
