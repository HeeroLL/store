package com.isell.bis.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * 权限拦截器，实现权限控制等功能
 * 
 * @author lilin
 * @version [版本号, 2015年6月30日]
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    
    /**
     * 定义忽略拦截的package列表
     */
    private List<String> ignorePackageList;
    
    /**
     * 允许访问的ip地址列表
     */
    private List<String> permissionIpList;
    
    /**
     * 忽略校验
     */
    private boolean ignoreValidation;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (ignoreValidation) {
            return true;
        }
        // 忽略指定的包的url
        if (CollectionUtils.isNotEmpty(ignorePackageList)) {
            for (String pkg : ignorePackageList) {
                HandlerMethod method = (HandlerMethod)handler;
                if (method.getBean().getClass().getPackage().getName().startsWith(pkg)) {
                    return true;
                }
            }
        }
        // ip过滤
        if (!permissionIpList.contains(request.getRemoteAddr())) {
            throw new RuntimeException("exception.no-access-permission");
        }
        
        return true;
    }
    
    public void setIgnorePackageList(List<String> ignorePackageList) {
        this.ignorePackageList = ignorePackageList;
    }

    public void setPermissionIpList(List<String> permissionIpList) {
        this.permissionIpList = permissionIpList;
    }

    public void setIgnoreValidation(boolean ignoreValidation) {
        this.ignoreValidation = ignoreValidation;
    }
}
