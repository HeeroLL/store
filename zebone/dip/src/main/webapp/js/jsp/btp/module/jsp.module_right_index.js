var grid,editData,dialog,loadModule,setBtnDisabled;
var parentModuleId = "1000";
var maxNo,minNo;
var col1=[ {text: '模块ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'moduleId'			},
		   {text: '父模块ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'parentModuleId'			},
		   {text: '模块名称',		width: 110,	textAlign: 'center',	align : 'left',		dataIndex: 'moduleName'			},
		   {text: '模块URL',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'url'			},
		   {text: '大图标路径',	width: 250,	textAlign: 'center',	align : 'left',		dataIndex: 'maxicon'		},
		   {	
		   		text: '排序号',
				width: 110, 
				textAlign: 'center',	
				align : 'left',		
				dataIndex: 'orderNo',
		   		renderer: function(value){
 					return "<button onClick='clickUpDown(1,\""+value+"\");'>↑</button><button onClick='clickUpDown(2,\""+value+"\");'>↓</button>"; 				
 				}
 			}];

//排序
var clickUpDown = function(status,val){
	if(status == 1){
		if(val == minNo){
			JDialog.tip('该模块已在最前面了');
			return;
		}
	}else{
		if(val == maxNo){
			JDialog.tip('该模块已在最后面了');
			return;
		}
	}
	var data = "{typeCode:"+status+",orderNo:"+val+",parentModuleId:'"+parentModuleId+"'}";
	$.ajax({
		url:'btp/module/moduleOrder.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			if(res.success){
				window.parent.frames["innerlLeftFrame"].loadmoduleTree(parentModuleId);
				grid.loadData();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器异常');
		}
	});
};

$(function(){
	grid = new JGrid({
		title: '模块列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.body.clientHeight-80,
		dataUrl  : 'btp/module/moduleInfoList.zb',
		render   : 'grid',
		crudOpera: true,
		countEveryPage: 25,
		listeners: {
			dblclick: function(row){//Grid双击事件
				editData("修 改", row['moduleId']);
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
 					editData("修 改", rows[0]['moduleId']);
 				}else if(btn=='remove'){ //删除操作
 					var rows = grid.getSelections();
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行删除');
 						return;
 					}
 					grid.opt.removeUrl = "btp/module/moduleInfoRemove.zb";
 					grid.opt.crudID = "moduleId";
 					grid.removeData(function(res){
 						if(res){
 							window.parent.frames["innerlLeftFrame"].loadmoduleTree(parentModuleId);
 						}
 					});
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			},
 			afterload: function(data){
 				if(data.length>0){
	 				maxNo = data[data.length-1].typeCode;
	 				minNo = data[0].typeCode;
 				}
 			}
 		}
	});
	
	editData=function(title, id){
		dialog=new JDialog({
			title : title,
			width : 400,
			height :320,
			buttons: [{
	 			text: '保存',
	 			id: 'module_save',	 			
	 			handler: function(){
					rightModelEdit.saveInfo(function(){
						window.parent.frames["innerlLeftFrame"].loadmoduleTree(parentModuleId);
						dialog.closeDialog();
						grid.setParams({
							parentModuleId: parentModuleId
						});
						grid.loadData();
					});
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'module_close',
	 			handler: function(){
	 				dialog.closeDialog();
	 			}
	 		}]
		});
		
		dialog.show();
		dialog.add("<iframe id='rightModelEdit' name='rightModelEdit' src='btp/module/moduleRightEdit.zb?parentModuleId="+parentModuleId+"&moduleId="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
		
	};
	loadModule = function(id){
		parentModuleId = id;
		grid.setParams({
			parentModuleId: id
		});
		grid.loadData(1);
	};
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
});