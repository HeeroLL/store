var formDialog, editData;
var grid;
var editHTML = "";

$(document).ready(function() {

	var col = [ {text: '字典类型id',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'typeId'		},
				{text: '字典类型名称',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'typeName'	},
				{text: '字典类型编码',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'typeCode'	},
				{text: '字典类型说明',		width: 300,	textAlign: 'center',	align : 'center',	dataIndex: 'remark'		},
				{text: '删除标志',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'isDeleted'	},
				{text: '父类型id',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'parentId'	}];
	
	grid = new JGrid({
		title : '数据字典类型列表<font color=red>【' + pTypeName + '】</font>',
		col : col,
		dataCol:'',
		checkbox : true,
		dataUrl : 'btp/dict/dictTypePage.zb?parentId=' + parentId,
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
					JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
						return;
					}
					editData("修改字典类型",rows[0]['typeId']);
				} else if (btn == 'remove') { // 删除操作
					if (!grid.getSelectionIndex()) {
						JDialog.showMessageDialog('提示','请选择数据进行删除。');
							return;
					} else {
						JDialog.showConfirmDialog('警告','字典类型可能正在使用，请仔细确认，确定是否删除？',JDialog.WARNING,function(id,text) {
							if (id == 'yes') {
								$("#grid").mask('正在删除数据，请稍候...');
								var rows = grid.getSelections();
								var id = '';
								for ( var i = 0; i < rows.length; i++) {
									id += rows[i].typeId+ ',';
								}
								id = id.substring(0,id.length - 1);
								var datas = "id="+ id;
								$.ajax({
									url : 'dictTypeRemove.zb',
									type : 'POST',
									cache : false,
									data : encodeURI(datas),
									dataType : 'json',
									success : function(res) {
										if (res) {
											JDialog.showMessageDialog('成功',"字典类型删除成功！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													reflushLeftTree();
													grid.loadData();
													//刷新本页面
													window.location.reload();													
												}
											});
										} else {
											JDialog.showMessageDialog('失败',"字典类型删除失败！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													reflushLeftTree();
													grid.loadData(1);
												}
											});
										}
									}
								});
							}
						});
					}
				} else if (btn == 'refresh') {
					grid.loadData();
				}
			}
		}
	});

	// 加载数据
	grid.loadData();

	// 编辑数据
	editData = function(title, id) {
		formDialog = new JDialog(
			{
				id : 'editdata',
				title : title,
				width : $(document.body).width() * 0.6,
				height : $(document.body).height() * 0.5,
				icon : 'images/icons/pencil.png',
				btns   : JDialog.SAVE_CLOSE_OPTION,
				listeners : {
					buttonClick : function(btnId, text) {
						if (btnId == 'ok') {
							if (!checkAll('dictionaryTypeForm')) {
								return false;
							}
						var data = "{";
						$("#dictionaryTypeForm").find("input[type='text']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#dictionaryTypeForm").find("input[type='hidden']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#dictionaryTypeForm").find("select").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#dictionaryTypeForm").find("textarea").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						if(data.length>1){
							data = data.substring(0,data.length-1);
						}
						data +="}";
						//alert(data);
						formDialog.mask('正在保存数据，请稍候...');
						$.ajax({
							url : 'btp/dict/dictTypeSave.zb',
							type : 'POST',
							cache : false,
							data : eval('('+data+')'),
							dataType : 'json',
							success : function(res) {
								if (res) {
									JDialog.showMessageDialog('成功','字典类型信息保存成功。',JDialog.INFORMATION,function(id,text) {
										if (id == 'ok') {
											formDialog.unmask();
											formDialog.closeDialog();
											grid.loadData();
											reflushLeftTree();
											//window.location.reload();
										}
									});
								} else {
									JDialog.showMessageDialog('失败','字典类型信息保存失败。',JDialog.ERROR,function(id,text) {
										formDialog.unmask();
									});
								}
							},
							error : function(XMLHttpRequest,textStatus,errorThrown) {
								formDialog.unmask();
								JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。',function(id,text) {
									formDialog.unmask();
								});
							}
						});
					} else {
						formDialog.closeDialog();
					}
				}
			}
		});
		formDialog.show();
		if (editHTML == '') {
			editHTML = $('#edit').html();
			$('#edit').remove();
		}
		formDialog.add(editHTML);
		bindCheckEvent();

		if (id) {// 加载Form数据
			formDialog.mask('正在加载数据，请稍候...');
			$.ajax({
				url : 'btp/dict/dictTypeLoad.zb',
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
						formDialog.getComponent().find("select").each(function() {
							var name = $(this).attr('name');
							if (name) {
								$(this).val(res[name]);
							}
						});
						formDialog.unmask();
					} else {
						formDialog.unmask();
						JDialog.showMessageDialog('失败','数据加载失败。',JDialog.ERROR);
					}
				},
				error : function(XMLHttpRequest,textStatus, errorThrown) {
				    formDialog.unmask();
					JDialog.showMessageDialog('异常','服务器出现异常，数据加载失败。',JDialog.ERROR);
				}
			});
		}
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

// 刷新左边字典类型树
function reflushLeftTree() {
	window.parent.frames["innerlLeftFrame"].callback();
}
