package com.heero.nosql.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器，实现权限控制等功能
 * 
 * @author lilin
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 定义忽略拦截的package列表
     */
    private List<String> ignorePackageList;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 忽略指定的包
        if (CollectionUtils.isNotEmpty(ignorePackageList)) {
            for (String pkg : ignorePackageList) {
                HandlerMethod method = (HandlerMethod) handler;
                if (method.getBean().getClass().getPackage().getName().startsWith(pkg)) {
                    return true;
                }
            }
        }
        // 权限判断
        System.out.println("in LoginInterceptor");
        return super.preHandle(request, response, handler);
    }

    public void setIgnorePackageList(List<String> ignorePackageList) {
        this.ignorePackageList = ignorePackageList;
    }
}
