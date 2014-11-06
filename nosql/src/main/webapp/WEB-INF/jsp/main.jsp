<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/js/jquery/themes/icon.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.3.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
		<title>Insert title here</title>
</head>
<body class="easyui-layout">
	<div region="north" title="North Title" split="true"
		style="height: 100px;">欢迎登录nosql系统</div>
	<div region="south" title="South" split="true" style="height: 100px;">
	</div>
	<div region="east" iconCls="icon-reload" title="East" split="true"
		style="width: 100px;"></div>
	<div region="west" split="true" title="在线用户列表" style="width: 150px;">
		<c:forEach items="${onlineUser}" var="user">
			<p>${user.nickname}</p>
		</c:forEach>
	</div>
	<div region="center" title="center title"
		style="padding: 5px; background: #eee;"></div>
</body>

</html>