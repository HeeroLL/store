var grid,editData,formDialog,setBtnDisabled;
var col1 = [{text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'			},
		   {text: '数据元标识符',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'pCode'			},
		   {text: '数据元名称',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'pName'			},
		   {text: '数据类型',		width: 90,	textAlign: 'center',	align : 'left',		dataIndex: 'pType'			},
		   {text: '表示格式',	width: 90,	textAlign: 'center',	align : 'left',		dataIndex: 'pFormat'		},
		   {text: '描述定义',	width: 200,	textAlign: 'center',	align : 'left',		dataIndex: 'pDesc'		},
		   {text: '数据元允许值',	width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'pValue'		},
		   {text: '值类型',	width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'pValueType',
		   renderer: function(value){
		   		if(value == 'D1'){
		   			return "枚举型";
		   		}else if(value == 'D2'){
		   			return "GB/T";
		   		}else if(value == 'D3'){
		   			return "CV型";
		   		}else if(value == 'M'){
		   			return "主数据编码";
		   		}else if(value == 'J'){
		   			return "机构代码";
		   		}else if(value == 'QT'){
		   			return "其他";
		   		}else{
		   			return "";
		   		}
		   }}];

$(function(){
	//查询相关标准数据集信息
	$("#querybtn").click(function(){
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
		grid.setParams(eval('('+datas+')'));
		grid.loadData();
	});
	
	grid = new JGrid({
		title: '任务列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.documentElement.clientHeight-110,
		dataUrl  : 'dataset/datasetInfoList.zb',
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
 					//return;
 					var rows = grid.getSelections();
 					if(rows.length<1){
 						JDialog.tip('请选择记录进行删除');
 						return;
 					}
 					grid.opt.removeUrl = "dataset/datasetInfoRemove.zb";
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
		formDialog = new JDialog({
			title : title,
			width : 400,
			height :345,
			buttons: [{
	 			text: '保存',
	 			id: 'p_dataset_save',	 			
	 			handler: function(){
					datasetEdit.saveInfo(function(){
						formDialog.closeDialog();
						grid.loadData();
					});
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'p_dataset_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		formDialog.show();
		formDialog.add("<iframe id='datasetEdit' name='datasetEdit' src='data/datasetEdit.zb?id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	};
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
});