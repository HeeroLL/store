<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<title>数据字典匹配管理</title>
<script type="text/javascript" src="js/jsp/dip/dict/dict_match_main.js"></script>
<script type="text/javascript">
var dictFlag = '${str}';
</script>
</head>
<body>
<div class="container">
<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
<span class="title-l">
<span class="title-r">
<b class="icon"></b><span class="title-span">字典匹配</span>
</span>
</span>
<div class="tools-panel"></div>
<div class="c_w">
<div class="c_e">
<div class="c_content">
<div id="dictMatch">
<div style="background-color: #eeeeee;height: 20px;font-size: 15px;">
<font style="margin-top: 5px;">字典类型</font>
</div>
<table align="center">
<tr>
<td style="width: 200px;">数据源数据</td>
<td style="width: 200px;">标准数据</td>
</tr>
<tr>
	<td>${typeName}</td>
	<td>
	<c:if test="${str != '1'}">
	<select name="dictType" id="dictTypeStandard">
		<option value="">请选择</option>
		<c:forEach items="${pdtList}" var="pdt">
			<option value="${pdt.typeId}"<c:if test="${pdt.typeId == dicttypeId}"><c:out value="selected"/> </c:if> >${pdt.typeName}</option>
		</c:forEach>
	</select>
	</c:if>
	</td>
</tr>
</table>
<div style="background-color: #eeeeee;height: 20px;font-size: 15px;">
<font style="margin-top: 5px;">字典数据</font>
</div>
<table align="center">
<tr>
<td style="width: 200px;">数据源数据</td>
<td style="width: 200px;">标准数据</td>
</tr>
<c:forEach items="${list}" var="dcitVal" varStatus="i">
<tr class="dictData">
<td>
${dcitVal.dictName}<input type="hidden" name="valueId${i.index}" value="${dcitVal.valueId}"/>
</td>
<td>
<select name="dictId${i.index}">
<option value="">请选择</option>
<c:forEach items="${list1}" var="list1">
<option value="${list1.dictId},${list1.dictCode}"<c:if test="${dcitVal.dictId == list1.dictId}"><c:out value="selected"/></c:if>>${list1.dictName}</option>
</c:forEach>
</select>
</td>
</tr>
</c:forEach>
<tr>
<td colspan="2">
<div style="margin-left: 100px;">
<a class="btn" href="javascript:void(0);">
	<span class="btn-left">
		<span class="btn-text icon-save" id="dictSave">保存</span>
	</span>
</a>
</div>
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