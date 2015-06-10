var grid,editData,formDialog,setBtnDisabled;
var editHTML = "";
var col = [ {text: '患者编号',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'		}, 
			{text: '患者主索引',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'hzzsy'		},
			{text: '姓名',	width: 80,	textAlign: 'center',	align : 'center',	dataIndex: 'xm'	},
			{text: '性别',	width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'xb'	},
			{text: '出生日期',	width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'csrq'	},
			{text: '系统域名称',	width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'xtymc'	},
			{text: '门诊卡号',	width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'mzkh'	},
			{text: '住院号',	width: 80,	textAlign: 'center',	align : 'center',	dataIndex: 'zyh'		},
			{text: '身份证号',	width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'sfzh'	},			
			{text: '联系电话',	width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'lxdh'	},
			{text: '家庭地址',	width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'jtdz'	},
			{text: '匹配度',	width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'ppd'	},
			{text: '操作',	width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'cz'	}];


$(function(){
	grid = new JGrid({
		title: '患者信息列表',
		col	 :col,
		dataCol:'',
		checkbox : true,
		striped	 :true,
		height   :document.body.clientHeight-95,
		dataUrl  : x=='x'?'merge/hzs.json':'merge/hz.json',
		render   : 'grid',
		pageBar  : false,
		crudOpera: false
	});
	if(x=='x') grid.loadData();
	
	// 查询
	$("#find").click(function() {
		var datas = '{';
		$("#query").find("input").each(function() {
			if ($.trim($(this).val()) != '') {
				datas += $(this).attr("name")+ ":'" + $(this).val()+ "',";
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
	
	$("#hb").click(function(){
		var rows = grid.getSelections();
		if(rows.length<2){
			JDialog.tip('至少选择两条记录进行合并。');					
			return;
		}
		var hzzsys = "";
		for(var i=0; i<rows.length; i++){
			hzzsys += rows[i].id + ",";
		}
		hzzsys = hzzsys.substring(0, hzzsys.length-1);
		$("#detailForm input").val(hzzsys);
		$("#detailForm").submit();
	});
	
});