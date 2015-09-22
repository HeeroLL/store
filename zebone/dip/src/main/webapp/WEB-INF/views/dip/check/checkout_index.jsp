<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<btp:htmlbase/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css" />
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css" />
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/jsp/dip/check/checkout_index.js"></script>
<title>开关校验</title>
<style type="text/css">
table{border-collapse:collapse;}
table,tr,td{
border: 1px solid #c7c6cb;
}
tr,td{
height: 20px;
}
td{
text-align: center;
}
</style>
</head>
<body>
<div class="container">
<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
<span class="title-l">
	<span class="title-r">
		<b class="icon"></b><span class="title-span">校验开关配置信息</span>
	</span>
</span>
<div class="tools-panel"></div>
<div class="c_w">
	<div class="c_e">
		<div class="c_content">
			<div style="margin: 5px;">
				批量操作：<input type="checkbox" name="operAll" id="operAll"/>
			</div>
			<table id="checkoutNoOff" style="margin-left: 5px;">
				<tr>
					<td style="width: 80px;">序号</td>
					<td style="width: 150px;">文档类型</td>
					<td style="width: 80px;">可选性</td>
					<td style="width: 80px;">重复性</td>
					<td style="width: 80px;">数据格式</td>
					<td style="width: 80px;">业务格式</td>
					<td style="width: 80px;">唯一性</td>
					<td style="width: 80px;">值域</td>
				</tr>
				<c:forEach items="${list}" var="checkConf" varStatus="i">
				<tr class="doccheckout">
					<td>${i.index + 1}</td>
					<td><input type="hidden" value="${checkConf.docId}"/> ${checkConf.docName} </td>
					<td><input type="checkbox" <c:if test="${checkConf.isSelect == 1}"><c:out value="checked" /> </c:if> /></td>
					<td><input type="checkbox" <c:if test="${checkConf.isRepeat == 1}"><c:out value="checked" /> </c:if> /></td>
					<td><input type="checkbox" <c:if test="${checkConf.isDataFormat == 1}"><c:out value="checked" /> </c:if> /></td>
					<td><input type="checkbox" <c:if test="${checkConf.isBusiFormat == 1}"><c:out value="checked" /> </c:if> /></td>
					<td><input type="checkbox" <c:if test="${checkConf.isOnly == 1}"><c:out value="checked" /> </c:if> /></td>
					<td><input type="checkbox" <c:if test="${checkConf.isValue == 1}"><c:out value="checked" /> </c:if> /></td>
				</tr>
				</c:forEach>
			</table>
			<div>
				<a class="btn" href="javascript:void(0);" id="saveToDB" style="margin-left: 300px;margin-top: 5px;">
				    <span class="btn-left">
				        <span class="btn-text icon-save" >保存</span>
				    </span>
				</a>
			</div>
		</div>
	</div>
</div>
<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
