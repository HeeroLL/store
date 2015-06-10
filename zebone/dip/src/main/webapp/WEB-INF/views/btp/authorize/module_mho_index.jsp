<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>机构授权主页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<frameset id="modeMain" rows="*" cols="210,*" frameborder="no" border="0" framespacing="0">
  <frame src="btp/authorize/mhoLeftTree.zb" name="innerlLeftFrame" scrolling="auto"  noresize="noresize" id="innerlLeftFrame" title="innerlLeftFrame" />
  <frame src="btp/authorize/moduleMhoRightTree.zb" name="innerMainFrame" scrolling="no" id="innerMainFrame" title="innerMainFrame" />
 </frameset>
<noframes>
<body>
您的浏览器不支持Iframe框架。
</body>
</noframes>
</html>
