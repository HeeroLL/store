var grid,editData,dialog,setBtnDisabled;
var col1 = [{text: '数据源ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'			},
			{text: '数据源名称',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'pName'       },
			{text: '数据源驱动类',    width: 250,  textAlign: 'center',	align : 'left',	dataIndex: 'pDriver'       },
			{text: '数据源url',    width: 250,  textAlign: 'center',	align : 'left',	dataIndex: 'pUrl'       },
			{text: '数据源用户名',    width: 120,  textAlign: 'center',	align : 'left',	dataIndex: 'pUserName'       },
			{text: '数据源密码',    width: 0,  textAlign: 'center',	align : 'left',	dataIndex: 'pPassword'       }
			];

var queryCondition = function(){
	var datas = "{";
	$("#query").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	if(datas.length>1) datas = datas.substring(0, datas.length-1);
	datas += "}";
	return datas;
};

var detectDsObj = function(id){
	$(document.body).mask("正在检测数据源，请稍后...");
	$.ajax({
		url:'ds/detectDsObjById.zb',
		type: 'POST',
		cache: false,
		data: eval("({id:'"+id+"'})"),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.showMessageDialog('信息', '数据源检测可以建立连接', JDialog.INFORMATION);
			}else{
				JDialog.showMessageDialog('信息', '数据源检测不可建立连接', JDialog.INFORMATION);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document.body).unmask();
			JDialog.tip('服务器异常，检测失败',JDialog.ERROR);
		}
	});
};

$(function(){
	//查询事件
	$("#querybtn").click(function(){
		//查询的条件
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData();
	});
	
	grid = new JGrid({
		title: '数据源列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.body.clientHeight-100,
		dataUrl  : 'ds/dsObjList.zb',
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
 					grid.opt.removeUrl = "ds/dsObjRemove.zb";
 					grid.opt.crudID = "id";
 					grid.removeData();
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
			}
		}
	});
	
	grid.addButton({
		text : '检测',
		handler : function() {
			var rows = grid.getSelections();
			if(rows.length != 1){
				JDialog.tip('请选择一个数据源进行检测');
				return;
			}
			//检测数据源是否通畅
			detectDsObj(rows[0]['id']);
		}
	});
	
	editData = function(title,id){
		dialog = new JDialog({
			title : title,
			width : 400,
			height :320,
			buttons: [{
	 			text: '保存',
	 			id: 'p_dsObj_save',	 			
	 			handler: function(){
					dsObjInfoEdit.saveInfo();
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'p_dsObj_close',
	 			handler: function(){
	 				dialog.closeDialog();
	 				grid.loadData();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		dialog.show();
		dialog.add("<iframe id='dsObjInfoEdit' name='dsObjInfoEdit' src='ds/dsObjInfoEdit.zb?id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	}
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	
	grid.loadData();
});