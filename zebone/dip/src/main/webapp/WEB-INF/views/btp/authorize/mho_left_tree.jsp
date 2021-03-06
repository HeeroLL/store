<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>机构树</title>
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
height: auto;
}
</style>

<script type="text/javascript">
var zTreeOnCheck = function(event, treeId, treeNode){
	var mhoId = "";
	if(treeNode.checked) mhoId = treeNode.mhoId;
	window.parent.frames["innerMainFrame"].loadModuleByMhoId(mhoId);
}
var setting = {
	treeId:'mho_tree',
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	
	data: {
		key: {
			name: "mhoName"
		},
		simpleData: {
			enable: true,
			idKey:'mhoId',
			pIdKey:'parentId'
		}
	},
	callback: {
		onCheck: zTreeOnCheck
	}
};

$(function(){
	var nodes = ${list};
	$.fn.zTree.init($("#mho_tree"), setting, nodes);
	var treeObj = $.fn.zTree.getZTreeObj("mho_tree");
	var nodes = treeObj.getNodes();
	if (nodes.length>0) {
		for(var i=0 ;i<nodes.length;i++){
			if(nodes[i].levelCode =='${mhoLevelCode}'){
				treeObj.expandNode(nodes[i], true, false, false, false);
			}
		}
	}
});
</script>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">医疗机构</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div id="mho_tree" class="ztree"></div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
