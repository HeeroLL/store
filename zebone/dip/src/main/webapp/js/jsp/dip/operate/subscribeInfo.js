var grid;
var serviceCol = [ {text: '订阅ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'   },
	                {text: '所属机构',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'orgCode'	},
	                {text: '节点名称',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'nodeName'	},
	                {text: '节点状态',		width: 70,	textAlign: 'center',	align : 'center',	
	                	formatter: function(data){
					       		var value = "";
					       		if(data['nodeState']){
					       			if(data['nodeState'] =='1'){
					       				value = "启用";
					       			}else if(data['nodeState'] == '2'){
					       				value = "停用";
					       			}
					       		}
					       		return value;
					       }	
					 },
	                {text: '服务名称',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'serviceName'},
	                {text: '服务状态',		width: 70,	textAlign: 'center',	align : 'center',	
	                	formatter: function(data){
					       		var value = "";
					       		if(data['serviceState']){
					       			if(data['serviceState'] =='1'){
					       				value = "启用";
					       			}else if(data['serviceState'] == '2'){
					       				value = "停用";
					       			}
					       		}
					       		return value;
					       }
					 },
	                {text: '服务类型',    width: 90,  align : 'center',	
				       formatter: function(data){
					       		var value = "";
					       		if(data['serviceType']){
					       			if(data['serviceType'] =='1'){
					       				value = "文档服务";
					       			}else if(data['serviceType'] == '2'){
					       				value = "资源服务";
					       			}
					       		}
					       		return value;
					       }
				       },
	                {text: '订阅描述',		width: 300,	textAlign: 'center',	align : 'center',	dataIndex: 'subFrequ'	}
	                ];
$(function(){
	grid = new JGrid({
		title : '订阅信息',
		col : serviceCol,
		checkbox : true,
		dataUrl : 'operationalGuidance/subscribeQuery.zb',
		render : 'grid',
		countEveryPage : 20,
		height   :380,
		pageBar  : true,
		crudOpera: false,
		selectModel:true
	});
	
	grid.loadData();
	
	$("#querybtn").click(function(){
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
		grid.setParams(eval('('+datas+')'));
		grid.loadData(1);
	});
	
	var options={
		width: 180,
	    maxHeight: 200,
		select:false,
		textField:'nodeOrgName',
		serviceUrl:'operationalGuidance/getOrgInfo.zb',
		col:[{dataIndex:'nodeOrgName'}],
		valueField:{dataIndex:'nodeOrg',dataName:'orgCode'}//隐藏文本的name
	};
	$("#query_mhoName").autocomplete(options);
	
});