var editData,formDialog,grid,setBtnDisabled;
var editHTML = "";
var serviceCol = [ {text: '服务ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'   },
	                {text: '服务名称',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'serviceName'	},
	                {text: '服务分类',    width: 150,  align : 'center',	
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
	                {text: '服务编码',		width: 80,	textAlign: 'center',	align : 'center',	dataIndex: 'serviceCode'	},
	                 {text: '服务状态',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'serviceState'	},
	                {text: '操作',    width: 150,  align : 'center',	
				       formatter: function(data){
					       		var value = "";
					       		if(data['serviceState']){
					       			if(data['serviceState'] =='1'){
					       				value = "停用";
					       			}else if(data['serviceState'] == '2'){
					       				value = "启用";
					       			}
					       		}
					       		var str = '';
					       		if(value){
					       			str = '<a class="btn" href="javascript:void(0);"><span class="btn-left" onclick="changeState(\''+data['id']+'\',\''+data['serviceState']+'\')"><span >'+value+'</span></span></a>';
					       		}
					       		return str;
					       }
				       }
	                ];
var changeState = function(id,serviceState){
		$.ajax({
		url:'operationalGuidance/serviceChangeState.zb',
		type: 'POST',
		cache: false,
		data: eval("({serviceId:'"+id+"',serviceState:'"+serviceState+"'})"),
		dataType: 'json',
		success: function(res){
			if(res.success){
				if(serviceState=='1'){
					JDialog.tip('服务已停用',2);
				}else if(serviceState=='2'){
					JDialog.tip('服务已启用',2);
				}else{
					JDialog.tip('保存成功',2);
				}
				grid.loadData();
			}else{
				if(serviceState=='1'){
					JDialog.tip('服务停用失败',2);
				}else if(serviceState=='2'){
					JDialog.tip('服务启用失败',2);
				}else{
					JDialog.tip('保存失败',2);
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，数据加载失败。', JDialog.ERROR);
		}
	});
}
var loadServiceData = function(id){
	$(document.body).mask("正在加载，请稍后...");
	$.ajax({
		url:'operationalGuidance/serviceLoad.zb',
		type: 'POST',
		cache: false,
		data: eval("({serviceId:'"+id+"'})"),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				formDialog.getComponent().find("input[type='text']").each(function() {
					var name = $(this).attr('name');
					if (name) {
						$(this).val(res.data[name]);
					}
				});
				formDialog.getComponent().find("input[type='hidden']").each(function() {
					var name = $(this).attr('name');
					if (name) {
						$(this).val(res.data[name]);
					}
				});
				formDialog.getComponent().find("textarea").each(function() {
					var name = $(this).attr('name');
					if (name) {
						$(this).val(res.data[name]);
					}
				});
				formDialog.getComponent().find("input[type='radio']").each(function() {
					var name = $(this).attr('name');
					if (name) {
						if($(this).val()==res.data[name]){
							$(this).attr("checked",true);
						}
					}
				});
				formDialog.getComponent().find("select").each(function(){
					var name = $(this).attr('name');
					$(this).find("option").each(function(){
							if($(this).val()==res.data[name]){
									$(this).attr("selected",true);
							}
						});
				});
				var serviceFrequs = res.data["serviceFrequ"].split(',');
				for(var i=0;i<serviceFrequs.length;i++){
					formDialog.getComponent().find("input[name='serviceFrequ']").each(function(){
						if($(this).val()==serviceFrequs[i]){
							$(this).attr("checked","checked");
						}
					});
				}
				formDialog.unmask();
			}else{
				formDialog.unmask();
				JDialog.tip('数据加载失败',JDialog.ERROR);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，数据加载失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
};
var saveServiceData = function(){
	if (!checkAll('serviceForm')) {
		return;
	}
	setBtnDisabled ('p_node_save', false);
	formDialog.mask('正在保存数据，请稍候...');
	var data = "{";
	$("#serviceForm").find("input[type='text']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#serviceForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#serviceForm").find("input[type='radio']").each(function(){
		if($(this).attr("checked")!= undefined){
			data += $(this).attr('name')+":'"+$(this).val()+"',";
		}
	});
	$("#serviceForm").find("select").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
		var val = $(this).val();
	});
	$("#serviceForm").find("textarea").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	var str = '';
	$("#serviceForm").find("input[name='serviceFrequ']").each(function(){
		if($(this).attr("checked")=="checked"){
			str+=$(this).val()+',';
		}
	});
	if(str.length>1){
		str = str.substring(0,str.length-1);
		data +="serviceFrequ:'"+str+"',";
	}
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	
	$.ajax({
		url:'operationalGuidance/serviceSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			if(res.success){
				formDialog.closeDialog();
				grid.loadData();
			}else{
				JDialog.tip(res.msg,2);
			}
			setBtnDisabled ('p_service_save', true);
			formDialog.unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			setBtnDisabled ('p_service_save', true);
			formDialog.unmask();
			JDialog.tip('服务器出现异常，数据保存失败。',JDialog.ERROR);
		}
	});
};
var removeDataSet = function(){
	var rows = grid.getSelections();
	if(rows.length>0){
		if(rows[0]['serviceState']=='1'){
			JDialog.tip('服务启用状态，无法删除。',3);	
			return;
		}
		JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
			if(id=='yes'){
				grid.jgridMain.mask('正在删除数据，请稍候...');
				var ids = '';
				for(var i=0; i<rows.length; i++){
					ids += rows[i]['id'] + ',';
				}
				ids = ids.substring(0, ids.length-1);
				var datas = "id="+ids;
				$.ajax({
					url: "operationalGuidance/serviceDelete.zb",
					type: 'POST',
					cache: false,
					data: encodeURI(datas),
					dataType: 'json',
					success: function(res){
						grid.jgridMain.unmask();					
						if(res.success){
							var msg = res.msg?res.msg:'数据删除成功。';
							JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
								if(id=='ok'){
									grid.setParams('');
									grid.loadData(1);
								}
							});	
						}else{					 		
							JDialog.tip(res.msg?res.msg:'数据删除失败。',2);							
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						grid.jgridMain.unmask();
						JDialog.tip('服务器出现异常，数据删除失败。',3);
					}
				});
			}
		});
	}
};
$(function(){
	grid = new JGrid({
		title : '服务信息',
		col : serviceCol,
		checkbox : true,
		dataUrl : 'operationalGuidance/servicesQuery.zb',
		render : 'grid',
		countEveryPage : 20,
		height   :380,
		pageBar  : true,
		crudOpera: true,
		selectModel:true,
		listeners: {
			click : function(row) {
				
			},
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
 					var rows = grid.getSelections();
 					if(rows.length<1){
 						JDialog.tip('请选择记录进行删除');
 						return;
 					}
 					removeDataSet();
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
		}
	});
	
	editData = function(title,id){
		formDialog = new JDialog({
			title : title,
			width : 350,
			height :285,
			buttons: [{
	 			text: '确定',
	 			id: 'p_service_save',	 			
	 			handler: function(){
					saveServiceData();
	 			}
	 		},{
	 			text: '返回',
	 			id: 'p_service_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		
		formDialog.show();
		if (editHTML == '') {
			editHTML = $('#edit').html();
			$('#edit').remove();
		}
		formDialog.add(editHTML);
		if(id){//加载表单数据
			loadServiceData(id);
		}
	};
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	grid.loadData();
	
	$("#querybtn").click(function(){
		var name = $.trim($("input[name=serviceNameQuery]").val());
		if(name!=''){
			grid.setParams(eval("({serviceName:'"+name+"'})"));
			grid.loadData(1);
		}else{
			grid.setParams(eval("({})"));
			grid.loadData(1);
		}
	});
	
});