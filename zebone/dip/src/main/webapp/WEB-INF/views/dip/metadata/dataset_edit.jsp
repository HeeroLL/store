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
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/jsp/dip/metadata/dataset_edit.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">数据集信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table class="checkForm" id="checkForm">
					<tr>
						<td>数据集名称：</td><td><input type="hidden" name="id" value="${dataSetConf.id}"/>
							<input type="text" name="datasetName" value="${dataSetConf.datasetName}" validate="string|1-32"/>
						</td>
					</tr>
					<tr>
						<td>数据集编码：</td><td>
							<input type="text" name="datasetCode" value="${dataSetConf.datasetCode}" reg="[]" validate="string|1-32"/>
						</td>
					</tr>
					<tr>
						<td>版本：</td><td>
							<!-- 
							<input type="text" name="datasetVersion" value="${dataSetConf.datasetVersion}" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-8"/>
							 -->
							<select id="datasetVersion" name="datasetVersion" validate="select" style="width: 120px;">
								<option value="">请选择</option>
								<c:forEach items="${versions}" var="dict1">
									<option value="${dict1.dictCode}" <c:if test="${dict1.dictCode == dataSetConf.datasetVersion }"><c:out value="selected" /></c:if> >${dict1.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>标准组织：</td><td>
							<select id="standardCode" name="standardCode" validate="select" style="width: 120px;">
								<option value="">请选择</option>
								<c:forEach items="${list}" var="dict">
									<option value="${dict.dictCode}" <c:if test="${dict.dictCode == dataSetConf.standardCode }"><c:out value="selected" /></c:if> >${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<!-- 
					<tr>
						<td>数据集描述：</td><td>
							<input type="text" name="datasetDesc" value="${dataSetConf.datasetDesc}"/>
						</td>
					</tr>
					 -->
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
