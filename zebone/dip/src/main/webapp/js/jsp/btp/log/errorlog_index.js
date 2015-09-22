var tab,mainHTML;
var colArray=[ 	{text: '日志ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'errorLogId'		},
	   	    {text: '类名',		width: 440,	textAlign: 'center',	align : 'center', 	dataIndex: 'className'	},
	   	    {text: '方法名',	    width: 240,	textAlign: 'center',	align : 'left',		dataIndex: 'methodName'	},
	   	    {text: '记录时间',	width: 180,	textAlign: 'center',	align : 'center',	dataIndex: 'createTime'	}
//	   	    {text: '操作',	    width: 70,	textAlign: 'center',	align : 'left',	    
//	   	    	formatter: function(data){
//	   	    		return "<button onclick=\"showErrorInfo('"+data.errorLogId+"')\">详情</button>";
//	   	    	}
//	   	    }
	   	 ];

function showErrorInfo(id){
	var url = "btp/errorlog/getErrorInfoById.zb";
	$.post(url,{errorLogid:id},function(data){
//		alert(data);
		showDialog(data);
	});
}

var grid;
$(function(){
	grid = new JGrid({
			title: '错误日志列表',
			crudID: 'errorLogId',
			col	 :colArray,
			dataCol:'',
			checkbox : true,
			striped:true,
			height   :document.body.clientHeight-175,
			dataUrl  : 'btp/errorlog/findErrorLog.zb',
			removeUrl: 'btp/errorlog/deleteErrorLog.zb',
			render   : 'grid',
			pageBar  : true,
			crudOpera: false,
			countEveryPage: 20,
			listeners: {
				//Grid双击事件
 				dblclick: 
 					function(row){
						showErrorInfo(row['errorLogId']);
 					},
 				curdButtonClick: 
 						function(btn){
			 				if(btn=='remove'){ //删除操作
			 					var rows = grid.getSelections();
			 					if(rows.length == 0){
			 						JDialog.tip('请至少选择一条记录进行删除');
			 						return;
			 					}
			 					grid.removeData();
			 				}else if(btn=='info'){
			 					var rows = grid.getSelections();
			 					if(rows==0){
			 						JDialog.tip('您未选择要查看的记录');
			 						return;
			 					}
			 					if(rows.length >1){
			 						JDialog.tip('请选择一条记录进行查看');
			 						return;
			 					}
			 					showErrorInfo(rows[0].errorLogId);
			 				}else if(btn=='refresh'){
			 					grid.loadData();
			 				}
			 			}
 			}
	});
 	grid.getTopBar().append("<button name='remove' class='JButtonClass' toggle=''><span class='removeSpan'>删 除</span></button>");
	grid.getTopBar().append("<button name='info' class='JButtonClass' toggle=''><span class='addSpan'>详情</span></button>");
 	grid.getTopBar().append("<button name='refresh' class='JButtonClass' toggle=''><span class='refreshSpan'>刷 新</span></button>");
	
	// 注册按钮
	grid.registerCRUDEvent();
	grid.loadData();
});

function showDialog(errorInfo){
	$("#errorInfo").html(errorInfo);
	d=new JDialog({
		title : '详细信息',
		width : 800,
		height :500,
		content:$('#cHTML').html(),
		buttons: [{
	 			text: '关闭',
	 			id: 'P_close',
	 			handler: function(){
					$("#errorInfo").html("");
	 				d.closeDialog();
	 			}
	 		}]
	});
};


function doSearch(){
	 var options = { 
        	url:     'btp/errorlog/findErrorLog.zb',   // target element(s) to be updated with server response 
        	success: function(res){
				grid.setData(res.data);	 
        	} 
    	}; 
	$("#frm").ajaxSubmit(options);
}

function doSearchAll(){
	grid.loadData();
}
