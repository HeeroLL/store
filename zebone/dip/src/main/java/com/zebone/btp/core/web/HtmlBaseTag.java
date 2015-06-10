package com.zebone.btp.core.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 输出html base标记。
 * @author  宋俊杰
 * @date 2012-11-28
 */
@SuppressWarnings("serial")
public class HtmlBaseTag extends TagSupport {
	private static final Log log = LogFactory.getLog(HtmlBaseTag.class);
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		JspWriter out = this.pageContext.getOut();
		try {
			String htmlbase = "<base href=\""+basePath+"\">";
			out.println(htmlbase);
		} catch (IOException e) {
			log.error(e);
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;

	}

	@Override
	public int doEndTag() throws JspException {

		return EVAL_PAGE;

	}

	@Override
	public void release() {
		super.release();
	}

}
