var grid,editData,formDialog,setBtnDisabled;
var editHTML = "";
var maxNo,minNo;
var col = [ {text: '字典id',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'dictId'		},
			{text: '字典名称',	width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'dictName'	},
			{text: '字典编码',	width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'dictCode'	},
			{text: '字典拼音',	width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'namePinyin'	},
			{text: '字典简拼',	width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'nameJianpin'},
			{text: '字典说明',	width: 300,	textAlign: 'center',	align : 'center',	dataIndex: 'remark'		},
			{text: '字典类型id',	width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'dicttypeId'	},
			{text: '删除标志',	width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'isDeleted'	},
			{text: '排序号 ',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'orderNo'	},
			{	
		   		text: '排序',
				width: 110, 
				textAlign: 'center',	
				align : 'left',		
				//dataIndex: 'orderNo',
				formatter: function(data){
					return "<button onClick='clickUpDown(1,\""+data['orderNo']+"\",\""+data['dictId']+"\");'>↑</button><button onClick='clickUpDown(2,\""+data['orderNo']+"\",\""+data['dictId']+"\");'>↓</button>";
				}
 			}];

var clickUpDown = function(status,orderNo,did){
	if(orderNo == "null"){
		var data = "{isDeleted:'"+status+"',dicttypeId:'"+dicttypeId+"',dictId:'"+did+"'}";
	}else{
		if(status == 1){
			if(orderNo == minNo){
				JDialog.tip('该模块已在最前面了');
				return;
			}
		}else{
			if(orderNo == maxNo){
				JDialog.tip('该模块已在最后面了');
				return;
			}
		}
		//isDeleted 临时作为向上或向下移动的标志，1：向上；2：向下
		var data = "{isDeleted:'"+status+"',orderNo:'"+orderNo+"',dicttypeId:'"+dicttypeId+"',dictId:'"+did+"'}";
	}
	$.ajax({
		url:'dict/dictStandardOrder.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			if(res.success){
				grid.loadData();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器异常');
		}
	});
};
var loadDictData = function(id){
	formDialog.mask('正在加载数据，请稍候...');
	$.ajax({
		url : 'dict/dictStandardLoad.zb',
		type : 'POST',
		cache : false,
		data : encodeURI("id=" + id),
		dataType : 'json',
		success : function(res) {
			if (res) {
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

				dTId = $('#dicttypeId').val();
				grid.setParams({
					dicttypeId : dTId
				});

			} else {
				formDialog.unmask();
				JDialog.tip('数据加载失败。',JDialog.ERROR);
			}
		},
		error : function(XMLHttpRequest,textStatus, errorThrown) {
			formDialog.unmask();
			JDialog.tip('服务器出现异常，数据加载失败。',JDialog.ERROR);
		}
	});
};
var saveDictInfo = function(){
	if (!checkAll('dictionaryForm')) {
		return;
	}
	formDialog.mask('正在保存数据，请稍候...');
	setBtnDisabled ('dict_save', false);
	
	
	var data = "{";
	$("#dictionaryForm").find("input[type='text']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#dictionaryForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#dictionaryForm").find("textarea").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	
	$.ajax({
		url:'dict/saveDictStandardInfo.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			var id = $("#dictionaryForm").find("input[name='dictId']").val();
			if(res.success){
				if(!id){
					$("#dictionaryForm").find("input[name='dictId']").val(res.dictId);
				}
				JDialog.tip(res.msg,JDialog.INFORMATION);
				formDialog.closeDialog();
				grid.loadData();
			}else{
				JDialog.tip(res.msg,JDialog.ERROR);
			}
			setBtnDisabled ('dict_save', true);
			formDialog.unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			formDialog.unmask();
			setBtnDisabled ('dict_save', true);
			JDialog.tip('服务器出现异常，数据保存失败。',JDialog.ERROR);
		}
	});
};

$(function(){
	grid = new JGrid({
		title: '标准数据字典列表<font color=red>【' + typeName + '】</font>',
		col	 :col,
		dataCol:'',
		checkbox : true,
		striped	 :true,
		height   :document.body.clientHeight-95,
		dataUrl  : 'dict/dictStandardList.zb?dicttypeId=' + dicttypeId,
		render   : 'grid',
		pageBar  : false,
		crudOpera: true,
		listeners: {
			dblclick : function(row) {// Grid双击事件
				editData("修改数据字典", row['dictId']);
			},
			curdButtonClick : function(btn) {
				if (btn == 'add') {
					editData("新建数据字典");
				} else if (btn == 'update') {
					var rows = grid.getSelections();
					if (rows.length != 1) {
						JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
						return;
					}
					editData("修改数据字典",rows[0]['dictId']);
				} else if (btn == 'remove') { // 删除操作
					if (!grid.getSelectionIndex()) {
						JDialog.tip('请选择数据进行删除');
						return;
					}
					grid.opt.removeUrl = "dict/removeDictStandardByIds.zb";
					grid.opt.crudID = "dictId";
					grid.removeData();
				} else if (btn == 'refresh') {
					grid.loadData();
				}
			},
			afterload: function(data){
 				if(data.length>0){
	 				maxNo = data[data.length-1].orderNo;
	 				minNo = data[0].orderNo;
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
					saveDictInfo();
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
		formDialog.getComponent().find('input[name="dicttypeId"]').val(dicttypeId);
		if(id){//加载表单数据
			loadDictData(id);
		}
	};
	
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