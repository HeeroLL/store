<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>各清洗表列表树</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<style type="text/css">
html,body{
background-color: #EEF7FE;
overflow: hidden;
height: auto;
}
</style>

<script type="text/javascript">
var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
	if(treeNode.tableType){
		window.parent.document.getElementById("innerMainFrame").src = "clear/clearConfMain.zb?id="+treeNode.id+"&dsId="+treeNode.dsId+"&tableName="+treeNode.tableName;
	}
};
var setting = {
	treeId:'dict_tree',
	data: {
		key: {
			name: "tableName"
		},
		simpleData: {
			enable: true,
			idKey:'id',
			pIdKey:'tableType'
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};
//var refleshTree = function(){
//	window.location.reload();
//};

$(function(){
	var nodes = ${list};
	$.fn.zTree.init($("#table_tree"), setting, nodes);
});
</script>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">需清洗的表配置信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div id="table_tree" class="ztree"></div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
