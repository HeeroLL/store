package com.sell.ei.pay.alipay.util;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.sell.core.util.Coder;

/**
 * 支付宝支付工具类
 * 
 * @author lilin
 * @version [版本号, 2015年7月26日]
 */
public final class AlipayUtil {
    
    /**
     * 获取参数
     * 
     * @param map map
     * @return 拼装后的参数
     */
    public static String getParameter(TreeMap<String, String> map) {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isEmpty(entry.getValue()) || entry.getKey().equalsIgnoreCase("sign")
                || entry.getKey().equalsIgnoreCase("sign_type")) {
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
