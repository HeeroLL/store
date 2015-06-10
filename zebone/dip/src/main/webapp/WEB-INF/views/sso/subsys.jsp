<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="../js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="../js/jsp/sso/subsys.js"></script>
	</head>
	<body oncontextmenu="return false">
		<input id="sysId" type="hidden" value="${sysRegInfo.sysId}"/>
		<form id="subsysForm" action="${sysRegInfo.sysUrl}" method="post">
			<input id="ticket" name="ticket" type="text" value="" style="display: none;"/>
		</form>
	</body>
</html>