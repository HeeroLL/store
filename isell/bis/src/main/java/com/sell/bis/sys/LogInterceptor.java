package com.sell.bis.sys;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * 权限拦截器，实现权限控制等功能
 * 
 * @author lilin
 * @version [版本号, 2015年6月30日]
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    
    /** log */
    private static final Logger log = Logger.getLogger(LogInterceptor.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getParameterMap() != null) {
            Map<String, String> paramsMap = new HashMap<String, String>();
            
            Map<String, Object> paramMap = request.getParameterMap();
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                paramsMap.put(entry.getKey(), ArrayUtils.toString(entry.getValue()));
            }
            log.info(paramsMap);
        }
        return true;
    }
}
