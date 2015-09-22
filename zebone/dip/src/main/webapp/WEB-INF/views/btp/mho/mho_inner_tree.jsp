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
<title>医疗机构树</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript">
        var type='${type}';
		var nodes = ${ztree};
	    var setting = {
	    	check:{
                enable:true,  
				chkStyle: "checkbox",
				chkboxType: { "Y": "", "N": "" }  
				
            },
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
				onCheck:onCheck
			}
	    };
	    
		  var radio_setting = {
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
							pIdKey:'parentId',
							rootPId:'root'
						}
					},
					callback: {
						onCheck:onCheck
					}
			    };
if(type=='checkbox'){
    $(function(){
    var nodes = ${ztree};
		$.fn.zTree.init($("#mho_tree"), setting, nodes);
		var treeObj = $.fn.zTree.getZTreeObj("mho_tree");
		var nodes = treeObj.getNodes();
		if (nodes.length>0) {
			for(var i=0 ;i<nodes.length;i++){
				if(nodes[i].levelCode =='${levelCode}'){
					treeObj.expandNode(nodes[i], true, false, false, false);
				}
			}
		}
	});
}
if(type=='radio'){
	$(function(){
	    var nodes = ${ztree};
		$.fn.zTree.init($("#mho_tree"), radio_setting, nodes);
		var treeObj = $.fn.zTree.getZTreeObj("mho_tree");
		var nodes = treeObj.getNodes();
		if (nodes.length>0) {
			for(var i=0 ;i<nodes.length;i++){
				if(nodes[i].levelCode =='${levelCode}'){
					treeObj.expandNode(nodes[i], true, false, false, false);
				}
			}
		}
		
	});
}
function onCheck(e,treeId,treeNode){ 
	var treeObj=$.fn.zTree.getZTreeObj("mho_tree"),
    nodes=treeObj.getCheckedNodes(true),
    value = ""; 
    for(var i=0;i<nodes.length;i++){
   		value += nodes[i].mhoName +":"+ nodes[i].mhoId +":"+nodes[i].levelCode+":";
	}
    value = value.substring(0,value.length-1);
    //把选中的医疗机构ID的值，放到这个隐藏框里面去
    $("#tree_value").val(value);
};
//获取选中的后隐藏的id和name以供调用
var getTreeValue = function(){
 	return $("#tree_value").val();
}

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
			<div class="content">
				<div id="mho_tree" class="ztree" style="height: 80%"></div>
				<input type="hidden" name="tree_value" id="tree_value"/>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
    
</body>
</html>