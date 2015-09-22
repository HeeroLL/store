<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统注销页面</title>
	    <link rel="stylesheet" type="text/css" href="../../../bootstrap/assets/css/bootstrap.css" media="screen" >
  		<link rel="stylesheet" type="text/css" href="../../../bootstrap/assets/css/docs.css">
   		<link rel="stylesheet" type="text/css" href="../../../bootstrap/assets/css/bootstrap-responsive.css">
		<script src="../jquery.js"></script>
        <script src="../js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$("img").load(function(){
				var isjump = confirm("注销成功，确认跳转");
				if(isjump){
					window.location="login.zb";
				}
			});
		</script>
	</head>
	<body>
		
		<c:forEach items="${sysRegInfoList}" var="sysRegInfo">
			<img src="${sysRegInfo.sysLogoutUrl}" style="display: none;"/>
		</c:forEach>
		
		<div></div>
	</body>
</html>