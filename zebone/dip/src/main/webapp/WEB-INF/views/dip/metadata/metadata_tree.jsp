<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>元数据管理树</title>
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
var nodes = [  //{id:"0",pId:"",name:"元数据管理系统",open:true},
	            {id:"1",pId:"0",name:"文档模型管理"},
				{id:"2",pId:"0",name:"数据集管理"},
				{id:"3",pId:"0",name:"数据元管理"},
				{id:"4",pId:"0",name:"数据模型管理"},
				{id:"5",pId:"0",name:"注册模型管理"}
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
		window.parent.document.getElementById("innerMainFrame").src = "metadata/docvIndex.zb";//文档版本管理
	}else if(str ==2){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/datasetIndex.zb";//数据集
	}else if(str ==3){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/metadataIndex.zb";//元数据
	}else if(str ==4){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/tablestruIndex.zb";//表结构
	}else if(str ==5){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/regvIndex.zb";//注册版本管理
	}
}
 
 
/* function addClassChecked(str){
	$("#"+checkedClass).removeClass("checked");
	$("#"+str).addClass("checked");
}
function onClickDIV(str,obj){
	$("li").removeClass("active");
	$("li:eq("+(str-1)+")").addClass("active");
	
	if(str ==1){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/docvIndex.zb";//文档版本管理
	}else if(str ==2){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/datasetIndex.zb";//数据集
	}else if(str ==3){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/metadataIndex.zb";//元数据
	}else if(str ==4){
		window.parent.document.getElementById("innerMainFrame").src = "metadata/tablestruIndex.zb";//表结构
	}else if(str ==5){
		addClassChecked("wdysjys");
		checkedClass = "wdysjys";
		window.parent.document.getElementById("innerMainFrame").src = "metadata/docmapIndex.zb";//文档数据映射
	}else if(str ==6){
		addClassChecked("bzd");
		checkedClass = "bzd";
		window.parent.document.getElementById("innerMainFrame").src = "metadata/tablefieldIndex.zb";//表字段
	}
}*/

$(function(){
	typeTree = $.fn.zTree.init($("#type_tree"), setting, nodes);
	//window.parent.document.getElementById("innerMainFrame").src = "metadata/docvIndex.zb";//文档版本管理
});

</script>
</head>
<body>
<div class="container" style="width:210px;margin-left: 10px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l" style="padding-left:4px;">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">元数据管理系统</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e" style="text-align:center;">
			<div class="c_content"	>
				<div class="ztree" id="type_tree"></div>
				<!-- <div class="dropdown">
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display: block; position: static;margin:10px 0px 5px 5px;margin-bottom: 5px; width: 280px;">
					   <li class="active"><a tabindex="-1" href="javascript:onClickDIV(1,this)">文档版本管理</a></li>
					   <li><a tabindex="-1" href="javascript:onClickDIV(2,this)">数据集</a></li>
					   <li><a tabindex="-1" href="javascript:onClickDIV(3,this)">元数据管理</a></li>
					   <li><a tabindex="-1" href="javascript:onClickDIV(4,this)">表结构</a></li>
					   
					</ul>
				</div> -->
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
				
				
				<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
