<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>角色管理</title>
</head>
<frameset id="main_frame" rows="*" cols="20%,*" frameborder="no" border="0" framespacing="0">
	<frame src="btp/mho/mhoTree.zb?mhoId=${mhoId }" name="innerlLeftFrame"   scrolling="auto" noresize="noresize" id="innerlLeftFrame" title="innerlLeftFrame" />
	<frame src="btp/role/btpRoleMain.zb" name="innerMainFrame" id="innerMainFrame" title="innerMainFrame"/>
</frameset>
<noframes>
<body>
您的浏览器不支持Iframe框架。
</body>
</noframes>
</html>
