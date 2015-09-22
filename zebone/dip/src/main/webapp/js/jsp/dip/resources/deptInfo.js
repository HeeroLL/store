function lostFocusInput(obj){
	var value = $.trim($(obj).val());
	if(!value) $(obj).val("科室代码或名称");
}

function getFocusInput(obj){
	var value = $.trim($(obj).val());
	if(value == "科室代码或名称") $(obj).val('');
}
var col = [ {text: '科室代码',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'deptCode'   },
			{text: '科室名称',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'deptName'	},
			{text: '科室说明',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'deptDesc'	},
            {text: '县、市',		width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'cityCode'	},
            {text: '镇、街道',		width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'townCode'	},
            {text: '村、社区',		width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'communityCode'	},
            {text: '医疗机构',		width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'medicalOrgCode'	},
            {text: '归类',		width: 70,	textAlign: 'center',	align : 'center',	dataIndex: 'classifiedCode'	},
            {text: '门诊、住院',		width: 70,	textAlign: 'center',	align : 'center',	dataIndex: 'clinicHospCode'	},
            {text: '科室分类',		width: 80,	textAlign: 'center',	align : 'center',	dataIndex: 'deptClassifyCode'	},
            {text: '院内码',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'hospInternalCode'	},
			{text: '所属科室',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'parentDeptCode', 
			formatter:function(data){
			 	var value = data['parentDeptCode'];
			 	if(value){
			 		return value;
			 	}else{
			 		return "无";
			 	}
			 } }
			
		  ];

 
 
$(function(){
	var grid = new JGrid({
		title : '科室信息',
		col : col,
		checkbox : false,
		dataUrl : 'resourcesManage/depts.zb?orgCode='+orgCode,
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
		var name = $("input[name=NameValue]").val();
		if(name!='科室代码或名称'){
			grid.setParams(eval("({deptName:'"+name+"'})"));
			grid.loadData(1);
		}else{
			grid.setParams(eval("({})"));
			grid.loadData(1);
		}
	});
});