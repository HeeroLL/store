var grid,queryCondition,editData;
$(function(){
/*****************查询grid***START*******************************/
var col=[  {text: '医疗工作人员ID',	width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'personnelId'		},
	   	    {text: '姓名',			width: 110,	textAlign: 'center',	align : 'left',		dataIndex: 'fullname'			},
	   	    {text: '职称',			width: 70,	textAlign: 'center',	align : 'left',		dataIndex: 'jobTitle'			},
	   	    {text: '人员类别',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'personnelType'		}
	   	     ];

grid = new JGrid({
	title: '模块列表',
	col	 :col,
	dataCol:'',
	checkbox : true,
	striped:true,
	height   :document.body.clientHeight-135,
	dataUrl  : 'btp/personnel/personnelRightPage.zb',
	render   : 'grid',
	pageBar  : true,
	crudOpera: false,
	countEveryPage: 20,
	formToggleId:'eoc',//切换表单显示的id
	listeners: {
		dblclick: function(row){//Grid双击事件
			editData("角色分配", row['personnelId']);
		}
	}
});
//grid.loadData(); 
/************************查询grid***END*******************************/

grid.addButton({
	text : '分配角色',
	handler : function() {
		var rows = grid.getSelections();
		if(rows.length != 1){
			JDialog.tip('请选择一条记录!');
			return;
		}
		editData("角色分配",rows[0]['personnelId']);
	}
});


//查询条件的拼装
queryCondition = function(){
	var datas="{";
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
}
//查询单击事件
$("#querybtn").click(function(){
	var datas=queryCondition();
	grid.setParams(eval('('+datas+')'));
	grid.loadData();
});

//单击左侧树节点，加载列表数据
zTreeOnClick = function(mhoId){
	$("#query").find("input[name='mhoId']").val(mhoId);
	grid.setParams({
		mhoId: mhoId
	});
	grid.loadData();
}

editData = function(title,id){
	formDialog=new JDialog({
		title : title,
		width : 800,
		height :700,
		buttons: [{
 			text: '保存',
 			id: 'personnel_save',	 			
 			handler: function(){
				ztree.save(function callback(){
					formDialog.closeDialog();
				});
 			}
 		},{
 			text: '关闭',
 			id: 'personnel_close',
 			handler: function(){
 				formDialog.closeDialog();
 			}
 		}]
	});
	var medicalOrganId = $("#mhoId").val();
	var url = "btp/role/goAllotRole.zb";
	formDialog.show();
	formDialog.add("<iframe id='ztree' name='ztree' src='"+url+"?id="+id+"&organId="+medicalOrganId+"' width='100%' height='100%' frameBorder='0' scrolling='yes' />"); 
}
});

