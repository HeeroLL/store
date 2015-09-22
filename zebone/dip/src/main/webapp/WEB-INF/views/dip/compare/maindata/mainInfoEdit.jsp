<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
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
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id="" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
<title>主数据对照修改页面</title>
<script type="text/javascript">
var projectPath = '${pageContext.request.contextPath}';
var fields = '${cols}';//动态列说明
var maindataId = '${info.mid}';//标准主数据标识
var tableName = '${info.tableName}';
</script>
<script type="text/javascript" src="js/jsp/dip/compare/maindata/mainInfoEdit.js"></script>
<style type="text/css">
html,body{background-color: #ffffff;height: 99%;}
#index_div{
	margin: 5px;
	padding: 10px;
	border: 1px solid #dddddd;
}
#orgMainInfoDiv,#mainInfoDiv{
	margin: 5px;
	padding: 5px;
	border: 1px solid #dddddd;
}
</style>
</head>
<body>
<div id="index_div">
<div id="orgMainInfoDiv">
	<b>步骤一：</b><br/><br/>
	<form id="checkForm" class="checkForm"><input type="hidden" name="id" value="${info.id}"/>
	名称（机构）：<input type="text" name="name" validate="string|1-15" value="${info.name}"/><span style="margin-left: 20px;"></span>
	编码（机构）：<input type="text" name="code" validate="string|1-30" value="${info.code}"/>
	<input type="hidden" name="mid"/>
	<input type="hidden" name="tableName" value="${info.tableName}"/>
	</form>
</div>
<div id="mainInfoDiv" style="margin-top: 10px;">
	<b>步骤二：</b><br/><br/>
	查询条件：<span style="margin-left: 50px;"></span>名称（标准）：<input type="text" name="stdName" id="stdName"/> <input type="hidden" name="maindataId" id="maindataId"/>
	<a class="btn" href="javascript:void(0);">
		<span class="btn-left" id="mdstdquery">
			<span class="btn-text icon-search">查询</span>
		</span>
	</a>
	<div id="grid" style="margin-top: 5px;"></div>
</div>
<div style="margin-top: 15px;text-align: center;">
<a class="btn" href="javascript:void(0);">
	<span class="btn-left" id="mdstdsave">
		<span class="btn-text icon-save">保存</span>
	</span>
</a>
<span style="margin-left: 30px;"></span>
<a class="btn" href="javascript:void(0);">
	<span class="btn-left" id="mdstdcancel">
		<span class="btn-text icon-cancel">关闭</span>
	</span>
</a>
</div>
</div>
</body>
</html>