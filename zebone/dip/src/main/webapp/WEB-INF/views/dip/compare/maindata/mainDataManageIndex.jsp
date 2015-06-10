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
<title>主数据对照管理</title>
<script type="text/javascript">
var projectPath = '${pageContext.request.contextPath}';
var tableName = '${tableName}';
</script>
<script type="text/javascript" src="js/jsp/dip/compare/maindata/dataManageIndex.js"></script>
<style type="text/css">
</style>
</head>
<body>
<div class="ui-layout-west">
<div style="margin: 5px;">
	<div>主数据类型：</div>
	<div id="treeDiv" style="overflow: auto;margin-top: 10px;">
	<div class="ztree" id="maintypeInfo"></div>
	</div>
</div>
</div>
<div class="ui-layout-center">
<div class="container">
<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
<span class="title-l">
	<span class="title-r">
		<b class="icon"></b><span class="title-span">查询条件</span>
	</span>
</span>
<div class="tools-panel"></div>
<div class="c_w">
	<div class="c_e">
		<div class="c_content">
			<span style="margin-left: 25px;"></span>名称（机构）：<input type="text" name="orgName" id="orgName"/>
			<input type="hidden" name="orgId" id="orgId"/>
			<a class="btn" href="javascript:void(0);">
				<span class="btn-left" id="dictquery">
					<span class="btn-text icon-search">查询</span>
				</span>
			</a>
		</div>
	</div>
</div>
<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
<div id="grid" style="margin: 0px 10px;"></div>
</div>
</body>
</html>