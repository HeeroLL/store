var dataSyncMain_grid;
//初始化grid控件
var dataSyncMain_grid_col = [ 
			{ text: '系统名称', width: 200,	textAlign: 'center', align: 'center', dataIndex: 'sysName'	},
			{ text: '上次同步时间', width: 200,	textAlign: 'center',	align : 'center', renderer:function(value){
				if(value==null || value==""){
					return "未同步";
				}else{
					return value;
				}
			},	 dataIndex: 'currentSyncTime'	},
			{ text: '同步状态', width: 200,	textAlign: 'center',	align : 'center', renderer:function(value){
				if(value==null || value==""){
					return "未同步";
				}else{
					if(value=="1"){
						return "已同步";
					}else{
						return "未同步";
					}
					
				}
			},	dataIndex: 'syncFlag'	},
			{ text: '同步内容', width: 0,	textAlign: 'center',	align : 'center',renderer:function(value){
				if(value==null || value==""){
					return "--";
				}else{
					return value;
					
				}
			},	dataIndex: 'isAll'	},
			{ text: '同步地址', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'wsUrl'	},
			{ text: '同步操作', width: 300,	textAlign: 'center',	align : 'center', renderer:syncOperation,	dataIndex: 'id'},
			{ text: '编号', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'}];

//加载完成，执行以下代码
$(function(){
	
	//定义dataSyncMain_grid控件
	dataSyncMain_grid = new JGrid({
		title: '同步操作列表',
		col	 : dataSyncMain_grid_col,
		dataCol:'',
		checkbox : true,
		striped	 :true,
		height   :document.body.clientHeight-95,
		dataUrl  : 'dataSyncMain/all.zb',
		render   : 'dataSyncMain_grid',
		pageBar  : false,
		crudOpera: true,
		listeners: {
			afterload:function(){
				//Prevent default
			    $("a.prevent-default").click(function(event) {
				   event.preventDefault(); 
				   event.stopPropagation();
	            });
				if(dataSyncMain_grid.getRecordCount()==0 && isSearchLoad){
					 isSearchLoad = false;
					 JDialog.showMessageDialog('提示','查询结果为空！');
				}else if(dataSyncMain_grid.getRecordCount() > 0){
		            isSearchLoad = false;
		        }
			},
			dblclick : function(row) {// Grid双击事件
				editDomainData("修改", row['id']);
			},
			curdButtonClick : function(btn) {
			if (btn == 'add') {
				//打开添加窗口
				editdataSyncMain("新建同步项目");
			} else if (btn == 'update') {
				var rows = dataSyncMain_grid.getSelections();
				if (rows.length != 1) {
					JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
					return;
				}
				editdataSyncMain("修改",rows[0]['id']);
			} else if (btn == 'remove') { // 删除操作
				if (!dataSyncMain_grid.getSelectionIndex()) {
					JDialog.showMessageDialog('提示','请选择需要删除的字典值域信息。');
					return;
				} else {
					JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？',JDialog.WARNING,function(id,text) {
						if (id == 'yes') {
							$("#dataSyncMain_grid").mask('正在删除数据，请稍候...');
							var rows = dataSyncMain_grid.getSelections();
							var id = '';
							for ( var i = 0; i < rows.length; i++) {
								id += rows[i].id + ',';
							}
							id = id.substring(0,id.length - 1);
							var d = "ids="+ id;
							$.ajax({
								url : "dataSyncMain/delete.zb",
								type : 'POST',
								cache : false,
								data : encodeURI(d),
								dataType : 'json',
								success : function(res) {
									if (res) {
										JDialog.showMessageDialog('成功',"删除成功！",JDialog.INFORMATION,function(id,text) {
											if (id == 'ok') {
												$("#dataSyncMain_grid").unmask();
												//reflushLeftTree();
												dataSyncMain_grid.loadData(1);
											}
										});
									} else {
										JDialog.showMessageDialog('失败',"删除失败！",JDialog.INFORMATION,function(id,text) {
											if (id == 'ok') {
												$("#dataSyncMain_grid").unmask();
												//reflushLeftTree();
												dataSyncMain_grid.loadData(1);
											}
										});
									}
								}
							});
						}
					});
				}
			} else if (btn == 'refresh') {
					dataSyncMain_grid.loadData();
				}
			}
		}						
	});
	
	dataSyncMain_grid.loadData();
});



//编辑数据
var editdataSyncMainHTML="";
var formDialog;
var editdataSyncMain = function(title, id) {
	formDialog = new JDialog({
		id : 'editdataSyncMain',
		title : title,
		width : 600,
		height : 500,
		icon : 'images/icons/pencil.png',
		btns   : JDialog.SAVE_CLOSE_OPTION,
		listeners : {
			buttonClick : function(btnId, text) {
				if (btnId == 'ok') {
					if (!checkAll('editdataSyncMainForm')) {
						return false;
					} 
					//将数据库表值付给tableNames，以json格式
					encodeTableNamesToHiddenField();
					//将checkbox中的值付给isAll
					encodeSyncDataToIsAll();
					var data = "{";
					$("#editdataSyncMainForm").find("input[type='text']").each(function(){
                        var name = $(this).attr("name");
                        if(name == 'type_name'){
                            data += name + ":'" + $(this).val().replace(/\s+/g,"") + "',";
                        } else {
                            data += name + ":'" + $(this).val() + "',";
                        }
					});
					$("#editdataSyncMainForm").find("input[type='hidden']").each(function(){
						data += $(this).attr("name")+":'"+$(this).val()+"',";
					});
					$("#editdataSyncMainForm").find("select").each(function(){
						data += $(this).attr("name")+":'"+$(this).val()+"',";
					});
					$("#editdataSyncMainForm").find("textarea").each(function(){
						data += $(this).attr("name")+":'"+$(this).val()+"',";
					});
					if(data.length>1){
						data = data.substring(0,data.length-1);
					}
					data +="}";
					
					
					formDialog.mask('正在保存数据，请稍候...');
					$.ajax({
						url : "dataSyncMain/save.zb",
						type : 'POST',
						cache : false,
						data : eval('('+data+')'),
						success : function( res) {
							if (res.success=="true") {
								//刷新类型树
								formDialog.unmask();
								formDialog.closeDialog();
								JDialog.tip("数据保存成功！",JDialog.INFORMATION);
								dataSyncMain_grid.loadData();
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
					
				}else{
					formDialog.closeDialog();
				}
			}
		}
	});
	formDialog.show();
	if (editdataSyncMainHTML == '') {
		editdataSyncMainHTML = $('#editdataSyncMainEdit').html();
		$('#editdataSyncMainEdit').remove();
	}
	formDialog.add(editdataSyncMainHTML);
	if (dataSyncMain_grid.opt.params != undefined) {
		formDialog.getComponent().find('input[name="dicttypeId"]').val(dataSyncMain_grid.opt.params.dicttypeId);
	}
	//bindCheckEvent();
	
	var tableInitRowHtml = $("#tableInitRow").html();
	$("#btn_addDataTable").click(function(){
		var temptrHtml = $("<tr class='addedTableRows'>"+tableInitRowHtml+"<tr/>");
		temptrHtml.appendTo("#tableManageTable");
		var comboBoxLocalOpt={
				select:true,
				delimiter: ',',
				width:200,
				serviceUrl:'dataSyncMain/getDictTypeByNameLikes.zb',
				textField:'typeName',//当前文本框传值对应的json的name
				valueField:{dataIndex:'typeId',dataName:'typeId'},//json的name和隐藏文本的id 
				col:[
					{width:50,dataIndex:'typeName'},
					{width:50,dataIndex:'typeCode'},
					{width:100,dataIndex:'typeId'}
				]
		};
		temptrHtml.find(".autoComplete").autocomplete(comboBoxLocalOpt);
		temptrHtml.find("input").addClass("hiddenSelect");
		temptrHtml.find("span").addClass("hiddenSelect");
		
		return false;
	});

	if (id) {// 加载Form数据
		formDialog.mask('正在加载数据，请稍候...');
		$.ajax({
			url : "dataSyncMain/getById.zb",
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
					
					//同步设置
					if(res['isAll']!=null){
						if(res['isAll'].indexOf('M')>-1){
							formDialog.getComponent().find("#syncMainData").attr('checked','checked');
						}
						if(res['isAll'].indexOf('D')>-1){
							formDialog.getComponent().find("#syncDictData").attr('checked','checked');
						}
						if(res['isAll'].indexOf('F')>-1){
							formDialog.getComponent().find("#syncMetaData").attr('checked','checked');
						}
					}
					
					//数据表初始化,修改的时候初始化
					var dataSyncItemList = res['dataSyncItemList'];
					for(var i=0; i<dataSyncItemList.length; i++){
						var html = "<tr class='addedTableRows'>"+tableInitRowHtml+"<tr/>";
						var temp$ = $(html);
						if(dataSyncItemList[i].dataType=="D"){
							temp$.appendTo("#tableManageTable");
							var comboBoxLocalOpt1={
									select:true,
									delimiter: ',',
									width:200,
									serviceUrl:'dataSyncMain/getDictTypeByNameLikes.zb',
									textField:'typeName',//当前文本框传值对应的json的name
									valueField:{dataIndex:'typeId',dataName:'typeId'},//json的name和隐藏文本的id 
									col:[
										{width:50,dataIndex:'typeName'},
										{width:50,dataIndex:'typeCode'},
										{width:100,dataIndex:'typeId'}
									]
							};
							
							temp$.find(".autoComplete").autocomplete(comboBoxLocalOpt1);
							
							var dataContentSplited = dataSyncItemList[i].dataContent.split("$$");
							temp$.find("input:eq(0)").val(dataContentSplited[0]);
							temp$.find("input:eq(1)").val(dataContentSplited[1]);
							
							//隐藏对应的select
							temp$.find("input").removeAttr("class");
							temp$.find("select:eq(1)").attr("class","hiddenSelect");
							temp$.find("select:eq(0)").find("option:eq(1)").attr("selected","selected");
							 
							
						}else if(dataSyncItemList[i].dataType=="M"){
							dataSyncItemList[i].dataContent;
							var tempM$ = $(html);
							tempM$.find("select:eq(1)").find("option").each(function(){
								if($(this).val()==dataSyncItemList[i].dataContent){
									$(this).attr("selected","selected");
								}
							});
							tempM$.find("input").addClass("hiddenSelect");
							tempM$.appendTo("#tableManageTable");
						}
						
					}
					
					formDialog.unmask();

					//dataSyncMain_grid.loadData(1);
					
					

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

//编码需要更新的表名称
function encodeTableNamesToHiddenField(){
	var dataNames = "";
	$(".addedTableRows").each(function(){
		var dorm = $(this).find("select:first").val();
		if(dorm=="0"){
			var mdVal = $(this).find("select:eq(1)").val();
			dataNames+=("0:"+mdVal+",");
		}else if(dorm=="1"){
			var dictVal = $(this).find("input:eq(0)").val();
			var dictVal1 = $(this).find("input:eq(1)").val();
			dataNames+=("1:"+dictVal+"$$"+dictVal1+",");
		}
	});
	dataNames = dataNames.substring(0, dataNames.length-1);
	$("#input_tableNames").val(dataNames);
}

//编码要所有更新的数据名称
function encodeSyncDataToIsAll(){
	var tempStr="";
	var M = $("#syncMainData").is(':checked');
	var D = $("#syncDictData").is(':checked');
	var F = $("#syncMetaData").is(':checked');
	if(M){
		tempStr+="M,"
	}
	if(D){
		tempStr+="D,"
	}
	if(F){
		tempStr+="F,"
	}
	if(tempStr!=""){
		tempStr=tempStr.substring(tempStr, tempStr.length-1);
	}
	$("#syncIsAll").val(tempStr);
	//alert($("#syncIsAll").val());
}


//数据类型改变事件
function changeSelectedDataType(target){
	//字典
	if($(target).val()=="1"){
		$(target).parent().parent().find("input").removeAttr("class");
		$(target).parent().parent().find("span").removeClass("hiddenSelect");
		$(target).parent().parent().find("select:eq(1)").attr("class","hiddenSelect");
	}else if($(target).val()=="0"){
		//主数据
		$(target).parent().parent().find("select:eq(1)").removeAttr("class");
		$(target).parent().parent().find("input").attr("class","hiddenSelect");
		$(target).parent().parent().find("span").addClass("hiddenSelect");
	}
}

//同步操作
function syncOperation(id){
	return '<a class="btn" href="javascript:void(0);"  onclick="syncTheSystem(\''+id+'\');"> <span class="btn-left"> <span class="btn-text icon-reload">同步</span> </span> </a>';
	//return '<a class="prevent-default" href="javascript:void(0)" onclick="syncTheSystem(\''+id+'\');">同步</a>';
}
//进行同步
function syncTheSystem(mainId){
	
	var d=new JDialog({
		title : '同步数据中',
		width : 100,
		height:100,
		closeable:false,
		content:"<div style='text-align:center;margin:10px 0 0 0;'><img src='images/loading.gif'/></div>",
	});
	//d.mask('正在保存数据，请稍候...');
	
	$.ajax({
		url : "dataSyncMain/syncData.zb",
		type : 'POST',
		cache : false,
		data : encodeURI("mainId=" + mainId),
		dataType : 'json',
		success : function(res) {
			d.closeDialog();
			dataSyncMain_grid.loadData();
			JDialog.showMessageDialog('成功','数据同步成功！',JDialog.INFORMATION);
		},
		error : function(XMLHttpRequest,textStatus, errorThrown) {
			d.closeDialog();
			JDialog.showMessageDialog('异常','服务器出现异常，数据同步失败。',JDialog.ERROR);
		}
	});
	
}







