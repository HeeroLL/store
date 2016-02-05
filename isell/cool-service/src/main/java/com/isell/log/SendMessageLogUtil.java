package com.isell.log;

import org.apache.log4j.Logger;

import com.isell.core.util.HttpUtils;

/**
 * 发送消息日志记录工具类
 * 
 * @author lilin
 * @version [版本号, 2015年12月11日]
 */
public final class SendMessageLogUtil {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(HttpUtils.class);
    
    /**
     * info
     *
     * @param message 消息
     */
    public static void info(String message) {
        log.info(message);
    }
}
