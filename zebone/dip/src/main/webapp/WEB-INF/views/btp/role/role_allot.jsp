<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色分配页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<frameset id="main_frame" rows="*" cols="20%,*" frameborder="no" border="0" framespacing="0">
	<frame src="btp/personnel/personnelTree.zb" name="innerlLeftFrame"   scrolling="auto" noresize="noresize" id="innerlLeftFrame" title="innerlLeftFrame" />
	<frame src="btp/role/roleAllotRight.zb" name="innerMainFrame" id="innerMainFrame" title="innerMainFrame"/>
</frameset>
<noframes>
<body>
您的浏览器不支持Iframe框架。
</body>
</noframes>
</html>
