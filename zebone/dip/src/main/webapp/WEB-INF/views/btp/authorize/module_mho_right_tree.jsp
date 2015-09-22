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
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<style type="text/css">
html,body{
background-color: #EEF7FE;
overflow: hidden;
height: auto;
}
</style>

<script type="text/javascript">
var moduleData;
var mhoId = "";
var setting = {
	check: {
		enable: true
	},
	
	data: {
		key: {
			name: "moduleName"
		},
		simpleData: {
			enable: true,
			idKey:'moduleId',
			pIdKey:'parentModuleId'
		}
	}
};

//设置医疗机构的id并加载相应的模块
var loadModuleByMhoId = function(id){
	mhoId = id;
	var treeObj = $.fn.zTree.getZTreeObj("module_tree1");
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	for(var j=0;j<nodes.length;j++){
		treeObj.checkNode(nodes[j], false, false);
	}
	$(document.body).mask("正在加载，请稍后...");
	$.ajax({
		url:'btp/authorize/loadModuleByMhoId.zb',
		type: 'POST',
		cache: false,
		data: eval('({mhoId:"'+id+'"})'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res){
				moduleData = res;
				for(var i=0;i<res.length;i++){
					for(var j=0;j<nodes.length;j++){
						if(nodes[j].moduleId == res[i].moduleId){
							treeObj.checkNode(nodes[j], true, false);
						}
					}
				}
			}else{
				JDialog.tip("加载失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document.body).unmask();
			JDialog.tip("服务器异常");
		}
	});
};

var getSelectNodes = function(){
	var treeObj = $.fn.zTree.getZTreeObj("module_tree1");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = "";
	for(var i=0;i<nodes.length;i++){
		ids += nodes[i].moduleId+",";
	}
	if(ids.length>0) ids = ids.substring(0,ids.length-1);
	return ids;
}

$(function(){
	var nodes = ${list};
	$.fn.zTree.init($("#module_tree1"), setting, nodes);
	var treeObj = $.fn.zTree.getZTreeObj("module_tree1");
	var nodes = treeObj.getNodes();
	if (nodes.length>0) {
		for(var i=0 ;i<nodes.length;i++){
			if(nodes[i].moduleId =='1000'){
				treeObj.expandNode(nodes[i], true, false, false, false);
			}
		}
	}
	
	$("#saveModuleMho").click(function(){
		if(!mhoId){
			JDialog.tip("请选择医疗机构");
			return;
		}
		$(document.body).mask("正在授权，请稍后...");
		var moduleIds = getSelectNodes();
		//shuju
		var sdata = "";
		if(moduleData){
			for(var i = 0; i < moduleData.length; i++){
				if(moduleIds.indexOf(moduleData[i].moduleId) < 0){
					sdata += moduleData[i].moduleId + ",";
				}
			}
		}
		if(sdata.length > 1) sdata = sdata.substring(0,sdata.length-1);
		var data = "{mhoId:'"+mhoId+"',moduleId:'"+moduleIds+"',sId:'"+sdata+"'}";
		$.ajax({
			url:'btp/authorize/moduleMhoSave.zb',
			type: 'POST',
			cache: false,
			data: eval('('+data+')'),
			dataType: 'json',
			success: function(res){
				$(document.body).unmask();
				if(res.success){
					JDialog.tip("授权成功");
				}else{
					JDialog.tip("授权失败");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				$(document.body).unmask();
				JDialog.tip("服务器异常");
			}
		});
	});
});
</script>
</head>
<body>
<div class="container" style="width: 400px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">模块信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div id="module_tree1" class="ztree"></div>
				<center>
				<a class="btn" href="javascript:void(0);">
					<span class="btn-left">
						<span class="btn-text icon-save" id="saveModuleMho">保存</span>
					</span>
				</a>
				</center>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
