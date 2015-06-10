<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>标准数据集管理界面</title>
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
<script type="text/javascript" src="js/jsp/dip/dataset/dataset_edit.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">标准数据集</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table class="checkForm" id="checkForm">
					<tr>
						<td>数据元标识符：</td><td>
						<input type="hidden" name="id" value="${dataSet.id}"/>
						<input type="text" name="pCode" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-15" value="${dataSet.pCode}"/> 
						</td>
					</tr>
					<tr>
						<td>数据元名称：</td><td><input type="text" name="pName" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-35" value="${dataSet.pName}"/> </td>
					</tr>
					<tr>
						<td>数据类型：</td><td><select name="pType" validate="select">
							<option value="">请选择</option>
							<option value="S1" <c:if test="${dataSet.pType=='S1'}"><c:out value="selected" /> </c:if>>S1</option>
							<option value="S2" <c:if test="${dataSet.pType=='S2'}"><c:out value="selected" /> </c:if>>S2</option>
							<option value="S3" <c:if test="${dataSet.pType=='S3'}"><c:out value="selected" /> </c:if>>S3</option>
							<option value="L" <c:if test="${dataSet.pType=='L'}"><c:out value="selected" /> </c:if>>L</option>
							<option value="N" <c:if test="${dataSet.pType=='N'}"><c:out value="selected" /> </c:if>>N</option>
							<option value="D" <c:if test="${dataSet.pType=='D'}"><c:out value="selected" /> </c:if>>D</option>
							<option value="DT" <c:if test="${dataSet.pType=='DT'}"><c:out value="selected" /> </c:if>>DT</option>
							<option value="T" <c:if test="${dataSet.pType=='T'}"><c:out value="selected" /> </c:if>>T</option>
							<option value="BY" <c:if test="${dataSet.pType=='BY'}"><c:out value="selected" /> </c:if>>BY</option>
						</select> </td>
					</tr>
					<tr>
						<td>表示格式：</td><td><input type="text" name="pFormat" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-10" value="${dataSet.pFormat}"/> </td>
					</tr>
					<tr>
						<td>数据元允许值：</td><td><input type="text" name="pValue" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-20" value="${dataSet.pValue}"/> </td>
					</tr>
					<tr>
						<td>值类型：</td><td><select name="pValueType">
							<option value="">请选择</option>
							<option value="D1" <c:if test="${dataSet.pValueType=='D1'}"><c:out value="selected" /> </c:if>>枚举型</option>
							<option value="D2" <c:if test="${dataSet.pValueType=='D2'}"><c:out value="selected" /> </c:if>>GB/T</option>
							<option value="D3" <c:if test="${dataSet.pValueType=='D3'}"><c:out value="selected" /> </c:if>>CV型</option>
							<option value="M" <c:if test="${dataSet.pValueType=='M'}"><c:out value="selected" /> </c:if>>主数据编码</option>
							<option value="J" <c:if test="${dataSet.pValueType=='J'}"><c:out value="selected" /> </c:if>>机构代码等</option>
							<option value="QT" <c:if test="${dataSet.pValueType=='QT'}"><c:out value="selected" /> </c:if>>其他</option>
						</select> </td>
					</tr>
					<tr>
						<td>定义描述：</td><td>
						<textarea rows="4" cols="" name="pDesc" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-100">${dataSet.pDesc}</textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
