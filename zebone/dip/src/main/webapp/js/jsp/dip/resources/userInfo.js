var col = [ {text: '姓名',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'name'   },
            {text: '岗位类别',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'postCode'	},
			{text: '职务类别',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'positionalTitlesCode'	},
			{text: '所属科室',	width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'departmentCode' },
			{text: '性别',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'sexCode'	},
			{text: '个人简介',	width: 300,	textAlign: 'center',	align : 'left',	dataIndex: 'resume'	}
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
	var options={
			width: 200,
		    maxHeight: 200,
			select:false,
			textField:'deptName',
			serviceUrl:'resourcesManage/getDeptByOrgCode.zb?orgCode='+orgCode,
			col:[{dataIndex:'deptName'},{dataIndex:'deptCode'}],
			valueField:{dataIndex:'deptCode',dataName:'departmentCode'}//隐藏文本的name
		};
	
	$("#deptName").autocomplete(options);
	
	var grid = new JGrid({
		title : '人员信息',
		col : col,
		checkbox : false,
		dataUrl : 'resourcesManage/users.zb?medicalOrganCode='+orgCode,
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
		if($.trim($("#deptName").val())==""){
			$("input[name=departmentCode]").val("");
		}
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData(1);
	});
});