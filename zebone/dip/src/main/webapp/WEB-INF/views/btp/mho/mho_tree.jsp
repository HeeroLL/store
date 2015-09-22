<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>医疗机构树</title>
<link rel="stylesheet" type="text/css" href="css/icons.css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript">
var zTreeOnClick = function(event, treeId, treeNode){
	 window.parent.frames["innerMainFrame"].loadMho(treeNode.mhoId,treeNode.levelCode,treeNode.parentId);
};
	   var loadMhoTree = function(){
window.location.href = "mhoTree.zb";
	
};
var setting = {
	treeId:'mho_tree1',
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
		onClick: zTreeOnClick
	}
};


	    
$(function(){
var nodes = ${mhoList};
$.fn.zTree.init($("#tree_1"), setting, nodes);
var treeObj = $.fn.zTree.getZTreeObj("tree_1");
var nodes = treeObj.getNodes();
if (nodes.length>0) {
	for(var i=0 ;i<nodes.length;i++){
		if(nodes[i].levelCode =='${levelCode}'){
			treeObj.expandNode(nodes[i], true, false, false, false);
		}
	}
}
});



</script>
</head>
<body >
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">医疗机构树</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div id="tree_1" class="ztree"></div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
    
</body>
</html>