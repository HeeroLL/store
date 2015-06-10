<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>节点管理界面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/jsp/dip/node/node_edit.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span"></span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table class="checkForm" id="checkForm">
					<tr>
						<td>节点名称：</td>
						<td>
							<input type="text" name="nodesName" value="${pNode.nodesName }" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-20"/>
							<input type="hidden" name="id" value="${pNode.id }" /> 
						</td>
					</tr>
					<tr>
						<td>节点描述：</td>
						<td><input type="text" name="nodeDesc" value="${pNode.nodeDesc }" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-30"/> </td>
					</tr>
					<tr>
						<td>节点IP：</td>
						<td><input type="text" name="nodeIp" value="${pNode.nodeIp }" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-20"/> </td>
					</tr>
					<tr>
						<td>端口号：</td>
						<td><input type="text" name="nodePort" value="${pNode.nodePort }" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-20"/> </td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
