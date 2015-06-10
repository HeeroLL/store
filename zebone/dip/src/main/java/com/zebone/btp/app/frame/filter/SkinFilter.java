package com.zebone.btp.app.frame.filter;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 根据用户设定的界面皮肤，返回正确的css路径
 * 
 * @author 宋俊杰
 * 
 */
@Deprecated
public class SkinFilter implements Filter {
	private ServletContext servletContext = null;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String skin = (String) req.getSession().getAttribute("skin");
		// 如果没有得到用户的皮肤设，不做处理。
		if (StringUtils.isBlank(skin)) {
			filterChain.doFilter(request, response);
			return;
		}
		String uri = req.getRequestURI();

		uri = uri.trim();

		// 判断请求路径是否为对皮肤css的请求，如果是，将皮肤替换成用户自定义皮肤。
		if (Pattern.matches(".*skin/\\w+/.*\\w+\\.css$", uri)) {
			System.out.println("========" + uri);
//			if (Pattern.matches(".*skin/default/.*\\w+\\.css$", uri)) {
//				filterChain.doFilter(request, response);
//				return;
//			}

			uri = uri.replaceAll("skin/\\w+", "skin/" + skin);
			String skinPath = this.servletContext.getRealPath(uri);
			String css = FileUtils.readFileToString(new File(skinPath));
			response.setContentType("text/css;charset=utf-8");
			response.getWriter().write(css);
			response.getWriter().flush();
//			System.out.println(">>>>>>>>>>>>>>>" + skinPath);
//			// filterChain.doFilter(request, response);
//			// uri = uri.replace(contextPath, "");
//			// RequestDispatcher dispatcher = req.getRequestDispatcher(uri);
//			// dispatcher.forward(request, response);
//
//			((HttpServletResponse) response).sendRedirect(uri);
//			return;
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.servletContext = filterConfig.getServletContext();
	}
}
