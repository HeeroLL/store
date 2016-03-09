package com.isell.ei.pay.fuiou.util;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class FuiouUtil {
    
    /**
     * 获取拼接后的参数并
     * 
     * @param map map
     * @return 拼装后的参数
     */
    public static String getParameter(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("detail_data")) {
                continue;
            }
            if(StringUtils.isBlank(entry.getValue()))
            	builder.append("").append('|');
            else
            	builder.append(entry.getValue()).append('|');
        }
        
        return builder.toString();
    }
    
    
}
