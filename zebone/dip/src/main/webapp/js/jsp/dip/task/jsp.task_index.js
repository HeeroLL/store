var grid,queryCondition,editData,setBtnDisabled;
var col1=[ {text: '任务ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'			},
		   {text: '编码',		width: 110,	textAlign: 'center',	align : 'left',		dataIndex: 'taskCode'			},
		   {text: '描述',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'taskDesc'			},
		   {text: '任务状态',		width: 0,	textAlign: 'center',	align : 'left',		dataIndex: 'taskDeploy'			},
		   {
		   		text: '任务状态',    
				width: 100,
				textAlign: 'center',	
				align : 'left',
		   		formatter: function(data){
			   		if(data['taskDeploy']=='1'){
		   				return "启用";
		   			}else{
		   			  	return "停用";
		   			}
		   		}
		   },
		   {text: '执行频率',	width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'taskFreq'		},
		   {text: '执行时间',	width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'taskTime'		},
		   {text: '类型',	width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'taskType',
		   renderer: function(value){
		   		if(value == '1'){
		   			return "ETL任务";
		   		}else if(value == '2'){
		   			return "SQL任务";
		   		}else if(value == '3'){
		   			return "其他任务";
		   		}else if(value == '5'){
		   			return "清洗任务";
		   		}
		   }
		   		},
		   {text: '发布的前置节点',	width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'taskNode'		},
		   {text: '执行状态',			width: 110, textAlign: 'center',    align : 'left',		dataIndex: 'taskState',
		   		renderer: function(value){
 	     			  if(value=='1'){
 	     			  	 return "正常";
 	     			  }else if(value=='0'){
 	     			  	 return "异常";
 	     			  }else{
 	     			  	 return "";
 	     			  }
 				}
 		}];
var updateTaskState = function(data){
	$(document.body).mask("正在更新任务状态，请稍后...");
	$.ajax({
		url:'task/updateTaskState.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("任务更新成功");
				grid.loadData();
			}else{
				JDialog.tip("任务更新失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，数据更新失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
};
 			
$(function(){
	queryCondition = function(){
		var datas = "{";
		$("#query").find("input").each(function(){
			if($.trim($(this).val())!=''){
				datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
			}
		});
		$("#query").find("select").each(function(){
			if($.trim($(this).val())!=''&&$.trim($(this).val())!='-1'){
				datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
			}
		});
		if(datas.length>1) datas = datas.substring(0, datas.length-1);
		datas += "}";
		return datas;
	};
	
	$("#querybtn").click(function(){
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData();
	});
	
	grid = new JGrid({
		title: '任务列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.body.clientHeight-100,
		dataUrl  : 'task/taskInfoList.zb',
		render   : 'grid',
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 20,
		listeners: {
			dblclick: function(row){//Grid双击事件
				editData("修 改", row['id']);
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
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行删除');
 						return;
 					}
 					grid.opt.removeUrl = "task/taskInfoRemove.zb";
 					grid.opt.crudID = "id";
 					grid.removeData();
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
		}
	});
	
	grid.addButton({
		text : '启用/停用',
		handler : function() {
			var rows = grid.getSelections();
			if(rows.length == 0){
				JDialog.tip('请选择记录进行操作!');
				return;
			}
			var data = "{taskData:'";
			for(var i=0; i<rows.length ;i++){
				data += rows[i].id + "," + rows[i].taskDeploy + ";" ;
			}
			if(data.length > 11) data = data.substring(0,data.length-1);
			data += "'}";
			updateTaskState(data);
		}
	});
	
	editData = function(title,id){
		dialog = new JDialog({
			title : title,
			width : 420,
			height :405,
			buttons: [{
	 			text: '保存',
	 			id: 'p_task_save',	 			
	 			handler: function(){
					taskInfoEdit.saveInfo(function(){
						dialog.closeDialog();
	 					grid.loadData();
					});
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'p_task_close',
	 			handler: function(){
	 				dialog.closeDialog();
	 				grid.loadData();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		dialog.show();
		dialog.add("<iframe id='taskInfoEdit' name='taskInfoEdit' src='task/taskInfoEdit.zb?id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	}
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	
	grid.loadData();
});