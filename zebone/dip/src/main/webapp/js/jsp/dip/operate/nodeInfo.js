var editData,formDialog,setBtnDisabled,nodeGrid;
var editHTML = "";
var nodeCol = [ {text: 'id',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'   },
				{text: '所属机构',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'nodeOrg'   },
                {text: '节点名称',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'nodeName1'	},
                {text: 'IP地址',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'nodeIp'	},
                {text: 'MQ地址',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'mqQueueUrl'	},
                {text: '节点状态',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'nodeState'	},
                {text: '操作',    width: 150,  align : 'center',	
			       formatter: function(data){
			       		var value = "";
			       		if(data['nodeState']){
			       			if(data['nodeState'] =='1'){
			       				value = "停用";
			       			}else if(data['nodeState'] == '2'){
			       				value = "启用";
			       			}
			       		}
			       		var str = '';
			       		if(value){
			       			str = '<a class="btn" href="javascript:void(0);"><span class="btn-left" onclick="changeState(\''+data['id']+'\',\''+data['nodeState']+'\')"><span >'+value+'</span></span></a>';
			       		}
			       		return str;
			       }
			       }
                ];
var changeState = function(id,nodeState){
		$.ajax({
		url:'operationalGuidance/nodeChangeState.zb',
		type: 'POST',
		cache: false,
		data: eval("({nodeId:'"+id+"',nodeState:'"+nodeState+"'})"),
		dataType: 'json',
		success: function(res){
			if(res.success){
				if(nodeState=='1'){
					JDialog.tip('节点已停用',2);
				}else if(nodeState=='2'){
					JDialog.tip('节点已启用',2);
				}else{
					JDialog.tip('保存成功',2);
				}
				nodeGrid.loadData();
			}else{
				if(nodeState=='1'){
					JDialog.tip('节点停用失败',2);
				}else if(nodeState=='2'){
					JDialog.tip('节点启用失败',2);
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
var loadNodeData = function(id){
	$(document.body).mask("正在加载，请稍后...");
	$.ajax({
		url:'operationalGuidance/nodeLoad.zb',
		type: 'POST',
		cache: false,
		data: eval("({nodeId:'"+id+"'})"),
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

var saveNodeData = function(){
	if (!checkAll('nodeForm')) {
		return;
	}
	setBtnDisabled ('p_node_save', false);
	formDialog.mask('正在保存数据，请稍候...');
	var data = "{";
	$("#nodeForm").find("input[type='text']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#nodeForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#nodeForm").find("input[type='radio']").each(function(){
		if($(this).attr("checked")!= undefined){
			data += $(this).attr('name')+":'"+$(this).val()+"',";
		}
	});
	$("#nodeForm").find("select").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
		var val = $(this).val();
	});
	$("#nodeForm").find("textarea").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	
	$.ajax({
		url:'operationalGuidance/nodeSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			if(res.success){
				formDialog.closeDialog();
				nodeGrid.loadData();
			}else{
				JDialog.tip(res.msg,2);
			}
			setBtnDisabled ('p_node_save', true);
			formDialog.unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			setBtnDisabled ('p_node_save', true);
			formDialog.unmask();
			JDialog.tip('服务器出现异常，数据保存失败。',JDialog.ERROR);
		}
	});
};

var removeDataSet = function(){
	var rows = nodeGrid.getSelections();
	if(rows.length>0){
		if(rows[0]['nodeState']=='1'){
			JDialog.tip('节点启用状态，无法删除。',3);	
			return;
		}
		JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
			if(id=='yes'){
				nodeGrid.jgridMain.mask('正在删除数据，请稍候...');
				var ids = '';
				for(var i=0; i<rows.length; i++){
					ids += rows[i]['id'] + ',';
				}
				ids = ids.substring(0, ids.length-1);
				var datas = "id="+ids;
				$.ajax({
					url: "operationalGuidance/nodeDelete.zb",
					type: 'POST',
					cache: false,
					data: encodeURI(datas),
					dataType: 'json',
					success: function(res){
						nodeGrid.jgridMain.unmask();					
						if(res.success){
							var msg = res.msg?res.msg:'数据删除成功。';
							JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
								if(id=='ok'){
									nodeGrid.setParams('');
									nodeGrid.loadData(1);
								}
							});	
						}else{					 		
							JDialog.tip(res.msg?res.msg:'数据删除失败。',2);							
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						nodeGrid.jgridMain.unmask();
						JDialog.tip('服务器出现异常，数据删除失败。',3);
					}
				});
			}
		});
	}
};
// 机构选择树
var orgTreeChoice = function(obj, type) {
	treeDialog = new JDialog({
		id : 'tree',
		title : '请选择机构',
		width : $(document.body).width() * 0.35,
		height : $(document.body).height() * 0.55,
		btns   : JDialog.OK_CANCEL_OPTION,
		listeners : {
			buttonClick : function(btnId, text) {
				if (btnId == "ok") {
					//给关联机构赋值
					var selectedOrg = window.frames["ztree"].getTreeValue();
					$(obj).parent().find('input[name=nodeOrgName]').val(selectedOrg.split(':')[0]);
					$(obj).parent().find('input[name=nodeOrg]').val(selectedOrg.split(':')[2]);
				}
				treeDialog.closeDialog();
			}
		}
	});
	treeDialog.show();
	treeDialog.add("<iframe id='ztree' name='ztree' src='btp/mho/mhoInnerTree.zb?type="+type+"' width='100%' height='100%' frameBorder='0' scrolling='auto' />");
};
$(function(){
	nodeGrid = new JGrid({
		title : '节点信息',
		col : nodeCol,
		checkbox : true,
		dataUrl : 'operationalGuidance/nodesQuery.zb',
		render : 'grid',
		countEveryPage : 20,
		height   :380,
		pageBar  : false,
		crudOpera: true,
		selectModel:true,
		listeners: {
			dblclick: function(row){//Grid双击事件
				editData("修 改", row['id']);
			},
			curdButtonClick: function(btn){
 				if(btn=='add'){
 					editData("新 建");
 				}else if(btn=='update'){ 					
 					var rows = nodeGrid.getSelections();
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行修改');
 						return;
 					}
 					editData("修 改", rows[0]['id']);
 				}else if(btn=='remove'){ //删除操作
 					var rows = nodeGrid.getSelections();
 					if(rows.length<1){
 						JDialog.tip('请选择记录进行删除');
 						return;
 					}
 					removeDataSet();
 				}else if(btn=='refresh'){
 					nodeGrid.loadData();
 				}
 			}
		}
	});
	

	editData = function(title,id){
		formDialog = new JDialog({
			title : title,
			width : 340,
			height :370,
			buttons: [{
	 			text: '确定',
	 			id: 'p_node_save',	 			
	 			handler: function(){
					saveNodeData();
	 			}
	 		},{
	 			text: '返回',
	 			id: 'p_node_close',
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
			loadNodeData(id);
		}
	};
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	
	nodeGrid.loadData();
	
	$("#querybtn").click(function(){
		var nodeName = $.trim($("input[name=nodeNameQuery]").val());
		var orgName = $.trim($("input[name=query_mhoName]").val());
		var orgCode = $.trim($("input[name=query_orgCode]").val());
		var datas = "{";
		if(nodeName!=''){
			datas +="nodeName:'"+nodeName+"',";
		}
		if(orgName!=''){
			datas +="orgLevelCode:'"+orgCode+"',";
		}
		if(datas.length>1) datas = datas.substring(0, datas.length-1);
		datas += "}";
		nodeGrid.setParams(eval("("+datas+")"));
		nodeGrid.loadData(1);
	});
	
	var options={
		width: 180,
	    maxHeight: 200,
		select:false,
		textField:'nodeOrgName',
		serviceUrl:'operationalGuidance/getOrgInfo.zb',
		col:[{dataIndex:'nodeOrgName'}],
		valueField:{dataIndex:'nodeOrg',dataName:'query_orgCode'}//隐藏文本的name
	};
	$("#query_mhoName").autocomplete(options);
			
});