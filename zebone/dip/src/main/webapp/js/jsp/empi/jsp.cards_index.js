var formDialog, editData;
var grid;
var editHTML = "";

$(document).ready(function() {
	//绑定点击不同用户时候的不同card显示，以用户empiId变更作为事件
//	document.getElementById("userName").addEventListener("input",function(){
//															alert($(this).val());
//														},false);
	
	var col = [ {text: '证件ID',	 width: 0,	 textAlign: 'center',	align : 'center',	dataIndex: 'cardId'},
	            {text: 'empiId', 		width: 0,		textAlign: 'center',	align : 'center',	dataIndex: 'empiId'	},
				{text: '证件号码',	 width: 120,	 textAlign: 'center',	align : 'center',	dataIndex: 'cardNo'},
				{text: '证件序列号',	 width: 127,	textAlign: 'center',	align : 'center',	dataIndex: 'cardSid'},
				{text: '证件类型',	 width: 110,	textAlign: 'center',	align : 'center',	dataIndex: 'cardType'}];
	//主数据列表
	grid = new JGrid({
		title : '',
		col : col,
		dataCol:'',
		checkbox : true,
/**************************************************标准的json格式数据，包含分页信息：如：{"success":"success","total":1,"data":[{"name":"Mike","age":13}]}*******************************************************/
		dataUrl : 'empi/empi-card-list.zb',
		render : 'cardGrid',
		pageBar : true,
		width:350,
		height : document.body.clientHeight/2-50,
		countEveryPage : 20,
		crudOpera : true,
		listeners : {
			afterload: function (jsondata) {
				for(var i=0; i<jsondata.length; i++){
					//jsondata[i].cardType = "helloboy";
					var cardTypeChinese;

					switch (jsondata[i].cardType) {
						case 'N0002' : 
							cardTypeChinese = '军官证';
							break;
						case "N0003": 
							cardTypeChinese = '护照';
							break;
						case "N0004": 
							cardTypeChinese = '就诊卡';
							break;
						case "N0005": 
							cardTypeChinese = '社保卡';
							break;
						case "N0006": 
							cardTypeChinese = '新农合证';
							break;
						case "N0007": 
							cardTypeChinese = '市民卡';
							break;
						case "N0008": 
							cardTypeChinese = '健康卡';
							break;
						case "N0009": 
							cardTypeChinese = '保健卡';
							break;
					};
					$("td[title=" + jsondata[i].cardType + "]").html(cardTypeChinese);
					//grid.updateRow(jsondata[i]);
					//alert(jsondata[i].cardType);
				}
			},
			dblclick : function(row) {// Grid双击事件
				editData("修改证件信息", row['cardId']);
			},
			curdButtonClick : function(btn) {
				if (btn == 'add') {
					//如果选中了相应的患者信息才可以进行添加card操作
					if($("#detailEmpiId").val()!=''){
						editData("新建证件信息");
					}else{
						JDialog.showMessageDialog('提示','请先从左侧选择对应的患者！');
					}
					
				} else if (btn == 'update') {
				var rows = grid.getSelections();
				if (rows.length != 1) {
					JDialog.showMessageDialog('提示','请选择一条记录进行修改。');
						return;
					}
					editData("修改证件信息",rows[0]['cardId']);
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
									id += rows[i].cardId+ ',';
								}
								id = id.substring(0,id.length - 1);
								var datas = "id="+ id;
								$.ajax({
/***********************************************************删除数据格式为逗号分割的id,如：id=12,123,121,321,,成功则返回json:{"msg":"success"}***********************************************/
									url : 'empi/empi-card-remove.zb',//如果成功则返回格式为json格式：{"msg":"success"}
									type : 'POST',
									cache : false,
									data : encodeURI(datas),
									dataType : 'json',
									success : function(res) {
										 
										if (res['success']) {
											JDialog.showMessageDialog('成功',"证件信息删除成功！",JDialog.INFORMATION,function(id,text) {
												if (id == 'ok') {
													$("#grid").unmask();
													grid.loadData();
												}
											});
										} else {
											JDialog.showMessageDialog('失败',"证件信息删除失败！",JDialog.INFORMATION,function(id,text) {
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
	//grid.setParams({"empiId":id});
	//grid.loadData();

	
	// 编辑Card信息
	editData = function(title, id) {
		formDialog = new JDialog(
			{
				id : 'editdata',
				title : title,
				width : $(document.body).width() * 0.85,
				height : $(document.body).height() * 0.45,
				icon : 'images/icons/pencil.png',
				btns   : JDialog.SAVE_CLOSE_OPTION,
				listeners : {
					buttonClick : function(btnId, text) {
						if (btnId == 'ok') {
							if (!checkAll('userform')) {
								return false;
							}
						var data = "{";
						$("#userform").find("input[type='text']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#userform").find("input[type='hidden']").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#userform").find("select").each(function(){
							data += $(this).attr("name")+":'"+$(this).val()+"',";
						});
						$("#userform").find("textarea").each(function(){
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
							url : 'empi/empi-card-operate.zb',
							type : 'POST',
							cache : false,
							data : eval('('+data+')'),
							dataType : 'json',
							success : function(res) {
								if (res['success']) {
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
		
		//维持empi的一致性
		$("#formEmpiId").val($("#detailEmpiId").val());
		//bindCheckEvent();

		if (id) {// 加载Form数据
			formDialog.mask('正在加载数据，请稍候...');
			$.ajax({
				
/**************************************返回被选中的需要修改的条目，参数为accountId=123123,返回json格式数据：{"name":"Mike","age":12}*****************************************************************************/
				url : 'empi/empi-card-load.zb',
				type : 'POST',
				cache : false,
				data : encodeURI("cardId=" + id),
				dataType : 'json',
				success : function(res) {
					if (res['success']) {
						formDialog.getComponent().find("input").each(function() {
							var name = $(this).attr('name');
							if (name) {
								$(this).val(res['object'][name]);
							}
						});
						formDialog.getComponent().find("textarea").each(function() {
							var name = $(this).attr('name');
							if (name) {
								$(this).val(res['object'][name]);
							}
						});
						formDialog.getComponent().find("select").each(function() {
							var name = $(this).attr('name');
							if (name) {
								$(this).val(res['object'][name]);
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
 
 
//按照empiId加载数据
function loadCardsByEmpiId(empiId){
	grid.setParams({"empiId":empiId});
	grid.loadData();
}


