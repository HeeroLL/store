package com.sell.core.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 多视图处理器
 * 
 * @author lilin
 * @version [版本号, 2013-4-3]
 */
public class MixedViewResolver implements ViewResolver {
    /**
     * 定义支持的视图处理器map集合，通过spring注入
     */
    private Map<String, ViewResolver> resolvers;
    
    /**
     * 实现如何区分多处理器
     * 
     * @param viewName 视图名称
     * @param locale 本地语言
     * @return View
     * @throws Exception exception
     */
    @Override
    public View resolveViewName(String viewName, Locale locale)
        throws Exception {
        int n = viewName.lastIndexOf(".");
        if (n != -1) {
            // 取出扩展名
            String suffix = viewName.substring(n + 1);
            // 取出对应的ViewResolver
            ViewResolver resolver = resolvers.get(suffix);
            if (resolver == null) {
                throw new RuntimeException("No ViewResolver for " + suffix);
            }
            return resolver.resolveViewName(viewName.substring(0, n), locale);
        } else {
            ViewResolver resolver = resolvers.get("jsp");
            return resolver.resolveViewName(viewName, locale);
        }
    }
    
    public void setResolvers(Map<String, ViewResolver> resolvers) {
        this.resolvers = resolvers;
    }
}