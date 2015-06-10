var formDialog, editData;
var editHTML = "";
var grid;
var maxNo,minNo;
var clickUpDown;
$(document).ready(function() {
	
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
						//alert(data['orderNo']);
						return "<button onClick='clickUpDown(1,\""+data['orderNo']+"\",\""+data['dictId']+"\");'>↑</button><button onClick='clickUpDown(2,\""+data['orderNo']+"\",\""+data['dictId']+"\");'>↓</button>"; 
						//return "<button onClick='clickUpDown(1,"+data+");'>↑</button><button onClick='clickUpDown(2,"+data+");'>↓</button>";
					}
//			   		renderer: function(value){
//	 					return "<button onClick='clickUpDown(1,\""+value+"\");'>↑</button><button onClick='clickUpDown(2,\""+value+"\");'>↓</button>"; 				
//	 				}
	 			}];
				
	//排序
	clickUpDown = function(status,orderNo,did){
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
		//alert(data);
		$.ajax({
			url:'btp/dict/dictOrder.zb',
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
	
	grid = new JGrid({
		title: '数据字典列表<font color=red>【' + typeName + '】</font>',
		col	 :col,
		dataCol:'',
		checkbox : true,
		striped	 :true,
		height   :document.body.clientHeight-95,
		dataUrl  : 'btp/dict/dictPage.zb?dicttypeId=' + dicttypeId,
		render   : 'grid',
		pageBar  : false,
		crudOpera: true,
		//countEveryPage: 20,
		formToggleId:'eoc',//切换表单显示的id
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
						JDialog.showMessageDialog('提示','请选择数据进行删除。');
						return;
					} else {
						JDialog.showConfirmDialog('警告','数据字典可能正在使用，请仔细确认，确定是否删除？',JDialog.WARNING,function(id,text) {
							if (id == 'yes') {
								$("#grid").mask('正在删除数据，请稍候...');
								var rows = grid.getSelections();
								var id = '';
								for ( var i = 0; i < rows.length; i++) {
									id += rows[i].dictId + ',';
								}
								id = id.substring(0,id.length - 1);
								var d = "id="+ id;
								$.ajax({
									url : 'btp/dict/dictRemove.zb',
									type : 'POST',
									cache : false,
									data : encodeURI(d),
									dataType : 'json',
									success : function(res) {
										if (res) {
											JDialog.showMessageDialog('成功',"删除成功！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													//reflushLeftTree();
													grid.loadData(1);
												}
											});
										} else {
											JDialog.showMessageDialog('失败',"删除失败！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													//reflushLeftTree();
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
			},
			afterload: function(data){
 				if(data.length>0){
	 				maxNo = data[data.length-1].orderNo;
	 				minNo = data[0].orderNo;
 				}
 			}
		}
	});
	
	//加载数据
	grid.loadData();
	
	//编辑数据
	editData = function(title, id) {
		formDialog = new JDialog({
			id : 'editdata',
			title : title,
			width : $(document.body).width() * 0.6,
			height : $(document.body).height() * 0.5,
			icon : 'images/icons/pencil.png',
			btns   : JDialog.SAVE_CLOSE_OPTION,
			listeners : {
				buttonClick : function(btnId, text) {
					if (btnId == 'ok') {
						if (!checkAll('dictionaryForm')) {
							return false;
						}
						var data = "{";
						$("#dictionaryForm").find("input[type='text']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#dictionaryForm").find("input[type='hidden']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#dictionaryForm").find("select").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#dictionaryForm").find("textarea").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						if(data.length>1){
							data = data.substring(0,data.length-1);
						}
						data +="}";
						//alert(data);
						formDialog.mask('正在保存数据，请稍候...');
						$.ajax({
							url : 'btp/dict/dictSave.zb',
							type : 'POST',
							cache : false,
							data : eval('('+data+')'),
							dataType : 'json',
							success : function( res) {
								if (res) {
									JDialog.showMessageDialog('成功','数据字典信息保存成功。',JDialog.INFORMATION,function(id,text) {
										if (id == 'ok') {
											formDialog.unmask();
											formDialog.closeDialog();
											grid.loadData(1);
										}
									});
									//reflushLeftTree(); // 刷新左边字典类型树
								} else {
									JDialog.showMessageDialog('失败','数据字典信息保存失败。',JDialog.ERROR,function(id,text) {
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
		if (grid.opt.params != undefined) {
			formDialog.getComponent().find('input[name="dicttypeId"]').val(grid.opt.params.dicttypeId);
		}
		bindCheckEvent();

		if (id) {// 加载Form数据
			formDialog.mask('正在加载数据，请稍候...');
			$.ajax({
				url : 'btp/dict/dictLoad.zb',
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
						formDialog.getComponent().find("select").each(function() {
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
						// alert(dTId);
						grid.setParams({
							dicttypeId : dTId
						});

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
