var tab,mainHTML;
var col1=[ 	{text: '日志ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'logId'		},
	   	    {text: '操作人',		width: 60,	textAlign: 'center',	align : 'center', 	dataIndex: 'fullName'	},
	   	    {text: '操作时间',	width: 120,	textAlign: 'center',	align : 'left',		dataIndex: 'createTime'	},
	   	    {text: 'IP地址',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'ipAddress'	},
	   	    {text: '所属医疗机构',	width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'mhoName'	},
	   	    {text: '模块',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'moduleName'	},
	   	    {text: '业务id',		width: 200,	textAlign: 'center',	align : 'left',		dataIndex: 'serviceId'	},
	   	    {text: '操作标识',	width: 60,	textAlign: 'center',	align : 'left',		dataIndex: 'opFlag', renderer: function(value){
	   	    	if (value=="1")
	   	    	{
	   	    		return "新增";
	   	    	}
	   	    	if (value=="2")
	   	    	{
	   	    		return "修改";
	   	    	}
	   	    	if (value=="3")
	   	    	{
	   	    		return "删除";
	   	    	}
	   	    	if (value=="4")
	   	    	{
	   	    		return "查询";
	   	    	}
	   	    }},
	   	    {text: '描述',		width: 250,	textAlign: 'center',	align : 'left',		dataIndex: 'description'} ];
var grid;
var editData=function(title, id){
	tab.addTab({
		id: id,
		title: title,
		icon: 'images/icons/update.png',
		closable: true,
		url:'btp/log/findLogById.zb?logId='+id
	});
}
$(function(){
	tab=new JTab();
	mainHTML=$('#main').html();$('#main').remove();
	tab.addTab({
		id: 'main',
		title: '审计日志查询',
		icon: 'images/console.png',
		html: mainHTML
	});
	grid = new JGrid({
			title: '审计日志列表',
			crudID: 'logId',
			col	 :col1,
			dataCol:'',
			checkbox : true,
			striped:true,
			height   :document.body.clientHeight-175,
			dataUrl  : 'btp/log/searchLog.zb',
			removeUrl: 'btp/log/removeLog.zb',
			render   : 'grid',
			pageBar  : true,
			crudOpera: false,
			countEveryPage: 20,
			listeners: {
 			dblclick: function(row){//Grid双击事件
 				editData("查 询", row['logId']);
 			},
 			curdButtonClick: function(btn){
 				if(btn=='detail'){
 					var rows = grid.getSelections();
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行查询');
 						return;
 					}
 					editData("查 询", rows[0]['logId']);
 				}else if(btn=='remove'){ //删除操作
 					var rows = grid.getSelections();
 					if(rows.length == 0){
 						JDialog.tip('请至少选择一条记录进行删除');
 						return;
 					}
 					grid.removeData();
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
 		}
	});
	grid.getTopBar().append("<button name='detail' class='JButtonClass' toggle=''><span class='updateSpan'>查看详情</span></button>");
 	grid.getTopBar().append("<button name='remove' class='JButtonClass' toggle=''><span class='removeSpan'>删 除</span></button>");
	grid.getTopBar().append("<button name='refresh' class='JButtonClass' toggle=''><span class='refreshSpan'>刷 新</span></button>");
	// 注册按钮
	grid.registerCRUDEvent();
	grid.loadData();

	// 查询
	$("#find").click(function() {
		var datas = '{';
		$("#query").find("input").each(function() {
			if ($.trim($(this).val()) != '') {
				datas += $(this).attr("name")+ ":'" + $(this).val()+ "',";
			}
		});
		$("#query").find("select").each(function() {
			if ($.trim($(this).val()) != ''&&$.trim($(this).val()) !='-1') {
				datas += $(this).attr("name") + ":'" + $(this).val() + "',";
			}
		});

		if (datas.length > 1){
			datas = datas.substring(0, datas.length - 1);
		}
		datas += "}";
		grid.setParams(eval('(' + datas + ')'));
		grid.loadData(1);
	});
	$("#findAll").click(function() {
		grid.setParams(null);
		grid.loadData(1);
	});
});