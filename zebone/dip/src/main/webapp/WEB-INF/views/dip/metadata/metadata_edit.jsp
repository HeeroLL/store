<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript">
var field_type = '${fieldConf.fieldType}';
</script>
<script type="text/javascript" src="js/jsp/dip/metadata/metadata_edit.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">元数据信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table class="checkForm" id="checkForm">
					<tr>
						<td>名称：<input type="hidden" name="id" value="${fieldConf.id}"/> </td>
						<td><input type="text" name="fieldName" value="${fieldConf.fieldName}" reg="[]" validate="string|1-32"/></td>
						<td>编码：</td>
						<td><input type="text" name="fieldCode" id="fieldCode" value="${fieldConf.fieldCode}" /></td>
					</tr>
					<tr>
						<td>数据元标识符：</td>
						<td><input type="text" name="fieldId" title="标识符" id="fieldId" value="${fieldConf.fieldId}" /></td>
						<td>说明与定义：</td>
						<td><input type="text" name="fieldDesc" value="${fieldConf.fieldDesc}" reg="[]" validate="string|0-64"/></td>
					</tr>
					<tr>
						<td>值域类型：</td>
						<td>
						<select name="fieldType" style="width: 132px;" id="fieldType">
							<option value="">请选择</option>
							<option value="1" <c:if test="${fieldConf.fieldType ==1}"><c:out value="selected" /></c:if> >日期</option>
							<option value="2" <c:if test="${fieldConf.fieldType ==2}"><c:out value="selected" /></c:if> >字符</option>
							<option value="3" <c:if test="${fieldConf.fieldType ==3}"><c:out value="selected" /></c:if> >数字</option>
							<option value="4" <c:if test="${fieldConf.fieldType ==4}"><c:out value="selected" /></c:if> >数据字典</option>
							<option value="5" <c:if test="${fieldConf.fieldType ==5}"><c:out value="selected" /></c:if> >主数据</option>
						</select>
						</td>
						<td>值域：</td>
						<td>
							<input style="height:19px; width: 125px;" type="text" id="fieldValname" name="fieldValname" value="${fieldConf.fieldValname}"/>
							<input type="hidden" id="fieldValue" name="fieldValue" value="${fieldConf.fieldValue}"/>
						</td>
					</tr>
					<tr>
						<td>数据格式：</td>
						<td><input type="text" name="fieldFormat" value="${fieldConf.fieldFormat}" reg="[]" validate="string|0-32"/></td>
						<td>业务格式：</td>
						<td>
							<select name="fieldRuleFormat" style="width: 132px;">
								<option value="">请选择</option>
								<option value="1" <c:if test="${fieldConf.fieldRuleFormat ==1}"><c:out value="selected" /></c:if> >邮件</option>
								<option value="2" <c:if test="${fieldConf.fieldRuleFormat ==2}"><c:out value="selected" /></c:if> >电话号码</option>
								<option value="3" <c:if test="${fieldConf.fieldRuleFormat ==3}"><c:out value="selected" /></c:if> >身份证</option>
								<option value="4" <c:if test="${fieldConf.fieldRuleFormat ==4}"><c:out value="selected" /></c:if> >手机</option>
							</select>
						</td>
						<!-- 
						<td>可选性：</td>
						<td>
							<select name="fieldSelect" style="width: 130px;">
								<option value="1" <c:if test=""><c:out value="selected" /></c:if> >可选</option>
								<option value="2" <c:if test=""><c:out value="selected" /></c:if> >必选</option>
							</select>
						</td>
						 -->
					</tr>
					<tr>
						<!-- 
						<td>长度：</td>
						<td><input type="text" name="fieldLength" value=""/></td>
						 -->
						<td>目录分类：</td>
						<td>
							<select name="onlySort" style="width: 132px;" validate="select">
								<option value="">请选择</option>
								<c:forEach items="${list}" var="dict">
									<option value="${dict.dictCode}" <c:if test="${fieldConf.onlySort == dict.dictCode}"><c:out value="selected" /></c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td><!-- 唯一性： --></td>
						<td>
							<!-- 
							<select name="onlyCode" style="width: 132px;">
								<option value="0">否</option>
								<option value="1" <c:if test="${fieldConf.onlyCode ==1}"><c:out value="selected" /></c:if> >是</option>
							</select>
							 -->
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
