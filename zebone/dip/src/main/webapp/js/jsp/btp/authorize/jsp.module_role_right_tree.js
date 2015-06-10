//根据医疗机构获取角色和模块的信息
var loadModuleByMhoId = function(id){
	if(!id){
		$("#roleDiv").html('');
		$("#module_tree").html('');
		return;
	}
	$(document.body).mask("正在加载，请稍后...");
	$.ajax({
		url:'btp/authorize/getModuleRoleByMhoId.zb',
		type: 'POST',
		cache: false,
		data: eval('({mhoId:"'+id+'"})'),
		dataType: 'json',
		success: function(res){
			if(res.btpModules){
				var data = res.btpModules;
				setModuleTree(data);
			}
			if(res.roles){
				var data = res.roles;
				var rolehtml = "";
				for(var i=0;i<data.length;i++){
					rolehtml +="<input type='radio' onClick='roleClick(this);' name='roleName' value="+data[i].roleId+">"+data[i].name+"<br/>";
				}
				$("#roleDiv").html(rolehtml);
			}
			$(document.body).unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$("#roleDiv").html('');
			$("#module_tree").html('');
			$(document.body).unmask();
			JDialog.tip("服务器异常");
		}
	});
};
//设置模块树
var setModuleTree = function(data){
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
	//var nodes = "";
	$.fn.zTree.init($("#module_tree"), setting, data);
	var treeObj = $.fn.zTree.getZTreeObj("module_tree");
	var nodes = treeObj.getNodes();
	if (nodes.length>0) {
		for(var i=0 ;i<nodes.length;i++){
			if(nodes[i].moduleId =='1000'){
				treeObj.expandNode(nodes[i], true, false, false, false);
			}
		}
	}
};
//根据角色加载与模块的关系数据
var loadModuleByRoleId = function(id){
	$("#module_tree").mask("正在加载，请稍后...");
	$.ajax({
		url:'btp/authorize/getModuleByRoleId.zb',
		type: 'POST',
		cache: false,
		data: eval('({roleId:"'+id+'"})'),
		dataType: 'json',
		success: function(res){
			$("#module_tree").unmask();
			var treeObj = $.fn.zTree.getZTreeObj("module_tree");
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			for(var j=0;j<nodes.length;j++){
				treeObj.checkNode(nodes[j], false, false);
			}
			if(res){
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
			$("#module_tree").unmask();
			JDialog.tip("服务器异常");
		}
	});
};
//选择角色出发的事件
var roleClick = function(obj){
	if($("#module_tree").html()){
		loadModuleByRoleId($(obj).val());
	}
};
//获取选择的模块
var getSelectNodes = function(){
	var treeObj = $.fn.zTree.getZTreeObj("module_tree");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = "";
	for(var i=0;i<nodes.length;i++){
		ids += nodes[i].moduleId+",";
	}
	if(ids.length>0) ids = ids.substring(0,ids.length-1);
	return ids;
}
//给角色分配功能模块
var saveModuleRole = function(data){
	$(document.body).mask("正在授权，请稍后...");
	$.ajax({
		url:'btp/authorize/saveModuleRole.zb',
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
}

$(function(){
	$("#module_tree").css("max-height",document.documentElement.clientHeight-155);
	
	$("#saveModuleRole").click(function(){
		if(!$("#roleDiv").html()){
			JDialog.tip("没有角色，无法保存");
			return;
		}
		if(!$("#module_tree").html()){
			JDialog.tip("没有模块，无法保存");
			return;
		}
		var roleId = "";
		$("input[name='roleName']").each(function(){
			if($(this).attr("checked")){
				roleId = $(this).val();
			}
		});
		if(!roleId){
			JDialog.tip("请选择角色，再进行分配保存");
			return;
		}
		var moduleIds = getSelectNodes();
		var data = "{roleId:'"+roleId+"',moduleId:'"+moduleIds+"'}";
		saveModuleRole(data);
	});
});