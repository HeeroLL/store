package com.isell.bis.sys;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
        if (!permissionIpList.contains(getIpAddr(request))) {
            throw new RuntimeException("exception.no-access-permission");
        }
        
        return true;
    }
    
    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        // ipAddress = request.getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
            
        }
        
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
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
