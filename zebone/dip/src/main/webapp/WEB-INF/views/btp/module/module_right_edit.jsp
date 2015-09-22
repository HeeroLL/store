<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/jsp/btp/module/jsp.module_right_edit.js"></script>
<title>功能模块管理</title>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">模块信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div class="checkForm" id="checkForm">
				<table border="0" cellpadding="1" cellspacing="1" align="center" style="float: left">
					<tr>
						<td>模块名称：</td>
						<td>
							<input type="hidden" name="moduleId" value="${btpModule.moduleId}"/>
							<input type="hidden" name="parentModuleId" value="${btpModule.parentModuleId}"/>
							<input type="text" name="moduleName" title="模块名称" style="width: 150px;" maxlength="30"  msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-30" value="${btpModule.moduleName}"/>
						</td>
					</tr>
					<tr>
						<td>模块URL：</td>
						<td>
							<input type="text" name="url" title="模块url" style="width: 150px;" maxlength="150"  msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-150" value="${btpModule.url}"/>
						</td>
					</tr>
					<tr>
						<td>大图标：</td>
						<td>
							<input type="text" name="maxicon" title="大图标" style="width: 250px;" maxlength="100"  msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-100" value="${btpModule.maxicon}"/>
						</td>
					</tr>
					<tr>
						<td>小图标：</td>
						<td>
							<input type="text" name="minicon" title="小图标" style="width: 250px;" maxlength="100"  msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-100" value="${btpModule.minicon}"/>
						</td>
					</tr>
					<tr>
						<td>是否为系统：</td>
						<td>
							<select name="isSys" style="width: 150px;" validate="select">
								<option value="0" <c:if test="${btpModule.remark==0}"><c:out value="selected"/></c:if>>否</option>
								<option value="1" <c:if test="${btpModule.remark==1}"><c:out value="selected"/></c:if>>是</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>备注：</td>
						<td>
							<textarea rows="3" cols="" title="备注" style="width: 150px;" name="remark" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-500">${btpModule.remark}</textarea>
						</td>
					</tr>
				</table>
				</div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
