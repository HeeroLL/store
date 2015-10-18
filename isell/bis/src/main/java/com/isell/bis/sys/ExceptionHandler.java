package com.isell.bis.sys;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.isell.core.util.MessageUtil;

/**
 * 通用异常捕获处理器
 * 
 * @author lilin
 * @version [版本号, 2014-6-17]
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(ExceptionHandler.class);
    
    /**
     * 对捕获的异常进行处理
     * 
     * @param request 请求
     * @param response 响应
     * @param handler 控制层对象
     * @param ex 异常
     * @return view
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {
        MappingJacksonJsonView view = new MappingJacksonJsonView();
        Map<String, Object> map = new HashMap<String, Object>();
        
        // 记录日志
        log.error(ex.getMessage(), ex);
        
        String message = MessageUtil.getMessage(ex.getMessage());
        
        if (StringUtils.isEmpty(message)) {
            message = MessageUtil.getMessage(ex.getClass().getName());
        }
        
        if (StringUtils.isEmpty(message) && ex.getCause() != null) {
            message = MessageUtil.getMessage(ex.getCause().getClass().getName());
        }
        
        if (StringUtils.isEmpty(message)) {
            message = MessageUtil.getMessage("exception.defaultException");
        }
        // 对于不同hander需要组装不同的json返回
        if (handler != null && handler.toString().indexOf("JoogeGatewayController") != -1) {
            map.put("Code", "2"); 
            map.put("Desc", message);
        } else {
            map.put("success", false);
            map.put("msg", message);
        }
        
        view.setAttributesMap(map);
        return new ModelAndView(view);
    }
    
}
