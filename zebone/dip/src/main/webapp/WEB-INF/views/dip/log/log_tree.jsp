<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>运行监控平台管理树</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="bootstrap/jquery.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<link href="bootstrap/css/${sessionScope.skin}-bootstrap.min.css" rel="stylesheet" media="screen"/>

<style type="text/css">
html,body{
	background-color: #EEF7FE;
	overflow: hidden;
	height: auto;
	}
	.overClass{
	background: #e8fcde;
	}
	.c_content div{
		height: 300px;
		overflow:auto;
		width:280px;
	}
	.checked{background: #FBEC88;}
	.dropdown-menu li{
		text-align:center;
	}
</style>

<script type="text/javascript">
var checkedClass = "wdbbgl";

/* var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
	alert(treeNode.name);
}; */
var nodes = [  {id:"0",pId:"",name:"运行监控平台管理系统",open:true},
	            {id:"1",pId:"0",name:"文档上传管理"},
				{id:"2",pId:"0",name:"EMPI管理"},
				{id:"4",pId:"2",name:"EMPI注册与更新"},
				{id:"5",pId:"2",name:"EMPI查询"},
				{id:"3",pId:"0",name:"档案调阅日志"},
				{id:"6",pId:"0",name:"前置监控"}
			];

var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};

function zTreeOnClick(event, treeId, treeNode){
	onClicktree(treeNode.id);
}
 
 
function onClicktree(str){
	if(str ==1){
		window.parent.document.getElementById("innerMainFrame").src = "log/logIndex.zb";//文档上传管理
	}else if(str ==3){
		window.parent.document.getElementById("innerMainFrame").src = "log/docViewIndex.zb";//档案调阅日志
	}else if(str ==4){
		window.parent.document.getElementById("innerMainFrame").src = "log/logEmpiModify.zb";//EMPI注册与更新
	}else if(str ==5){
		window.parent.document.getElementById("innerMainFrame").src = "log/logEmpiQuery.zb";//EMPI查询
	}else if(str == 6){
		window.parent.document.getElementById("innerMainFrame").src = "http://localhost:8080/lsReport/index.jsp?reportHome=qzjInfo";
	}
}
 
$(function(){
	typeTree = $.fn.zTree.init($("#type_tree"), setting, nodes);
});

</script>
</head>
<body>
<div class="container" style="width:286px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l" style="padding-left:4px;">
		<span class="title-r" style="padding-right:19px;">
			<b class="icon"></b><span class="title-span">运行监控平台管理系统</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e" style="text-align:center;">
			<div class="c_content"	>
				<div class="ztree" id="type_tree"></div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
