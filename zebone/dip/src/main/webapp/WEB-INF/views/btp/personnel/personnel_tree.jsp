<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head> 
    <title>医疗机构树</title>
    <btp:htmlbase/>
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
	<script type="text/javascript" src="js/jsp/btp/personnel/jsp.personnel_tree.js"></script>
    <script type="text/javascript">
		var nodes = ${mhoList};
		var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
			parent.innerMainFrame.zTreeOnClick(treeNode.mhoId,treeNode.mhoName);
			$("#tree_checked_id").val(treeNode.mhoId);
			$("#tree_checked_name").val(treeNode.mhoName);
	    };
	    var setting = {
	    	treeId:'person_mho_tree',
		    data: {
		    	key: {
					name: "mhoName"
				},
				simpleData: {
					enable: true,
					idKey:'mhoId',
					pIdKey:'parentId',
					rootPId:'root'
				}
			},
			callback: {
				onClick: zTreeOnClick
			}
	    };
	    $(function(){
			$.fn.zTree.init($("#mho_tree"), setting, nodes);
			var treeObj = $.fn.zTree.getZTreeObj("mho_tree");
			var node = treeObj.getNodes();
			if (node.length>0) {
				for(var i=0 ;i<node.length;i++){
					if(node[i].mhoId ="root"){
						treeObj.expandNode(node[i], true, false, false, false);
					}
				}
			}
		});
		
		var getTreeCheckedVaule = function(){
			return $("#tree_checked_id").val();
		}
		var getTreeCheckedName = function(){
			return $("#tree_checked_name").val();
		}
    </script>
    <style type="text/css">
    html,body{
    	height :auto;
    }
    </style>
  </head>
  <body>
    
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
				<div id="mho_tree" class="ztree" style="height: 80%"></div>
				<input type="hidden" id="tree_checked_id"/>
				<input type="hidden" id="tree_checked_name"/>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
    
  </body>
</html>
