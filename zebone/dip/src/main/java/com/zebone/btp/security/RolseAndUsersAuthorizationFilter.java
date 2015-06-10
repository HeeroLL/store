package com.zebone.btp.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 对用户和角色进行权限检查。对于一个url地址，先检查当前用户角色是否有权限访问。如果没有权限，在查看用户
 * 是否有权限。此种情况适合对一个人通过角色授权，又可以对人单独授权的情况。
 * @author 宋俊杰
 *
 */
public class RolseAndUsersAuthorizationFilter extends AuthorizationFilter {
	private PatternMatcher pathMatcher = new AntPathMatcher();
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		String requestURI = getPathWithinApplication(request);
		//为角色分配的url权限列表
		Map<String,String> rolePermissionMap = new HashMap<String,String>();
		//TODO rolePermissionMap 需要从数据库中获得。目前还没有实现。
		rolePermissionMap.put("/securityTest.zb", "admin");
		
		//为用户分配的url权限列表
		Map<String,String> userPermissionMap = new HashMap<String,String>();
		//TODO userPermissionMap 需要从数据库获得。目前还没有实现。
		//对角色权限进行检查
		for(Map.Entry<String, String>  entry : rolePermissionMap.entrySet()){
			String pattern = entry.getKey();
			if(pathMatcher.matches(pattern, requestURI)){
				 Subject subject = getSubject(request, response);
			     String roles = entry.getValue();
			   //no roles specified, so nothing to check - allow access.
			     if(StringUtils.isBlank(roles)){
			    	 return true;
			     }
				 String[] rolesArray = roles.split(",");

			        if (rolesArray == null || rolesArray.length == 0) {
			            //no roles specified, so nothing to check - allow access.
			            return true;
			        }

			        Set<String> rolesSet = CollectionUtils.asSet(rolesArray);
			        return subject.hasAllRoles(rolesSet);
			}
		}
		
		
		
		
		
//		Subject subject = getSubject(request, response);
//		String[] usersArray = (String[]) mappedValue;
//
//		if (usersArray == null || usersArray.length == 0) {
//			// 没有指定用户，不做检查，允许访问。
//			return true;
//		}
//		Set<String> users = CollectionUtils.asSet(usersArray);
//		UserDetails userDetails = (UserDetails) subject.getPrincipal();
//		if (userDetails == null) {
//			return false;
//		}
//		userDetails.getUserId();
//
//		System.out.println(mappedValue);
		return true;
	}

	/**
	 * Merely returns
	 * 
	 * <code>WebUtils.{@link org.apache.shiro.web.util.WebUtils#getPathWithinApplication(javax.servlet.http.HttpServletRequest) getPathWithinApplication(request)}</code>
	 * and can be overridden by subclasses for custom
	 * request-to-application-path resolution behavior.
	 * 
	 * @param request
	 *            the incoming {@code ServletRequest}
	 * @return the request's path within the appliation.
	 */
	protected String getPathWithinApplication(ServletRequest request) {
		return WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
	}

	protected boolean pathMatches(String pattern, String path) {
		return pathMatcher.matches(pattern, path);
	}

}
