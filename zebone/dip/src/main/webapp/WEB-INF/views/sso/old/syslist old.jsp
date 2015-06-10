<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>子系统列表页面</title>
		<link rel="stylesheet" type="text/css" href="../css/icons.css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/main.css"
			id="main-css" />
		<link rel="stylesheet" type="text/css"
			href="../skin/default/jquery-grid.css" id="grid-css" />
		<link rel="stylesheet" type="text/css"
			href="../skin/default/jquery-dialog.css" id="dialog-css" />
		<link rel="stylesheet" type="text/css"
			href="../skin/default/jquery.checkForm.css" id="checkform-css" />
		<link rel="stylesheet" type="text/css"
			href="../skin/default/jquery-button.css" id="button-css" />
		<link rel="../stylesheet" type="text/css"
			href="js/jquery/css/jquery-tab.css" id="tab-css" />
		<script type="text/javascript" src="../js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-grid.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-tab.js"></script>
	</head>
	<body>
		<div id="main">
			<div class="container">
				<div class="c_nw">
					<div class="c_ne">
						<div class="c_n"></div>
					</div>
				</div>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<c:forEach items="${sysRegInfoList}" var="sysRegInfo">
								<a class="btn" target="_blank" href="toSubSystem.zb?regId=${sysRegInfo.regId}"> <span
									class="btn-left"> <span style="height: 200px"
										class="btn-text icon-back">${sysRegInfo.sysName}</span> </span> </a>
								<br />
								<br />
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="c_sw">
					<div class="c_se">
						<div class="c_s"></div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>