<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>数据集</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jsp/dip/metadata/dataset_index.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="ui-layout-north">
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">数据集查询</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table id="query">
					<tr>
						<td>标准组织：
						<select name="standardCode" style="width: 130px;">
							<option value="">请选择</option>
							<c:forEach items="${list}" var="dict">
								<option value="${dict.dictCode}">${dict.dictName}</option>
							</c:forEach>
						</select>
						 </td>
						<td>数据集：<input type="text" name="datasetName" maxlength="32"/> </td>
						<td>
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" id="querybtn">
									<span class="btn-text icon-search">查询</span>
								</span>
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</div>
<div class="ui-layout-center">
<div id="grid"></div>
</div>
<div class="ui-layout-east">
<div style="margin: 5px 10px; id="query1">
	数据集中元数据检索<span style="margin-left: 30px;"></span>
	元数据别名 ：<input type="text" name="bieming"/>
	<a class="btn" href="javascript:void(0);">
		<span class="btn-left" id="querybtn1">
			<span class="btn-text icon-search">查询</span>
		</span>
	</a>
</div>
<hr />
<div style="margin: 5px 0px;">
	<span style="margin-left: 10px;"></span> 元数据：<input type="text" name="metaInfo" id="metaInfo" style="width: 110px;"/><input type="hidden" name="metadataId"/>
	元数据别名：<input type="text" name="fieldName" id="ysjbm" maxlength="33" style="width: 110px;"/>
	<a class="btn" href="javascript:void(0);">
		<span class="btn-left" id="addYsj">
			<span class="btn-text icon-add">增加</span>
		</span>
	</a>
</div>
<div id="grid1"></div>
</div>
</body>
</html>
