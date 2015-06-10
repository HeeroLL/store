var familyInfo = function(famiId){
		formDialog = new JDialog({
			title : "家庭档案详情",
			width : 580,
			height :350,
			buttons: [{
	 			text: '关闭',
	 			id: 'p_dataset_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		formDialog.show();
		formDialog.add("<iframe id='familyEdit' name='familyEdit' src='resourcesManage/familyEdit.zb?id="+famiId+"' width='100%' height='100%' frameBorder='0'  />");
}

var col = [ {text: '家庭档案id',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'famiId'   },
            {text: '家庭档案编号',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'famiCode'   },
			{text: '户主姓名',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'householdName'	},
			{text: '责任医生',	width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'doctorCode'},
			{text: '居住类型',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'liveType'	},
			{text: '家庭住址',	width: 300,	textAlign: 'center',	align : 'left',	dataIndex: 'familyAddr'	},
			{ text: '操作',
				  width:100,	
				  textAlign: 'center',	
				  align : 'center',	
				  formatter: 
					  function(rowData){
					  	return '<a href="javascript:familyInfo(\''+rowData['famiId']+'\')">详情</a>';
			      	  }
				}
		  ];

var queryCondition = function(){
	var datas = "{";
	$("#query").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	$("#query").find("select").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	if(datas.length>1) datas = datas.substring(0, datas.length-1);
	datas += "}";
	return datas;
};
 
$(function(){
	var grid = new JGrid({
		title : '家庭档案信息',
		col : col,
		checkbox : false,
		dataUrl : 'resourcesManage/familys.zb?familyOrgCode='+orgCode,
		render : 'grid',
		height : 375,
		countEveryPage : 20,
		pageBar  : true,
		crudOpera: false
	});
	
	grid.loadData();
	
	
	
	$("#querybtn").click(function(){
		if(orgCode=="XXXX"){
			JDialog.tip('请选择医疗机构',2);	
			return;
		}
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData(1);
	});
});