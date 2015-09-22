<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<btp:htmlbase/>
<title>元数据信息页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
</head>
<body>
<div class="container">
<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
<span class="title-l">
	<span class="title-r">
		<b class="icon"></b><span class="title-span">字典对照导入</span>
	</span>
</span>
<div class="tools-panel"></div>
<div class="c_w">
	<div class="c_e">
		<div class="c_content">
			导入文件：<input type="file" name=""/><button >提交</button>
		</div>
	</div>
</div>
<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>