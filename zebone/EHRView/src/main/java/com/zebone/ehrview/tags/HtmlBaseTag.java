package com.zebone.ehrview.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This tag used to generate the base tag of html file , 
 * for the purpose of managing the relative file path easily. 
 * i.e <base href='http://localhost:8080/cmstudio/'/>
 * @author charmyin
 * @since 2013-7-19
 * @serial 1.0
 */
public class HtmlBaseTag extends TagSupport {

	
	/*
	 * Wtrite the <base href=""/> tag to jsp
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		//Get full scheme serverName port and contextPath
		HttpServletRequest httpServletRequest = (HttpServletRequest)this.pageContext.getRequest();
		String scheme = httpServletRequest.getScheme();
		String serverName = httpServletRequest.getServerName();
		int localPort = httpServletRequest.getLocalPort();
		String contextPath = httpServletRequest.getContextPath();
		try {
			this.pageContext.getOut().print("<base href='"+scheme+"://" + serverName + ":" + localPort + contextPath + "/'/>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		
		return super.doEndTag();
	}
	
}
