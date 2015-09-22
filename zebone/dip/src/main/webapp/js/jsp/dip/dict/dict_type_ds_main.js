var col = [ {text: '字典类型id',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'typeId'		},
			{text: '字典类型名称',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'typeName'	},
			{text: '字典类型编码',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'typeCode'	},
			{text: '字典来源',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'dsId'		},
			{text: '字典类型说明',		width: 300,	textAlign: 'center',	align : 'center',	dataIndex: 'remark'		},
			{text: '删除标志',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'isDeleted'	},
			{text: '父类型id',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'parentId'	}];

var grid,editData,formDialog,setBtnDisabled;
var editHTML = "";
var loadDictData = function(id){
	formDialog.mask('正在加载数据，请稍候...');
	$.ajax({
		url:'dict/loadDictTypeDsInfo.zb',
		type: 'POST',
		cache: false,
		data: eval("({id:'"+id+"'})"),
		dataType: 'json',
		success: function(res){
			if(res){
				formDialog.getComponent().find("input").each(function() {
					var name = $(this).attr('name');
					if (name) {
						$(this).val(res[name]);
					}
				});
				formDialog.getComponent().find("textarea").each(function() {
					var name = $(this).attr('name');
					if (name) {
						$(this).val(res[name]);
					}
				});
				formDialog.unmask();
			}else{
				formDialog.unmask();
				JDialog.tip('数据加载失败',JDialog.ERROR);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			formDialog.unmask();
			JDialog.tip('服务器出现异常，数据加载失败。',JDialog.ERROR);
		}
	});
};
var saveDictTypeInfo = function(){
	if (!checkAll('dictionaryTypeForm')) {
		return;
	}
	setBtnDisabled ('dict_save', false);
	formDialog.mask('正在保存数据，请稍候...');
	var data = "{";
	$("#dictionaryTypeForm").find("input[type='text']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#dictionaryTypeForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#dictionaryTypeForm").find("select").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
		var val = $(this).val();
		$(this).find("option").each(function(){
			if($(this).attr("value") == val){
				data += "dsName:'"+$(this).text()+"',";
			}
		});
	});
	$("#dictionaryTypeForm").find("textarea").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	
	$.ajax({
		url:'dict/saveDictTypeDsInfo.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			var id = $("#dictionaryTypeForm").find("input[name='typeId']").val();
			if(res.success){
				if(!id){
					$("#dictionaryTypeForm").find("input[name='typeId']").val(res.typeId);
				}
				JDialog.tip(res.msg,JDialog.INFORMATION);
				formDialog.closeDialog();
				grid.loadData();
				window.parent.frames["innerlLeftFrame"].refleshTree();
			}else{
				JDialog.tip(res.msg,JDialog.ERROR);
			}
			setBtnDisabled ('dict_save', true);
			formDialog.unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			setBtnDisabled ('dict_save', true);
			formDialog.unmask();
			JDialog.tip('服务器出现异常，数据保存失败。',JDialog.ERROR);
		}
	});
};

$(function(){
	grid = new JGrid({
		title : '数据字典类型列表<font color=red>【数据源：'+dsName+'】</font>',
		//title : '数据字典类型列表',
		col : col,
		dataCol:'',
		checkbox : true,
		dataUrl : 'dict/dictTypeDsList.zb?dsId='+dsId,
		render : 'grid',
		pageBar : true,
		height : document.body.clientHeight-95,
		countEveryPage : 20,
		crudOpera : true,
		listeners : {
			dblclick : function(row) {// Grid双击事件
				editData("修改字典类型", row['typeId']);
			},
			curdButtonClick : function(btn) {
				if (btn == 'add') {
					editData("新建字典类型");
				} else if (btn == 'update') {
					var rows = grid.getSelections();
					if (rows.length != 1) {
						JDialog.tip('请选择一条记录进行修改。');
						return;
					}
					editData("修改字典类型",rows[0]['typeId']);
				} else if (btn == 'remove') { // 删除操作
					if (!grid.getSelectionIndex()) {
						JDialog.tip('请选择数据进行删除');
						return;
					}
					grid.opt.removeUrl = "dict/removeDictTypeDsByIds.zb";
					grid.opt.crudID = "typeId";
					grid.removeData(function(bool){
						if(bool){
							window.parent.frames["innerlLeftFrame"].refleshTree();
						}
					});
				} else if (btn == 'refresh') {
					grid.loadData();
				}
			}
		}
	});
	
	grid.loadData();
	
	editData = function(title,id){
		formDialog = new JDialog({
			title : title,
			width : 400,
			height :320,
			buttons: [{
	 			text: '保存',
	 			id: 'dict_save',	 			
	 			handler: function(){//保存数据字典类型信息
					saveDictTypeInfo();
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'dict_close',
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
			loadDictData(id);
		}
	}
	
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	
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
});