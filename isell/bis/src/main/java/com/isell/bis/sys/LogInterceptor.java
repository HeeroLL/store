package com.isell.bis.sys;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * 权限拦截器，实现权限控制等功能
 * 
 * @author lilin
 * @version [版本号, 2015年6月30日]
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    /** 默认字符集 */
    private static final Charset CHARSET = Charset.forName("UTF-8");
    
    /** log */
    private static final Logger log = Logger.getLogger(LogInterceptor.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 记录日志：调用者、ip、接口名、调用时间、响应时间
        StringBuilder builder = new StringBuilder();
        builder.append("ip:").append(request.getRemoteAddr());
        builder.append(", callmethod:").append(handler);
        builder.append(", Content-Type:").append(request.getHeader("Content-Type"));
        builder.append(", Referer:").append(request.getHeader("Referer"));
        builder.append(System.getProperty("line.separator", "\n"));
        
        if (MapUtils.isNotEmpty(request.getParameterMap())) {
            int index = 0;
            Map<String, Object> paramMap = request.getParameterMap();
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                if (index++ != 0) {
                    builder.append('&');
                }
                builder.append(entry.getKey()).append('=');
                if (entry.getValue().getClass().isArray()) {
                    Object[] values = (Object[])entry.getValue();
                    if (values.length == 1) {
                        builder.append(values[0]);
                    } else {
                        builder.append(ArrayUtils.toString(entry.getValue()));
                    }
                } else {
                    builder.append(entry.getValue());
                }
            }
        } else {
            try {
                builder.append(StreamUtils.copyToString(request.getInputStream(), CHARSET));
            } catch (IOException e) {
                builder.append(e);
                log.error(builder.toString());
                throw new RuntimeException(e);
            }
        }
        log.info(builder.toString());
        return true;
    }
}
