<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>数据字典管理</title>
	</head>
	<frameset id="main_frame" rows="*" cols="780,*" frameborder="no" border="0" framespacing="0">
		<frame src="/DIP/empi/goempi.zb" name="innerLeftFrame" scrolling="auto" noresize="noresize" id="innerLeftFrame" title="innerlLeftFrame" />
		<frame src="/DIP/empi/goempiDetail.zb" name="innerMainFrame" id="innerMainFrame" title="innerMainFrame" />
	</frameset>
	<noframes>
		<body>
			您的浏览器不支持Iframe框架。
		</body>
	</noframes>
</html>