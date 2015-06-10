//声明使用的两个grid控件全局变量
var dict_grid;
var domain_grid;

//保存选中的type_id [目录标识]
var global_selected_type_id = '';

//字典类型标识
var global_type_id = '';

//保存Domain 路径
var saveDomainUrl = "dip/dict/saveDipDict.zb";
//单挑domain数据加载路径
var singDomainLoadUrl = "dip/dict/getDipDictById.zb";
//domain编辑框html
var editDomainHTML = "";
//删除路径
var domainRemoveUrl = "dip/dict/delDipDictByIds.zb";

//判断该字典类型是否存在子节点或者值域的路径
var checkDictUrl = "dip/dict/checkDictById.zb";

//初始化grid控件
var domain_grid_col = [ { text: '编号', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'dict_id'	},
			{ text: '值', width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'dict_code'	},
			{ text: '值含义', width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'dict_name'	}//,
//			{ text: '值说明', width: 400,	textAlign: 'center',	align : 'center',	dataIndex: 'remark'	},
//			{ text: '拼音', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'name_pinyin'	},
//			{ text: '拼音', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'name_pinyin'	},
//			{ text: '类别ID', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'dicttype_id'}
			];
			
			
			
 //编辑数据
var editDomainData = function(title, id) {
	formDialog = new JDialog({
		id : 'editDomainData',
		title : title,
		width : 390,
		height : 300,
		//icon : 'images/icons/pencil.png',
		//btns   : JDialog.SAVE_CLOSE_OPTION,
		buttons: [{
            text: '保存',
            id: 'p_mdcontent_save',
            handler: function(){
                $("#dicttype_id").val(global_type_id);
					 
				if (!checkAll('dictionaryForm')) {
					return false;
				} 
				
				//检查是否有重复值
                   var check_domain_id = domain_grid.getColData(0);
                   var check_domain_code = domain_grid.getColData(1);
                   var check_domain_name = domain_grid.getColData(2);
                   var check_domain_count = domain_grid.getRecordCount();
                   var dict_name_check = $("#dict_name").val();
                   var dict_code_check = $("#dict_code").val();
                   for (var i = 0; i < check_domain_count; i++) {
                       if ((check_domain_code[i] == dict_code_check || check_domain_name[i] == dict_name_check) &&
                           (!id || check_domain_id[i] != id)) {
                           if (check_domain_code[i] == dict_code_check) {
                               JDialog.showMessageDialog('添加失败', '值与值含义不能与其他重复！', JDialog.ERROR, function () {
                                   $('#dict_code').focus();
                               });
                           } else if (check_domain_name[i] == dict_name_check) {
                               JDialog.showMessageDialog('添加失败', '值与值含义不能与其他重复！', JDialog.ERROR, function () {
                                   $('#dict_name').focus();
                               });
                           }
                           return false;
                       }
                   }
				
				var data = "{";
				$("#dictionaryForm").find("input[type='text']").each(function(){
                       var name = $(this).attr("name");
                       if (name == "dict_code") {
                           data += name + ":'" + $(this).val().replace(/\s+/g, "") + "',";
                       } else {
                           data += name + ":'" + $(this).val() + "',";
                       }
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
				formDialog.mask('正在保存数据，请稍候...');
				$.ajax({
					url : saveDomainUrl,
					type : 'POST',
					cache : false,
					data : eval('('+data+')'),
					dataType : 'json',
					success : function( res) {
						if (res.success==true) {
							JDialog.showMessageDialog('成功','数据字典信息保存成功。',JDialog.INFORMATION,function(id,text) {
								if (id == 'ok') {
									formDialog.unmask();
									formDialog.closeDialog();
								}
							});
						} else {
							JDialog.showMessageDialog('失败','数据字典信息保存失败。',JDialog.ERROR,function(id,text) {
								formDialog.unmask();
							});
						}
						domain_grid.loadData(1);
					},
					error : function(XMLHttpRequest,textStatus,errorThrown) {
						formDialog.unmask();
						JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。');
					}
				});
            }
        },{
            text: '关闭',
            id: 'p_mdcontent_close',
            handler: function(){
                formDialog.closeDialog();
            }
        }]
	});
	formDialog.show();
	if (editDomainHTML == '') {
		editDomainHTML = $('#domain_edit').html();
		$('#domain_edit').remove();
	}
	formDialog.add(editDomainHTML);
	if (domain_grid.opt.params != undefined) {
		formDialog.getComponent().find('input[name="dicttypeId"]').val(domain_grid.opt.params.dicttypeId);
	}
	//bindCheckEvent();

	if (id) {// 加载Form数据
		formDialog.mask('正在加载数据，请稍候...');
		$.ajax({
			url : singDomainLoadUrl,
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

					domain_grid.loadData(1);

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

//----------------------------------上面为值域管理的js----------------------------------------//
//保存doc链接，和更新公用一个连接，由是否存在id值来判断是否更新
var saveDictUrl = "dip/dict/saveType.zb";
//单条记录载入路径,需要配置参数
var singDictLoadUrl = "dip/dict/getDictTypeById.zb";
//删除路径,注意param的格式
var dictRemoveUrl = "dip/dict/removeTypeByIds.zb";
//编辑框的html
var editHTML = '';

//树形控件配置
var typeTree;
var setting = {
 	view:{
 		showTitle: false
	},
	
	async:{
		enable:true,
		contentType: "application/json",
		url:'dip/dict/allDictType.zb'
	},
	data: {
		key:{
			name:'type_name',
			title:"version"
		},
		simpleData:{
			enable : true,
			idKey : 'type_id',
			pIdKey : 'parent_id'
			//rootPId: '0'
		}
	},
 
	callback: {
		onAsyncSuccess: function(){
			var node1 = typeTree.getNodes();
			if(node1.length > 0){
				typeTree.selectNode(node1[0]);
				global_selected_type_id = node1[0].type_id;
				dict_grid.setParams({parent_id:global_selected_type_id});
	 			dict_grid.loadData(1);
			}
		},		
		onClick: function(event, treeId, treeNode){
			global_selected_type_id = treeNode.type_id;
			//$("#dict_container").show();
			//$("#domain_container").hide();
			 dict_grid.setParams({parent_id:treeNode.type_id});
			 dict_grid.loadData(1);
			
			//清空查询条件
			$("#query_type_name").val('编码或名称');
			
		}
	}
};
 


//初始化grid控件
var dict_grid_col = [ { text: '编号', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'type_id'	},
//			{ text: '类型编码', width: 110,	textAlign: 'center',	align : 'center',	dataIndex: 'type_code',renderer: function(value){
//                if (value) {
//                    return (value.split("_"))[0];
//                }
//            }},
			{ text: '字典标识', width: 120,	textAlign: 'center',	align : 'center',	dataIndex: 'standard_code'	},
			{ text: '类型名称', width: 180,	textAlign: 'center',	align : 'center',	dataIndex: 'type_name'	},
			{ text: '版本', width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'version'	},
			{ text: '字典目录', width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'parent_id'	}];
			

//编辑数据
var editData = function(title, id) {
	 
	formDialog = new JDialog({
		id : 'editData',
		title : title,
		width : 390,
		height : 360,
		//icon : 'images/icons/pencil.png',
		//btns : JDialog.SAVE_CLOSE_OPTION,
		buttons: [{
            text: '保存',
            id: 'p_mdcontent_save',
            handler: function(){
                if (!checkAll('dictionaryTypeForm')) {
					return false;
				}
                   //检查是否有重复值
                   var check_type_id = dict_grid.getColData(0);
                   var check_type_name = dict_grid.getColData(2);
                   var check_type_count = dict_grid.getRecordCount();
                   var type_name_check = $("#type_name").val();
                   for (var i = 0; i < check_type_count; i++) {
                       if (check_type_name[i] == type_name_check && (!id || check_type_id[i] != id)
                           && global_selected_type_id=="1") {
                           JDialog.showMessageDialog('添加失败', '类型名称不能与其他重复！', JDialog.ERROR,function(){
                               $('#type_name').focus();
                           });
                           return false;
                       }
                   }
				var data = "{";
				$("#dictionaryTypeForm").find("input[type='text']").each(function(){
                       var name = $(this).attr("name");
                       if(name == 'type_name'){
                           data += name + ":'" + $(this).val().replace(/\s+/g,"") + "',";
                       } else {
                           data += name + ":'" + $(this).val() + "',";
                       }
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
				formDialog.mask('正在保存数据，请稍候...');
				$.ajax({
					url : saveDictUrl,
					type : 'POST',
					cache : false,
					data : eval('('+data+')'),
					dataType : 'json',
					success : function( res) {
						if (res.success=="true") {
							formDialog.unmask();
							formDialog.closeDialog();
							JDialog.tip("数据保存成功！");
							dict_grid.loadData(1);
						}else if(res.success=="cannot"){
                               formDialog.unmask();
							JDialog.showMessageDialog('修改异常', '含子类型的类型不可修改！', JDialog.ERROR);
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
            }
        },{
            text: '关闭',
            id: 'p_mdcontent_close',
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
	
	$("#version").attr('disabled', true);
	$("#type_code").attr('disabled', true);
	
	//载入左侧树中对应的类型节点信息
	
	loadTypeOptionFromTree();
	
	var selectedParentValue = formDialog.getComponent().find("select").val();
	if(selectedParentValue==="1"){
		formDialog.getComponent().find("#standard_codeTr").hide();
        $("#standard_code").removeAttr("validate");
	}
	

	
	if (id) {// 加载Form数据
		formDialog.mask('正在加载数据，请稍候...');
		$.ajax({
			url : singDictLoadUrl,
			type : 'POST',
			cache : false,
			data : encodeURI("type_id=" + id),
			dataType : 'json',
			success : function(response) {
				if (response.success) {
					var res = response.ddt;
					formDialog.getComponent().find("input").each(function() {
						var name = $(this).attr('name');
						if (name) {
                            if (name == 'type_code') {
                                $(this).val((res[name].split("_"))[0]);
                            } else {
                                $(this).val(res[name]);
                            }
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


//从tree中载入类型选项，用于选择类别
//isUpdate--是否为更新,load_type_id更新的类型id
function loadTypeOptionFromTree(){
	var allNodes = typeTree.transformToArray(typeTree.getNodes());
	
	//var nodes = typeTree.getSelectedNodes();
	
	$("#select_parent_id").empty();
	if(!allNodes) return;
	//添加类型的option
	for(var i=0; i<allNodes.length; i++){
		var	temp = $("<option></option>").attr("value",allNodes[i].type_id).text(allNodes[i].type_name);
		$("#select_parent_id").append(temp);
	}
	//修改时，自动补全上级类型
	if(global_selected_type_id != ''){
		$("#select_parent_id option").each(function(){
			$(this).removeAttr("selected");
			if($(this).val()==global_selected_type_id){
				$(this).attr("selected","selected");
			}
		});
	} 
}

//查询条件 获取焦点事件
function getFocus(obj){
	var value = $.trim($(obj).val());
	if(value == '编码或名称'){
		$(obj).val('');
	}
}
//查询条件 失去焦点事件
function lostFocus(obj){
	var value = $.trim($(obj).val());
	if(!value){
		$(obj).val('编码或名称');
	}
}

//加载完成，执行以下代码
$(function(){
	
	
	//加载layout，布局控件
	$('body').layout({ 
		resizable : false,
		livePaneResizing : true,
		slidable:true,
		west__size : 220,
		west__minSize : 220,
		west__maxSize : .5 ,// 50% of layout width
		west__resizable:true,
		center__resizable:true
	});
	$("#centerDiv").layout({
		resizable : false,
		livePaneResizing : true,
		slidable:true,
		north_size:80,
		north_minSize:80,
		east_size:480,
		east__minSize : 480,
		center__resizable:true
	});
	
	
	//定义grid控件
	dict_grid = new JGrid({
						//title: "字典管理——类别管理<input type='hidden' id='select_type' value='1'/>",
						col	 :dict_grid_col,
						dataCol:'',
						striped	 :true,
						height   :document.body.clientHeight-115,
						dataUrl  : 'dip/dict/searchType.zb',
						checkbox : true,
						selectModel:1,
						render   : 'dict_grid',
						pageBar  : true,
						crudOpera: true,
						countEveryPage: 18,
						listeners: {
							afterload:function(){
								domain_grid.setParams(''); 
								domain_grid.loadData(1);
								global_type_id = '';
								$("#domain_grid").find("button[name='add']").attr("disabled","disabled");
								$("#domain_grid").find("button[name='update']").attr("disabled","disabled");
								$("#domain_grid").find("button[name='remove']").attr("disabled","disabled");
							},
							click : function(row) {
								domain_grid.setParams({dicttype_id:row['type_id']}); 
								global_type_id = row['type_id'];
								domain_grid.loadData(1);
								$("#domain_grid").find("button[name='add']").attr("disabled",false);
								$("#domain_grid").find("button[name='update']").attr("disabled",false);
								$("#domain_grid").find("button[name='remove']").attr("disabled",false);
							},
							dblclick : function(row) {// Grid双击事件
                                var d = "id=" + row['type_id'];
                                $.ajax({
                                    url: checkDictUrl,
                                    type: 'POST',
                                    cache: false,
                                    data: encodeURI(d),
                                    dataType: 'json',
                                    success: function (res) {
                                        if (res.success == true) { //表示该字典类型存在子类型或者值域
                                        	JDialog.showMessageDialog('提示', '不允许修改已有记录的字典类型。');
                                        } else {
                                            editData("修改", row['type_id']);
                                        }
                                    }
                                });
                            },
							curdButtonClick : function(btn) {
								if (btn == 'add') {
									editData("新建字典");
									//autoCompleteVersionAndTypeCode();
								} else if (btn == 'update') {
									var rows = dict_grid.getSelections();
									if (rows.length != 1) {
										JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
									}else{
                                        var d = "id=" + rows[0]['type_id'];
                                        $.ajax({
                                            url: checkDictUrl,
                                            type: 'POST',
                                            cache: false,
                                            data: encodeURI(d),
                                            dataType: 'json',
                                            success: function (res) {
                                                if (res.success == true) { //表示该字典类型存在子类型或者值域
                                                    JDialog.showMessageDialog('提示', '不允许修改已有记录的字典类型。');
                                                } else {
                                                    editData("修改字典", rows[0]['type_id']);
                                                }
                                            }
                                        });
                                    }
								} else if (btn == 'remove') { // 删除操作
									if (!dict_grid.getSelectionIndex()) {
										JDialog.showMessageDialog('提示','请选择需要删除的字典类型。');
										return;
									} else {
										JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？',JDialog.WARNING,function(id,text) {
											if (id == 'yes') {
												$("#dict_grid").mask('正在删除数据，请稍候...');
												var rows = dict_grid.getSelections();
												var id = '';
												for ( var i = 0; i < rows.length; i++) {
													id += rows[i].type_id + ',';
												}
												id = id.substring(0,id.length - 1);
												var d = "id="+ id;
												$.ajax({
													url : dictRemoveUrl,
													type : 'POST',
													cache : false,
													data : encodeURI(d),
													dataType : 'json',
													success : function(res) {
														if (res.success==true) {
															JDialog.showMessageDialog('成功',"删除成功！",JDialog.INFORMATION,function(id,text) {
																if (id == 'ok') {
                                                                    $("#dict_grid").unmask();
																	dict_grid.loadData();
																}
															});
														} else {
															JDialog.showMessageDialog('失败',"不允许删除已有记录的字典类型。",JDialog.INFORMATION,function(id,text) {
																if (id == 'ok') {
																	$("#dict_grid").unmask();
																}
															});
														}
														
													}
												});
											}
										});
									}
								} else if (btn == 'refresh') {
									dict_grid.loadData();
								}
							}
						}						
					});
	//加载文档树
	 typeTree = $.fn.zTree.init($("#type_tree"), setting, []);
	 
 //----------------------------------------------值域管理grid----------------------------------------------------//
 


 //定义grid控件
	domain_grid = new JGrid({
						//title: '字典值域管理',
						col	 : domain_grid_col,
						dataCol:'',
						checkbox : true,
						striped	 :true,
						height   :document.body.clientHeight-115,
						dataUrl  : 'dip/dict/searchDipDict.zb',
						render   : 'domain_grid',
						pageBar  : true,
						crudOpera: true,
						countEveryPage: 18,
						listeners: {
							dblclick : function(row) {// Grid双击事件
								editDomainData("修改", row['dict_id']);
							},
							curdButtonClick : function(btn) {
								if (btn == 'add') {
									
									//打开添加窗口
									editDomainData("新建字典值域");
								} else if (btn == 'update') {
									var rows = domain_grid.getSelections();
									if (rows.length != 1) {
										JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
										return;
									}
									editDomainData("修改",rows[0]['dict_id']);
								} else if (btn == 'remove') { // 删除操作
									if (!domain_grid.getSelectionIndex()) {
										JDialog.showMessageDialog('提示','请选择需要删除的字典值域信息。');
										return;
									} else {
										JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？',JDialog.WARNING,function(id,text) {
											if (id == 'yes') {
												$("#domain_grid").mask('正在删除数据，请稍候...');
												var rows = domain_grid.getSelections();
												var id = '';
												for ( var i = 0; i < rows.length; i++) {
													id += rows[i].dict_id + ',';
												}
												id = id.substring(0,id.length - 1);
												var d = "id="+ id;
												$.ajax({
													url : domainRemoveUrl,
													type : 'POST',
													cache : false,
													data : encodeURI(d),
													dataType : 'json',
													success : function(res) {
														if (res) {
															JDialog.showMessageDialog('成功',"删除成功！",JDialog.INFORMATION,function(id,text) {
																if (id == 'ok') {
																	$("#domain_grid").unmask();
																	//reflushLeftTree();
																	domain_grid.loadData(1);
																}
															});
														} else {
															JDialog.showMessageDialog('失败',"删除失败！",JDialog.INFORMATION,function(id,text) {
																if (id == 'ok') {
																	$("#domain_grid").unmask();
																	//reflushLeftTree();
																	domain_grid.loadData(1);
																}
															});
														}
													}
												});
											}
										});
									}
								} else if (btn == 'refresh') {
										domain_grid.loadData();
									}
								}
							}						
						});
 
	 $("#domain_grid").find("button[name='refresh']").css("display","none");
	 
	 //查询按钮事件
	 $("#querytypebtn").click(function(){
		 if(!checkAll("style_query_form")){
			 return false;
		 }
		 dict_grid.setParams({type_name:encodeURIComponent($.trim($("#query_type_name").val()))});
		 dict_grid.loadData(1);
		 typeTree.cancelSelectedNode();
	 });
	 $("#queryType input").bind('keypress',function(e){
		 if(e.keyCode==13){
			 if(!checkAll("style_query_form")){
				 return false;
			 }
			 dict_grid.setParams({type_name:encodeURIComponent($.trim($("#query_type_name").val()))});
			 dict_grid.loadData(1);
			 dict_grid.setTitle("字典查询结果");
		 }
		 
	 });
	  
	 //自动完成添加版本和编码信息
//	 function autoCompleteVersionAndTypeCode(){
//		//selected改变事件
//		 
//		 $("#select_parent_id").change(function(){
//			 var selectedParentValue = $("#select_parent_id").val();
//			 if(selectedParentValue==="1"){
//				$("#standard_codeTr").hide();
//                $("#standard_code").removeAttr("validate");
//			 }else{
//				 $("#standard_codeTr").show();
//                 $("#standard_code").attr("validate","string|1-16");
//			 }
//			 if($("#type_name").val()!=""){
//				 getVersionInfoAndFillText();
//			 }
//		 });
//		 
//		 $("#type_name").focusout(function(){
//			 if($("#type_name").val()!=""){
//				 getVersionInfoAndFillText();
//			 }
//		 });
//	 }
	 
//	 function getVersionInfoAndFillText(){
//		 $.ajax({
//				url : 'dip/dict/getDictVersionInfo.zb',
//				type : 'POST',
//				cache : false,
//				data : encodeURI('type_name='+$("#type_name").val()+'&parent_id='+ $("#select_parent_id").val()),
//				dataType : 'json',
//				success : function(res) {
//					$("#version").val(res.version);
//					$("#type_code").val((res.type_code.split("_"))[0]);
//				}
//		 });
//	 }
	
	$(window).resize(function(){
		dict_grid.setGridHeight($(document.body).height()-$('.container').outerHeight()-37);
		domain_grid.setGridHeight($(document.body).height()-$('.container').outerHeight()-37);
	});
	
});