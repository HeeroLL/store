var grid,editData,dialog,setBtnDisabled;
var col1 = [{text: '节点ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'			},
			{text: '节点名称',    width: 100,  textAlign: 'center',	align : 'left',	dataIndex: 'nodesName'       },
			{text: '节点描述',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeDesc'       },
			{text: 'IP地址',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeIp'       },
			{text: '端口号',    width: 100,  textAlign: 'center',	align : 'left',	dataIndex: 'nodePort'       },
			{text: '网络状态',    width: 150,  align : 'left',	
			       formatter: function(data){
			       		var value = "";
			       		if(data['nodeNet']){
			       			if(data['nodeNet'] =='1'){
			       				value = "正常";
			       			}else if(data['nodeNet'] == '0'){
			       				value = "不通";
			       			}
			       		}
			       		var str = "<span style=\"width:50px;\">"+value+"</span>";
			       		if(value){
			       			str += "<button onClick=\"checkNodeNet(this,'"+data['id']+"','"+data['nodeIp']+"');\" style=\"margin-left: 45px;\">重新检测</button>";
			       		}else{
			       			str += "<button onClick=\"checkNodeNet(this,'"+data['id']+"','"+data['nodeIp']+"');\" style=\"margin-left: 45px;\">检测</button>";
			       		}
			       		return str;
			       }
			       },
			{text: '运行状态',    width: 150,  align : 'left',
				   formatter: function(data){
			       		var value = "";
			       		if(data['nodeRun']){
			       			if(data['nodeRun'] =='1'){
			       				value = "正常";
			       			}else if(data['nodeRun'] == '0'){
			       				value = "异常";
			       			}
			       		}
			       		var str = "<span style=\"width:50px;\">"+value+"</span>";
			       		if(value){
			       			str += "<button onClick=\"checkNodeRun(this,'"+data['id']+"','"+data['nodeIp']+"');\" style=\"margin-left: 45px;\">重新检测</button>";
			       		}else{
			       			str += "<button onClick=\"checkNodeRun(this,'"+data['id']+"','"+data['nodeIp']+"');\" style=\"margin-left: 45px;\">检测</button>";
			       		}
			       		return str;
			       }
			       },
			{text: '节点状态',    width: 0,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeState'		},
			{
				text: '节点状态',    
				width: 150,  
				textAlign: 'center',	
				align : 'left',
				formatter: function(data){
   	     			  var value = data['id']+","+data['nodeState'];
   	     			  var val="";
   	     			  if(data['nodeState']){
			       			if(data['nodeState'] =='1'){
			       				val = "启动";
			       			}else if(data['nodeState'] == '0'){
			       				val = "停用";
			       			}else{
			       				val = "未启用";
			       			}
			       		}
			       		var str = "<span style=\"width:50px;\">"+val+"</span>";
   	     			  if(data['nodeState']=='1'){
   	     				 return str+"<button onClick=\"updateNodeState('"+value+"',this)\" style=\"margin-left: 45px;\">停止</button>";
   	     			  }else if(data['nodeState']=='0'){
   	     			  	 return str+"<button onClick=\"updateNodeState('"+value+"',this)\" style=\"margin-left: 45px;\">启动</button>";
   	     			  }else{
   	     			  	 return str+"<button onClick=\"updateNodeState('"+value+"',this)\" style=\"margin-left: 45px;\">启动</button>";
   	     			  }
 					   
 				}
 			}];

var getCondition = function(){
	var datas = "{";
	$("#queryTj").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	$("#queryTj").find("select").each(function(){
		if($.trim($(this).val())!=''&&$.trim($(this).val())!='-1'){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	if(datas.length>1) datas = datas.substring(0, datas.length-1);
	datas += "}";
	return datas;
};

function onQueryCondition(){
	var data = getCondition();
	grid.setParams(eval('('+data+')'));
	grid.loadData();
}

var updateNodeState = function(data,obj){
	$(document.body).mask("正在更新节点状态，请稍后...");
	var str = data.split(",");
	$.ajax({
		url:'node/updateNodeState.zb',
		type: 'POST',
		cache: false,
		data: eval("({nodeData:'"+data+"'})"),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			var val = "";
			if(str[1]){
				if(str[1] == "1"){
					val = "停止";
				}else{
					val = "启动";
				}
			}else{
				val = "启动";
			}
			if(res.success){
				//JDialog.tip("节点"+val+"成功");
//				if(str[1] == "1"){
//					$(obj).parent().find("span").text("停用");
//					$(obj).text("启动");
//				}else{
//					$(obj).parent().find("span").text("启动");
//					$(obj).text("停止");
//				}
				grid.loadData();
			}else{
				JDialog.tip("节点"+val+"失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，数据更新失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
};

/**
var updateNodeState = function(data){
	$(document.body).mask("正在更新节点状态，请稍后...");
	$.ajax({
		url:'node/updateNodeState.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("节点更新成功");
				grid.loadData();
			}else{
				JDialog.tip("节点更新失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，数据更新失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
};*/
//节点网络状态检测
var checkNodeNet = function(obj,id,ip){
	$(document.body).mask("正在检测节点网络状态，请稍后...");
	var data="{id:'"+id+"',nodeIp:'"+ip+"'}";
	$.ajax({
		url:'node/checkNodeNet.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			$(obj).parent().find("span").text(res.msg == "1"?"正常":"不通");
			$(obj).text("重新检测");
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，节点检测失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
};
//节点运行状态检测
var checkNodeRun = function(obj,id,ip){
	$(document.body).mask("正在检测节点运行状态，请稍后...");
	var data="{id:'"+id+"',nodeIp:'"+ip+"'}";
	$.ajax({
		url:'node/checkNodeRun.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			$(obj).parent().find("span").text(res.msg == "1"?"正常":"异常");
			$(obj).text("重新检测");
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，节点检测失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
};
var checkNodeState = function(data){
	$(document.body).mask("节点检测中，请稍后...");
	$.ajax({
		url:'node/checkNodeState.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("节点运行正常",3);
				grid.loadData();
			}else{
				if(res.status == 0){
					JDialog.tip("节点网络不通",3);
				}else if(res.status == 2){
					JDialog.tip("节点运行异常",3);
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，节点检测失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
};

$(function(){
	grid = new JGrid({
		title: '任务列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.body.clientHeight-100,
		dataUrl  : 'node/nodeManaList.zb',
		render   : 'grid',
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 20,
		listeners: {
			dblclick: function(row){//Grid双击事件
				editData("修改",row['id']);
			},
			curdButtonClick: function(btn){
				if(btn=='add'){
 					editData("新 建");
 				}else if(btn=='update'){ 					
 					var rows = grid.getSelections();
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行修改');
 						return;
 					}
 					editData("修 改", rows[0]['id']);
 				}else if(btn=='remove'){ //删除操作
 					var rows = grid.getSelections();
 					if(rows.length==0){
 						JDialog.tip('请选择记录进行删除');
 						return;
 					}
 					grid.opt.removeUrl = "node/nodeInfoRemove.zb";
 					grid.opt.crudID = "id";
 					grid.removeData();
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
			}
		}
	});
	/** 
	grid.addButton({
		text : '启用/停用',
		handler : function() {
			var rows = grid.getSelections();
			if(rows.length == 0){
				JDialog.tip('请选择记录进行操作!');
				return;
			}
			var data = "{nodeData:'";
			for(var i=0; i<rows.length ;i++){
				data += rows[i].id + "," + rows[i].nodeState + ";" ;
			}
			if(data.length > 11) data = data.substring(0,data.length-1);
			data += "'}";
			updateNodeState(data);
		}
	});
	
	grid.addButton({
		text : '节点检测',
		handler : function() {
			var rows = grid.getSelections();
			if(rows.length != 1){
				JDialog.tip('请选择一个节点进行检测!');
				return;
			}
			var data = "{nodeData:'";
			for(var i=0; i<rows.length ;i++){
				data += rows[i].id + "," + rows[i].nodeIp + ";" ;
			}
			if(data.length > 11) data = data.substring(0,data.length-1);
			data += "'}";
			checkNodeState(data);
		}
	});
	*/
	
	editData = function(title,id){
		dialog = new JDialog({
			title : title,
			width : 400,
			height :320,
			buttons: [{
	 			text: '保存',
	 			id: 'node_save',	 			
	 			handler: function(){
					nodeInfoEdit.saveInfo(function(){
						dialog.closeDialog();
	 					grid.loadData();
					});
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'node_close',
	 			handler: function(){
	 				dialog.closeDialog();
	 				grid.loadData();
	 			}
	 		}]
		});
		if(!id) id = "";
		dialog.show();
		dialog.add("<iframe id='nodeInfoEdit' name='nodeInfoEdit' src='node/nodeInfoEdit.zb?id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	}
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	
	grid.loadData();
});