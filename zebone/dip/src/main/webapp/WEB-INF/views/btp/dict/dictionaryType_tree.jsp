<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zebone.btp.app.dict.pojo.DictionaryType"%>
<%@page import="java.util.List;"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>字典类型树</title>
		<btp:htmlbase/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/icons.css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
	    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
		<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
    	<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
		<script type="text/javascript">
			var nodes = ${typeList};
			var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
				if(treeNode.parentId != ''){
					window.parent.document.getElementById("innerMainFrame").src = "dict.zb?dicttypeId=" + treeNode.typeId ;
				}else{
					window.parent.document.getElementById("innerMainFrame").src = "dictType.zb?parentId=" + treeNode.typeId;					
				}
		    };
		    var setting = {
		    	treeId:'type_tree',
			    data: {
			    	key: {
						name: "typeName"
					},
					simpleData: {
						enable: true,
						idKey:'typeId',
						pIdKey:'parentId',
						rootPId:''
					}
				},
				callback: {
					onClick: zTreeOnClick
				}
		    };
		    $(function(){
				$.fn.zTree.init($("#dictionaryType_tree"), setting, nodes);
			});			
			function callback(){
				window.location.reload();
			}
		</script>
	</head>
	<body>
		<div class="container">
			<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">字典类型树</span>
					</span>
				</span>
			<div class="tools-panel"></div>
			<div class="c_w">
				<div class="c_e">
					<div class="c_content">
						<div id="dictionaryType_tree" class="ztree" style="height: 80%"></div>
					</div>
				</div>
			</div>
			<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
		</div>	
	</body>
</html>