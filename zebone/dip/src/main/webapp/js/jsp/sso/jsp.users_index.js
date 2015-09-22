var formDialog, editData;
var grid;
var editHTML = "";
$(document).ready(function() {
	var col = [ {text: '用户编号',	 width: 0,	 textAlign: 'center',	align : 'center',	dataIndex: 'accountId'},
	            {text: '用户名', 		width: 180,		textAlign: 'center',	align : 'center',	dataIndex: 'account'	},
				{text: '密码（已加密）',	 width: 270,	 textAlign: 'center',	align : 'center',	dataIndex: 'password'},
				{text: '邮件地址',	 width: 270,	textAlign: 'center',	align : 'center',	dataIndex: 'email'},
				{text: '移动电话',	 width: 260,	textAlign: 'center',	align : 'center',	dataIndex: 'mobile'},
				{text: '账户描述',	 width: 390,	textAlign: 'center',	align : 'center',	dataIndex: 'description'}];
	//主数据列表
	grid = new JGrid({
		title : '单点登录用户列表',
		col : col,
		dataCol:'',
		checkbox : true,
/**************************************************标准的json格式数据，包含分页信息：如：{"success":"success","total":1,"data":[{"name":"Mike","age":13}]}*******************************************************/
		dataUrl : 'sso/queryAccount.zb',
		render : 'grid',
		pageBar : true,
		height : document.body.clientHeight-95,
		countEveryPage : 20,
		crudOpera : true,
		 listeners : {
			dblclick : function(row) {// Grid双击事件
				editData("修改单点登录用户", row['accountId']);
			},
			curdButtonClick : function(btn) {
				if (btn == 'add') {
					editData("新建单点登录用户");
				} else if (btn == 'update') {
				var rows = grid.getSelections();
				if (rows.length != 1) {
					JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
						return;
					}
					editData("修改单点登录用户",rows[0]['accountId']);
				} else if (btn == 'remove') { // 删除操作
					if (!grid.getSelectionIndex()) {
						JDialog.showMessageDialog('提示','请选择数据进行删除。');
							return;
					} else {
						JDialog.showConfirmDialog('警告','确定是否删除？',JDialog.WARNING,function(id,text) {
							if (id == 'yes') {
								$("#grid").mask('正在删除数据，请稍候...');
								var rows = grid.getSelections();
								var id = '';
								for ( var i = 0; i < rows.length; i++) {
									id += rows[i].accountId+ ',';
								}
								id = id.substring(0,id.length - 1);
								var datas = "accountId="+ id;
								$.ajax({
/***********************************************************删除数据格式为逗号分割的id,如：id=12,123,121,321,,成功则返回json:{"msg":"success"}***********************************************/
									url : 'sso/removeAccountByIds.zb',//如果成功则返回格式为json格式：{"msg":"success"}
									type : 'POST',
									cache : false,
									data : encodeURI(datas),
									dataType : 'json',
									success : function(res) {

										if (res['msg']=='success') {
											JDialog.showMessageDialog('成功',"单点登录用户删除成功！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													grid.loadData();
												}
											});
										} else {
											JDialog.showMessageDialog('失败',"单点登录用户删除失败！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													grid.loadData(1);
												}
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
				width : $(document.body).width() * 0.25,
				height : $(document.body).height() * 0.55,
				icon : 'images/icons/pencil.png',
				btns   : JDialog.SAVE_CLOSE_OPTION,
				listeners : {
					buttonClick : function(btnId, text) {
						if (btnId == 'ok') {
							if (!checkAll('accountform')) {
								return false;
							}
						var data = "{";
						$("#accountform").find("input[type='text']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#accountform").find("input[type='password']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#accountform").find("input[type='hidden']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#accountform").find("select").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#accountform").find("textarea").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						if(data.length>1){
							data = data.substring(0,data.length-1);
						}
						data +="}";
						//alert(data);
						formDialog.mask('正在保存数据，请稍候...');
						$.ajax({
/**********************************保存数据,成功则返回json格式数据（必须为一下格式）:{"msg":"success"}***********************/
							url : 'sso/saveAccountInfo.zb',
							type : 'POST',
							cache : false,
							data : eval('('+data+')'),
							dataType : 'json',
							success : function(res) {
								if (res['msg']=='success') {
									JDialog.showMessageDialog('成功','系统信息保存成功。',JDialog.INFORMATION,function(id,text) {
										if (id == 'ok') {
											formDialog.unmask();
											formDialog.closeDialog();
											grid.loadData();
										}
									});
								} else {
									JDialog.showMessageDialog('失败','系统信息保存失败。',JDialog.ERROR,function(id,text) {
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

		//bindCheckEvent();

		if (id) {// 加载Form数据
			formDialog.mask('正在加载数据，请稍候...');
			$.ajax({

/**************************************返回被选中的需要修改的条目，参数为accountId=123123,返回json格式数据：{"name":"Mike","age":12}*****************************************************************************/
				url : 'sso/getAccountInfoById.zb',
				type : 'POST',
				cache : false,
				data : encodeURI("accountId=" + id),
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

	/*****************************查询使用json格式参数，查询所用到的controller和初始化加载数据的controller是同一个：********************************/
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
