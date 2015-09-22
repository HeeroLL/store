var formDialog, editData, showimportDlg;
var showConfigDlg;
var grid;
var editHTML = "";
//存放tr节点（添加card信息）
var rootelm ;
//上传导入文件的window
var winUpload;

$(document).ready(function() {
	 
	var col = [ {text: '患者ID',	 width: 0,	 textAlign: 'center',	align : 'center',	dataIndex: 'userId'},
	            {text: 'EmpiId', 		width: 120,		textAlign: 'center',	align : 'center',	dataIndex: 'empiId'	},
				{text: '患者姓名',	 width: 90,	 textAlign: 'center',	align : 'center',	dataIndex: 'userName'},
				{text: '性别',	 width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'sex'},
				{text: '身份证',	 width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'icn'},
				{text: '生日',	 width: 0,	 textAlign: 'center',	align : 'center',	dataIndex: 'birthday'},
				{text: '民族',	 width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'nation'},
				{text: '电话',	 width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'tel'},
				{text: '照片',	 width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'photo'},
				{text: '户籍地址',	 width: 260,	textAlign: 'center',	align : 'center',	dataIndex: 'regaddress'},
				{text: '患者常住地址',	 width: 260,	textAlign: 'center',	align : 'center',	dataIndex: 'preaddress'},
				{text: '创建时间',	 width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'createDate'},
				{text: '更新时间',	 width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'updateDate'},
				{text: '创建人',	 width: 70,	textAlign: 'center',	align : 'center',	dataIndex: 'creator'},
				{text: '更新人',	 width: 70,	textAlign: 'center',	align : 'center',	dataIndex: 'updator'}];
	//主数据列表
	grid = new JGrid({
		title : '患者基本信息列表',
		col : col,
		dataCol:'',
		checkbox : true,
/**************************************************标准的json格式数据，包含分页信息：如：{"success":"success","total":1,"data":[{"name":"Mike","age":13}]}*******************************************************/
		dataUrl : 'empi/empi-user-list.zb',
		render : 'grid',
		pageBar : true,
		height : document.body.clientHeight-120,
		countEveryPage : 20,
		crudOpera : true,
		 listeners : {
			dblclick : function(row) {// Grid双击事件
				editData("修改患者基本信息", row['empiId']);
			},
			click:function(row){
				syncRightInfo(row);
			},
			curdButtonClick : function(btn) {
				if (btn == 'add') {
					editData("新建患者基本信息");
				} else if (btn == 'update') {
				var rows = grid.getSelections();
				if (rows.length != 1) {
					JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
						return;
					}
					editData("修改患者基本信息",rows[0]['empiId']);
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
									id += rows[i].empiId+ ',';
								}
								id = id.substring(0,id.length - 1);
								var datas = "id="+ id;
								$.ajax({
/***********************************************************删除数据格式为逗号分割的id,如：id=12,123,121,321,,成功则返回json:{"msg":"success"}***********************************************/
									url : 'empi/empi-user-remove.zb',//如果成功则返回格式为json格式：{"msg":"success"}
									type : 'POST',
									cache : false,
									data : encodeURI(datas),
									dataType : 'json',
									success : function(res) {
										 
										if (res['success']) {
											JDialog.showMessageDialog('成功',"患者基本信息删除成功！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													grid.loadData();
													
													//初始化右侧，使之选中第一列
													grid.selectRecord(0);
													var rows = grid.getSelections();
													if(rows.length>0){
														syncRightInfo(rows[0]);
													}
													
												}
											});
										} else {
											JDialog.showMessageDialog('失败',"患者基本信息删除失败！",JDialog.INFORMATION,function(id,text) {
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
					//初始化右侧，使之选中第一列
					grid.selectRecord(0);
					var rows = grid.getSelections();
					if(rows.length>0){
						syncRightInfo(rows[0]);
					}
				}
			}
		}
	});
	
	// 加载数据
	grid.loadData();

	//grid.addButton(new JButton({
		//id:'importdata',
		//text:'导入数据'
	//}));
	
	//导入数据界面
	showimportDlg = function (){
		var formAddDialog = new JDialog({
			id : 'importdlg',
			title : "导入数据",
			width : $(document.body).width() * 0.65,
			height : $(document.body).height() * 0.35,
			icon : 'images/icons/pencil.png',
			btns   : JDialog.SAVE_CLOSE_OPTION,
			listeners : {
				buttonClick : function(btnId, text) {
					if (btnId == 'ok') {
						formAddDialog.mask('正在保存数据，请稍候...');
						 
							var options = {
								url:'empi/importEmpiData.zb',
								type:'post',
								success:function(data){
									//console.log(data);
								}
							};
							$("#uploadForm").ajaxSubmit(options);
					 
					} else {
						formAddDialog.closeDialog();
					}
				}
			}
		});
		formAddDialog.add($("#importdata").html());
		//formAddDialog.show();
	};
	
	$(".jgridcrudBtn").append('<a class="btn" id="btnImport" style="margin-left:150px;" href="javascript:openUploadWindow();"> <span class="btn-left"> <span class="btn-text icon-add">导入<\/span> <\/span> <\/a>');
	//$(".jgridcrudBtn").append('<a class="btn" id="btnImport" href="javascript:showexportDlg();"> <span class="btn-left"> <span class="btn-text icon-remove">导出<\/span> <\/span> <\/a>');
	
	//参数配置界面
	showConfigDlg = function (){
		var formConfigDialog = new JDialog({
			id : 'importdlg',
			title : "Empi参数配置",
			width : $(document.body).width() * 0.55,
			height : $(document.body).height() * 0.50-20,
			icon : 'images/icons/pencil.png',
			btns   : JDialog.SAVE_CLOSE_OPTION,
			listeners : {
				buttonClick : function(btnId, text) {
					if (btnId == 'ok') {
						
						formConfigDialog.mask('正在保存数据，请稍候...');
						
						$.post("empi/empi-config-operate.zb",$("#configForm").serialize(),function(data){
							JDialog.showMessageDialog('成功','系统信息保存成功。');
						});
						
					} 
					$("#configForm").remove();
					$("#configDiv form:first").attr("id","configForm");
					formConfigDialog.closeDialog();
				}
			}
		});
		formConfigDialog.add($("#configDiv").html());
		$("#configDiv form:first").attr("id","configFormOld");
		
		//formAddDialog.show();
		
		$.ajax({
			/***********************************************************删除数据格式为逗号分割的id,如：id=12,123,121,321,,成功则返回json:{"msg":"success"}***********************************************/
			url : 'empi/empi-config.zb',//如果成功则返回格式为json格式：{"msg":"success"}
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(res) {
				if(res["success"]){
					//radio
					if(res["object"]['updateCardFlag']==='1'){
						$("#configForm input[name=updateCardFlag][value=1]:first").attr('checked','checked');
					}else{
						$("#configForm input[name=updateCardFlag][value=0]:first").attr('checked','checked');
					}
					
					if(res["object"]['updateEmpiFlag']==='1'){
						$("#configForm input[name=updateEmpiFlag][value=1]:first").attr('checked','checked');
					}else{
						$("#configForm input[name=updateEmpiFlag][value=0]:first").attr('checked','checked');
					}
					
					//text
					$("#configForm input[type=text]").each(function(){
						$(this).val(res["object"][$(this).attr('name')]);
					});
					
					//select
					$("#configForm select").each(function(){
						var value ='option[value='+ res["object"][$(this).attr('name')]+']';
						$(this).find(value).attr("selected","selected");
					});
				}else{
					formDialog.unmask();	
					JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。',function(id,text) {
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
	};
	
	
	// 编辑User数据
	editData = function(title, id) {
		//console.log("hello");
		//设定默认的empi类型
		$.ajax({
			url : 'empi/empi-config.zb',//如果成功则返回格式为json格式：{"msg":"success"}
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(res) {
				if(res["success"]){
					$("#empiIdTypeEdit").val(res['object']['empiType']);
				}else{
					JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。');
				}
				
				
			},
			error : function(XMLHttpRequest,textStatus,errorThrown) {
				formDialog.unmask();	
				JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。',function(id,text) {
					formDialog.unmask();
				});
			}
		});
		
		
		formDialog = new JDialog(
			{
				id : 'editdata',
				title : title,
				width : $(document.body).width() * 0.75,
				height : $(document.body).height() * 0.85,
				icon : 'images/icons/pencil.png',
				btns   : JDialog.SAVE_CLOSE_OPTION,
				listeners : {
					buttonClick : function(btnId, text) {
						if (btnId == 'ok') {
							if (!checkAll('userform')) {
								return false;
							}
						formDialog.mask('正在保存数据，请稍候...');
						$("#userform").ajaxSubmit({
/**********************************保存数据,成功则返回json格式数据（必须为一下格式）:{"msg":"success"}***********************/
							url : 'empi/empi-user-operate.zb',
							type : 'POST',
							success : function(res) {
								if (res['success']==true) {
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
								var tempval = $("#empiIdEdit").val()
								
								if(tempval!=undefined && tempval!=''){
									window.parent.window.frames['innerMainFrame'].loadCardsByEmpiId(tempval);
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
	
		//绑定修改Empi后触发事件，用来自动补全用户信息
		 $("#empiIdEdit").blur(function(){
			    
			 
			 	//获取删除空格后的id
		    	id=$(this).val().replace(/(^\s*)|(\s*$)/g, "");
		    	
			 
		    	//console.log('填入ID：'+id);后面判断是否为修改状态，还是新建状态，修改的花empi不需要在加载了。
		    	if(id&&!$("#empiIdEdit").attr("readonly")){
		    	  
		    	  //载入患者基本信息
		    	  loadEmpiInfo(id,true);
		    	  
		    	}else{
		    		//清空原有数据,(empiid和empi类型那个不清空)
					formDialog.getComponent().find("#mainTABLE input").each(function() {
						var name = $(this).attr('name');
						if (name&&name!='empiId') {
							$(this).val("");
						}
					});
					formDialog.getComponent().find("#mainTABLE textarea").each(function() {
						var name = $(this).attr('#mainTABLE name');
						if (name) {
							$(this).val("");
						}
					});
					formDialog.getComponent().find("#mainTABLE select").each(function() {
						var name = $(this).attr('name');
						if (name&&name!='empiType') {
							$(this).val("");
						}
					});
		    		//根据id是否存在来判断是新建还是加载
		    		if(id){
		    			loadEmpiInfo(id,false);
		    		}
		    	}
		    	
		 });

		//根据id是否存在来判断是新建还是加载
 		if(id){
 			loadEmpiInfo(id,false);
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
		
	
});

//加载Empi，id是empiid, isAjaxCheck判断是否为新建加载（true），还是修改时候的加载（false）
function loadEmpiInfo(id,isAjaxCheck){

		if(!isAjaxCheck){
			//只读化empi文本框，防止修改empi，造成错误
			$("#empiIdEdit").attr("readonly","true");
			$("#empiIdTypeEdit").attr("disabled","true");
		}
		

		formDialog.mask('正在加载数据，请稍候...');
		$.ajax({
			
/**************************************返回被选中的需要修改的条目，参数为accountId=123123,返回json格式数据：{"name":"Mike","age":12}*****************************************************************************/
			url : 'empi/empi-user-load.zb',
			type : 'POST',
			cache : false,
			data : encodeURI("empiId=" + id),
			dataType : 'json',
			success : function(res) {
				if (res["success"]) {
					formDialog.getComponent().find("#mainTABLE input").each(function() {
						var name = $(this).attr('name');
						if (name) {
							$(this).val(res['object'][name]);
						}
					});
					formDialog.getComponent().find("#mainTABLE textarea").each(function() {
						var name = $(this).attr('#mainTABLE name');
						if (name) {
							$(this).val(res['object'][name]);
						}
					});
					formDialog.getComponent().find("#mainTABLE select").each(function() {
						var name = $(this).attr('name');
						if (name) {
							$(this).val(res['object'][name]);
						}
					});
					formDialog.unmask();
				} else {
					
					//根据身份证来填充性别和生日
			    	  if($("#empiIdTypeEdit").val()==='1'){
			    		  $("#empiIdEdit").attr("title","身份证");
			    		  //判断身份证格式是否正确
			    		  var cardInfo = checkIdCardNo($("#empiIdEdit"));
			    		  if(cardInfo){
			    			  JDialog.showMessageDialog('错误',cardInfo);
			    		  }else{
			    			  //生日
			    			  var tmpStr = "";
			    			  //性别
			    			  var set = "";
			    			  if (id.length == 15) {
			    				  tmpStr = id.substring(6, 12);
			    				  tmpStr = "19" + tmpStr;
			    				  tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)
			    				  if (parseInt(id.charAt(14) / 2) * 2 != id.charAt(14))
			    			            sex = '男';
			    			        else
			    			            sex = '女';
		    				  }else {
			    				  tmpStr = id.substring(6, 14);
			    				  tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)
			    				  if (parseInt(id.charAt(16) / 2) * 2 != id.charAt(16))
			    			            sex = '男';
			    			        else
			    			            sex = '女';
		    				  }
			    			  $("#sex").val(sex);
			    			  $("#birthday").val(tmpStr);
			    		  }
			    	  }
					
					formDialog.unmask();
					//去出只读化empi文本框
					$("#empiIdEdit").removeAttr("readonly");
					$("#empiIdTypeEdit").removeAttr("disabled");
					$("#empiIdEdit").val(id);
					if(!isAjaxCheck){
						JDialog.showMessageDialog('失败',res["msg"],JDialog.ERROR);
					}
					
				}
			},
			error : function(XMLHttpRequest,textStatus, errorThrown) {
			    formDialog.unmask();
				JDialog.showMessageDialog('异常','服务器出现异常，数据加载失败。',JDialog.ERROR);
			}
		});
		
		//先清空证件ID
		$("#cardTableBody tr").remove();
			//按empiId获取证件列表，显示在更新界面上
			$.ajax({
				/***********************************************************删除数据格式为逗号分割的id,如：id=12,123,121,321,,成功则返回json:{"msg":"success"}***********************************************/
				url : 'empi/empi-card-list.zb',//如果成功则返回格式为json格式：{"msg":"success"}
				type : 'POST',
				cache : false,
				data:{empiId:id},
				success : function(res) {
					for(var i=0; i<res.data.length;i++){
						appendCardInfo(res.data[i]);
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



//同步右侧的患者基本信息
function syncRightInfo(row){
	 
	//加载右侧患者基本信息
	var inputs = $(window.parent.frames["innerMainFrame"].document).find("input");
	for(var i in row){
		
		$(inputs).each(function(){
			if($(this).attr("name")===i)
			$(this).val(row[i]);
		});
	}	
	
	//加载右侧证件列表
	window.parent.window.frames['innerMainFrame'].loadCardsByEmpiId(row['empiId']);
}

//新增患者标识卡
function appendCardInfo(card){
	//用于标识前面的index
	var trIndex = $("#cardTableBody tr").size()+1;
	if(card==undefined){
		$("#cardTableBody").append('<tr><td width="60px;">'+trIndex+'.</td><td><input type="text" style="width:100px;" name="cardNo" validate="string|1-50" value=""/></td><td><input type="text" style="width:100px;" name="cardSid" validate="number|0-10" value=""/></td><td><select name="cardType"><option value="N0002">军官证</option><option value="N0003">护照</option><option value="N0004">就诊卡</option><option value="N0005">社保卡</option><option value="N0006">新农合证/卡</option><option value="N0007">市民卡</option><option value="N0008">健康卡</option><option value="N0009">保健卡</option></select></td><td width="70px"><a class="btn" onclick="showConfirmDeleteCardInfo(event);"><span class="btn-left" > <span class="btn-text icon-cancel" alt="删除"></span></span></a></td></tr>');
	}else{
		$("#cardTableBody").append('<tr><td width="60px;">'+trIndex+'.</td><td><input type="hidden" name="cardId" validate="string|1-50" value="'+card.cardId+'"/><input type="text" style="width:100px;" name="cardNo"  validate="string|1-50" value="'+card.cardNo+'"/></td><td><input type="text" style="width:100px;" validate="number|0-10" name="cardSid" value="'+card.cardSid+'"/></td><td><select name="cardType"><option value="N0002">军官证</option><option value="N0003">护照</option><option value="N0004">就诊卡</option><option value="N0005">社保卡</option><option value="N0006">新农合证/卡</option><option value="N0007">市民卡</option><option value="N0008">健康卡</option><option value="N0009">保健卡</option></select></td><td width="70px"><a class="btn" onclick="showConfirmDeleteCardInfo(event);"><span class="btn-left" > <span class="btn-text icon-cancel" alt="删除"></span></span></a></td></tr>');
	}
	
	$("#cardTableBody tr:last").find("select:first").val((card==undefined?2:card.cardType));
}

function showConfirmDeleteCardInfo(e){
	var isdelete = false;
	var tempe = e;

	var e = window.event || e;
    var o = e.srcElement || e.target;
 
    //移除页面上的card行
    if($(o).hasClass('btn')){
    	rootelm = $(o).parent().parent();
    }else if($(o).hasClass('btn-left')){
    	rootelm = $(o).parent().parent().parent();
    }else{
    	rootelm = $(o).parent().parent().parent().parent();
	}
    
    //保留删除的card行的cardId
    var cardIdWeb = $(rootelm).find("input[name=cardId]").val();
    var empiIdWeb = '';
    
    
	JDialog.showConfirmDialog("提示", "是否删除该证件信息", JDialog.WARNING, function(id,text){
		if (id == 'yes') {
			rootelm.remove();
			if(cardIdWeb===undefined){
				return;
		    }
	    	empiIdWeb =  $("#empiIdEdit").val();
	    	
	    	//ajax删除数据
	    	//数据库删除card通过cardId
	        if(cardIdWeb!=''){
	        	formDialog.mask('正在删除数据，请稍候...');
	        	$.ajax({
	        		/***********************************************************删除数据格式为逗号分割的id,如：id=12,123,121,321,,成功则返回json:{"msg":"success"}***********************************************/
	        		url : 'empi/empi-card-remove.zb',//如果成功则返回格式为json格式：{"msg":"success"}
	        		type : 'POST',
	        		cache : false,
	        		data:{id:cardIdWeb},
	        		success : function(res) {
	        			formDialog.unmask();
	        			if(res['success']){
	        				JDialog.showMessageDialog("提示","证件信息删除成功");
	        			}else{
	        				JDialog.showMessageDialog("提示","证件信息删除失败!");
	        			}
	        			
	        			//更新右侧card列表
	        			window.parent.window.frames['innerMainFrame'].loadCardsByEmpiId(empiIdWeb);
	        			
	        		},
	        		error : function(XMLHttpRequest,textStatus,errorThrown) {
	        			formDialog.unmask();	
	        			JDialog.showMessageDialog('异常','服务器出现异常，数据提交失败。',function(id,text) {
	        				formDialog.unmask();
	        			});
	        		}
	        	});
	        }
	        
	    	
	        //用于计数，将序号初始化为0
	        var tempIndexStart = 0;
	        $("#cardTableBody tr").each(function(){
	        	tempIndexStart++;
	        	var aaa = $(rootelm).find("td:first").html(tempIndexStart+'.');
	        });

	    	
	    	
		}else{
			return;
		}
	}, true);
	
}
function deleteCardInfo(e){

	var e = window.event || e;
    var o = e.srcElement || e.target;
    //保留删除的card行的cardId
    var cardIdWeb = '';
    var empiIdWeb = '';
    //移除页面上的card行
    if($(o).hasClass('btn')){
    	cardIdWeb = $(o).parent().parent().find("input[name=cardId]").val();
    	empiIdWeb = $(o).parent().parent().find("input[name=empiId]").val();
    	$(o).parent().parent().remove();
    }else if($(o).hasClass('btn-left')){
    	cardIdWeb = $(o).parent().parent().parent().find("input[name=cardId]").val();
    	empiIdWeb = $(o).parent().parent().parent().find("input[name=empiId]").val();
    	$(o).parent().parent().parent().remove();
    }else{
    	cardIdWeb = $(o).parent().parent().parent().parent().find("input[name=cardId]").val();
    	empiIdWeb = $(o).parent().parent().parent().parent().find("input[name=empiId]").val();
    	$(o).parent().parent().parent().parent().remove();
	}
   
    //数据库删除card通过cardId
    if(cardIdWeb!=''){
    	formDialog.mask('正在删除数据，请稍候...');
    	$.ajax({
    		/***********************************************************删除数据格式为逗号分割的id,如：id=12,123,121,321,,成功则返回json:{"msg":"success"}***********************************************/
    		url : 'empi/empi-card-remove.zb',//如果成功则返回格式为json格式：{"msg":"success"}
    		type : 'POST',
    		cache : false,
    		data:{id:cardIdWeb},
    		success : function(res) {
    			formDialog.unmask();
    			if(res['success']){
    				JDialog.showMessageDialog("提示","证件信息删除成功!");
    			}else{
    				JDialog.showMessageDialog("提示","证件信息删除失败!");
    			}
    			//更新右侧card列表
    			window.parent.window.frames['innerMainFrame'].loadCardsByEmpiId(empiIdWeb);
    		},
    		error : function(XMLHttpRequest,textStatus,errorThrown) {
    			formDialog.unmask();	
    			JDialog.showMessageDialog('异常','服务器出现异常，数据提交失败。',function(id,text) {
    				formDialog.unmask();
    			});
    		}
    	});
    }
    
	
    //用于计数，将序号初始化为0
    var tempIndexStart = 0;
    $("#cardTableBody tr").each(function(){
    	tempIndexStart++;
    	var aaa = $(this).find("td:first").html(tempIndexStart+'.');
    	
    });
    
}

//打开窗体
var openUploadWindow=function(){
	var wWidth=500;
	winUpload = new JWindow({
		id:'winUpload',
		title:'导入Empi数据文件',
		width:wWidth,
		height:210,
		top:'30px',
		url:'empi/uploadFile.zb',
		appendTo: $("body")
		
	});
	winUpload.active();
	
}

//关闭导入数据的窗口并刷新数据
function closeWindowRefresh(){
	winUpload.close();
	grid.loadData();
}
