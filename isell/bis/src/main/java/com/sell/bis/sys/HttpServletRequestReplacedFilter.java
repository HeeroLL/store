package com.sell.bis.sys;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;

/**
 * 为了记录日志，扩展HttpRequest，使得可以从流中多次获取请求体
 * 
 * @author lilin
 * @version [版本号, 2015年7月22日]
 */
public class HttpServletRequestReplacedFilter implements Filter {
    
    @Override
    public void destroy() {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        if (request instanceof HttpServletRequest && MapUtils.isEmpty(request.getParameterMap())
            && !"multipart/form-data".equals(((HttpServletRequest)request).getHeader("Content-Type"))) {
            chain.doFilter(new BodyReaderHttpServletRequestWrapper((HttpServletRequest)request), response);
        } else {
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException {
        
    }
    
}
