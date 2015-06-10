var grid,editData,dialog,setBtnDisabled;
var col1=[ {text: '任务ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'			},
		   {text: '表名',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'tableName'			},
		   {text: '描述',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'tableDesc'			},
		   {text: '表类型',		width: 0,	textAlign: 'center',	align : 'left',		dataIndex: 'tableType'			},
		   {
		   		text: '表类型',    
				width: 100,
				textAlign: 'center',	
				align : 'left',
		   		formatter: function(data){
			   		if(data['tableType']=='1'){
		   				return "源数据层";
		   			}else if(data['tableType']=='2'){
		   			  	return "报表层";
		   			}else{
		   				return "";
		   			}
		   		}
		   },
		   {text: '表应用数据源',	width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'dsId'		}];


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
		title: '表配置列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.body.clientHeight-100,
		dataUrl  : 'clear/tableConfList.zb',
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
 					grid.opt.removeUrl = "clear/tableConfInfoRemove.zb";
 					grid.opt.crudID = "id";
 					grid.removeData();
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
		}
	});
	
	grid.loadData();
	
	editData = function(title,id){
		dialog = new JDialog({
			title : title,
			width : 400,
			height :320,
			buttons: [{
	 			text: '保存',
	 			id: 'p_table_save',	 			
	 			handler: function(){
					tableConfEdit.saveInfo(function(){
						dialog.closeDialog();
						grid.loadData();
					});
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'p_table_close',
	 			handler: function(){
	 				dialog.closeDialog();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		dialog.show();
		dialog.add("<iframe id='tableConfEdit' name='tableConfEdit' src='clear/tableConfEdit.zb?id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	}
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
});